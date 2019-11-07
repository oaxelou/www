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

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			  throws IOException, ServletException{
		String user=req.getParameter("userName");
		String pass=req.getParameter("userPassword");
		
		PrintWriter pwriter = res.getWriter();
		
		try{
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/SimpleDB/header.html"), "UTF-8" );
			String text = scanner.useDelimiter("\\A").next();
			scanner.close(); 
			pwriter.println(text);
		} catch(FileNotFoundException efnotf){
			pwriter.println("<html><body><h2>File not found: "+ System.getProperty("user.dir") + " </h2></body></html>");
			System.out.println(efnotf.toString());
			System.out.println("Could not display html. File not found.");
		} catch(Exception e){
			pwriter.println("<html><body><h2>Exception?</h2></body></html>");
			//pwriter.close();
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
			
			if(searchResult.next()) {
				// Get user's password and compare it to the one the user gave.
				System.out.println(searchResult.getString("username") + ", " + searchResult.getString("password") + ", " + pass);
				if(searchResult.getString("password").equals(pass)) {
					pwriter.println("<h2>Successfully logged in as " + user + "</h2>");
				}
				else {pwriter.println("<h2>Wrong password for user  " + user + ".</h2>");}
			}
			else {pwriter.println("<h2>User " + user + " does not exist in DB. Login failed.</h2>");}
		}catch(Exception exc) {
			pwriter.println("<p>Error occured: " + exc.toString() + "</p>");
			System.out.println("An error occurred while connecting MySQL databse: demo");
			exc.printStackTrace();
			System.out.println("\nError again my friend...");
		}
		
		// Adding Footer
		try{
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/SimpleDB/footer.html"), "UTF-8" );
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
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/SimpleDB/header.html"), "UTF-8" );
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
			Scanner scanner = new Scanner( new File("/opt/tomcat/webapps/SimpleDB/footer.html"), "UTF-8" );
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
