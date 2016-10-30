package Graphical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class DefaultFrame extends JFrame {
	public DefaultFrame(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setBackground(Color.blue);
		Dimension screenDimensions = toolkit.getScreenSize();
		setSize(1200, 800);		
		setLocation(new Point(50,0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
		
}


