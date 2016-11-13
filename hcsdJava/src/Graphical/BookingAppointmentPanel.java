package Graphical;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Database;
import Database.Patient;

public class BookingAppointmentPanel extends JPanel implements ActionListener {
	ArrayList<String> timesString = new ArrayList<String>();
	List<java.sql.Time> times = new ArrayList<>();
	private JTextField firstNameText,lastNameText,birthDateText,postcodeText;
	JComboBox<String> startTimesComboBox = new JComboBox<>();
	JComboBox<String> endTimesComboBox = new JComboBox<>();
	JComboBox<String> staffMember = new JComboBox<>();
	String firstName;
	private String lastName;
	private String birthDate;
	
	private String postcode;
	private Time appointmentStart,appointmentEnd;
	private Integer patientID =0; 
	public JLabel idLabel = new JLabel(patientID.toString());
	
	private int currentMonth;
	private int currentYear;
	
	private int daysInMonth = 31;
	private JComboBox<String> dayComboBox = new JComboBox<String>();
	private JComboBox<String> yearComboBox = new JComboBox<String>();
	private JComboBox<String> monthComboBox = new JComboBox<String>();
	
	
	public BookingAppointmentPanel(){
		populateTimesOfDay();
		firstNameText= new JTextField(20);
		lastNameText= new JTextField(20);
		birthDateText= new JTextField(20);
		postcodeText = new JTextField(20);
		
		yearComboBox.setMaximumSize(new Dimension(200,30));
		monthComboBox.setMaximumSize(new Dimension(200,30));
		dayComboBox.setMaximumSize(new Dimension(200,30));
		
		yearComboBox.addItem("2016");
		yearComboBox.addItem("2017");
		yearComboBox.addItem("2018");
		
		staffMember.addItem("Dentist");
		staffMember.addItem("Hygienist");
		
		for (int i = 1; i <= daysInMonth;i++){
			String x = Integer.toString(i);
			Date dt = new Date(currentYear, currentMonth, i);
			int day = dt.getDay();
			
			String dayName;
			switch (day+1){
				case 7: dayName = " Sunday";break;
				case 1: dayName = " Monday";break;
				case 2: dayName = " Tuesday";break;
				case 3: dayName = " Wednesday";break;
				case 4: dayName = " Thursday";break;
				case 5: dayName = " Friday";break;
				case 6: dayName = " Saturday";break;
				default: dayName = " Fail";break;
			}
			dayComboBox.addItem(x+dayName);
		}
	
		for (int i = 1; i < 13;i++){
			String x = Integer.toString(i);
			monthComboBox.addItem(x);
		}
		
		
		for (int i=0;i<times.size();i++){
			startTimesComboBox.addItem(timesString.get(i));
			endTimesComboBox.addItem(timesString.get(i));
		}
		Date todaysDate = new Date();
		int yearToday = todaysDate.getYear()+1900;
		int monthToday = todaysDate.getMonth()+1;
		int dayToday = todaysDate.getDate() -1;
		String todaysYear = Integer.toString(yearToday);
		String todaysMonth = Integer.toString(monthToday);
		String todaysDay = Integer.toString(dayToday);
		
		yearComboBox.setSelectedItem(todaysYear);
		monthComboBox.setSelectedItem(todaysMonth);
		dayComboBox.setSelectedItem(todaysDay);
		yearComboBox.addActionListener(this);
		monthComboBox.addActionListener(this);
		Dimension textDim = new Dimension(600,40); 
		firstNameText.setMaximumSize(textDim);
		lastNameText.setMaximumSize(textDim);
		birthDateText.setMaximumSize(textDim);
		postcodeText.setMaximumSize(textDim);
		startTimesComboBox.setMaximumSize(new Dimension(200,30));
		endTimesComboBox.setMaximumSize(new Dimension(200,30));
		
		staffMember.setMaximumSize(new Dimension(200,30));
		
		this.setLayout(new BoxLayout(this,1));
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Surname: ");
		JLabel birthDateLabel = new JLabel("Date of Birth: ");
		JLabel postcodeLabel = new JLabel("Postcode: ");
		
		idLabel = new JLabel(patientID.toString());
		idLabel.setVisible(false);
		idLabel.setFont(new Font("Arial",0,25));
	
		this.add(firstNameLabel);
		this.add(firstNameText);
		this.add(lastNameLabel);
		this.add(lastNameText);
		this.add(birthDateLabel);
		this.add(birthDateText);
		this.add(postcodeLabel);
		this.add(postcodeText);
		
		
		
		this.add(startTimesComboBox);
		this.add(endTimesComboBox);
		this.add(yearComboBox);
		this.add(monthComboBox);
		this.add(dayComboBox);
		this.add(staffMember);
		
		JButton makeBooking = new JButton("Book Appointment");
		makeBooking.addActionListener(new BookingHandler(this));
		this.add(makeBooking);


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
	@Override
	public void actionPerformed(ActionEvent e) {
		idLabel.setVisible(true);
		JComboBox combo = (JComboBox)e.getSource();
		//get the current year and month from the combo boxes.
		if (combo.equals(yearComboBox)){
        	String selectedYear = (String)  combo.getSelectedItem();
        	currentYear = Integer.parseInt(selectedYear);
		}
		else {
			String selectedMonth = (String) combo.getSelectedItem();
	        currentMonth = Integer.parseInt(selectedMonth);
		}
		//switch to determine which months have how many days.
			switch (currentMonth) {
		        case 1:
		        case 3:
		        case 5:
		        case 7:
		        case 8:
		        case 10:
		        case 12:
		            daysInMonth = 31;
		            break;
		        case 4:
		        case 6:
		        case 9:
		        case 11:
		            daysInMonth = 30;
		            break;
		        case 2:
		            if (((currentYear % 4 == 0) && 
		                 !(currentYear % 100 == 0))
		                 || (currentYear % 400 == 0))
		                daysInMonth = 29;
		            else
		                daysInMonth = 28;
		            break;
		        default:
		            daysInMonth=1;
		            break;
			}
			dayComboBox.removeAllItems();
			for (int i = 1; i <= daysInMonth;i++){
				String x = Integer.toString(i);
				Date dt = new Date(currentYear, currentMonth, i);
				int day = dt.getDay();
				String dayName;
				switch (day){
					case 0: dayName = " Sunday";break;
					case 1: dayName = " Monday";break;
					case 2: dayName = " Tuesday";break;
					case 3: dayName = " Wednesday";break;
					case 4: dayName = " Thursday";break;
					case 5: dayName = " Friday";break;
					case 6: dayName = " Saturday";break;
					default: dayName = " Fail";break;
				}
				
				dayComboBox.addItem(x + dayName);
			}

		
	}
	
	public String getFirstNameText() {
		return firstNameText.getText();
	}
	public String getLastNameText() {
		return lastNameText.getText();
	}
	public String getBirthDateText() {
		return birthDateText.getText();
	}
	public String getPostcodeText() {
		return postcodeText.getText();
	}
	public String getYear(){
		return (String) yearComboBox.getSelectedItem();
	}
	public String getMonth(){
		return (String) monthComboBox.getSelectedItem();
	}
	public String getDay(){
		return (String) dayComboBox.getSelectedItem();
	}
	public String getStartTime(){
		System.out.println(startTimesComboBox.getSelectedItem());
		return (String) startTimesComboBox.getSelectedItem();
	}
	public String getEndTime(){
		return (String) endTimesComboBox.getSelectedItem();
	}
	public String getStaff(){
		return (String) staffMember.getSelectedItem();
	}
	
}
