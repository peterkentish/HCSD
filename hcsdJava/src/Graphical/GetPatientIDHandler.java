package Graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.Database;
import Database.Patient;

public class GetPatientIDHandler implements ActionListener{
	private String firstName,lastName, birthDate,postcode;
    public GetPatientIDHandler(String fn, String ln, String bd, String pc) {
    	firstName = fn;
    	lastName = ln;
    	birthDate = bd;
    	postcode = pc;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Database db = new Database();
		Patient pat = (Patient) db.selectPatient("*", "patients", "firstName='"+firstName+"' and last_name='"+lastName+"' and birth_date="
				+birthDate+"' and postcode ='"+postcode+"'");
	}

}
