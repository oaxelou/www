This is a simple Register-Login website. For the back end, I used JPA interface (in particular Hibernate Framework) and JDBC to connect the Java Servlet with the database (MySQL).

Notes: 
1) Use the mysql-code.sql file to create the database.
2) Store the SimpleDB folder in the tomcat's webapps folder.
3) Before enabling Tomcat, compile the Java files with the cmd :
(ensure that you are in the WEB-INF/classes folder)
(Linux)   javac -cp "../lib/*:../../../../lib/servlet-api.jar" Util/*.java Entity/User.java
(Windows) javac -cp "..\lib\*;..\..\..\..\lib\servlet-api.jar" Util\*.java Entity\User.java
