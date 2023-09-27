
/*
 * Thread for a Hospital profile
 * Starts if a Hospital profile is opened from the Home Menu
 */
public class HospitalThread extends Thread {
	
	public void run()
	{
		int choice = 0;
		boolean menu_flag = true;
		while(menu_flag)
		{
			boolean input_validity_flag = true;
		
			while(input_validity_flag)
			{
				System.out.println("\n\n1. Create a  new Hospital");
				System.out.println("2. Open already existing hospital");
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
				Hospital hosp = Main.aggregator.addHospital();
				if(hosp != null)
				{
					System.out.println("\nHospital Added Successfully!!");
					Main.aggregator.hospitalMenu(hosp);
					menu_flag = false;
				}
				else
					System.out.println("\nHospital already exists");
				break;
				
			case 2:
				System.out.print("\nEnter Hospital ID: ");
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
				
				menu_flag = !Main.aggregator.getHospital(ID);
				break;
			case 0:
				System.out.println("\n\nReturning to main menu!!!");
				menu_flag = false;
				break;
			default:
				System.out.println("\nPlease Enter a valid choice!!");
			}
		}
	}

}
