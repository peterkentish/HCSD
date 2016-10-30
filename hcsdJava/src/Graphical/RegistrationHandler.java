package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Database;
import Database.Patient;
public class RegistrationHandler implements ActionListener {
	private int patientID;
	private Database db = new Database();
	private String title, firstName,lastName,birthDate, streetAddress,postcode,contactNo,healthCare;
	private RegistrationInformationPanel info = new RegistrationInformationPanel();
	public RegistrationHandler(String title, String firstName,String lastName,String birthDate,String steetAddress,String postcode, String contactNo, String healthCare,RegistrationInformationPanel inf){
		this.title= title;
		this.firstName = firstName;
		this.lastName=lastName;
		this.birthDate = birthDate;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.contactNo = contactNo;
		this.healthCare = healthCare;
		this.info = inf;
	}
	public void actionPerformed(ActionEvent e) {
		db.addPatient(new Patient(title, firstName, lastName, birthDate, streetAddress, postcode, contactNo));
		
	}

}
