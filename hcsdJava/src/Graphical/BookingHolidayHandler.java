package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Database.Appointment;
import Database.Database;
import Database.Patient;

public class BookingHolidayHandler implements ActionListener{
	String startTime,endTime,year,month,day,staff;
	Appointment app;
	BookingHolidayPanel pan;
	public BookingHolidayHandler(BookingHolidayPanel panel) {
		this.pan = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.startTime = pan.getStartTime();
		this.endTime =pan.getEndTime();
		this.year = pan.getYear();
		this.month = pan.getMonth();
		this.day = pan.getDay();
	
		if (pan.getStaff().equals("Dentist")) {
			this.staff = "dentist_appointments";
		} else {
			this.staff = "hygiene_appointments";
		}
		Database db = new Database();
		
		Patient pat = (Patient) db.selectPatient("*", "patients", "patient_id = 1");
		
		String st = sqlFormatter(year, month, day, startTime);
		String end = sqlFormatter(year, month, day, endTime);
		if (db.getAppointmentsBooked("appointment_start >= "+ st+" AND "+ "appointment_end <= "+end, staff)== null){
			db.bookDentistAppointment(st+","+end+","+"1", staff);
			pan.getSuccess().setVisible(true);
		} else {
			pan.getFailure().setVisible(true);
		}
		
	}
	public String sqlFormatter(String year, String month, String day, String time){
		String x = "'"+year+"-"+month+"-"+day.substring(0,2)+" "+time+":00' ";
		return x;
	}

}
