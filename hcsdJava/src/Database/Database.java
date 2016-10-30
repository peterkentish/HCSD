package Database;
import java.sql.*;
import java.util.ArrayList;

public class Database{
	public boolean selectQ;
	public String query;
	public String table;
	public ArrayList<Object> results = new ArrayList<Object>();
	public ArrayList<Object> resultsRow = new ArrayList<Object>();
	
	public Patient selectPatient(String item, String table){
		selectQ = true;
		query = "SELECT "+item+" FROM "+table;
		this.table = table;
		return (Patient) excQuery(query);
	}
	public Object selectPatient(String item, String table,String param){
		selectQ = true;
		query = "SELECT "+item+" FROM "+table+" WHERE "+param;
		this.table = table;
		return excQuery(query);
	}
	public Appointment selectAppointment(String item, String table){
		query = "SELECT "+item+" FROM "+table;
		this.table = table;
		return (Appointment) excQuery(query);
	}
	public Object selectAppointment(String item, String table,String param){
		
		query = "SELECT "+item+" FROM "+table+" WHERE "+param;
		this.table = table;
		return excQuery(query);
	}
	public void addPatient(Patient patient){
		selectQ = false;
		query = "INSERT INTO patient_table VALUES ("+patient.toSQLString()+")";
		excQuery(query);
	}
	public void deletePatient(Patient patient){
		selectQ = false;
		query = "DELETE FROM patient_table WHERE patient_id="+patient.getPatientID();
		excQuery(query);
	}
  public Object excQuery(String query)
  {
	  Object result = null;
    try
    {
      // create our mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://stusql.dcs.shef.ac.uk/team005?user=team005&password=1e87c9c7";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl);

      // create the java statement
      Statement st = conn.createStatement();
      if (selectQ){
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      // iterate through the java resultset
	      
	      while (rs.next())
	      {
	    	  if (table=="patient_table"){
	    		result = getPatientResults(rs);
	    	  }else if (table=="appointments"){
	    		  result = getAppointmentResults(rs);
	    	  }
      }} else {
    	  st.executeUpdate(query);
      }
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! Ooops! ");
      System.err.println(e.getMessage());
    }
	return result;
  }
  public static void main(String args[]){
	  Database db = new Database();
	  db.addPatient(new Patient(2004, "Juno", "Late", "No"));
	 System.out.println(db.selectPatient("*", "patient_table", "patient_first_name='Juno'"));
	 db.deletePatient(new Patient(2004, "Juno", "Late", "No"));
	 System.out.println(db.selectPatient("*", "patient_table", "patient_first_name='Juno'"));
  }
  public Patient getPatientResults(ResultSet r) throws SQLException{
	    Patient allRes = new Patient(); 
	    allRes.setPatientID(r.getInt("patient_id"));
		allRes.setFirstName(r.getString("patient_first_name"));
		allRes.setSurname(r.getString("patient_surname"));
		allRes.setHealthCare(r.getString("healthcare_plan"));
		return allRes;
  }
  public Appointment getAppointmentResults(ResultSet r) throws SQLException{
	    Appointment allRes = new Appointment(); 
	    allRes.setStartTime(r.getTime("appointment_start"));
		allRes.setEndTime(r.getTime("appointment_end"));
		allRes.setPatient_id(r.getInt("patient_id"));
		return allRes;
}

  
}