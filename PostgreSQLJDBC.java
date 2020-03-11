import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk/",
            "krishna", "08tgfexbf8");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String schema1 = "CREATE TABLE USERS" +
            "(ID VARCHAR(10) PRIMARY KEY     NOT NULL," +
            " FIRSTNAME      VARCHAR(250) NOT NULL," +
            " LASTNAME		VARCHAR(250) NOT NULL," +
            " PASSWORD		VARCHAR(250) NOT NULL," +
            " EMAIL			VARCHAR(250) NOT NULL,"	+
            " SECURITY_CODE	INT NOT NULL CHECK(SECURITY_CODE BETWEEN 0 AND 9999)," +	
            " LOGGEDIN		BOOLEAN NOT NULL," +
            " REMAINING_LOGIN_ATTEMPTS INT NOT NULL CHECK(REMAINING_LOGIN_ATTEMPTS BETWEEN 0 AND 3)," +
            " DATE        CHAR(8))"; 
       
         stmt.executeUpdate(schema1);
         
         stmt = c.createStatement();
         String schema2 = "CREATE TABLE MESSAGES " +
            "(ID CHAR(10) PRIMARY KEY     NOT NULL," +
            " RECEIVER_ID		VARCHAR(10)," +
            " MESSAGE		VARCHAR(250)," +
            " DATE        CHAR(8))"; 
       
         stmt.executeUpdate(schema2);
         
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
}