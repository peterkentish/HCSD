package Graphical;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class UserSelect extends DefaultFrame  {	
	public UserSelect(){
		
		setTitle("Select User");
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridLayout(3, 1));
		//links to secretary page
		JButton button1 = new JButton("Secretary");
		contentPane.add(button1);
			class button1 implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					JFrame x = new SecretaryMain();
					x.setVisible(true);
					setVisible(false);
				}
			}
		button1.addActionListener(new button1());
		
		//links to dentist page
		JButton button2 = new JButton("Dentist");
		contentPane.add(button2);
		class button2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame x = new DentistMain();
				x.setVisible(true);
				setVisible(false);
			}
		}
	button2.addActionListener(new button2());

		//links to hygienist page
		JButton button3 = new JButton("Hygienist");
		contentPane.add(button3);
		class button3 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JFrame x = new HygienistMain();
				x.setVisible(true);
				setVisible(false);
			}
		}
	button3.addActionListener(new button3());
	}
	//run
    public static void main(String[] args) throws IOException{
    	JFrame frm = new UserSelect();
    	frm.setVisible(true);
    }
	

}

