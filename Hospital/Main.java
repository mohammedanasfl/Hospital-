import java.util.Scanner;

/*
 * Driver Class of the program
 * Displays menu, and creates different threads for different user profiles
 */

public class Main 
{
	// Object of the Aggregation class
	static Aggregation aggregator = new Aggregation(); 
	
	// Scanner object used to get input throughout the program
	public static Scanner sc = new Scanner(System.in);
	
	// Home Menu of the application
	static void homeScreen()
	{
		System.out.println("Are you a: ");
		System.out.println("1. Hospital\n2. Doctor\n3. Patient\n0. Exit");
		System.out.print("Enter your choice: ");
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Scanner sc = new Scanner(System.in);
		boolean main_flag = true;
		
		while(main_flag)
		{
			int choice = 0;
			boolean main_validity_flag = true;
			
			while(main_validity_flag)
			{
				try
				{
					System.out.print("\n\n");
					Main.homeScreen();
					choice = Integer.parseInt(Main.sc.next());
					main_validity_flag = false;
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("\nPlease Enter a valid input!!\n");
				}
			}
			
			switch(choice)
			{
			// Creates a thread for hospital page
			case 1: 
				HospitalThread ht = new HospitalThread();
				ht.start();
				ht.join();
				break;
			// Creates a thread for Doctor page
			case 2:
				DoctorThread dt = new DoctorThread();
				dt.start();
				dt.join();
				break;
			// Creates a thread for Patient page
			case 3:
				PatientThread pt = new PatientThread();
				pt.start();
				pt.join();
				break;
			case 0:
				System.out.println("Exiting...!!!");
				Thread.sleep(500);
				System.out.println("Exited!!!");
				main_flag = false;
				break;
			default:
				System.out.print("Please enter a valid input!!!");
			}	
		}
		sc.close();
	}
}
