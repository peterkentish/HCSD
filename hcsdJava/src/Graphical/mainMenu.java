package Graphical;
import java.awt.*;
import javax.swing.*;

public class MainMenu extends DefaultFrame {
	private JLabel title;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	String[] titleList = { "Mr", "Mrs", "Miss", "Dr"};

	
	public MainMenu(){
		
//		defaultFrame();
		Container contentPane = getContentPane();
		JPanel panel = new JPanel();
		
		label1 = new JLabel("Sheffield Dental Care Main Menu", SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 50));
		contentPane.add(label1);
		//title
		label2 = new JLabel("Title", SwingConstants.CENTER);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(label2);
		
		JComboBox title = new JComboBox(titleList);
		panel.add(title);
		

		

		//user input
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.yellow);
		label3 = new JLabel("First Name", SwingConstants.CENTER);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(label3);
		JTextField firstName = new JTextField("Enter Patient First Name here", 20);
		firstName.setSize(100, 30);
		panel.add(firstName);
		
		label4 = new JLabel("Surname", SwingConstants.CENTER);
		label4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(label4);

		JTextField surname = new JTextField("Enter Patient surname here", 20);
		surname.setSize(100, 30);
		panel.add(surname);
		

		

		
	}

}













