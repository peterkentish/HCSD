package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Database.Appointment;
import Database.Database;
import Database.Patient;

public class BookingHandler implements ActionListener{
	String startTime,endTime,year,month,day,staff;
	Appointment app;
	BookingAppointmentPanel pan;
	public BookingHandler(BookingAppointmentPanel panel) {
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
		
		Patient pat = (Patient) db.selectPatient("*", "patients", "first_name='"+pan.getFirstNameText()+"' and last_name='"+pan.getLastNameText()+"' and birth_date='"
				+pan.getBirthDateText()+"' and postcode ='"+pan.getPostcodeText()+"'");
		int i = (pat.getPatientID());
		String st = sqlFormatter(year, month, day, startTime);
		String end = sqlFormatter(year, month, day, endTime);
		if (db.getAppointmentsBooked("appointment_start >= "+ st+" AND "+ "appointment_end <= "+end, staff)== null){
			db.bookDentistAppointment(st+","+end+","+i, staff);
		} else {
			System.out.println("POOOOOO9045809530850498543098509345409853098");
		}
		
		
		
		
		
	}
	public String sqlFormatter(String year, String month, String day, String time){
		String x = "'"+year+"-"+month+"-"+day.substring(0,2)+" "+time+":00' ";
		return x;
	}

}
