import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Contains details of a Hospital
 * also contains details of the Doctors associated with the hospital
 */
public class Hospital 
{	
	private int hospital_id;
	private String hospital_name;
	private String phone_number;
	private final ArrayList<String> slots = new ArrayList<>();;
	private final HashMap<Doctor, Doctor> doctor_list = new HashMap<>();
	
	Hospital(){
	}
	
	Hospital(int ID)
	{
		hospital_id = ID;
	}
	
	// To set all the details of a Hospital Profile
	public void setHospitalDetails()
	{
		Main.sc.nextLine();
		System.out.print("Enter Hospital Name: ");
		while(true)
		{
			hospital_name = Main.sc.nextLine();
			if(hospital_name.length() == 0)
				continue;
			break;
		}
		System.out.print("Enter Hospital Phone Number: ");
		phone_number = Main.sc.next();
		Main.sc.nextLine();
		updateSlots();
	}
	
	// To fix the Visiting hour options for the doctors to choose from, exactly 4 options are allowed
	public void setSlots()
	{
		int slot_cnt = 5;
		System.out.println("Enter four different slots for your doctors to choose from: ");
		
		while(--slot_cnt != 0)
		{
			System.out.println("Slot " + (5 - slot_cnt) + ": ");
			
			while(true)
			{
				String slot = Main.sc.nextLine();
				if(slot.length() == 0)
					continue;
				slots.add(slot);
				break;
			}
		}
	}
	
	// To display the slot options for Doctors to choose from
	public int displaySlots()
	{
		System.out.println("Available slots: \n");
		int cnt = 0;
		for(String slot: slots)
		{
			System.out.printf( "Slot %d: %s\n", ++cnt, slot );
		}
		return cnt;
	}
	
	/*
	 *  To Create profile for a new Doctor and add it to the list
	 *  Return true if the new profile was created successfully, and 
	 *  returns false if the profile already existed
	 */
	public boolean addDoctor()
	{
		Doctor doctor = new Doctor();
		boolean validity_flag = true;
		short doctor_id = 0;
		while(validity_flag)
		{
			try
			{
				System.out.print("\n\nEnter Hospital issued Doctor ID: ");
				String ID = Main.sc.next();
				if(ID.length() > 4)
				{
					System.out.println("\nDoctor ID can only be upto 4 digits!!\n");
					continue;
				}
				doctor_id = Short.parseShort(ID);
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("\nDoctor ID must be a number!!\n\n");
			}
		}
		if(doctor_list.containsKey(new Doctor(doctor_id)))
			return false;
			
		doctor.setDoctorDetails(this, doctor_id);
		Aggregation.doctor_list.put(doctor, doctor);
		doctor_list.put(doctor, doctor);
		return true;
	}
	
	// To remove a Doctor profile from the hospital
	public boolean removeDoctor()
	{
		int ID = 0;
		boolean validity_flag = true;
		
		while(validity_flag)
		{
			try
			{
				System.out.print("\nEnter the Doctor ID to remove: ");
				ID = Integer.parseInt(Main.sc.next());
				validity_flag = false;
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Doctor ID must be a number!!");
			}
		}
		
		Doctor temp_doctor = new Doctor(ID);
		
		if(!doctor_list.containsKey(temp_doctor))
		{
			System.out.println("\nDoctor not present in the hospital!\n");
			return false;
		}
		
		temp_doctor = doctor_list.get(temp_doctor);
		if(temp_doctor.getBookedApp() != 0)
		{
			System.out.println("\nThis doctor has unfinished appointments!! Please try later\n" );
			return false;
		}
		
		doctor_list.remove(temp_doctor);
		Aggregation.doctor_list.remove(temp_doctor);
		return true;
		
	}
	
	// To display the details of all Doctors associated with teh hospital
	public void displayAllDoctors()
	{
		Iterator<Doctor> doctor = doctor_list.values().iterator();
		while(doctor.hasNext())
		{
			System.out.println(doctor.next().getDoctorDetails() + "\n");
		}
		System.out.println("\nEnd of Record");
	}
	
	// To update visiting hour options
	public void updateSlots()
	{
		if(slots.size() == 0)
			setSlots();
		else
		{
			int slot_count = displaySlots();
			int slot_number = 0;
			boolean validity_flag = true;
			while(validity_flag)
			{
				try
				{
					System.out.println("Enter the slot number to change: ");
					slot_number = Integer.parseInt(Main.sc.next());
					if(slot_number > slot_count)
					{
						System.out.println("Please enter a valid slot number!!");
						continue;
					}
					validity_flag = false;					
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Please enter a valid slot number!!");
				}
			}
			System.out.print("\nEnter the new slot: ");
			Main.sc.nextLine();
			
			while(true)
			{
				String slot = Main.sc.nextLine();
				if(slot.length() == 0)
					continue;
				slots.set(slot_number-1, slot );	
				break;
			}
			System.out.println("\nSlot updated successfully!!");
		}
	}
	
	// To display the details of Doctors that are eligible for appointments
	public ArrayList<Doctor> showAvailableDoctors()
	{
		ArrayList<Doctor> available_doctors = new ArrayList<>();
		for(Doctor doctor: doctor_list.values())
		{
			if(doctor.getSlot() != null && doctor.getAllowedApp() > doctor.getBookedApp())
			{
				available_doctors.add(doctor);
				System.out.println(doctor.getDoctorDetails());
				System.out.println();
			}
		}
		return available_doctors;
	}
	
	// To get all the details of the Hospital
	public String getHospitalDetails()
	{
		String details = "\nHospital ID: " + hospital_id;
		details += "\nHospital name: " + hospital_name;
		details += "\nPhone number: " + phone_number;
			
		return details;
	}
	
	public String getSlot(int slot)
	{
		return slots.get(slot-1);
	}
	
	public int getHospitalId()
	{
		return hospital_id;
	}
	
	public String getHospitalName()
	{
		return hospital_name;
	}
	
	public boolean equals(Object hosp) 
	{
		return this.hospital_id == ((Hospital)hosp).hospital_id;
	}
	
	public int hashCode()
	{
		return hospital_id;
	}

}
