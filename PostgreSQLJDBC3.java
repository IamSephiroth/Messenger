import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PostgreSQLJDBC3 {
   public static void main( String args[] ) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/projectdb3",
            		"postgres", "Sheff1eldunited");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
         while ( rs.next() ) {
            String id = rs.getString("id");
            String name1 = rs.getString("name1");
            String name2  = rs.getString("name2");
            String password = rs.getString("password");
            String date = rs.getString("date");
            System.out.println( id +"  |  "+ name1 +"  |  "+ name2 +"  |   "+ password +"  |  "+ date );
           
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Operation done successfully");
   }
}