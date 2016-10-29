package Graphical;

import java.awt.*;
import javax.swing.*;


public class defaultFrame extends JFrame {
	public void defaultFrame(){
		//using flow layout
				setLayout(new FlowLayout());
				//setTitle("Welcome");
				//background is light blue
				getContentPane().setBackground(Color.cyan);
				//using screen dimensions to set window size and location on screen
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Dimension screenDimention = toolkit.getScreenSize();
				setLocation(new Point(screenDimention.width / 4, 10));
				
				
		
	}

}
