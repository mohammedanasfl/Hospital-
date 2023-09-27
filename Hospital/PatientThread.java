
/*
 * Thread for a Patient/User Profile
 * Starts if a Patient profile is opened from the Home Menu
 */
public class PatientThread extends Thread {
	
	public void run()
	{
		int choice = 0;
		boolean menu_flag = true;
		
		while(menu_flag)
		{
			boolean input_validity_flag = true;
			
			while(input_validity_flag)
			{
				System.out.println("\n\n1. View an already existing ID");
				System.out.println("2. Create a new ID");
				System.out.println("0. Return to Main Menu");
				System.out.print("Enter your choice: ");
				try
				{
					choice = Integer.parseInt(Main.sc.next());
					input_validity_flag = false;
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("\nPlease Enter a valid choice!!");
				}
			}
			
			switch(choice)
			{
			case 1:
				System.out.print("\nEnter Patient ID: ");
				int ID = 0;
				boolean validity_flag = true;
				while(validity_flag)
				{
					try
					{
						ID = Integer.parseInt(Main.sc.next());
						validity_flag = false;
					}
					catch(NumberFormatException nfe)
					{
						System.out.print("\nPlease Enter a valid ID: ");
					}
				}
				
				menu_flag = !Main.aggregator.getPatient(ID);
				break;
			case 2:
				Patient patient = Main.aggregator.addPatient();
				
				if(patient != null)
				{
					System.out.println("ID Created successfully!!");
					Main.aggregator.patientMenu(patient);
					menu_flag = false;
				}
				else
					System.out.println("Patient already exists!!!");
				
				break;
			case 0:
				System.out.println("\n\nReturning to main menu!!!");
				menu_flag = false;
				break;
			default:
				System.out.println("Please Enter a valid input!!");
			}
			
			
		}
	}

}
