package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Database;
import Database.Patient;

public class GetPatientIDHandler implements ActionListener{
	private String firstName,lastName, birthDate,postcode;
    BookingAppointmentPanel panel;
	public GetPatientIDHandler(BookingAppointmentPanel pan) {
    	panel = pan;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		Database db = new Database();
		Patient pat = (Patient) db.selectPatient("*", "patients", "first_name='"+panel.getFirstNameText()+"' and last_name='"+panel.getLastNameText()+"' and birth_date='"
				+panel.getBirthDateText()+"' and postcode ='"+panel.getPostcodeText()+"'");
		System.out.println(pat.getPatientID());
		panel.idLabel.setText(pat.getPatientID()+"");
		
	}

}
