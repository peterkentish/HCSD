package Graphical;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;

public class CalendarPanel extends JPanel {
	Calendar c =  Calendar.getInstance();
	
	String[] dayOfWeek = new String[7];
	public void populateDaysOfWeek(){
		dayOfWeek[0]="Monday";
		dayOfWeek[1]="Tuesday";
		dayOfWeek[2]="Wednesday";
		dayOfWeek[3]="Thursday";
		dayOfWeek[4]="Friday";
		dayOfWeek[5]="Saturday";
		dayOfWeek[6]="Sunday";
	}
	public CalendarPanel(){
		setBackground(Color.WHITE);	
	}
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		populateDaysOfWeek();
		Font mainFont = new Font("Century Gothic", 0, 20);
		g.setFont(mainFont);
		for (int i=0;i<dayOfWeek.length;i++){
			g.drawString(dayOfWeek[i], 160+150*i, 50);
		}
	}
}
