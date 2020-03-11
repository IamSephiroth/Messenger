import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC2 {
   public static void main(String args[]) {
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
         String schema1 = "INSERT INTO USERS (ID,FIRSTNAME,LASTNAME,PASSWORD,EMAIL,SECURITY_CODE,LOGGEDIN,REMAINING_LOGIN_ATTEMPTS,DATE) "
            + "VALUES ('Johnny1985', 'John', 'Jones', 'Wednesday1', 'johnjones@hotmail.com', '123', 'TRUE', '2', '12/10/98' );";
         stmt.executeUpdate(schema1);

         schema1 = "INSERT INTO USERS (ID,FIRSTNAME,LASTNAME,PASSWORD,EMAIL,SECURITY_CODE,LOGGEDIN,REMAINING_LOGIN_ATTEMPTS,DATE) "
            + "VALUES ('EllieW98', 'Ellie', 'Woods', 'Wednesday2', 'woodse407@gmail.com', '456', 'TRUE', '2', '12/10/98' );";
         stmt.executeUpdate(schema1);

         schema1 = "INSERT INTO USERS (ID,FIRSTNAME,LASTNAME,PASSWORD,EMAIL,SECURITY_CODE,LOGGEDIN,REMAINING_LOGIN_ATTEMPTS,DATE) "
                 + "VALUES ('Cosmo2010', 'Cosmo', 'Woods', 'Wednesday3', 'cosmowoods2@gmail.com', '789', 'TRUE', '2', '12/10/98' );";
              stmt.executeUpdate(schema1);


         schema1 ="INSERT INTO USERS (ID,FIRSTNAME,LASTNAME,PASSWORD,EMAIL,SECURITY_CODE,LOGGEDIN,REMAINING_LOGIN_ATTEMPTS,DATE) "
                      + "VALUES ('JaneS1977', 'Jane', 'Smith', 'Wednesday4', 'janesmith77@gmail.com','246', 'TRUE', '2', '12/10/98' );";
                   stmt.executeUpdate(schema1);

         stmt.close();
         c.commit();
         c.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
}