package Week4Challenge;

//Server that echoes back client's messages.
//At end of dialogue, sends message indicating number of
//messages received. Uses datagrams.
import java.io.*;
import java.net.*;
public class UDPEchoServer implements Runnable
{
	private static final int PORT = 1234;
	public static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte[] buffer;
	@Override
	public void run()
	{
		System.out.println("Opening port...\n");
		UDPEchoServerGUI.UpdateText("Opening port...\n");
		try
		{
			datagramSocket = new DatagramSocket(PORT); //Step 1.
		}
		catch(SocketException sockEx)
		{
			UDPEchoServerGUI.UpdateText("Unable to attach to port!");
			System.out.println("Unable to attach to port!");
			System.exit(1);
		}
		handleClient();
	}
	
	private static void handleClient()
	{
		try
		{
			String messageIn,messageOut;
			int numMessages = 0;
			do
			{
				buffer = new byte[256]; //Step 2.
				inPacket = new DatagramPacket( buffer, buffer.length); //Step 3.
				datagramSocket.receive(inPacket);//Step 4.
				InetAddress clientAddress = inPacket.getAddress(); //Step 5.
				int clientPort = inPacket.getPort(); //Step 5.
				messageIn = new String(inPacket.getData(),0,inPacket.getLength()); //Step 6.
				System.out.println("Message received.");
				UDPEchoServerGUI.UpdateText("Message received.");
				numMessages++;
				messageOut = "Message " + numMessages
				+ ": " + messageIn;
				outPacket =
				new DatagramPacket(messageOut.getBytes(),
				messageOut.length(),clientAddress,
				clientPort); //Step 7.
				datagramSocket.send(outPacket); //Step 8.
			}while (!messageIn.equals("***CLOSE***"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally //If exception thrown, close connection.
		{
			System.out.println("\n* Closing connection... *");
			UDPEchoServerGUI.UpdateText("\n* Closing connection... *");
			datagramSocket.close(); //Step 9.
		}
	}
}