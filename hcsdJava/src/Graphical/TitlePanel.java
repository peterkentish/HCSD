package Graphical;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComboBox;
import javax.swing.JPanel;
/**
 * the title panel to be used at the top of frames
 * @author Peter Kentish De La Iglesia
 */
public class TitlePanel extends JPanel {
	String titleText;
	public TitlePanel(String s){
		titleText=s;
		this.setBackground(Color.red);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.black);
		g2.setFont(new Font("Century Gothic", Font.PLAIN, 80)); 
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON 
		); 
		g2.drawString(titleText,0,70);
	}
		
		
		
}
