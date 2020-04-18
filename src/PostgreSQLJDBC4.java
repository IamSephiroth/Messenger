import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC4 {
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
         String schema2 = "INSERT INTO MESSAGES (ID,RECEIVER_ID,MESSAGE,DATE) "
            + "VALUES ('Johnny1985', 'CosmoW2010', 'Hello Cosmo!', '12/10/98' );";
         stmt.executeUpdate(schema2);

         schema2 = "INSERT INTO MESSAGES (ID,RECEIVER_ID,MESSAGE,DATE) "
            + "VALUES ('EllieW98', 'JaneS!1977', 'I am working on my java today', '12/10/98' );";
         stmt.executeUpdate(schema2);

         schema2 = "INSERT INTO MESSAGES (ID,RECEIVER_ID,MESSAGE,DATE) "
                 + "VALUES ('Cosmo2010', 'EllieW1998', 'pls let me in, I am cold', '12/10/98' );";
              stmt.executeUpdate(schema2);


              schema2 = "INSERT INTO MESSAGES (ID,RECEIVER_ID,MESSAGE,DATE) "
                      + "VALUES ('JaneS1977', 'EllieW1998', 'How was your day?', '12/10/98' );";
                   stmt.executeUpdate(schema2);

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