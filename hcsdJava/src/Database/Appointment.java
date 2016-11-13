package Database;

import java.sql.Time;

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

}
