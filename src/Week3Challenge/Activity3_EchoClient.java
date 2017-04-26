package Week3Challenge;
//TCP Echo Client
//Kenneth Llanto
//u3161446

import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.*;
public class Activity3_EchoClient implements Runnable
{
	private static InetAddress host;
	private static final int PORT = 1234;
	private static String userInput ="";
	@Override
	public void run() 
	{
		try
		{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		accessServer();
	}
	private static void accessServer()
	{
		Socket link = null; //Step 1.
		try
		{
			link = new Socket(host,PORT); //Step 1.
			Scanner input = new Scanner(link.getInputStream()); //Step 2.
			PrintWriter output = new PrintWriter(link.getOutputStream(),true); //Step 2.
			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);
			String message = "", response = "";
			do
			{
				if(!userInput.equals(""))
				{
					Activity3_EchoClientGui.UpdateText("Enter message: ");
					System.out.print("Enter message: ");
					message = userInput;
					userInput = "";
					output.println(message); //Step 3.
					Activity3_EchoClientGui.UpdateText(message);
					response = input.nextLine(); //Step 3.
					System.out.println("\nSERVER> " + response);
					Activity3_EchoClientGui.UpdateText("\nSERVER> " + response);
				}
				Thread.sleep(100);
			}while (!message.equals("***CLOSE***"));
			input.close();
			userEntry.close();
		}
		catch(Exception e)
		{
			System.out.print("Error connectiong to server:" + e);
		}
		finally
		{
			try
			{
				System.out.println("\n* Closing connection... *");
				link.close(); //Step 4.
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
	public static void GetUserInput(String textToDisplay) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					userInput = textToDisplay;

				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
