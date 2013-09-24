import java.sql.*;

public class MySqlTest {
    public static void main(String args[]){
        try {
            Statement stmt;
            
            //Register the JDBC driver for MySQL.
            Class.forName("com.mysql.jdbc.Driver");
            
            //Define URL of database server for
            // database named mysql on the localhost
            // with the default port number 3306.
            String urlString = "jdbc:mysql://localhost:3306/mysql";
            
            //Get a connection to the database for a
            // user named root with a blank password.
            // This user is the default administrator
            // having full privileges to do anything.
            Connection con = DriverManager.getConnection(urlString,"root", "aasi");
            
            //Display URL and connection information
            System.out.println("URL: " + urlString);
            System.out.println("Connection: " + con);
            
            //Get a Statement object
            stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * from imayam2_phpbb1.smart_url order by url_id");            

            System.out.println("Display all results:");
            while(rs.next()){
                String link = rs.getString("url_link");
                String name = rs.getString("url_name");
                System.out.println("\turl_link " + link + "\turl_name = " + name);
            }//end while loop                        
            
            con.close();
        }catch( Exception e ) {
            e.printStackTrace();
        }//end catch
    }//end main
}//end class Jdbc12