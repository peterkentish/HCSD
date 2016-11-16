package Graphical;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class DentistMain extends DefaultFrame {
	public DentistMain() {
		
		setTitle("Hygienist");
		Container contentPane = getContentPane();
		AfterInformationPanel info = new AfterInformationPanel();
		CalendarPanelDay dayP = new CalendarPanelDay("dentist_appointments");
		JTabbedPane jtp = new JTabbedPane();
		contentPane.add(jtp);
		jtp.addTab("Day Schedule", dayP);
		jtp.addTab("After Patient Form", info);
	}

	public static void main(String[] args) {
		JFrame x = new DentistMain();
		x.setVisible(true);
	}

}
