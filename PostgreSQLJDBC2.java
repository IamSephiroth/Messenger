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
            .getConnection("jdbc:postgresql://localhost:5432/projectdb3",
            		 "postgres", "Sheff1eldunited");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

    
         
         stmt = c.createStatement();
         String schema1 = "INSERT INTO USERS (ID,NAME1,NAME2,PASSWORD,DATE) "
            + "VALUES ('Johnny1984', 'John', 'Jones', 'Wednesday1', '12/10/98' );";
         stmt.executeUpdate(schema1);

         schema1 = "INSERT INTO USERS (ID,NAME1,NAME2,PASSWORD,DATE) "
            + "VALUES ('EllieW1998', 'Ellie', 'Woods', 'Wednesday2', '12/10/98' );";
         stmt.executeUpdate(schema1);

         schema1 = "INSERT INTO USERS (ID,NAME1,NAME2,PASSWORD,DATE) "
                 + "VALUES ('CosmoW2010', 'Cosmo', 'Woods', 'Wednesday3', '12/10/98' );";
              stmt.executeUpdate(schema1);


              schema1 = "INSERT INTO USERS (ID,NAME1,NAME2,PASSWORD,DATE) "
                      + "VALUES ('JaneS!1977', 'Jane', 'Smith', 'Wednesday4', '12/10/98' );";
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