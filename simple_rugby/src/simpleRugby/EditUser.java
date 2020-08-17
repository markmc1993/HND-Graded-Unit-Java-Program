package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.ComponentOrientation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * <h1>Edit User</h1>
 * This JFrame class is used by admin to edit a user stored in the app. The admin will select a user, click to view user then edit the users
 * first name, last name or password
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class EditUser extends JFrame {
	
	//setup variables to be used by class
	private JPanel contentPane;
	private LinkedList<String> users = new LinkedList<>();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPassword;	
	private Controller theController;
	private String user;

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public EditUser(Controller cont) {	
		
		theController = cont;
		
		//loop through model users adding the name of user to users
		users.add("Select User");
		for (Entry<String, User> entry : theController.model.getUsers().entrySet()) {
			  String k = entry.getKey();  
			  users.add(k);
			}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JTextArea userInfo = new JTextArea();
		userInfo.setEditable(false);
		userInfo.setBounds(86, 126, 171, 185);
		contentPane.add(userInfo);
		
		//create combobox users in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox usersDropDown = new JComboBox(users.toArray());
		usersDropDown.setBounds(98, 51, 171, 22);
		contentPane.add(usersDropDown);		
		
		
		JLabel lblUserList = new JLabel("Select User");
		lblUserList.setOpaque(true);
		lblUserList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblUserList.setIgnoreRepaint(true);
		lblUserList.setBounds(98, 26, 73, 14);
		contentPane.add(lblUserList);
		
		JButton btnSelectUser = new JButton("Select User");
		btnSelectUser.setBounds(337, 51, 133, 23);
		contentPane.add(btnSelectUser);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(411, 162, 96, 20);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setBounds(411, 209, 96, 20);
		contentPane.add(txtLastName);
		txtLastName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(411, 255, 96, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setBounds(480, 51, 133, 23);
		contentPane.add(btnDeleteUser);
		
		JLabel lblFirstName = new JLabel("Edit First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setOpaque(true);
		lblFirstName.setBounds(290, 165, 96, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Edit Last Name");
		lblLastName.setOpaque(true);
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setIgnoreRepaint(true);
		lblLastName.setBounds(290, 212, 96, 14);
		contentPane.add(lblLastName);
		
		JLabel lblPassword = new JLabel("Edit Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setOpaque(true);
		lblPassword.setBounds(290, 258, 96, 14);
		contentPane.add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(246, 339, 89, 23);
		contentPane.add(btnSubmit);
		
		JLabel lblUserInfo = new JLabel("User Information");
		lblUserInfo.setOpaque(true);
		lblUserInfo.setBounds(86, 101, 100, 14);
		contentPane.add(lblUserInfo);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(246, 384, 89, 23);
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
		 * When select user button is clicked check that the admin has selected a user from users
		 * <p>
		 * If user selected, get information on user and display in userInformation.
		 * <p>
		 * if no user selected, display message telling admin to ensure user is selected
		 */
		btnSelectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedUser = (String)usersDropDown.getSelectedItem();
				user = selectedUser;
				if (!selectedUser.equals("Select User")) {					
					String firstname = theController.model.getUsers().get(selectedUser).getFirstName();
					String lastname = theController.model.getUsers().get(selectedUser).getLastName();
					String username = theController.model.getUsers().get(selectedUser).getUserName();
					String password = theController.model.getUsers().get(selectedUser).getPassword();
					int userType = theController.model.getUsers().get(selectedUser).getUserType();
					String type;
					if (userType == 0) {
						type = "Admin";
					}
					else {
						type = "Coach";
					}
					//Set information in userInformation to first name, last name, username, password and user type
					String userInformation = "Firstname: " + firstname + "\n" +
							"Lastname: " + lastname + "\n" +
							"Username: " + username + "\n" +
							"Password: " + password + "\n" +
							"User Type: " + type;
					
					
					userInfo.setText(userInformation);
				}
			}
		});
		
		/**
		 * When select user button is clicked check that the admin has selected a user from users
		 * <p>
		 * If user selected, get confirmation to delete user. If confirmed, delete user unless user is the only admin, then cannot delete.
		 * <p>
		 * if no user selected, display message telling admin to ensure user is selected
		 */
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedUser = (String)usersDropDown.getSelectedItem();
				if (!selectedUser.equals("Select User")) {
					int confirmDelete = JOptionPane.showConfirmDialog(contentPane, ("Are you sure you wish to delete " + selectedUser)); 
					
					if (confirmDelete == 0) {
						int admins = 0;
						//loop through model users and remove user from users. If a coach, loop through squads and remove from squad if assigned.
						for (Entry<String, User> entry : theController.model.getUsers().entrySet()) {
							  int l = entry.getValue().getUserType();  
							  if (l == 0) {
								  admins ++;
							  }
							}
						if (admins <= 1 && theController.model.getUsers().get(selectedUser).getUserType() == 0) {
							JOptionPane.showMessageDialog(contentPane, "Last admin, cannot delete");
						}else {
							for (int i = 0; i < theController.model.getSquadList().size(); i++) {
								if (theController.model.getSquadList().get(i).getCoach().equals(selectedUser)) {
									theController.model.getSquadList().get(i).setCoach("No Coach");
								}
							}
							theController.model.removeUser(selectedUser);
							JOptionPane.showMessageDialog(contentPane, "User deleted");
							//open menu sending admin userType(0) and theController. dispose of current JFrame
							Menu mn = new Menu(0 ,theController);
							mn.setVisible(true);
							dispose();
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "User not deleted");
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No user selected");
				}
			}
		});
		
		/**
		 * When submit button is clicked check that the selected user is the same as the user that is being viewed
		 * <p>
		 * If user selected is same, check if first name, last name or password is entered. If any have info, update selected users details to match new info.
		 * <p>
		 * if no user or different user selected, display message telling admin to ensure same user to that being viewed is selected
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedUser = (String)usersDropDown.getSelectedItem();
				if (selectedUser.equals(user)) {
					if (txtFirstName.getText().length() > 0 || txtLastName.getText().length() > 0 || txtPassword.getText().length() > 0) {
						if (txtFirstName.getText().length() > 0) {
							theController.model.getUsers().get(selectedUser).setFirstName(txtFirstName.getText().substring(0,1).toUpperCase() + txtFirstName.getText().substring(1));
							theController.messageDialogue("First name changed");
							txtFirstName.setText("");
						}
						
						if (txtLastName.getText().length() > 0) {
							theController.model.getUsers().get(selectedUser).setLastName(txtLastName.getText().substring(0,1).toUpperCase() + txtLastName.getText().substring(1));
							theController.messageDialogue("Last name changed");
							txtLastName.setText("");
						}
						
						if (txtPassword.getText().length() > 0) {
							theController.model.getUsers().get(selectedUser).setPassword(txtPassword.getText());
							theController.messageDialogue("Password changed");
							txtPassword.setText("");
						}
					}
					else {
						theController.messageDialogue("Enter information in one or more of the text boxes");
					}
				}else {
					theController.messageDialogue("Click select user before submitting changes");
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
