package Graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AfterForm extends JPanel  {
	
	public AfterForm(){
		
		//setTitle("After");
		
		JPanel panel = new JPanel();
		AfterInformationPanel info = new AfterInformationPanel();
		panel.setLayout(new BorderLayout());
		panel.add(info,BorderLayout.CENTER);
		JButton submit = new JButton("ADD PATIENT");
		
		//submit.addActionListener(new RegistrationHandler(info));
		submit.setMaximumSize(new Dimension(200,40));
		panel.add(submit,BorderLayout.SOUTH);
		

	}
//	public static void main(String[] args) {
//		JFrame frm = new AfterForm();
//		frm.setVisible(true);
//	}

}
