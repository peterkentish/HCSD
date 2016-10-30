package Database;

public class Patient {
	
	private int patientID;
	private String title, firstName,lastName,birthDate, streetAddress,postcode,contactNo,healthCare;
	
	public Patient(){
		
	}
	public Patient(String title, String firstName,String lastName,String birthDate,String streetAddress,String postcode, String contactNo, String healthcare){
		this.title= title;
		this.firstName = firstName;
		this.lastName=lastName;
		this.birthDate = birthDate;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.contactNo = contactNo;
		this.healthCare = healthcare;
	}

	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getHealthCare() {
		return healthCare;
	}
	public void setHealthCare(String healthCare) {
		this.healthCare = healthCare;
	}
	public String toString() {
		return String
				.format("%s, %s ,%s, %s, %s, %s ,%s, %s, %s \n",patientID,title, firstName,lastName,birthDate, streetAddress,postcode,contactNo, healthCare);
	}
	public String toSQLString() {
		return String
				.format("'%s', '%s' ,'%s', '%s', '%s', '%s' ,'%s', '%s', '%s'",patientID,title, firstName,lastName, birthDate,streetAddress,postcode,contactNo, healthCare);
	}
	
}
