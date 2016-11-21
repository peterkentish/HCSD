package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * A database access layer that connects to the database and allows a Database 
 * object to be called and the  methods to be called on it.
 */
public class Database {
	public boolean selectQ;
	public String query;
	public String table;
	public ArrayList<Object> results = new ArrayList<Object>();
	public ArrayList<Object> resultsRow = new ArrayList<Object>();

	// Select patient returns a patient given an item an a table to refer to
	public Patient selectPatient(String item, String table) {
		selectQ = true;
		query = "SELECT " + item + " FROM " + table;
		this.table = table;
		return (Patient) excQuery(query);
	}

	// get an arraylist object of appointments within a week from a start date
	public Object getAppointmentsWeek(Date weekCommencing, String table) {
		selectQ = true;
		query = "SELECT * FROM " + table + " WHERE appointment_start BETWEEN "
				+ sqlFormatter(weekCommencing) + " and "
				+ dateFormatter(weekCommencing, 7);
		System.out.println(query);
		this.table = table;
		return excQuery(query);
	}

	// get an arraylist object of all the appointments from the start of a day.
	public Object getAppointmentsDay(Date day, String table) {
		selectQ = true;
		query = "SELECT * FROM " + table + " WHERE appointment_start BETWEEN "
				+ sqlFormatter(day) + " and " + dateFormatter(day, 1);
		this.table = table;
		return excQuery(query);
	}

	// get an arraylist object of all appointments that have been booked at a
	// certain time
	public Object getAppointmentsBooked(String appt, String table) {
		selectQ = true;
		query = "SELECT * FROM " + table + " WHERE " + appt;
		this.table = table;
		return excQuery(query);
	}

	// get the total number of patients
	public int getPatientCount() {
		selectQ = true;
		query = "SELECT COUNT(*) AS total FROM patients";
		return (int) excQuery(query);
	}

	// select an arraylist object of appointments that match a criteria
	public Object selectPatient(String item, String table, String param) {
		selectQ = true;
		query = "SELECT " + item + " FROM " + table + " WHERE " + param;
		this.table = table;
		return excQuery(query);
	}

	// update the patient using the parameters
	public void updatePatient(String table, String item, String param) {
		selectQ = false;
		query = "UPDATE patients SET " + item + " WHERE " + param;
		this.table = table;
		excQuery(query);

	}

	// get an appointment object from the appointments table
	public Appointment selectAppointment(String item, String table) {
		query = "SELECT " + item + " FROM " + table;
		this.table = table;
		return (Appointment) excQuery(query);
	}
	//get an object with all the appointments that match param
	public Object selectAppointment(String item, String table, String param) {
		query = "SELECT " + item + " FROM " + table + " WHERE " + param;
		this.table = table;
		System.out.println(query);
		return excQuery(query);
	}
	//add a patient to the db, takina patient appointment 
	public void addPatient(Patient patient) {
		selectQ = false;
		query = "INSERT INTO patients VALUES (" + patient.toSQLString() + ")";
		excQuery(query);
	}
	//Delete patient removes a patient from the db
	public void deletePatient(Patient patient) {
		selectQ = false;
		query = "DELETE FROM patients WHERE patient_id="
				+ patient.getPatientID();
		excQuery(query);
	}
	//add an appointment to the db
	public void bookDentistAppointment(String booking, String table) {
		selectQ = false;
		query = "INSERT INTO " + table + " VALUES (" + booking + ")";
		// System.out.println(query);
		excQuery(query);
	}
	//creating the db connection 
	public Object excQuery(String query) {
		Object result = null;
		try {
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://stusql.dcs.shef.ac.uk/team005?user=team005&password=1e87c9c7";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl);

			// create the java statement
			Statement st = conn.createStatement();
			//if it is a select statement
			if (selectQ) {
				// execute the query, and get a java resultset
				ResultSet rs = st.executeQuery(query);
				// iterate through the java resultset
				while (rs.next()) {
					if (table == "patients") {
						result = getPatientResults(rs);
					} else if (table == "dentist_appointments"
							|| table == "hygiene_appointments") {
						result = getAppointmentResults(rs);
					} else if (query.substring(0, 12).equals("SELECT COUNT")) {
						result = getCount(rs);
					}
				}
			} else {
				st.executeUpdate(query);
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! Ooops! ");
			System.err.println(e.getMessage());
			System.out.println(e.toString());
		}
		return result;
	}
	
	//extract patient information from resultset
	public Patient getPatientResults(ResultSet r) throws SQLException {
		Patient allRes = new Patient();
		allRes.setPatientID(r.getInt("patient_id"));
		allRes.setTitle(r.getString("title"));
		allRes.setFirstName(r.getString("first_name"));
		allRes.setLastName(r.getString("last_name"));
		allRes.setBirthDate(r.getString("birth_date"));
		allRes.setStreetAddress(r.getString("street_address"));
		allRes.setPostcode(r.getString("postcode"));
		allRes.setContactNo(r.getString("contact_no"));
		allRes.setHealthCare(r.getString("healthcare"));
		allRes.setChecks(r.getInt("check_up"));
		allRes.setHyg(r.getInt("hygiene_visit"));
		allRes.setRepo(r.getInt("repair"));
		return allRes;
	}

	//return array list of appointments from the appointment results
	public ArrayList<Appointment> getAppointmentResults(ResultSet r)
			throws SQLException {
		ArrayList<Appointment> apps = new ArrayList<Appointment>();
		do {
			Appointment allRes = new Appointment();
			allRes.setStartTime(r.getString("appointment_start"));
			allRes.setEndTime(r.getString("appointment_end"));
			allRes.setPatient_id(r.getInt("patient_id"));
			apps.add(allRes);
		} while (r.next());
		return apps;
	}
	//get total number of something form result set
	public int getCount(ResultSet r) throws SQLException {
		return r.getInt("total");
	}
	
	//format a date using a start date and an amount of days to add
	public String dateFormatter(Date weekCommencing, int daysToAdd) {
		Date newDate = new Date(weekCommencing.getYear(),
				weekCommencing.getMonth(), weekCommencing.getDate(),
				weekCommencing.getHours(), weekCommencing.getMinutes());
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy");
		String startDateString = newDate.toString();
		String newDateString = "";
		Date newerDate = null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startDateString));
			c.add(Calendar.DATE, daysToAdd); // number of days to add
			newDateString = sdf.format(c.getTime());
			newerDate = c.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sqlFormatter(newerDate);
	}
	
	//format a date for sql
	public String sqlFormatter(Date d) {
		String x = "'" + (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-"
				+ d.getDate() + " " + d.getHours() + ":" + d.getMinutes()
				+ ":00' ";
		return x;
	}

}
