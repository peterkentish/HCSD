package Graphical;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Database.Database;

public class SecretaryMain extends DefaultFrame{
	
	public SecretaryMain(){

		Container contentPane = getContentPane();
		
		CalendarPanel dentistCal = new CalendarPanel();
		CalendarPanel hygieneCal = new CalendarPanel();
		RegistrationInformationPanel regPan = new RegistrationInformationPanel();
		
		BookingAppointmentPanel bookPan = new BookingAppointmentPanel();

		
	      setTitle("Secretary");
	      JTabbedPane jtp = new JTabbedPane();
	      contentPane.add(jtp);
	      
	      jtp.addTab("Dentist Week Schedule", dentistCal);
	      jtp.addTab("Hygiene Week Schedule", hygieneCal);
          jtp.addTab("Register a new Patient", regPan);
          jtp.addTab("Book another Appointment", bookPan);
          
	}
	public static void main(String[] args) {
		JFrame x = new SecretaryMain();
		x.setVisible(true);

	}
}
