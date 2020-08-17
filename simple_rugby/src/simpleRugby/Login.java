package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;


/**
 * <h1>Login</h1>
 * This JFrame class is for a user to enter their username and password to gain access to the app. When the user presses the login button
 * validation will occur to ensure the user has entered a valid username and the password entered matches the username.
 * <p>
 * The username will be stored in a public variable in Controller and Menu opened, sending the user type of the user to provide relevant menu.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame {
	
	//setup variables to be used by class
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private int attempts = 0;
	private Controller theController;

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public Login(Controller cont) {
		
		theController = cont;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(339, 137, 96, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(339, 209, 96, 20);
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Enter Username");
		lblUsername.setOpaque(true);
		lblUsername.setBackground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setBounds(205, 140, 96, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Enter Password");
		lblPassword.setBackground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setOpaque(true);
		lblPassword.setBounds(205, 212, 96, 14);
		contentPane.add(lblPassword);
		
		/**
		 * When Login button is clicked check that a username and password has been entered
		 * <p>
		 * If username and password entered. Validate to ensure that a valid username is entered and check the password matches User password.
		 * If valid and matching, open menu, sending usertype of user and setting public username variable in Model to username of user.
		 * <p>
		 * if no information entered, invalid message displays. If username and password don't match a user, message displays saying invalid.
		 * If more than 3 attempts have been made and invalid, app closes.
		 * 
		 */
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String pass = "";
				for (int i = 0; i < txtPassword.getPassword().length; i++) {
					pass += txtPassword.getPassword()[i];
				}
				//check to ensure a username and password entered
				try {
					theController.checkString(username);
					theController.checkString(pass);
					
					
					if (theController.model.getUsers().containsKey(username)) {
						char[] enteredPass = txtPassword.getPassword();
						char[] correctPass = theController.model.getUsers().get(username).getPassword().toCharArray();
						if (Arrays.equals(enteredPass, correctPass)) {
							//open menu sending userType and theController. dispose of current JFrame
							theController.model.setUsername(username);
							Menu m = new Menu(theController.model.getUsers().get(username).getUserType(), theController);
							
							m.setVisible(true);
							dispose();
						}
						else {
							if (attempts < 3) {
								theController.messageDialogue("Invalid password");
								attempts++;
							}
							else {
								theController.messageDialogue("Too many attempts, closing app");
								dispose();
							}
						}
					}
					else {
						if (attempts < 3) {
							theController.messageDialogue("Invalid username");
							attempts++;
						}
						else {
							theController.messageDialogue("Too many attempts, closing app");
							dispose();
						}
					}					
				
				
				}catch(Exception m) {
					theController.messageDialogue(m.toString()+ ", ensure username and password have been entered");
					if (attempts < 3) {
						attempts++;
					}
					else {
						theController.messageDialogue("Too many attempts, closing app");
						dispose();
					}
				}
			}
		});
		btnLogin.setBounds(277, 258, 89, 23);
		contentPane.add(btnLogin);
		
		//Import image, set to desired dimensions and set background label to image
		ImageIcon bg = new ImageIcon("rugby-players-4790343_1920.jpg");
		Image image = bg.getImage();
		Image newimg = image.getScaledInstance(643, 461, Image.SCALE_SMOOTH);
		bg = new ImageIcon(newimg);
		JLabel background = new JLabel(bg);
		background.setOpaque(true);
		
		background.setBounds(0, 0, 643, 461);
		contentPane.add(background);
	}
}
