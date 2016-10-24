package Graphical;
import java.awt.*;
import javax.swing.*;

public class mainMenu extends JFrame {
	private JLabel title;
	
	public mainMenu(){
		super("Main Menu");
		setLayout (new FlowLayout());
		
		title = new JLabel("Welcome to the Main Menu");
		title.setFont(new Font("Calibri", Font.PLAIN, 28));
		add(title);
		
	}

}
