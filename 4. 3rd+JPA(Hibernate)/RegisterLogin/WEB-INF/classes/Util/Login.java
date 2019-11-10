package Util;

import Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Login extends HttpServlet {
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
		
		System.out.println("Got in post method\nGoing to log in as " + user + ", " + pass);
		
		PrintWriter pwriter = res.getWriter();
		pwriter.println(header_str);
		//System.out.println(header_str);
		
		/****************  START OF HIBERNATE STUFF  *******************/
		
	
		try {
			SessionFactory factory = new Configuration().configure()
									.addAnnotatedClass(User.class)
									.buildSessionFactory();
		
			Session session = factory.getCurrentSession();
			
			System.out.println("Going to begin transaction");
			session.beginTransaction();
			
			System.out.println("Going to search for user");
			// retrieve user based on the username: primary key
			User dbUser = session.get(User.class, user);
			
			System.out.println("Get complete: " + dbUser);
			if (dbUser == null) {
				pwriter.println("<h2>User <i>" + user + "</i> does not exist in database.</h2>");
				System.out.println("User doesn't exist");
			}else {
				if(pass.equals(dbUser.getPassword())) {
					pwriter.println("<h2>Successfully logged in as <i>" + user + "</i></h2>");
					System.out.println("Registration Successful!");
				}else {
					pwriter.println("<h2>Wrong password for user  <i>" + user + "</i>.</h2>");
					System.out.println("Wrong password!");
				}	
			}

			// commit the transaction
			try {
				session.getTransaction().commit();
			}catch(Exception exc) {
				pwriter.println("<p>Error occured when commiting: " + exc.toString() + "</p>");
				System.out.println("There was a problem searching for: " + user);
				System.out.println(exc.toString());
			}finally {
				System.out.println("Got to finally!");
			}
			factory.close();
			System.out.println("Done!");
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
