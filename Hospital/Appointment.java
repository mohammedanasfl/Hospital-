
/*
 * This class describes an Appointment made between a patient and a doctor
 * Contains the reference to the object of both Patient and Doctor involved in the Appointment
 */
public class Appointment 
{
	private int appointment_number;
	private Patient patient;
	private Doctor doctor;
	private String slot;
	
	// Constructor to create an Appointment between the passed Patient and the Doctor
	Appointment(Patient patient, Doctor doctor)
	{
		appointment_number = ++Aggregation.appointment_number;
		this.patient = patient;
		this.doctor = doctor;
		patient.addAppointment(this);
		doctor.addAppointment(this);
		this.slot = doctor.getSlot();
	}
	
	/*
	 *  Constructor to create a dummy object using the Appointment ID passed
	 *  Used to check equality with a real Appointment
	 */
	Appointment(int ID)
	{
		this.appointment_number = ID;
	}
	
	// Get the details of the Doctor involved in the Appointment
	public String doctorDetails()
	{
		String appointment = "Appointment ID: " + getAppointmentNumber() + "\n";
		appointment += doctor.getDoctorDetails();
		
		return appointment;
	}
	
	// Get the details of the Patient involved in the Appointment
	public String patientDetails()
	{
		String appointment = "Appointment ID: " + getAppointmentNumber() + "\n";
		appointment += patient.getPatientDetails();
		appointment += "\nVisiting Hour: " + slot;
		
		return appointment;
	}
	
	public Doctor getDoctor()
	{
		return doctor;
	}
	
	public Patient getPatient()
	{
		return patient;
	}
	
	public int getAppointmentNumber()
	{
		return appointment_number;
	}
	
	public boolean equals(Object appointment)
	{
		return this.appointment_number == ((Appointment)appointment).appointment_number;
	}
	
	public int hashCode()
	{
		return appointment_number;
	}
	
}
