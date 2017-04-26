package Week3Challenge;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.net.*;

@SuppressWarnings("serial")
public class Activity1_IPFinderGUI extends JFrame 
{

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Activity1_IPFinderGUI frame = new Activity1_IPFinderGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Activity1_IPFinderGUI() {
		setTitle("IP Finder GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 108);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Host Name: ");
		lblNewLabel.setBounds(10, 11, 111, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IP Address:");
		lblNewLabel_1.setBounds(10, 42, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(85, 36, 222, 20);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(317, 11, 76, 46);
		btnNewButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when button is pressed
	            try 
	            {
					InetAddress address = InetAddress.getByName(textField.getText());
					if(textField.getText() == "")
					{
						textPane.setText("No Input detected!");						
					}
					else
					{
						textPane.setText(address.toString());						
					}

				} 
	            catch (UnknownHostException e1) 
	            {
	            	textPane.setText("Could not find " + textField.getText());
					System.out.println("Could not find " + textField.getText());
					e1.printStackTrace();
				}
	        }
	    });    
		contentPane.add(btnNewButton);
		
		
		
		textField = new JTextField();
		textField.setBounds(115, 11, 192, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
