package Graphical;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import Database.Appointment;
import Database.Database;
import Database.Patient;

public class CalendarPanel extends JPanel {
	
	
	ArrayList<Appointment> apps =new ArrayList<Appointment>(); 
	String[] dayOfWeek = new String[5];
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
	}
	public CalendarPanel(){
		setBackground(Color.WHITE);	
		populateTimesOfDay();
		populateDaysOfWeek();
		getStartOfWeek();
		apps = (ArrayList<Appointment>) db.getAppointmentsWeek(weekStart, "dentist_appointments");
		System.out.println(apps.size());
	}
	public void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		Font mainFont = new Font("Century Gothic", 0, 20);
		Font titleFont = new Font("Century Gothic", 0, 28);
		sqlFormatterToday(weekStart);
		g.setFont(titleFont);
		g.drawString("Week Commencing "+weekStart.toString().substring(4, 10), 400, 25);
		
		ArrayList<Date> appTimes = new ArrayList<Date>();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		
		g.setFont(mainFont);
		AffineTransform gSave = g.getTransform();
		g.rotate(-Math.PI/2);
		g.drawString("Appointment Start", -400, 30);
		g.setTransform(gSave);
		
		System.out.println(apps.size());
		for (Appointment a : apps){
			System.out.println(a.getStartTime());
			appTimes.add(stringToDate(a.getStartTime()));
			appTimes.add(stringToDate(a.getEndTime()));
			Date s = a.stringToDate(a.getStartTime());
			patients.add((Patient) db.selectPatient("*", "patients", "patient_id="+a.getPatient_id()));
		}
		
		for (int i=0;i<dayOfWeek.length;i++){
			int xValue = 135+210*i;
			g.drawString(dayOfWeek[i], 190+210*i, 50);
			g.drawLine(xValue, 50, xValue, this.getHeight());
		}
		

		for (int i=0;i<times.size();i++){
			int yValue = 70+23*i;
			g.drawString(timesString.get(i), 40, 70+23*i);
			g.drawLine(40,yValue,this.getWidth(),yValue);
			for (int j = 0; j<appTimes.size();j=j+2){
				if (getHoursMins(times.get(i)).equals(getHoursMins(appTimes.get(j)))){
					int apptLength= getDifference(appTimes.get(j), appTimes.get(j+1))/20;
					for (int z=0;z<apptLength;z++){
						System.out.println(z);
						g.drawString(patients.get(j/2).getFirstName(),140+(appTimes.get(j).getDay()-1)*210,yValue+z*23);
					}
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
	  public int getDifference(Date date1, Date date2) {
		    int numHours1 =date1.getHours();
		    int numMinutes1 = date1.getMinutes();
		    int total1 = numHours1*60+numMinutes1;
		    
		    int numHours2 = date2.getHours();
		    int numMinutes2 = date2.getMinutes();
		    int total2 =numHours2*60 +numMinutes2;
		    
		    int total = total2 - total1;
		
		    return total;
		}
}
