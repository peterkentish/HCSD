package Graphical;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import Database.Appointment;
import Database.Database;

public class CalendarPanel extends JPanel {
	
	
	
	String[] dayOfWeek = new String[7];
	List<java.sql.Time> times = new ArrayList<>();
	ArrayList<String> timesString = new ArrayList<String>();
	Date weekStart;
	Database db = new Database();
	
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
	public CalendarPanel(){
		setBackground(Color.WHITE);	
		populateTimesOfDay();
		populateDaysOfWeek();
		getStartOfWeek();
	}
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		Font mainFont = new Font("Century Gothic", 0, 20);
		Font titleFont = new Font("Century Gothic", 0, 28);
		sqlFormatterToday(weekStart);
		g.setFont(titleFont);
		g.drawString("Week Commencing "+weekStart.toString().substring(4, 10), 400, 25);
		ArrayList<Appointment> apps =(ArrayList<Appointment>) db.getAppointmentsWeek(weekStart, "dentist_appointments");
		ArrayList<Date> appTimes = new ArrayList<Date>();
		g.setFont(mainFont);
		for (int i=0;i<apps.size();i++){
			appTimes.add(stringToDate(apps.get(i).getStartTime()));
			appTimes.add(stringToDate(apps.get(i).getEndTime()));
		}
		for (int i=0;i<dayOfWeek.length;i++){
			int xValue = 135+150*i;
			g.drawString(dayOfWeek[i], 160+150*i, 50);
			g.drawLine(xValue, 50, xValue, this.getHeight());
			
		}
		
		
		for (int i=0;i<times.size();i++){
			int yValue = 70+23*i;
			g.drawString(timesString.get(i), 40, 70+23*i);
			g.drawLine(40,yValue,this.getWidth(),yValue);
			
			for (int j = 0; j<appTimes.size();j=j+2){
				System.out.println("---"+getHoursMins(times.get(i)));
				System.out.println("___"+getHoursMins(appTimes.get(j)));
				if (getHoursMins(times.get(i)).equals(getHoursMins(appTimes.get(j)))){
					g.drawString("APPOINTMENT",140, yValue);
				}
			}
		}

		
	}
	public String sqlFormatterToday(Date d){
		String x = "'"+(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":00' ";
		return x;
	}
	  public Date stringToDate(String s){
		  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String startDateString = s;
		    String newDateString= "";
		    Date newerDate= null;
		    try{
		        Calendar c = Calendar.getInstance();
		        c.setTime(sdf.parse(startDateString));
		        c.add(Calendar.DATE, 7);  // number of days to add
		        newDateString = sdf.format(c.getTime());
		        newerDate = c.getTime();
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		    return newerDate;
	  }
	  public String getHoursMins(Date d){
		 Integer h = d.getHours();
		 Integer m = d.getMinutes();
		 String x = h+":"+m;
		 return x;
		  
	  }
}
