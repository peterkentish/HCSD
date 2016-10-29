package Database;
import java.sql.*;
import java.util.ArrayList;
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

	public ArrayList<Patient> getAllPatients() throws Exception {
		ArrayList<Patient> list = new ArrayList<>();
		
		Statement myStatement = null;
		ResultSet myResult = null;
		
		try{
			
			myStatement = conn.createStatement();
			myResult = myStatement.executeQuery("select * from patient_table");
			
			while(myResult.next()){
				Patient singlePatient = convertRowToPatient(myResult);
				list.add(singlePatient);				
			}
			return list;
		}
		finally{
			close(conn,myStatement,myResult);
		}
		
		
	}
	
	private static void close(Connection conn, Statement myStatement, ResultSet myResult)
			throws SQLException {

		if (myResult != null) {
			myResult.close();
		}

		if (myStatement != null) {
			
		}
		
		if (conn != null) {
			conn.close();
		}
	}
	
	
	private Patient convertRowToPatient(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("patient_id");
		String firstName = myRs.getString("patient_first_name");
		String surname = myRs.getString("patient_surname");
		String healthcare = myRs.getString("healthcare_plan");
		
		Patient tempPatient = new Patient(id, firstName, surname, healthcare);
		
		return tempPatient;
	}
		
	public static void main(String args[]) throws Exception{
		connection();
		DataStuffs data = new DataStuffs();
		System.out.println(data.getAllPatients());
		lkdndskfv
	}
}
