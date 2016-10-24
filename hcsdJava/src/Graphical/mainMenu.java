package Graphical;
import java.awt.*;
import javax.swing.*;

public class mainMenu extends JFrame {
	private JLabel title;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	public mainMenu(){
		super("Main Menu");
		//using flow layout
		setLayout(new FlowLayout());
		//setTitle("Welcome");
		//background is light blue
		getContentPane().setBackground(Color.cyan);
		//using screen dimensions to set window size and location on screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimention = toolkit.getScreenSize();
		setLocation(new Point(screenDimention.width / 4, 10));
		Container contentPane = getContentPane();
		//grid layout within flow layout to ask for user input
		//contentPane.setLayout(new GridLayout(, 1));
		JPanel panel = new JPanel();
		



		//title
		label1 = new JLabel("Main Menu", SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 50));
		contentPane.add(label1);
		//user input
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(Color.yellow);
		label3 = new JLabel("First Name", SwingConstants.CENTER);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(label3);
		JTextField name = new JTextField("Enter Patient First Name here", 20);
		name.setSize(100, 30);
		panel.add(name);
		
	}

}













