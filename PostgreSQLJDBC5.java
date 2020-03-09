import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PostgreSQLJDBC5 {
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
         ResultSet rs = stmt.executeQuery( "SELECT * FROM MESSAGES;" );
         while ( rs.next() ) {
            String id = rs.getString("id");
            String receiver_id = rs.getString("receiver_id");
            String message  = rs.getString("message");
            String date = rs.getString("date");
            System.out.println( id +"  |  "+ receiver_id +"  |  "+ message +"  |  "+ date );
           
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
