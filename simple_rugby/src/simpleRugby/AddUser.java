package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * 
 * <h1>Add User</h1>
 * This JFrame class is used by admins to add new users to the system. The admin will enter first name, last name, username and password
 * for the user and select a user type of admin or coach from the dropdown.
 * <p>
 * This will be validated to ensure that information is entered into the first name, last name, username, and password textfields and to 
 * ensure that the admin has selected a user type for the new user.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class AddUser extends JFrame {
	//setup variables to be used by class
	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private Controller theController;
	private String[] userTypes = {"Select User Type", "Admin", "Coach"};

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public AddUser(Controller cont) {
		
		theController = cont;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(356, 109, 123, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(356, 158, 123, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(356, 204, 123, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(356, 251, 123, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		//create combobox using userTypes in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(userTypes);
		comboBox.setBounds(356, 298, 123, 22);
		contentPane.add(comboBox);
		
		JLabel lblFirstName = new JLabel("Enter First Name");
		lblFirstName.setOpaque(true);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setBounds(168, 112, 113, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Enter Last Name");
		lblLastName.setOpaque(true);
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setBounds(168, 161, 113, 14);
		contentPane.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Enter Username");
		lblUsername.setOpaque(true);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(168, 207, 113, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Enter Password");
		lblPassword.setOpaque(true);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(168, 254, 113, 14);
		contentPane.add(lblPassword);
		
		JLabel lblSelectType = new JLabel("Select User Type");
		lblSelectType.setOpaque(true);
		lblSelectType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectType.setBounds(168, 302, 113, 14);
		contentPane.add(lblSelectType);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(264, 369, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(264, 405, 89, 23);
		contentPane.add(btnBack);
		
		//Import image, set to desired dimensions and set background label to image
		ImageIcon bg = new ImageIcon("rugby-players-4790343_1920.jpg");
		Image image = bg.getImage();
		Image newimg = image.getScaledInstance(644, 461, Image.SCALE_SMOOTH);
		bg = new ImageIcon(newimg);
		JLabel background = new JLabel(bg);
		background.setHorizontalTextPosition(SwingConstants.CENTER);
		background.setOpaque(true);
		
		background.setBounds(0, 0, 644, 461);
		contentPane.add(background);
		
		/**
		 * When submit button is clicked check that the admin has entered information into txtFirstName, txtLastName, txtUsername and txtPassword
		 * and check that a usertype has been selected from the dropdown.
		 * <p>
		 * If all valid, and user type is selected check to see if user exists and if not, create user from information entered and
		 * set user type to 0 if admin selected or 1 if coach selected. Add to users hashmap in Controller with username being the key and
		 * User being the value
		 * <p>
		 * if information not entered or invalid, exception occurs with a message reminding the user to enter a first, last name, username and password. 
		 * Reminds to select user type
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedWord = (String)comboBox.getSelectedItem();
				//validate txtFirstName, txtLastName, txtUsername, txtPassword
				try {
					theController.checkString(txtFirstName.getText());
					theController.checkString(txtLastName.getText());
					theController.checkString(txtUsername.getText());
					theController.checkString(txtPassword.getText());
					if (!selectedWord.equals("Select User Type")) {
						//capitalise first letter of first name and last name
						String firstName = txtFirstName.getText().substring(0,1).toUpperCase() + txtFirstName.getText().substring(1);
						String lastName = txtLastName.getText().substring(0,1).toUpperCase() + txtLastName.getText().substring(1);
						String username = txtUsername.getText();
						String password = txtPassword.getText();
						//check if username already exists
						if(!theController.model.getUsers().keySet().contains(username)) {
							int userType;
							if (selectedWord.equals("Admin")){
								userType = 0;
							}
							else {
								userType = 1;
							}
							//create user using firstName, lastName, username, password, userType
							User newuser = new User(firstName, lastName, username, password, userType);
							theController.model.addUser(newuser);
							theController.messageDialogue("User added");
							//open menu sending admin userType(0) and theController. dispose of current JFrame
							Menu menu = new Menu(0 ,theController);
							menu.setVisible(true);
							dispose();
						}
						else {
							theController.messageDialogue("Username already taken, assign unique username");
						}
					}
					else {
						theController.messageDialogue("Ensure user type selected");
					}
				}catch(Exception m){
					theController.messageDialogue(m.toString() + "Ensure all all details are entered");
				}
			}
		});
		
		//If back button pressed, open menu sending admin userType(0) and theController. dispose of current JFrame
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu mn = new Menu(0 ,theController);
				mn.setVisible(true);
				dispose();
			}
		});
		
		
		
	}
}
