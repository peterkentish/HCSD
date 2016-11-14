package Graphical;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

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
		
		class submit implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try{
				    PrintWriter writer = new PrintWriter("Reciept.txt", "UTF-8");
				    writer.println("The first line");
				    writer.println("The second line");
				    writer.close();
				} catch (Exception a) {
				   // do something
				}
				
			}
		}
	submit.addActionListener(new submit());
		
		//submit.addActionListener(new RegistrationHandler(info));
		submit.setMaximumSize(new Dimension(200,40));
		panel.add(submit,BorderLayout.SOUTH);
		

	}
//	public static void main(String[] args) {
//		JFrame frm = new AfterForm();
//		frm.setVisible(true);
//	}

}
