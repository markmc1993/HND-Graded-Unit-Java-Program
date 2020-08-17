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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;


/**
 * <h1>Edit Squad</h1>
 * This JFrame class is used by admin to edit a squad stored in the app. The admin will select a squad, click to view squad then edit the squad
 * name or coach or delete squad.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class EditSquad extends JFrame {

	//setup variables to be used by class
	private JPanel contentPane;
	private JTextField textTeamName;
	private Controller theController;
	private LinkedList<String> squads = new LinkedList<>();
	private LinkedList<String> coaches = new LinkedList<>();
	private String coachName;

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public EditSquad(Controller cont) {
		theController = cont;
		
		//loop through Model squads adding them to squads LinkedList
		squads.add("Select Squad");
		for (int i = 0; i < theController.model.getSquadList().size(); i++) {
			squads.add(theController.model.getSquadList().get(i).getSquadName());
		}
		
		//loop through users, if user is coach and not assigned to a squad, add to coaches
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
		
		//create combobox squads in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboSquads = new JComboBox(squads.toArray());
		comboSquads.setBounds(38, 105, 142, 22);
		contentPane.add(comboSquads);
		
		JLabel lblSelectSquad = new JLabel("Select Squad");
		lblSelectSquad.setOpaque(true);
		lblSelectSquad.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectSquad.setBounds(38, 80, 79, 14);
		contentPane.add(lblSelectSquad);
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setBounds(310, 105, 120, 23);
		contentPane.add(btnViewDetails);
		
		JButton btnDeleteSquad = new JButton("Delete");
		btnDeleteSquad.setBounds(450, 105, 120, 23);
		contentPane.add(btnDeleteSquad);
		
		JLabel lblSquadDetails = new JLabel("Squad Details");
		lblSquadDetails.setOpaque(true);
		lblSquadDetails.setBounds(38, 175, 79, 14);
		contentPane.add(lblSquadDetails);
		
		JLabel lblChangeName = new JLabel("Change Team Name");
		lblChangeName.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeName.setOpaque(true);
		lblChangeName.setBounds(308, 226, 120, 14);
		contentPane.add(lblChangeName);
		
		JLabel lblChangeCoach = new JLabel("Change Coach");
		lblChangeCoach.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeCoach.setOpaque(true);
		lblChangeCoach.setBounds(308, 262, 110, 14);
		contentPane.add(lblChangeCoach);
		
		textTeamName = new JTextField();
		textTeamName.setBounds(450, 223, 120, 20);
		contentPane.add(textTeamName);
		textTeamName.setColumns(10);
		
		//create combobox coaches in the form of array
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox comboCoach = new JComboBox(coaches.toArray());
		comboCoach.setBounds(450, 258, 120, 22);
		contentPane.add(comboCoach);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(387, 308, 89, 23);
		contentPane.add(btnSubmit);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(35, 200, 160, 220);
		contentPane.add(textArea);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(235, 342, 89, 23);
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
		 * When view details button is clicked check that the admin has selected a squad from squads
		 * <p>
		 * If squad selected, display squad information and get players in squad to display.
		 * <p>
		 * if no squad selected, display message telling admin to ensure squad is selected
		 */
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedSquad = (String)comboSquads.getSelectedItem();
				if (!selectedSquad.equals("Select Squad")) {					
					
					int teamType = 0;
					String teamTypeName = "";
					String coach = "";
					String squad = "";
					LinkedList<Player> players = new LinkedList<>();
					LinkedList<String> squadPlayers = new LinkedList<>();	
					//loop through squadList to get squad
					for (int i = 0; i < theController.model.getSquadList().size(); i++) {
						if (theController.model.getSquadList().get(i).getSquadName().equals(selectedSquad)){
							squad = selectedSquad;
							coach = theController.model.getSquadList().get(i).getCoach();
							coachName = coach;
							players.addAll(theController.model.getSquadList().get(i).getSquad());
							//loop through squad in squadList to get players
							for (int j = 0; j < players.size(); j++) {
								squadPlayers.add(players.get(i).getName());
							}
							teamType = theController.model.getSquadList().get(i).getTeamType();
							if (teamType == 0) {
								teamTypeName = "Senior";
							}
							else {
								teamTypeName = "Junior";
							}
						}
					}
					
									
					
					//Display squad info in textArea
					textArea.setText("Squad name: " + squad + "\n" +
					"Coach: " + coach + "\n" +
					"Team type: " + teamTypeName + "\n" +
					"Squad: \n" + squadPlayers);
				}
				
			}
		});
		
		/**
		 * When delete squad button is clicked confirmation of deletion of selected squad is requested. If confirmed, remove squad from SquadList.
		 * Coach and players from squad will be able to be reassigned to other squads if available.
		 * <p>
		 * if no squad selected, display message telling admin to ensure squad is selected
		 */
		btnDeleteSquad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedSquad = (String)comboSquads.getSelectedItem();
				if (!selectedSquad.equals("Select Squad")) {
					int confirmDelete = JOptionPane.showConfirmDialog(contentPane, ("Are you sure you wish to delete " + selectedSquad)); 
					
					if (confirmDelete == 0) {
						
						theController.model.removeSquad(selectedSquad);
						theController.messageDialogue("Squad " + selectedSquad + " has been deleted successully");
						//open menu sending admin userType(0) and theController. dispose of current JFrame
						Menu mn = new Menu(0, theController);
						mn.setVisible(true);
						dispose();			
						
						
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Squad not deleted");
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No squad selected");
				}
				
			}
		});
		
		/**
		 * When submit button is clicked checks will occur to ensure selected squad is the one being viewed.
		 * <p>
		 * If same squad, checks either new coach is selected or new name is entered. If new coach entered, squads coach becomes new coach.
		 * If new name entered, squad name becomes new name
		 * <p>
		 * if no squad selected or different from the one being viewed, display message telling admin to ensure correct squad is selected.
		 * If no coach selected and no name entered, display message telling admin to select coach or enter a team name
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedCoach = (String)comboCoach.getSelectedItem();
				String selectedSquad = (String)comboSquads.getSelectedItem();
				boolean nameCheck = false;
				if (!selectedCoach.equals(coachName)) {
					for (int i = 0; i < theController.model.getSquadList().size(); i++) {
						if (theController.model.getSquadList().get(i).getSquadName().equals(textTeamName.getText())) {
							nameCheck = true;
						}
					}
					
					if (!selectedSquad.equals("Select Squad")) {
						if (!selectedCoach.equals("Select Coach") || textTeamName.getText().length() > 0 && !nameCheck) {
							if (!selectedCoach.equals("Select Coach")) {
								for (int i = 0; i < theController.model.getSquadList().size(); i++) {
									if (theController.model.getSquadList().get(i).getSquadName().equals(selectedSquad)) {
										theController.model.getSquadList().get(i).setCoach(selectedCoach);
									}
								}
							}
							
							if (textTeamName.getText().length() > 0) {
								for (int i = 0; i < theController.model.getSquadList().size(); i++) {
									if (theController.model.getSquadList().get(i).getSquadName().equals(selectedSquad)) {
										theController.model.getSquadList().get(i).setSquadName(textTeamName.getText().substring(0,1).toUpperCase() + textTeamName.getText().substring(1));
									}
								}
								theController.messageDialogue("Squad name changed to " + textTeamName.getText());
							}			
							//open menu sending admin userType(0) and theController. dispose of current JFrame
							Menu mn = new Menu(0 ,theController);
							mn.setVisible(true);
							dispose();
							
						}
						else {
							theController.messageDialogue("Please enter a team name that is not taken or select a coach");
						}
					}
					else {
						theController.messageDialogue("Please select a squad first");
					}
				}
				else {
					theController.messageDialogue("Please click View Squad before submitting changes");
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
