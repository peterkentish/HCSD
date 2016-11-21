package Graphical;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Database.Database;

/*
 * secretary window where the user can select tabs for dentist or hygiene week schedule. Also tabs to book appointment
 * and set holidays so unable to book appointment for specific date.
 * tabs linked to relevant panel
 */

public class SecretaryMain extends DefaultFrame {

	public SecretaryMain() {

		Container contentPane = getContentPane();

		CalendarPanel dentistCal = new CalendarPanel("dentist_appointments");
		CalendarPanel hygieneCal = new CalendarPanel("hygiene_appointments");
		RegistrationInformationPanel regPan = new RegistrationInformationPanel();
		BookingAppointmentPanel bookPan = new BookingAppointmentPanel();
		BookingHolidayPanel holPan = new BookingHolidayPanel();

		setTitle("Secretary");
		JTabbedPane jtp = new JTabbedPane();
		contentPane.add(jtp);

		jtp.addTab("Dentist Week Schedule", dentistCal);
		jtp.addTab("Hygiene Week Schedule", hygieneCal);
		jtp.addTab("Register A New Patient", regPan);
		jtp.addTab("Book Another Appointment", bookPan);
		jtp.addTab("Book Staff Holiday", holPan);

	}

	public static void main(String[] args) {
		JFrame x = new SecretaryMain();
		x.setDefaultCloseOperation(EXIT_ON_CLOSE);
		x.setVisible(true);

	}
}
