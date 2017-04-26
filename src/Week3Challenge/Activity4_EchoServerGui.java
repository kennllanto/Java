package Week3Challenge;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Activity4_EchoServerGui extends JFrame {

	private JPanel contentPane;
	private static Activity4_EchoServer server;
	private static Thread thread;
	private static JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Activity4_EchoServerGui frame = new Activity4_EchoServerGui();
					frame.setVisible(true);
					server = new Activity4_EchoServer();
			        
			        thread = new Thread(server);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Activity4_EchoServerGui() {
		setTitle("ChatServer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.setBounds(5, 5, 424, 23);
		btnStartServer.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	        	thread.start();
	        }
	    });
		contentPane.add(btnStartServer);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 39, 429, 217);
		contentPane.add(textArea);
	}
	
	public static void UpdateText(String textToDisplay) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					textArea.append(textToDisplay + "\n");

				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

}
