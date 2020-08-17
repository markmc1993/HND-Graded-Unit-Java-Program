package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * <h1>Edit Member</h1>
 * This JFrame class is used by admins to edit members stored in the app. The admin will select a member and can enter a new first name or last name.
 * <p>
 * This information will be validated to ensure the admin has entered information into either first name or last name textFields.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class EditMember extends JFrame {
	//setup variables to be used by class
	private JPanel contentPane;
	private Controller theController;
	private JTextField textFirstName;
	private JTextField textLastName;
	private LinkedList<String> members = new LinkedList<>();

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public EditMember(Controller cont) {
		theController = cont;
		
		//loop through the members and add to LinkedList
		members.add("Select Member");
		for (Entry<String, Member> entry : theController.model.getMembers().entrySet()) {
			members.add(entry.getValue().getName());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//create combobox using members in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboMembers = new JComboBox(members.toArray());
		comboMembers.setBounds(91, 83, 130, 22);
		contentPane.add(comboMembers);
		
		JLabel lblSelectMember = new JLabel("Select Member");
		lblSelectMember.setOpaque(true);
		lblSelectMember.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectMember.setBounds(97, 58, 100, 14);
		contentPane.add(lblSelectMember);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(342, 160, 96, 20);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setBounds(342, 219, 96, 20);
		contentPane.add(textLastName);
		textLastName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("Enter First Name");
		lblFirstName.setOpaque(true);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setBounds(150, 160, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Enter Last Name");
		lblLastName.setOpaque(true);
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setBounds(150, 219, 96, 14);
		contentPane.add(lblLastName);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(245, 287, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(245, 342, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(342, 83, 89, 23);
		contentPane.add(btnDelete);
		
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
		 * When submit button is clicked check that the admin has entered information into textFirstName or textLastName and a member has been selected
		 * <p>
		 * If all valid, edit user from information entered, capitalising the first letter of either name
		 * <p>
		 * if information not entered or invalid, exception occurs with a message reminding the user to enter a first or last name and select a member
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedMember = (String)comboMembers.getSelectedItem();				
				String[] selectedMemberArray = selectedMember.split(" ");
				if (!selectedMember.equals("Select Member")) {
					if (textFirstName.getText().length() > 0 || textLastName.getText().length() > 0) {
						if (textFirstName.getText().length() > 0) {
							String fName = textFirstName.getText().substring(0,1).toUpperCase() + textFirstName.getText().substring(1);
							//change member first name to fName
							theController.model.getMembers().remove(selectedMember);
							Member newMem = new Member(fName, selectedMemberArray[1]);
							theController.model.addMember(newMem);
							theController.messageDialogue("Member first name updated successfully");
						}
						
						if (textLastName.getText().length() > 0) {
							String lName = textLastName.getText().substring(0,1).toUpperCase() + textLastName.getText().substring(1);
							//change member last name to lName
							theController.model.getMembers().remove(selectedMember);
							Member newMem = new Member(selectedMemberArray[0], lName);
							theController.model.addMember(newMem);
							theController.messageDialogue("Member last name updated successfully");
						}
						//open menu sending admin userType(0) and theController. dispose of current JFrame
						Menu mn = new Menu(0, theController);
						mn.setVisible(true);
						dispose();
					}
					else {
						theController.messageDialogue("Enter a first or last name");
					}
				}
				else {
					theController.messageDialogue("Select the member you wish to edit");
				}
				
			}
		});
		
		/**
		 * When select member button is clicked check that the admin has selected a member from members
		 * <p>
		 * If member selected, get confirmation to delete user. If confirmed, delete member unless user is the only admin, then cannot delete.
		 * <p>
		 * if no member selected, display message telling admin to ensure member is selected
		 */
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedMember = (String)comboMembers.getSelectedItem();
				if (!selectedMember.equals("Select Member")) {
					int confirmDelete = JOptionPane.showConfirmDialog(contentPane, ("Are you sure you wish to delete " + selectedMember)); 
					
					if (confirmDelete == 0) {
						//remove member from members.
						
							theController.model.removeMember(selectedMember);
							JOptionPane.showMessageDialog(contentPane, "User deleted");
							//open menu sending admin userType(0) and theController. dispose of current JFrame
							Menu mn = new Menu(0 ,theController);
							mn.setVisible(true);
							dispose();
						
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Member not deleted");
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No member selected");
				}
			}
		});
		
		//If back button pressed, open menu sending admin userType(0) and theController. dispose of current JFrame
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu(0, theController);
				mn.setVisible(true);
				dispose();
				
			}
		});
	}

}
