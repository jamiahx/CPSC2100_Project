import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class RegestrationFormTester {

	public static void main (String [] args) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your file path. Do not leave a single character out. For example: /Users/emmaperez/Desktop/data(1).txt");
		String fileName = scanner.nextLine();
		System.out.println("You selected this file: " + fileName);
		
		File file = new File(fileName);

		Scanner s = new Scanner(file).useDelimiter(", ");
	
		ArrayList<RegestrationForm> customerInfo = new ArrayList<RegestrationForm>();
		
		while(s.hasNext())
		{
			ArrayList<String> info = new ArrayList<String>();
			for (int i = 0; i < 7; i++)
			{
				info.add(s.next());
			}
			customerInfo.add(new RegestrationForm(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5), info.get(6)));
		}
		for (int i = 0; i < customerInfo.size(); i++)
		{
			System.out.println(customerInfo.get(i).toString());
		}

		
	}
}
