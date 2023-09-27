import java.util.ArrayList;

/*
 * Contains details of an individual Doctor and methods for all the functions that
 * needs to be done by a doctor
 */
public class Doctor 
{	
	private Hospital associated_hospital;
	private short doctor_id;
	private String doctor_name;
	private byte age;
	private String phone_number;
	private final ArrayList<String> specializations = new ArrayList<String>();
	private String slot;
	private byte num_of_allowed_appointments = 2;
	private byte num_of_booked_appointments = 0;
	private final ArrayList<Appointment> upcoming_appointments = new ArrayList<>();
	private final ArrayList<Appointment> finished_appointments = new ArrayList<>();
	
	Doctor(){
	}
	
	Doctor(int ID)
	{
		doctor_id = (short)ID;
	}
	
	// Setter for all the details of an individual Doctor
	public void setDoctorDetails(Hospital associated_hospital, short doctor_id)
	{	
		this.doctor_id = doctor_id;
		Main.sc.nextLine();
		System.out.print("Enter Doctor Name: ");
		doctor_name = Main.sc.nextLine();
		
		System.out.print("Enter Doctor Age: ");
		age = Main.sc.nextByte();
		
		System.out.print("Enter Doctor Phone Number: ");
		phone_number = Main.sc.next();
		
		this.associated_hospital = associated_hospital;
		
		addSpecialization();
	}
	
	// Method to add specializations of a doctor to the doctor's profile
	public void addSpecialization()
	{
		boolean flag = true;
		String sp;
		while(flag)
		{
			Main.sc.nextLine();
			System.out.print("Enter a specialization: ");
			sp = Main.sc.nextLine();
			specializations.add(sp);
			
			System.out.print("Add more Specialization? (y/n): ");
			while(true)
			{
				char choice = Main.sc.next().charAt(0);
				if(choice == 'n' || choice == 'N')
				{
					flag = false;
					break;
				}
				else if(choice == 'y' || choice == 'Y')
				{
					break;
				}
				else
					System.out.println("Please enter a valid input!!");
			}
		}
	}
	
	// To choose Visiting Hours of the Doctor
	public void chooseSlot()
	{
		associated_hospital.displaySlots();
		System.out.print("Choose a slot: ");
		int slot = Main.sc.nextInt();
		
		this.slot = associated_hospital.getSlot(slot);
	}
	
	/*
	 *  To choose number of allowed patients that can visit the Doctor during
	 *  the visiting hours
	 */
	public void chooseNoOfPatients()
	{
		System.out.println("Current choice of appointments: " + num_of_allowed_appointments);
		System.out.print("Enter the number of patients you are willing to see in your slot: ");
		boolean validity_flag = true;
		
		while(validity_flag)
		{
			try
			{
				num_of_allowed_appointments = Byte.parseByte(Main.sc.next());
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.print("Please enter a valid number: ");
			}
		}
		
	}
	
	// To add a new appointment to the doctor
	public void addAppointment(Appointment app)
	{
		upcoming_appointments.add(app);
	}
	
	/*
	 * To mark an appointment as finished
	 * Changes reflect in the patient's profile also
	 */
	public void markFinishAppointment()
	{
		int app_no = 0;
		int appointments_left = viewUpcomingAppointments();
		boolean validity_flag = true;
		
		if(appointments_left == 0)
		{
			System.out.println("\nYou have no Appointments!\n");
			validity_flag = false;
		}
		
		while(validity_flag)
		{
			try
			{
				System.out.print("Choose the appointment to mark as finished: ");
				app_no = Integer.parseInt(Main.sc.next());
				if( ! upcoming_appointments.contains( new Appointment(app_no) ) )
				{
					System.out.println("Please Enter a valid choice!!");
					continue;
				}
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Please Enter a valid choice!!");
			}
		}
		if(appointments_left != 0)
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
			
			finished_appointments.add(temp_app);
			temp_app.getPatient().markFinishAppointment(temp_app);
			upcoming_appointments.remove(temp_app);
			this.num_of_booked_appointments -= 1;
			System.out.println("\nMarked as finished!!\n");
		}
	} 
	
	/*
	 *  To view the list of all the upcoming appointments
	 *  Return the count of upcoming appointments
	 */
	public int viewUpcomingAppointments()
	{
		int cnt = 0;
		System.out.println("\n\nUpcoming Appointments: ");
		for(Appointment app: upcoming_appointments)
		{
			System.out.println(  app.patientDetails() + "\n");
			++cnt;
		}
		System.out.println("\n\nEnd of Records!");
		return cnt;
	}
	
	// TO view the list of all the finished appointments
	public void viewFinishedAppointments()
	{
		System.out.println("\n\nCompleted Appointments: ");
		for(Appointment app: finished_appointments)
			System.out.println( app.patientDetails() + "\n" );
		System.out.println("\n\nEnd of Records!");
	}
	
	// Used to remove an appointment from profile, when a patient cancels an appointment
	public void removeAppointment(Appointment app)
	{
		upcoming_appointments.remove(app);
		this.num_of_booked_appointments -= 1;
	}
	
	// Increases the appointment count when a new appointment is made
	public void incNoOfBookedApp()
	{
		num_of_booked_appointments += 1;
	}
	
	// Gets all the details of the doctor in a string form
	public String getDoctorDetails()
	{
		String details = "Doctor ID: " + doctor_id;
		details += "\nDoctor name: " + doctor_name;
		details += "\nDoctor Phone Number: " + phone_number;
		details += "\nDoctor age: " + age;
		details += "\nDoctor visiting hours: " + getSlot();
		details += "\nDoctor Specializations: ";
		
		for(String spec: specializations)
			details += "\n   " + spec;
		
		return details;
	}
	
	public String getSlot()
	{
		return slot;
	}
	
	public short getDoctorId()
	{
		return doctor_id;
	}
	
	public byte getAllowedApp()
	{
		return num_of_allowed_appointments;
	}
	
	public byte getBookedApp()
	{
		return num_of_booked_appointments;
	}
	
	public boolean equals(Object doc)
	{
		return this.doctor_id == ((Doctor)doc).doctor_id; 
	}
	
	public int hashCode()
	{
		return doctor_id;
	}
}
