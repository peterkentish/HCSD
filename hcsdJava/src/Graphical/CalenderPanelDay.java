package Graphical;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalenderPanelDay extends JPanel{
	
	String[] dayOfWeek = new String[7];
	List<java.sql.Time> times = new ArrayList<>();
	ArrayList<String> timesString = new ArrayList<String>();
	Date weekStart;
	Date panelDate = new Date();
	
    
    
    
	
	public Date getPanelDate() {
		return panelDate;
	}

	public void setPanelDate(Date panelDate) {
		this.panelDate = panelDate;
	}

	public void getStartOfWeek(){
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		weekStart = c.getTime();
		int weekStartInt = weekStart.getDate();
		int monthInt = weekStart.getMonth();
	}
	
	public void populateTimesOfDay(){
		java.sql.Time startTime = new java.sql.Time(9, 0, 0);
		java.sql.Time endTime = new java.sql.Time(17, 0, 0);

		times.add(startTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		while (cal.getTime().before(endTime)) {
		    cal.add(Calendar.MINUTE, 20);
		    times.add(new java.sql.Time(cal.getTimeInMillis()));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for (java.sql.Time time : times) {
		    timesString.add(sdf.format(time));
		}
	}
	public void populateDaysOfWeek(){
		dayOfWeek[0]="Monday";
		dayOfWeek[1]="Tuesday";
		dayOfWeek[2]="Wednesday";
		dayOfWeek[3]="Thursday";
		dayOfWeek[4]="Friday";
		dayOfWeek[5]="Saturday";
		dayOfWeek[6]="Sunday";
	}
	public CalenderPanelDay(){
			
		populateTimesOfDay();

	}
	public void paintComponent(Graphics graphics){
		System.out.println("Panel d"+panelDate);
		super.paintComponents(graphics);
		Graphics2D g = (Graphics2D) graphics;
		Font mainFont = new Font("Century Gothic", 0, 20);
		Font titleFont = new Font("Century Gothic", 0, 28);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		dateFormat.format(panelDate); 
		
		g.setFont(titleFont);
		g.drawString("Day View  "+dateFormat.format(panelDate).toString(), 400, 25);
		
		
		g.setFont(mainFont);

		for (int i=0;i<times.size();i++){
			g.drawString(timesString.get(i), 40, 70+23*i);
			g.drawLine(40,74+23*i,this.getWidth(),74+23*i);
		}
	

	
}
	



}
