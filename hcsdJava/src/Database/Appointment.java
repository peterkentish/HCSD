package Database;

import java.sql.Time;

public class Appointment {
	public Time startTime;
	public Time endTime;
	public int patient_id;
	
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
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
