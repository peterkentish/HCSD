package Database;

public class Patient {
	
	private int patientID;
	private String firstName;
	private String surname;
	private String healthCare;
	
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setHealthCare(String healthCare) {
		this.healthCare = healthCare;
	}

	public Patient(){
		
	}
	public Patient(int patientID, String firstName, String surname, String healthCare){
		
		this.patientID = patientID;
		this.firstName = firstName;
		this.surname = surname;
		this.healthCare = healthCare;
	}

	public String toString() {
		return String
				.format("%s, %s ,%s, %s \n",patientID, firstName, surname, healthCare);
	}
	public String toSQLString() {
		return String
				.format("'%s', '%s' ,'%s', '%s'",patientID, firstName, surname, healthCare);
	}
	
}
