package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * <h1>Add Member</h1>
 * This JFrame class is used by admins to add members to the app. The admin will enter the first and last name of the member they wish to add to the system.
 * <p>
 * This information will be validated to ensure the admin has entered information into both first name and last name textfields.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class AddMember extends JFrame {
	//setup variables to be used by class
	private JPanel contentPane;
	private JTextField textFirstName;
	private JTextField textLastName;
	private Controller theController;


	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public AddMember(Controller cont) {
		theController = cont;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(342, 132, 110, 20);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("Enter First Name");
		lblFirstName.setOpaque(true);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setBounds(147, 135, 110, 14);
		contentPane.add(lblFirstName);
		
		textLastName = new JTextField();
		textLastName.setBounds(342, 206, 110, 20);
		contentPane.add(textLastName);
		textLastName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Enter Last Name");
		lblLastName.setOpaque(true);
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setBounds(147, 209, 110, 14);
		contentPane.add(lblLastName);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(257, 288, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(257, 338, 89, 23);
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
		 * When submit button is clicked check that the user has entered information into textFirstName and textLastName
		 * if both contain information, create a member from the Member class sending textFirstName.getText() and textLastName.getText()
		 * The member is then stored in a hashmap with key being set to name and value set to Member created. A message box appear to
		 * confirm this has occured then the menu is loaded, sending the admin usertype(0) and theController and this JFrame is disposed
		 * <p>
		 * if either text box is left empty, an exception occurs with a message reminding the user to enter a first and last name
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//validate textFirstName and textLastName
				try {
					theController.checkString(textFirstName.getText());
					theController.checkString(textLastName.getText());
					//Set first letter to uppercase in firstName and lastName
					String fName = textFirstName.getText().substring(0,1).toUpperCase() + textFirstName.getText().substring(1);
					String lName = textLastName.getText().substring(0,1).toUpperCase() + textLastName.getText().substring(1);
					Member mem = new Member(fName, lName);
					theController.model.addMember(mem);
					theController.messageDialogue(mem.getName() + " added to members");
					Menu mn = new Menu(0, theController);
					mn.setVisible(true);
					dispose();
				}catch(Exception m) {
					theController.messageDialogue(m.toString() + ", enter a first name and last name");
				}
				
			}
		});
		
		//If back button is clicked, load menu sending admin userType(0) and theController and this JFrame is disposed
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu(0, theController);
				mn.setVisible(true);
				dispose();
				
			}
		});
	}

}
