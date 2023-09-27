
/*
 * Thread for a Doctor Profile
 * Starts if a Doctor profile is opened from the Home Menu
 */
public class DoctorThread extends Thread {
	
	public void run()
	{
		short doctor_id = 0;
		boolean validity_flag = true;
		
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
		
		Doctor doctor = new Doctor(doctor_id);
		
		if(Aggregation.doctor_list.containsKey(doctor))
		{
			doctor = Aggregation.doctor_list.get(doctor);
			
			if(doctor.getSlot() == null)
			{
				System.out.println("You haven't chosen a slot yet!!");
				doctor.chooseSlot();
			}
			Main.aggregator.doctorMenu(doctor);
			
		}
		else
		{
			System.out.println("Doctor does not exist!");
			System.out.println("Please talk to your hospital authorities to create a profile for you");
			System.out.println("\nReturning to main menu!!!");
		}
	
	}
	
}
