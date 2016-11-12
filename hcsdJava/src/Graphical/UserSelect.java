package Graphical;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


public class UserSelect extends DefaultFrame  {
	

	
	
	
	public UserSelect(){
		
		setTitle("Select User");
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(3, 1));

		JButton button1 = new JButton("Secretary");

		contentPane.add(button1);
		
			class button1 implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Works");
					setVisible(false);
					
				}
			}
		button1.addActionListener(new button1());
		
		JButton button2 = new JButton("Dentist");

		contentPane.add(button2);
		
		JButton button3 = new JButton("Hygienist");

		contentPane.add(button3);
		

		

	}
	
    public static void main(String[] args) throws IOException{
    	JFrame frm = new UserSelect();
    	frm.setVisible(true);
    }
	

}

