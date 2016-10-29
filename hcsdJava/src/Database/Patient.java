package Database;

public class Patient {
	
	private int patientID;
	private String firstName;
	private String surname;
	private String healthCare;
	
	public Patient(int patientID, String firstName, String surname, String healthCare){
		super();
		this.patientID = patientID;
		this.firstName = firstName;
		this.surname = surname;
		this.healthCare = healthCare;
		
		
		
	}

	public int getID(){
		return patientID;
	}
	
	public String getFirst(){
		return firstName;
	}
	public String getSurname(){
		return surname;
	}
	public String getHealthCare(){
		return healthCare;
	}
	
	public String toString() {
		return String
				.format("%s, %s ,%s, %s\n",patientID, firstName, surname, healthCare);
	}
	
}
