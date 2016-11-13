package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class DayPanelHandlerPrev implements ActionListener {
	
	
	Date day;
	CalenderPanelDay cal;
	public DayPanelHandlerPrev(CalenderPanelDay cal) {
		this.cal = cal;
		this.day = cal.panelDate;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(calendar.DAY_OF_YEAR, -1);
		Date nextDay = calendar.getTime();
		System.out.println(nextDay);
		day = nextDay;
		cal.setPanelDate(day);
		cal.repaint();
		
		
		
	}

}
