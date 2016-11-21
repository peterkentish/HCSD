package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * this class obtains all relevant data and adds to database
 */

import Database.Database;
import Database.Patient;

public class RegistrationHandler implements ActionListener {
	private int patientID, checks, hyg, repa;
	private Database db = new Database();
	private String title, firstName, lastName, birthDate, streetAddress,
			postcode, contactNo, healthCare;
	private RegistrationInformationPanel info = new RegistrationInformationPanel();

	public RegistrationHandler(RegistrationInformationPanel inf) {
		this.info = inf;
	}

	public void actionPerformed(ActionEvent e) {
		title = info.getTitleComboBox();
		firstName = info.getFirstNameText();
		lastName = info.getLastNameText();
		birthDate = info.getBirthDateText();
		streetAddress = info.getStreetAddressText();
		postcode = info.getPostcodeText();
		contactNo = info.getContactNoText();
		healthCare = info.getHealthcareComboBox();
		checks = info.getChecks();
		hyg = info.getHyg();
		repa = info.getRepa();
		int startCount = db.getPatientCount();
		// insert into database
		db.addPatient(new Patient(title, firstName, lastName, birthDate,
				streetAddress, postcode, contactNo, healthCare, checks, hyg,
				repa));
		if (db.getPatientCount() == startCount + 1) {
			info.getSuccess().setVisible(true);
		} else
			info.getFailure().setVisible(true);
	}

}
