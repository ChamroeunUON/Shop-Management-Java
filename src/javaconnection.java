import java.sql.*;
import javax.swing.JOptionPane;
public class javaconnection{
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    public static Connection ConnecrDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdatabase","root","Khmer@12");
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}