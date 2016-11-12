package Graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class SecretaryMain extends DefaultFrame{
	
	public SecretaryMain(){
		setTitle("Secretary");
		Container contentPane = this.getContentPane();
		TitlePanel title = new TitlePanel("Secretary");
		title.setPreferredSize(new Dimension(200,100));
		CalendarPanel cal = new CalendarPanel();
		cal.setPreferredSize(new Dimension(200,100));
		//Add everything to the frame space
		contentPane.setLayout(new BorderLayout());
		contentPane.add(cal, BorderLayout.CENTER);
		contentPane.add(title, BorderLayout.NORTH);
		
	}
	public static void main(String[] args) {
		JFrame x = new SecretaryMain();
		x.setVisible(true);
	}
}
