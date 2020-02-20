/*
This is a test script that checks the Client class and ClientList class.
 */
import java.util.*;
import java.text.*;
import java.io.*;
public class Tester {
	public static String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	private static boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}
	public static void main(String[] s){
	Client c1 = new Client("John", "1", 1500.0F);
	Client c2 = new Client("Kyllie", "2", 2000.0F);
	Client c3 = new Client("Brittany", "3", 100.0F);
	Client c4 = new Client("Carl", "4", 1500.0F);

        if(yesOrNo("Would you like to load the client list"))

	{
		try {
			FileInputStream file = new FileInputStream("ClientData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
		}//end of try()
		catch (IOException ioe) {
			ioe.printStackTrace();
		}//end of catch()
		catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end of catch()
	}//end of if()

	ClientList clientList = ClientList.instance();
        clientList.insertClient(c1);
        clientList.insertClient(c2);
		clientList.insertClient(c3);
		clientList.insertClient(c4);
        System.out.println("Clients we are adding to our list:");
        System.out.println("Name: "+c1.getName());
        System.out.println("Id: "+c1.getId());
        System.out.println("Balance: "+c1.getBalance());
        System.out.println("\nName: "+c2.getName());
        System.out.println("Id: "+c2.getId());
        System.out.println("Balance: "+c2.getBalance());
		System.out.println("\nName: "+c3.getName());
		System.out.println("Id: "+c3.getId());
		System.out.println("Balance: "+c3.getBalance());
		System.out.println("\nName: "+c4.getName());
		System.out.println("Id: "+c4.getId());
		System.out.println("Balance: "+c4.getBalance());

        System.out.println("\nLists of Clients");
	Iterator clients = clientList.getClients();
        while(clients.hasNext())

	{
		System.out.println(clients.next());
	}//end of while()
        if(

	yesOrNo("Would you like to save the client list"))

	{
		try {
			FileOutputStream file = new FileOutputStream("ClientData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(clientList);
		}//end of try()
		catch (IOException ioe) {
			ioe.printStackTrace();
		}//end of catch()
	}//end of if()
}
}