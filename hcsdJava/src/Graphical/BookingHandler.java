package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Database.Appointment;
import Database.Database;
import Database.Patient;

//The hadnler that consults the database to check if the appointment was successful
// after trying to book it
public class BookingHandler implements ActionListener {
	String startTime, endTime, year, month, day, staff;
	Appointment app;
	BookingAppointmentPanel pan;

	public BookingHandler(BookingAppointmentPanel panel) {
		this.pan = panel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.startTime = pan.getStartTime();
		this.endTime = pan.getEndTime();
		this.year = pan.getYear();
		this.month = pan.getMonth();
		this.day = pan.getDay();
		// which staff member is it
		if (pan.getStaff().equals("Dentist")) {
			this.staff = "dentist_appointments";
		} else {
			this.staff = "hygiene_appointments";
		}
		Database db = new Database();
		// get the patient
		Patient pat = (Patient) db.selectPatient(
				"*",
				"patients",
				"first_name='" + pan.getFirstNameText() + "' and last_name='"
						+ pan.getLastNameText() + "' and birth_date='"
						+ pan.getBirthDateText() + "' and postcode ='"
						+ pan.getPostcodeText() + "'");
		int i = (pat.getPatientID());
		String st = sqlFormatter(year, month, day, startTime);
		String end = sqlFormatter(year, month, day, endTime);
		// check if the appointment being booked already exists in the db at
		// all, if not book it and set success to visible
		if (db.getAppointmentsBooked("appointment_start >= " + st + " AND "
				+ "appointment_end <= " + end, staff) == null) {
			db.bookDentistAppointment(st + "," + end + "," + i, staff);
			pan.getSuccess().setVisible(true);
		} else {
			pan.getFailure().setVisible(true);
		}

	}

	// format a set of date info to string
	public String sqlFormatter(String year, String month, String day,
			String time) {
		String x = "'" + year + "-" + month + "-" + day.substring(0, 2) + " "
				+ time + ":00' ";
		return x;
	}

}
