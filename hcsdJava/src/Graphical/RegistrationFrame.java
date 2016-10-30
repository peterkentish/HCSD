package Graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RegistrationFrame extends DefaultFrame {
	public RegistrationFrame(){
		
		setTitle("Registration");
		RegistrationInformationPanel info = new RegistrationInformationPanel();
		Container contentPane = this.getContentPane();
		//Add everything to the frame space
		contentPane.setLayout(new BorderLayout());
		contentPane.add(info,BorderLayout.CENTER);
		JButton submit = new JButton("ADD PATIENT");
		
		submit.addActionListener(new RegistrationHandler(info));
		submit.setMaximumSize(new Dimension(200,40));
		contentPane.add(submit,BorderLayout.SOUTH);
		

	}
	public static void main(String[] args) {
		JFrame frm = new RegistrationFrame();
		frm.setVisible(true);
	}
}
