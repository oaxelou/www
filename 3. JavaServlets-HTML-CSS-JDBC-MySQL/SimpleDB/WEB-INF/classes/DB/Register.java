/*
Author: Olympia Axelou, oaxelou@uth.gr
Date: October, 2019
*/

package DB;

import java.util.Scanner;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{
		String user=req.getParameter("userName");
		String pass=req.getParameter("userPassword");
		
		PrintWriter pwriter = res.getWriter();
		try{
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/New_3rdAssignment/header.html"), "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close(); 
			pwriter.println(text);
		} catch(FileNotFoundException efnotf){
			pwriter.println("<html><body><h2>File not found: "+ System.getProperty("user.dir") + " </h2></body></html>");
			System.out.println(efnotf.toString());
			System.out.println("Could not display html. File not found.");
		} catch(Exception e){
			pwriter.println("<html><body><h2>Exception?</h2></body></html>");
			System.out.println("Unexpected exception occured: " + e.toString());
		}
		
		/****************  START OF JDBC STUFF  *******************/
		String url = "jdbc:mysql://localhost:3306/usersdb";
		String dbUser = "root";
		String dbPassword = "2421057837olicia";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection(url, dbUser, dbPassword); // 1. Get a connection to database
			Statement myStmt = myConn.createStatement();                              // 2. Create a statement
			
			
			// Search DB for the user that they gave
			ResultSet searchResult = myStmt.executeQuery("select * from users " + " where username='" + user + "';");  // 3. Form the SQL Query
			
			if(!searchResult.next()) {
				// Execute Insert SQL Query 
				myStmt.executeUpdate("insert into users " + " (username, password) " + " values('" + user + "', '" + pass + "');");
				pwriter.println("<h2>Registration Successful!</h2>");
				pwriter.println("<br><p>Added " + user + " with pw: " + pass + "</p>");
			}
			else {
				pwriter.println("<h2>Registration failed!</h2>");
				pwriter.println("<br><p>User " + user + " already exists in DB.</p>");
			}
			
		}catch(Exception exc) {
			pwriter.println("<p>Error occured: " + exc.toString() + "</p>");
			System.out.println("An error occurred while connecting MySQL databse: demo");
			exc.printStackTrace();
			System.out.println("\nError again my friend...");
		}
		
		
		// Adding Footer
		try{
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/New_3rdAssignment/footer.html"), "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close();
			pwriter.println(text);
		} catch(FileNotFoundException efnotf){
			pwriter.println("<html><body><h2>File not found: "+ System.getProperty("user.dir") + " </h2></body></html>");
			pwriter.close();
			System.out.println(efnotf.toString());
			System.out.println("Could not display html. File not found.");
		} catch(Exception e){
			pwriter.println("<html><body><h2>Exception?</h2></body></html>");
			pwriter.close();
			System.out.println("Unexpected exception occured: " + e.toString());
		}
		pwriter.close();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{
		PrintWriter pwriter = res.getWriter();
		try{
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/New_3rdAssignment/header.html"), "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close();
			pwriter.println(text);
		} catch(FileNotFoundException efnotf){
			pwriter.println("<html><body><h2>File not found: "+ System.getProperty("user.dir") + " </h2></body></html>");
			System.out.println(efnotf.toString());
			System.out.println("Could not display html. File not found.");
		} catch(Exception e){
			pwriter.println("<html><body><h2>Exception?</h2></body></html>");
			System.out.println("Unexpected exception occured: " + e.toString());
		}
		
		pwriter.println("<html><body><h2>Page Not Found. <br> Return to <a href=\"index.html\" style=\"color: #2b7a78\">home</a></h2>");
		
		// Adding Footer
		try{
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/New_3rdAssignment/footer.html"), "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close();
			pwriter.println(text);
		} catch(FileNotFoundException efnotf){
			pwriter.println("<html><body><h2>File not found: "+ System.getProperty("user.dir") + " </h2></body></html>");
			pwriter.close();
			System.out.println(efnotf.toString());
			System.out.println("Could not display html. File not found.");
		} catch(Exception e){
			pwriter.println("<html><body><h2>Exception?</h2></body></html>");
			pwriter.close();
			System.out.println("Unexpected exception occured: " + e.toString());
		}
		pwriter.close();
	}
}