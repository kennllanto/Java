package Week4Challenge;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.*;
public class UDPEchoClient implements Runnable
{
	private static InetAddress host;
	private static final int PORT = 1234;
	private static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte[] buffer;
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
			UDPEchoClientGUI.UpdateText("Host ID not found!");
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		try 
		{
			accessServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void accessServer() throws Exception
	{
		try
		{
			//Step 1...
			datagramSocket = new DatagramSocket();
			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);
			String message="", response="";
			do
			{			
				message = userInput;
				if (!userInput.equals(""))
				{
					outPacket = new DatagramPacket(
					message.getBytes(),
					message.length(),
					host,PORT); //Step 2.
					//Step 3...
					datagramSocket.send(outPacket);
					UDPEchoClientGUI.UpdateText(message);
					buffer = new byte[256]; //Step 4.
					inPacket =
					new DatagramPacket(
					buffer, buffer.length);//Step 5.
					//Step 6...
					datagramSocket.receive(inPacket);
					response =
					new String(inPacket.getData(), 0, inPacket.getLength()); //Step 7.
					System.out.println("\nSERVER> "+response);
					UDPEchoClientGUI.UpdateText("\nSERVER> "+response);
					userInput ="";
				}
				Thread.sleep(100);
			}while (!message.equals("***CLOSE***"));
			userEntry.close();
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			UDPEchoClientGUI.UpdateText("\n* Closing connection... *");
			System.out.println("\n* Closing connection... *");
			datagramSocket.close(); //Step 8.
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
	
	public static void Close() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				System.out.println("\n* Closing connection... *");
				datagramSocket.close(); //Step 8.
			}
		});
	}
}