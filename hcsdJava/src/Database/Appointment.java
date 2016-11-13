package Database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Graphical.CalendarPanel;

public class Appointment {
	public String startTime;
	public String endTime;
	public int patient_id;
	public Appointment(String start, String end, int patientID){
		this.startTime = start;
		this.endTime = end;
		this.patient_id = patientID;
	}
	public Appointment(){
		
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_name) {
		this.patient_id = patient_name;
	}
	public String toString() {
		return String
				.format("%s, %s ,%s",startTime, endTime, patient_id);
	}
	public Date stringToDate(String s){
		System.out.println("---------"+s);
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String startDateString = s;
	    String newDateString= "";
	    Date newerDate= null;
	    try{
	        Calendar c = Calendar.getInstance();
	        c.setTime(sdf.parse(startDateString));
	        c.add(Calendar.DATE, 7);  // number of days to add
	        newDateString = sdf.format(c.getTime());
	        newerDate = c.getTime();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return newerDate;
	}

}
