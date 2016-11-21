package Graphical;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

// this is the super class all gui will inherit to  window settings  
public class DefaultFrame extends JFrame {
	public DefaultFrame() {
		// blue background
		setBackground(Color.blue);
		// standard windo size
		setSize(1200, 800);
		// where on screen
		setLocation(new Point(50, 0));
		// stop running on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
