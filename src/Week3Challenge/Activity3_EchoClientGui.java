package Week3Challenge;

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
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Activity3_EchoClientGui extends JFrame {

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
					Activity3_EchoClientGui frame = new Activity3_EchoClientGui();
					frame.setVisible(true);
					Activity3_EchoClient client = new Activity3_EchoClient();
			        
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
	public Activity3_EchoClientGui() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(333, 5, 96, 23);
		btnNewButton.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e)
	        {
	        	//close connection chuchu..
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
			        Activity3_EchoClient.GetUserInput(text);
			        textField.setText("");
                }       
		    }
		});
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 37, 419, 166);
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
