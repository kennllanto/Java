package Week3Challenge;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Activity2_FindMyIPAddressGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Activity2_FindMyIPAddressGUI frame = new Activity2_FindMyIPAddressGUI();
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
	public Activity2_FindMyIPAddressGUI() {
		setTitle("Find My IP Address GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 112);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP Address: ");
		lblNewLabel.setBounds(10, 51, 82, 14);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(78, 45, 242, 20);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("Get my IP Address");
		btnNewButton.setBounds(32, 11, 255, 29);
		btnNewButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	            //Execute when button is pressed
	            try 
	            {
					InetAddress address = InetAddress.getLocalHost();
					textPane.setText(address.toString());						
				} 
	            catch (UnknownHostException e1) 
	            {
					e1.printStackTrace();
				}
	        }
	    }); 
		contentPane.add(btnNewButton);
		

	}

}
