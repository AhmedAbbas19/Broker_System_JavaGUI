package projectOne;
import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	public static Connection dbConnection(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:resource\\dataBase.db");
			//JOptionPane.showMessageDialog(null, "Successful Connection!");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e,"DataBase Connection failed!",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
