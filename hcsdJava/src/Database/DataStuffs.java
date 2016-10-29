package Database;
import java.sql.*;
public class DataStuffs {
	public static Connection  conn = null;
	public static void connection(){
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String DB="jdbc:mysql://stusql.dcs.shef.ac.uk/team005?user=team005&password=1e87c9c7";
			try {
				conn = DriverManager.getConnection(DB);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Worked");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		connection();
	}
}
