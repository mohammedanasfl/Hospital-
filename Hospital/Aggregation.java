import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class aggregates the Hospital, Doctor and Patient classes together to form the working application
 * Maintains the pages for Hospital and Patient, and keeps a list of all the hospitals, Patients and Doctors
 */

public class Aggregation 
{
	public static final HashMap<Hospital, Hospital> hospital_list = new HashMap<>();
	public final HashMap<Patient, Patient> patient_list = new HashMap<>();
	public static  final HashMap<Doctor, Doctor> doctor_list = new HashMap<>();
	
	public static int patient_id = 0;
	public static int appointment_number = 0;
	
	// Method to create a new hospital profile and to check the validity of the Hospital
	public Hospital addHospital()
	{
		Hospital hospital;
		boolean validity_flag = true;
		int hospital_id = 0;
		
		while(validity_flag)
		{
			try
			{
				System.out.print("\n\nEnter Government issued hospital ID: ");
				hospital_id = Integer.parseInt(Main.sc.next());
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("\n\nHospital ID must be a number!!");
			}
		}
		
		hospital = new Hospital(hospital_id);
		
		if( hospital_list.containsKey(hospital) )
		{
			return null;
		}
		
		hospital.setHospitalDetails();
		hospital_list.put(hospital, hospital);
		return hospital;
	}
	
	// Method to create a new patient profile and to check validity of the new patient
	public Patient addPatient()
	{
		Patient patient = new Patient();
		patient.setPatientDetails();
		if( patient_list.containsKey(patient) )    // CURRENTLY COMPARING ID
		{
			return null;
		}
		patient_list.put(patient, patient);
		return patient;
	}
	
	/*
	 * Checks if the given hospital has a profile or not, and goes to the Home menu
	 * of the hospital profile
	 */
	public boolean getHospital(int ID)
	{
		if(hospital_list.containsKey( new Hospital(ID) ))
		{
			Hospital hosp = hospital_list.get( new Hospital(ID) );
			hospitalMenu(hosp);
			return true;
		}
		else
			System.out.println("Hospital does not exist!!");
		return false;
	}
	
	/*
	 * Checks if the given patient ID has a profile or not, and goes to the Home menu
	 * of the patient profile
	 */
	public boolean getPatient(int ID)
	{
		if(patient_list.containsKey( new Patient(ID) ))
		{
			Patient patient = patient_list.get( new Patient(ID) );
			patientMenu(patient);
			return true;
		}
		else
			System.out.println("User ID does not exist!!");
		return false;
	}
	
	
	/*
	 * Home menu of the Hospital Profile
	 * Has all the basic activities of a Hospital listed
	 */
	public void hospitalMenu(Hospital hosp)
	{
		int choice = 0;
		boolean menu_flag = true;
		while(menu_flag)
		{
			boolean input_validity_flag = true;
			while(input_validity_flag)
			{
				System.out.print( "\n\n\n-------" + hosp.getHospitalName() + "-------\n\n" );
				System.out.println("1. Add a Doctor");
				System.out.println("2. Delete a doctor");
				System.out.println("3. Display all Doctors");
				System.out.println("4. Update Visiting Hours");
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				try
				{
					choice = Integer.parseInt(Main.sc.next());
					input_validity_flag = false;
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("\nPlease enter a valid choice!!");
				}
			}
				
			switch(choice)
			{
			case 1:
				if( hosp.addDoctor() )
					System.out.println( "\n\nDoctor added Successfully!! " );
				else
					System.out.println( "\n\nDoctor already Exists" );
				break;
			case 2:
				if( hosp.removeDoctor() )
					System.out.print("\n\nRemoved Successfully");
				break;
			case 3:
				hosp.displayAllDoctors();
				break;
			case 4:
				hosp.updateSlots();
				break;
			case 0:
				System.out.println("Returning to main menu!!!\n");
				Main.sc.nextLine();
				menu_flag = false;
				break;
			default:
				System.out.println("\n\nPlease Enter a valid choice!!!");
			}
		}
	}
	
	/*
	 * Home menu of the Doctor profile
	 * Has all the basic operations of a doctor listed
	 */
	public void doctorMenu(Doctor doctor)
	{
		int choice = 0;
		boolean menu_flag = true;
		
		while(menu_flag)
		{
			boolean validity_flag = true;
			while(validity_flag)
			{
				System.out.println("\n\n1. View Upcoming Appointments");
				System.out.println("2. View Finished Appointments");
				System.out.println("3. Mark an Appointment as finished");
				System.out.printf("4. Change number of patients per slot "
						+ "(Current: %d)\n", doctor.getAllowedApp());
				System.out.printf("5. Change Visiting Hours (Current: %s)\n", doctor.getSlot());
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				
				try
				{
					choice = Integer.parseInt(Main.sc.next());
					validity_flag = false;
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Please enter a valid choice!! \n");
				}
			}
			
			switch(choice)
			{
			case 1:
				doctor.viewUpcomingAppointments();
				break;
			case 2:
				doctor.viewFinishedAppointments();
				break;
			case 3: 
				doctor.markFinishAppointment();
				break;
			case 4:
				doctor.chooseNoOfPatients();
				break;
			case 5:
				doctor.chooseSlot();
				break;
			case 0:
				menu_flag = false;
				System.out.println("Returning to main menu...\n");
				break;
			default:
				System.out.println("Please enter a valid choice!! \n");
			}	
		}	
	}
	
	/*
	 * Home menu of the Patient/User Profile
	 * Has all the basic operations of a Patient/User listed
	 */
	public void patientMenu(Patient patient)
	{
		int choice = 0;
		boolean menu_flag = true;
		
		while(menu_flag)
		{
			System.out.println("\nUser ID: " + patient.getPatientId());
			System.out.println("User Name: " + patient.getPatientName());
			System.out.println("\n1. View Upcoming Appointments");
			System.out.println("2. View Completed Appointments");
			System.out.println("3. View Cancelled Appointments");
			System.out.println("4. Book a new Appointment");
			System.out.println("5. Cancel an Appointment");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			boolean validity_flag = true;
			while(validity_flag)
			{
				try
				{
					choice = Integer.parseInt(Main.sc.next());
					validity_flag = false;
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Please enter valid input!!");
				}
			}
			
			switch(choice)
			{
			case 1:
				patient.viewUpcomingAppointments();
				break;
			case 2:
				patient.viewFinishedAppointments();
				break;
			case 3:
				patient.viewCancelledAppointments();
				break;
			case 4:                  
				bookAppointment(patient);
				break;
			case 5:
				patient.cancelAppointment();
				break;
			case 0:
				System.out.println("\n\nReturning to main menu!!!\n");
				menu_flag = false;
				break;
			default:
				System.out.println("\n\nPlease enter a valid input!!");
			}
		}
	}
	
	/*
	 * Method for a Patient to book an appointment with a doctor
	 * Lists all the registered Hospitals
	 * then lists all the registered Doctors from the Hospital
	 * Books an appointment with the selected doctor after checking availability  
	 */
	public void bookAppointment(Patient patient)
	{
		int hospital_cnt = showHospitals();   
		int hosp_id = 0;
		int choice = 0;
		boolean menu_flag = true;
		boolean validity_flag = true;
		
		if(hospital_cnt == 0)
		{
			System.out.println("\nNo Hospitals Available!!\n");
			validity_flag = false;
			menu_flag = false;
		}
		
		while(validity_flag)
		{
			try
			{
				System.out.println("Enter your choice of Hospital ID: ");
				hosp_id = Integer.parseInt(Main.sc.next());
				if(!hospital_list.containsKey( new Hospital(hosp_id) ))
				{
					System.out.println("Please enter a valid ID!!!\n");
					continue;
				}
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Please enter a valid ID!!!\n");
			}
		}
		
		Hospital hospital = hospital_list.get(new Hospital(hosp_id));
		
		while(menu_flag)
		{
			validity_flag = true;
			System.out.println("1. View all available doctors");
			System.out.println("0. Go back to menu");
			System.out.print("Enter your choice: ");
			while(validity_flag)
			{
				try
				{
					choice = Integer.parseInt(Main.sc.next());
					validity_flag = false;
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Please enter a valid choice!!!\n");
				}
			}
			switch(choice)
			{
			case 1:
				ArrayList<Doctor> docs = hospital.showAvailableDoctors();
				int doctor_id = 0;
				validity_flag = true;
				
				if(docs.size() == 0)
				{
					System.out.println("\nNo Doctors Available!!\n");
					validity_flag = false;
				}
				
				while(validity_flag)
				{
					try
					{
						System.out.println("Enter your choice of Doctor's ID: ");
						doctor_id = Integer.parseInt(Main.sc.next());
						validity_flag = false;
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("Please enter a valid ID!!!\n");
					}
				}
				
				if(docs.size() != 0)
				{
					if( docs.contains( new Doctor(doctor_id) ) )
					{
						if( !patient.containsAppointment( new Doctor(doctor_id) ) )
						{
							Doctor doctor = doctor_list.get( new Doctor(doctor_id) );
							new Appointment(patient, doctor);
							doctor.incNoOfBookedApp();
							System.out.println("Appointment Booked Successfully!!");
							System.out.println("Returning to menu!!");
							menu_flag = false;
						}
						else
							System.out.println("You have a pending appointment with this doctor, "
									+ "Please finish that first!!");
					}
					else
						System.out.println("Doctor not available... Try again later!");
			}
				break;
			case 0:
				menu_flag = false;
				break;
			default:
				System.out.println("Please enter a valid choice!!!");
			}
		}
		
	}
	
	/*
	 * Lists all the registered Hospital details, for patients to choose for registration
	 * of appointments
	 */
	public int showHospitals()
	{
		int cnt = 0;
		System.out.println("Hospitals: ");
		for(Hospital hosp: hospital_list.values())
		{
			System.out.println( hosp.getHospitalDetails() + "\n" );
			++cnt;
		}
		return cnt;
	}
	
}
