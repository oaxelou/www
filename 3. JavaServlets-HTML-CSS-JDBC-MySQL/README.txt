This is a simple Register-Login website. For the back end, I used JDBC to connect the Java Servlet with the database (MySQL).

Notes: 
1) Store the SimpleDB folder in the tomcat's webapps folder.
2) Copy the mysql-connector-java-5.1.48-bin.jar file in the Tomcat's lib folder.
3) Before enabling Tomcat, compile the Java files with the cmd :
(ensure that you are in the WEB-INF/classes folder)
javac -cp ../lib/mysql-connector-java-5.1.48-bin.jar:servlet-api.jar DB/Register.java DB/Login.java
