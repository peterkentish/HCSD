package Graphical;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
/*
 * hygienist window where the user can select tabs for day or week schedule and the after patient form.
 * the tables call the relevant panel
 */
public class HygienistMain extends DefaultFrame {
	public HygienistMain() {
		
		setTitle("Hygienist");
		Container contentPane = getContentPane();
		AfterInformationPanel info = new AfterInformationPanel();
		CalendarPanelDay dayP = new CalendarPanelDay("hygiene_appointments");
		CalendarPanel hygieneCal = new CalendarPanel("hygiene_appointments");
		JTabbedPane jtp = new JTabbedPane();
		contentPane.add(jtp);
		jtp.addTab("Day Schedule", dayP);
		jtp.addTab("Week Schedule",hygieneCal);
		jtp.addTab("After Patient Form", info);
	}

	public static void main(String[] args) {
		JFrame x = new HygienistMain();
		x.setVisible(true);
	}
}
