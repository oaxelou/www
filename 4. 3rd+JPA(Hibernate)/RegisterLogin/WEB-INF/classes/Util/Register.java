/*
Author: Olympia Axelou, oaxelou@uth.gr
Date: 11 October, 2019
*/

package Util;

import Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Register extends HttpServlet {
	static String header_str = "<!doctype html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<title>Olympia's DB</title>\r\n" + 
			"<link rel=\"icon\" type=\"image/x-icon\" href=\"images/db_logo.png\">\r\n" + 
			"<link type=\"text/css\" rel=\"stylesheet\" href=\"stylesheet.css\">\r\n" + 
			"<meta property=\"og:image\" content=\"images/thumbnail.png\"/>\r\n" + 
			"<meta name=\"description\"content=\"A simple database website. Here you can register\r\n" + 
			"and login. Cheers!\"/>\r\n" + 
			"<meta name=\"keywords\"content=\"db, database, simple, register, login\"/>\r\n" + 
			"<meta name=\"author\"content=\"Olympia Axelou\"/>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"	<div id=\"header\">\r\n" + 
			"		<img id=\"logo\" src=\"images/db_logo.jpg\" alt=\"logo\">\r\n" + 
			"		<h1>Olympia's Simple Database</h1>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"	<div id=\"main-body\">\r\n" + 
			"		<div id=\"form-container\">\r\n" + 
			"			<div id=\"result-message\">";
	
	static String footer_str = "			</div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"	<div id=\"footer\">\r\n" + 
			"	<a id=\"home-url\" href=\"index.html\" style=\"color: #17252a\">\r\n" + 
			"	<img id=\"home-button\" src=\"images/home.png\" alt=\"home\">\r\n" + 
			"	Return to home page\r\n" + 
			"	</a>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		String user=req.getParameter("userName");
		String pass=req.getParameter("userPassword");
		
		System.out.println("Got in post method\nGoing to register as " + user + ", " + pass);
		PrintWriter pwriter = res.getWriter();
		pwriter.println(header_str);
		//System.out.println(header_str);
		
		/****************  START OF HIBERNATE STUFF  *******************/
		try {
			// create a student object
			System.out.println("Creating a new user object...");
			User newUser = new User(user, pass);
			

			SessionFactory factory = new Configuration().configure()
									.addAnnotatedClass(User.class)
									.buildSessionFactory();
			
			Session session = factory.getCurrentSession();session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the user...");
			System.out.println(newUser);
			session.save(newUser);
			System.out.println("Saved " + newUser);
			try {
				// commit the transaction
				session.getTransaction().commit();
			}catch(javax.persistence.PersistenceException exc) {
				System.out.println("There is already an entry with this key. Registration failed.");
				System.out.println(exc.toString());
				
				pwriter.println("<h2>Registration failed!</h2>");
				pwriter.println("<br><p>User <i>" + user + "</i> already exists in DB.</p>");
			}finally {
				System.out.println("Got to finally!");
			}
			factory.close();
			System.out.println("Done!");
			pwriter.println("<h2>Successfully registered as <i>" + user + "</i>.</h2>");
		}catch(Exception e){
			pwriter.println("<h2>An error occurred while connecting to MySQL database</h2>");
			pwriter.println(e.toString());
			System.out.println("An error occurred while connecting MySQL databse");
			System.out.println(e.toString());
		}
		pwriter.println(footer_str);
		pwriter.close();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{	
		System.out.println("Got in get method");
		
		PrintWriter pwriter = res.getWriter();
		pwriter.println(header_str);
		pwriter.println("Get method is not supported.");
		pwriter.println(footer_str);
		pwriter.close();
	}
}


