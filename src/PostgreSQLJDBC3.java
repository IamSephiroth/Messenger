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
            .getConnection("jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk/",
            		"krishna", "08tgfexbf8");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
         while ( rs.next() ) {
            String id = rs.getString("id");
            String firstname = rs.getString("firstname");
            String lastname  = rs.getString("lastname");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String security_code = rs.getString("security_code");
          //  Boolean loggedin = rs.getBoolean("logged_in");
            String remaining_login_attempts = rs.getString("remaining_login_attempts");
            String date = rs.getString("date");
            System.out.println( id +"  |  "+ firstname +"  |  "+ lastname +"  |   "+ password +"  | "+ email +" | "+ security_code +" | " + remaining_login_attempts + " | "+ date );
           
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