package Graphical;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class HygienistMain extends AfterDayMain {

	JTabbedPane jtp = new JTabbedPane();

	public HygienistMain() {
		setTitle("Hygienist");
		Container contentPane = getContentPane();
		AfterInformationPanel info = new AfterInformationPanel();
		CalenderDayFrame cdf = new CalenderDayFrame();
		contentPane.add(jtp);
		jtp.addTab("After Patient Form", info);
		jtp.addTab("Day View", cdf);

	}

	public static void main(String[] args) {
		JFrame x = new HygienistMain();
		x.setVisible(true);
	}

}
