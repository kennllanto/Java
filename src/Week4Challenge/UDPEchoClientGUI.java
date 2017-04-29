package Week4Challenge;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UDPEchoClientGUI extends JFrame implements WindowListener{

	private JPanel contentPane;
	private JTextField textField;
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
				try {
					UDPEchoClientGUI frame = new UDPEchoClientGUI();
					frame.setVisible(true);
					UDPEchoClient client = new UDPEchoClient();
			        
			        Thread thread = new Thread(client);
			        
			        thread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UDPEchoClientGUI() {
		setTitle("UDP Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(333, 5, 96, 23);
		btnNewButton.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e)
	        {
	        	System.out.println("\n* Closing connection... *");
				UDPEchoClient.Close();
				System.exit(1);
	        }
		});
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 6, 313, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e) 
		    {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					JTextField textField = (JTextField) e.getSource();
			        String text = textField.getText();
			        UDPEchoClient.GetUserInput(text);
			        textField.setText("");
                }       
		    }
		});
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 37, 419, 466);
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
				try 
				{
					UDPEchoClient.Close();
					System.exit(1);
				}
				catch(Exception eClose) 
				{
					System.exit(1);
				}
				// dispose the frame
				dispose();		
	}

	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
