package Graphical;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class SecretaryMain extends DefaultFrame{
	
	public SecretaryMain(){
//		setTitle("Secretary");
//		title.setPreferredSize(new Dimension(200,100));
		Container contentPane = getContentPane();
		
		CalendarPanel dentistCal = new CalendarPanel();
		CalendarPanel hygieneCal = new CalendarPanel();
		RegistrationInformationPanel regPan = new RegistrationInformationPanel();
		BookingAppointmentPanel bookPan = new BookingAppointmentPanel();
//		JTabbedPane tabs = new JTabbedPane();
//		tabs.addTab("Diary", cal);
//		//Add everything to the frame space
//		contentPane.setLayout(new BorderLayout());
//		contentPane.add(cal, BorderLayout.CENTER);
//		contentPane.add(title, BorderLayout.NORTH);
//		
		
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
