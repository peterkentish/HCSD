package Graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AfterForm extends DefaultFrame  {
	
	public AfterForm(){
		
		setTitle("After");
		
		Container contentPane = this.getContentPane();
		AfterInformationPanel info = new AfterInformationPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(info,BorderLayout.CENTER);
		JButton submit = new JButton("ADD PATIENT");
		
		//submit.addActionListener(new RegistrationHandler(info));
		submit.setMaximumSize(new Dimension(200,40));
		contentPane.add(submit,BorderLayout.SOUTH);
		

	}
	public static void main(String[] args) {
		JFrame frm = new AfterForm();
		frm.setVisible(true);
	}

}
