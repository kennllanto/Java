package Week3Challenge;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Activity3_EchoServerGui extends JFrame implements WindowListener{

	private JPanel contentPane;
	private static Activity3_EchoServer server;
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
					Activity3_EchoServerGui frame = new Activity3_EchoServerGui();
					frame.setVisible(true);
					server = new Activity3_EchoServer();  
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
	public Activity3_EchoServerGui() {
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

	@Override
	public void windowClosing(WindowEvent e) {
				if(server != null) {
					try 
					{
						Activity3_EchoServer.link = null; 
						System.exit(0);
					}
					catch(Exception eClose) 
					{
						System.exit(1);
					}
					server = null;
				}
				// dispose the frame
				dispose();
				System.exit(0);	
	}

	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
