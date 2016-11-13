package Graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalenderDayFrame extends DefaultFrame {
	
	public CalenderDayFrame(){
		
		
		setTitle("Calendar Day View");
		CalenderPanelDay cpd = new CalenderPanelDay();
		this.setBackground(Color.WHITE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		
		
		JPanel subPanel = new JPanel();
		JButton prev = new JButton( "Previous" );
	    subPanel.add( prev);
	    JButton next = new JButton( "Next" );
	    subPanel.add( next);
	    next.addActionListener(new DayPanelHandler(cpd));

		contentPane.add(subPanel,BorderLayout.NORTH);
		
//		class next implements ActionListener {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Works");
//				//setVisible(false);
//				
//			}
//		}
//		next.addActionListener(new next());
		
		class prev implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Works");
				setVisible(false);
				
			}
		}
		prev.addActionListener(new prev());
		
		contentPane.add(cpd,BorderLayout.CENTER);
		
		pack();
		setSize(1200, 800);	
		

	}
	
	public static void main(String[] args) {
		JFrame frm = new CalenderDayFrame();
		frm.setVisible(true);

	}

}
