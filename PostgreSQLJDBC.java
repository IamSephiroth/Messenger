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
            .getConnection("jdbc:postgresql://localhost:5432/projectdb3",
            "postgres", "Sheff1eldunited");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String schema1 = "CREATE TABLE USERS " +
            "(ID CHAR(10) PRIMARY KEY     NOT NULL," +
            " NAME1      CHAR(10)," +
            " NAME2 		CHAR(10)," +
            " PASSWORD		CHAR(10)," +
            " DATE        CHAR(8))"; 
       
         stmt.executeUpdate(schema1);
         
         stmt = c.createStatement();
         String schema2 = "CREATE TABLE MESSAGES " +
            "(ID CHAR(10) PRIMARY KEY     NOT NULL," +
            " RECEIVER_ID		CHAR(10)," +
            " MESSAGE		CHAR(50)," +
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