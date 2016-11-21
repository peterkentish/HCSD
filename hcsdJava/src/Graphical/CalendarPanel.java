/*
 * A calendar which uses graphics 2D to produce a table, with 6 columns.
 * the first column being the time in 20 mintue intervals, the rest being
 * the days of the week from monday through friday. It then populates these
 * with the data from the database for each day and time segment.  
 */

package Graphical;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import Database.Appointment;
import Database.Database;
import Database.Patient;

public class CalendarPanel extends JPanel implements ActionListener {

	ArrayList<Appointment> apps = new ArrayList<Appointment>();
	String[] dayOfWeek = new String[5];
	List<java.sql.Time> times = new ArrayList<>();
	ArrayList<String> timesString = new ArrayList<String>();
	String staff;
	Date weekStart;

	Database db = new Database();

	// Get the start of the week, using the most recent monday as the start
	public void getStartOfWeek(int i) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		weekStart = c.getTime();
		int weekStartInt = weekStart.getDate();
		weekStart.setHours(1);
		weekStart.setMinutes(1);
		if (i != 0) {
			weekStart.setDate(i);
		}
		int monthInt = weekStart.getMonth();
	}

	// create the times in 20 minute intervals, only allow end time after start
	// time
	public void populateTimesOfDay() {
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

	public void populateDaysOfWeek() {
		dayOfWeek[0] = "Monday";
		dayOfWeek[1] = "Tuesday";
		dayOfWeek[2] = "Wednesday";
		dayOfWeek[3] = "Thursday";
		dayOfWeek[4] = "Friday";
	}

	// constructor that gets the appointments from the db and adds them to
	// arraylist apps.
	public CalendarPanel(String staffS) {
		this.staff = staffS;
		setBackground(Color.WHITE);
		populateTimesOfDay();
		populateDaysOfWeek();
		getStartOfWeek(0);
		apps = (ArrayList<Appointment>) db
				.getAppointmentsWeek(weekStart, staff);
		JButton next = new JButton("Next");
		next.addActionListener(this);
		JButton back = new JButton("Back");
		back.addActionListener(this);

		this.add(back);
		this.add(Box.createRigidArea(new Dimension(400, 0)));
		this.add(next);
		next.setBounds(200, 100, 50, 50);
	}

	// the big guns where the calendar is drawn
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		Font mainFont = new Font("Century Gothic", 0, 20);
		Font titleFont = new Font("Century Gothic", 0, 28);
		apps = (ArrayList<Appointment>) db
				.getAppointmentsWeek(weekStart, staff);

		// draw the axes labels
		g.setFont(titleFont);
		g.drawString(
				"Week Commencing " + weekStart.toString().substring(4, 10),
				400, 25);
		ArrayList<Date> appTimes = new ArrayList<Date>();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		g.setFont(mainFont);
		AffineTransform gSave = g.getTransform();
		g.rotate(-Math.PI / 2);
		g.drawString("Appointment Start", -400, 30);
		g.setTransform(gSave);

		// if there are appointments add them to apptimes, an arraylit of
		// appointment times
		if (apps != null) {
			for (Appointment a : apps) {
				appTimes.add(stringToDate(a.getStartTime()));
				appTimes.add(stringToDate(a.getEndTime()));
				patients.add((Patient) db.selectPatient("*", "patients",
						"patient_id=" + a.getPatient_id()));
			}
		}
		// for each day, draw the day string and the vertical lines
		for (int i = 0; i < dayOfWeek.length; i++) {
			int xValue = 135 + 210 * i;
			g.drawString(dayOfWeek[i], 190 + 210 * i, 50);
			g.drawLine(xValue, 50, xValue, this.getHeight());
		}

		// for each horizontal value, draw the lines and the string with the
		// time on it
		for (int i = 0; i < times.size(); i++) {
			int yValue = 70 + 23 * i;
			g.drawString(timesString.get(i), 40, 70 + 23 * i);
			g.drawLine(40, yValue, this.getWidth(), yValue);
			if (apps != null) {
				// for every other element in apptimes,
				for (int j = 0; j < appTimes.size(); j = j + 2) {
					// if the current appTimes start is equal to the horizontal
					// table element we are in
					// print the appt. So if current time is 9:00 and we have an
					// appointment at 9:00 in
					// the apps list, print it to the current space. Also, get
					// the appt length in units of 20.
					if (getHoursMins(times.get(i)).equals(
							getHoursMins(appTimes.get(j)))) {
						int apptLength = getDifference(appTimes.get(j),
								appTimes.get(j + 1)) / 20;
						// for the length of the appointment, fill in the z
						// amount of appointment spaces.
						for (int z = 0; z < apptLength; z++) {
							Patient p = patients.get(j / 2);
							g.drawString(
									p.getFirstName() + " " + p.getLastName(),
									140 + (appTimes.get(j).getDay() - 1) * 210,
									yValue + z * 23);
						}
					}
				}
			}
		}
	}

	// format a date to sql date format
	public String sqlFormatterToday(Date d) {
		String x = "'" + (d.getYear() + 1900) + "-" + (d.getMonth() + 1) + "-"
				+ d.getDate() + " " + d.getHours() + ":" + d.getMinutes()
				+ ":00' ";
		return x;
	}

	// turn a string into a date.
	public Date stringToDate(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDateString = s;
		String newDateString = "";
		Date newerDate = null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startDateString));
			c.add(Calendar.DATE, 7); // number of days to add
			newDateString = sdf.format(c.getTime());
			newerDate = c.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newerDate;
	}

	// return just the hours and mins of a date as a string
	public String getHoursMins(Date d) {
		Integer h = d.getHours();
		Integer m = d.getMinutes();
		String x = h + ":" + m;
		return x;

	}

	// get the time difference between two times using the expansion of minutes.
	public int getDifference(Date date1, Date date2) {
		int numHours1 = date1.getHours();
		int numMinutes1 = date1.getMinutes();
		int total1 = numHours1 * 60 + numMinutes1;
		int numHours2 = date2.getHours();
		int numMinutes2 = date2.getMinutes();
		int total2 = numHours2 * 60 + numMinutes2;
		int total = total2 - total1;
		return total;
	}

	@Override
	// buttons for the increasing and decreasing of the date
	public void actionPerformed(ActionEvent e) {
		int current = weekStart.getDate();
		if (e.getActionCommand().equals("Next")) {
			weekStart.setDate(current + 7);
		} else {
			weekStart.setDate(current - 7);
		}

		repaint();
	}
}
