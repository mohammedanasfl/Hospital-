import java.util.ArrayList;

/*
 * Contains details of an individual Patient/user and methods for all the functions that
 * needs to be done by a Patient/User
 */
public class Patient 
{	
	private int patient_id;
	private String patient_name;
	private byte age;
	private String phone_number;
	private ArrayList<Appointment> upcoming_appointments = new ArrayList<>();
	private ArrayList<Appointment> finished_appointments = new ArrayList<>();
	private ArrayList<Appointment> cancelled_appointments = new ArrayList<>();
	
	Patient(){
	}
	
	Patient(int ID){
		this.patient_id = ID;
	}
	
	// Setter for all the details of an individual Patient
	public void setPatientDetails()
	{		
		patient_id = ++Aggregation.patient_id;
		System.out.print("Enter Your Phone Number: ");
		phone_number = Main.sc.next();
		
		Main.sc.nextLine();
		System.out.print("Enter Your Full Name: ");
		patient_name = Main.sc.nextLine();
		
		System.out.print("Enter Your Age: ");
		age = Byte.parseByte(Main.sc.next());
	}
	
	/*
	 * To view the list of all upcoming appointments
	 * Returns the count of upcoming appointments
	 */
	public int viewUpcomingAppointments()
	{
		int cnt = 0;
		System.out.println("\n\nUpcoming Appointments: ");
		for(Appointment app: upcoming_appointments)
		{
			System.out.println( app.doctorDetails() );
			++cnt;
		}
		System.out.println("\n\nEnd of records!");
		return cnt;
	}
	
	// To view the list of all completed appointments
	public void viewFinishedAppointments()
	{
		System.out.println("\n\nCompleted Appointments: ");
		for(Appointment app: finished_appointments)
			System.out.println( app.doctorDetails() + "\n" );
		System.out.println("\n\nEnd of records!");
	}
	
	// To  view the list of all cancelled appointments by the Patient
	public void viewCancelledAppointments()
	{
		System.out.println("\n\nCancelled Appointments: ");
		for(Appointment app: cancelled_appointments)
			System.out.println( app.doctorDetails() + "\n" );
		System.out.println("\n\nEnd of records!");
	}
	
	// To add a new appointment to the list
	public void addAppointment(Appointment app)
	{
		upcoming_appointments.add(app);
	}
	
	// To mark an appointment as finished, done by the doctor
	public void markFinishAppointment(Appointment temp_app)
	{
		finished_appointments.add(temp_app);
		upcoming_appointments.remove(temp_app);
	}	
	
	/*
	 * To cancel a booked appointment
	 * Deletes the appointment from the doctor's profile
	 */
	public void cancelAppointment()
	{
		int app_no = 0;
		int appointment_count = viewUpcomingAppointments();
		boolean validity_flag = true;
		
		if(appointment_count == 0)
		{
			System.out.println("\nYou have no Appointments!!");
			validity_flag = false;
		}
		
		while(validity_flag)
		{
			try
			{
				System.out.print("Enter the appointment ID to cancel: ");
				app_no = Integer.parseInt(Main.sc.next());
				if( ! upcoming_appointments.contains( new Appointment(app_no)  )  )
				{
					System.out.println("Please Enter a valid appointment ID!!");
					continue;
				}
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Please Enter a valid choice!!");
			}
		}
		
		if(appointment_count != 0)
		{
			Appointment temp_app = null;
			for(Appointment app: upcoming_appointments)
			{
				if( app.equals( new Appointment(app_no) ) )
				{
					temp_app = app;
					break;
				}
			}
			
			temp_app.getDoctor().removeAppointment(temp_app);
			cancelled_appointments.add(temp_app);
			upcoming_appointments.remove(temp_app);
			
			System.out.println("\nAppointment Cancelled Successfully\n");
		}
	}
	
	// Checks if any upcoming appointment is associated with the given doctor
	public boolean containsAppointment(Doctor doctor)
	{
		for(Appointment app: upcoming_appointments)
		{
			if( app.getDoctor().equals(doctor) )
				return true;
		}
		
		return false;
	}
	
	// Getter for all the details of the Patient/User
	public String getPatientDetails()
	{
		String details = "Patient ID: " + patient_id;
		details += "\nPatient name: " + patient_name;
		details += "\nPatient Phone Number: " + phone_number;
		details += "\nPatient age: " + age;
		
		return details;
	}
	
	public String getPatientName()
	{
		return patient_name;
	}
	
	public int getPatientId()
	{
		return patient_id;
	}
	
	public boolean equals(Object patient)
	{
		return this.patient_id == ((Patient)patient).patient_id;
	}
	
	public int hashCode()
	{
		return patient_id;
	}
}
