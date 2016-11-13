package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Appointment;

public class BookingHandler implements ActionListener{
	String time,year,month,day,staff;
	Appointment app;
	public BookingHandler(String time, String year, String month, String day,String staff) {
		this.time = time;
		this.year = year;
		this.month = month;
		this.day = day;
		this.staff = staff;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		
	}
	public String sqlFormatter(String year, String month, String day, String time){
		String x = year+"-"+month+"-"+day.substring(0,2)+time+":00";
		return x;
	}

}
