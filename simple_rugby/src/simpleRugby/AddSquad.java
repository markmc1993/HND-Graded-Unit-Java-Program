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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.util.LinkedList;
import java.util.Map.Entry;


/**
 * 
 * <h1>Add Squad</h1>
 * This JFrame class is used by admins to add new squads to the system. The admin will enter a name for the squad, select a coach from the available
 * coaches and select if the squad will be a junior or senior squad.
 * <p>
 * This will be validated to ensure that information is entered into the squad name textfield and to ensure that the admin has selected a coach, if
 * there is one available and will also ensure that a team type has been selected
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class AddSquad extends JFrame {
	//setup variables to be used by class
	private JPanel contentPane;
	private Controller theController;
	private JTextField textTeamName;
	private LinkedList<String> coaches = new LinkedList<>();
	private String[] teamTypes = {"Select Team Type", "Senior", "Junior"};
	
	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public AddSquad(Controller cont) {
		theController = cont;
		
		//loop through users and if user type is 1 (coach) loop through squadList, checking if coach is in a squad. Add to coaches if not assigned a squad
		boolean coachTeam = false;
		coaches.add("Select Coach");
		for (Entry<String, User> entry : theController.model.getUsers().entrySet()) {
			  int l = entry.getValue().getUserType();  
			  if (l == 1) {
				  for (int i = 0; i < theController.model.getSquadList().size(); i++) {
					  if (theController.model.getSquadList().get(i).getCoach().equals(entry.getValue().getUserName())) {
						  coachTeam = true;
					  }
					}
				  if(!coachTeam) {
					  coaches.add(entry.getValue().getUserName());
				  }
			  }
			}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textTeamName = new JTextField();
		textTeamName.setBounds(306, 101, 130, 20);
		contentPane.add(textTeamName);
		textTeamName.setColumns(10);
		
		//create combobox using coaches in the form of array
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboCoach = new JComboBox(coaches.toArray());
		comboCoach.setBounds(306, 166, 130, 22);
		contentPane.add(comboCoach);
		
		//create combobox using teamTypes in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboTeam = new JComboBox(teamTypes);
		comboTeam.setBounds(306, 232, 130, 22);
		contentPane.add(comboTeam);
		
		JLabel lblTeamName = new JLabel("Enter Squad Name");
		lblTeamName.setOpaque(true);
		lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName.setBounds(154, 104, 110, 14);
		contentPane.add(lblTeamName);
		
		JLabel lblSelectCoach = new JLabel("Select Coach");
		lblSelectCoach.setOpaque(true);
		lblSelectCoach.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectCoach.setBounds(154, 170, 110, 14);
		contentPane.add(lblSelectCoach);
		
		JLabel lblTeamType = new JLabel("Select Team Type");
		lblTeamType.setOpaque(true);
		lblTeamType.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamType.setBounds(154, 236, 110, 14);
		contentPane.add(lblTeamType);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(259, 298, 89, 23);
		contentPane.add(btnSubmit);		
		
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(259, 342, 89, 23);
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
		 * When submit button is clicked check that the admin has entered information into textTeamName, selected a coach and selected a team type.
		 * <p>
		 * If information entered valid, and coach and team type selected, check to see if team name already exists. If all information provided
		 * create squad with team name of entered information (uppercase first letter), selected coach and team type 0 if senior and 1 if junior.
		 * Add squad to squadList in Controller class.
		 * <p>
		 * If invalid information entered, exception or message displays to inform admin to enter/select valid information
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Validate textTeamName
				try {
					theController.checkString(textTeamName.getText());
					String coach = (String)comboCoach.getSelectedItem();
					String teamType = (String)comboTeam.getSelectedItem();
					//Capitalise first letter of team name entered
					String teamName = textTeamName.getText().substring(0,1).toUpperCase() + textTeamName.getText().substring(1);
					boolean teamTaken = false;
					//loop through squadlist to check if team already exists
					for (int i = 0; i < theController.model.getSquadList().size(); i++) {
						if (theController.model.getSquadList().get(i).getSquadName().equals(teamName)) {
							teamTaken = true;
						}
					}
					
					//create squad with team name entered, coach selected and teamType of 0 for senior or 1 for junior. 
					//Load menu sending admin userType(0) and theController and this JFrame is disposed
					if (!coach.equals("Select Coach") && !teamType.equals("Select Team Type") && !teamTaken) {
						if (teamType.equals("Senior")) {
							Squad sq = new Squad(teamName, coach, 0);
							theController.model.addSquad(sq);
							theController.messageDialogue("Senior team " + teamName + " added to system");
							Menu mn = new Menu(0 ,theController);
							mn.setVisible(true);
							dispose();
							
							
						}
						else {
							Squad sq = new Squad(teamName, coach, 1);
							theController.model.addSquad(sq);
							theController.messageDialogue("Junior team " + teamName + " added to system");
							Menu mn = new Menu(0 ,theController);
							mn.setVisible(true);
							dispose();
						}
					}
					else {
						theController.messageDialogue("Something wasn't right there. Make sure you select a coach and team type and enter a team name that does not already exist.");
					}
				}catch(Exception m) {
					theController.messageDialogue(m.toString() + ", enter a team name");
				}
			}
		});
		
		//If back button is clicked, load menu sending admin userType(0) and theController and this JFrame is disposed
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu mn = new Menu(0 ,theController);
				mn.setVisible(true);
				dispose();
			}
		});
		
	}
}
