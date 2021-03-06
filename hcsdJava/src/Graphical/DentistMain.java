package Graphical;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/*
 * dentist window where the user can select tabs for day or week schedule and the after patient form.
 * the tables call the relevant panel
 */
public class DentistMain extends DefaultFrame {
	public DentistMain() {
		setTitle("Dentist");
		Container contentPane = getContentPane();
		AfterInformationPanel info = new AfterInformationPanel();
		CalendarPanel weekP = new CalendarPanel("dentist_appointments");
		CalendarPanelDay dayP = new CalendarPanelDay("dentist_appointments");
		JTabbedPane jtp = new JTabbedPane();
		contentPane.add(jtp);
		jtp.addTab("Day Schedule", dayP);
		jtp.addTab("Week Schedule", weekP);
		jtp.addTab("After Patient Form", info);
	}

	public static void main(String[] args) {
		JFrame x = new DentistMain();
		x.setVisible(true);
	}
}
