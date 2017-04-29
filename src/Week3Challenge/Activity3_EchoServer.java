package Week3Challenge;
import java.awt.EventQueue;
//TCP Echo Server
//Kenneth Llanto
//u3161446
//Server that echoes back client's messages.
//At end of dialogue, sends message indicating number of
//messages received. Uses TCP.
import java.io.*;
import java.net.*;
import java.util.*;

public class Activity3_EchoServer implements Runnable
{
	private static ServerSocket servSock;
	private static final int PORT = 1234;
	public static Socket link;
	
	@Override
	public void run() 
	{
		System.out.println("Opening port...\n");
		Activity3_EchoServerGui.UpdateText("Opening port...\n");
		try
		{
			servSock = new ServerSocket(PORT); //Step 1.
		}
		catch(IOException ioEx)
		{
			Activity3_EchoServerGui.UpdateText("Unable to attach to port!");
			System.out.println("Unable to attach to port!");
			System.exit(1);
		}
		do
		{
			handleClient();
		}while (true);
	}
	
	private static void handleClient()
	{
		link = null; //Step 2.
		try
		{
			link = servSock.accept(); //Step 2.
			Scanner input = new Scanner(link.getInputStream());//Step 3.
			PrintWriter output = new PrintWriter( link.getOutputStream(),true); //Step 3.
			int numMessages = 0;
			String message = input.nextLine(); //Step 4.
			while (!message.equals("***CLOSE***"))
			{
				System.out.println("Message received.");
				Activity3_EchoServerGui.UpdateText("Message received.");
				numMessages++;
				output.println("Message " + numMessages + ": " + message); //Step 4.
				message = input.nextLine();
			}
				Activity3_EchoServerGui.UpdateText(numMessages + " messages received.");
				output.println(numMessages + " messages received.");//Step 4.
				input.close();
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println("\n* Closing connection... *");
				Activity3_EchoServerGui.UpdateText("\n* Closing connection... *");
				link.close(); //Step 5.
				servSock.close();
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
			
		}
	}
	public static void Close() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					System.out.println("\n* Closing connection... *");
					Activity3_EchoServerGui.UpdateText("\n* Closing connection... *");
					link.close(); //Step 5.
					servSock.close();
				}
				catch(IOException ioEx)
				{
					System.out.println("Unable to disconnect!");
					System.exit(1);
				}
			}
		});
	}
}
