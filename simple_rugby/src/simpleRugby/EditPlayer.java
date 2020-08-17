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
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 * <h1>Edit Player</h1>
 * This JFrame class is used by coaches to edit a player stored in the app. The coach will select a player, click to view player then edit their skill
 * ratings and notes or delete the player.
 * <p>
 * Any coach can edit any player but cannot add or remove from a squad unless it is their own.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class EditPlayer extends JFrame {
	
	//setup variables to be used by class
	private JPanel contentPane;
	private JTable table;
	private Controller theController;
	private LinkedList<String> players = new LinkedList<>();
	private String[] nums = {"1","2","3","4","5"};
	private String playerType = "";
	private LinkedList<Skill> skills = new LinkedList<>();
	private String currentPlayer;
	
	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public EditPlayer(Controller cont) {
		theController = cont;
		
		//loop through Controller players and juniors adding them to players LinkedList
		players.add("Select Player");
		for (Entry<Integer, Player> entry : theController.model.getPlayers().entrySet()) {
			players.add(entry.getValue().getName() + " " + entry.getValue().getSruNumber());
		}
		for (Entry<Integer, JuniorPlayer> entry : theController.model.getJuniors().entrySet()) {
			players.add(entry.getValue().getName() + " " + entry.getValue().getSruNumber());
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//create combobox players in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboPlayers = new JComboBox(players.toArray());
		comboPlayers.setBounds(65, 41, 163, 22);
		contentPane.add(comboPlayers);
		
		JLabel lblSelectPlayer = new JLabel("Select Player");
		lblSelectPlayer.setOpaque(true);
		lblSelectPlayer.setBounds(65, 16, 77, 14);
		contentPane.add(lblSelectPlayer);
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setBounds(288, 41, 120, 23);
		contentPane.add(btnViewDetails);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(475, 41, 120, 23);
		contentPane.add(btnDelete);
		
		JTextArea textDetails = new JTextArea();
		textDetails.setEditable(false);
		textDetails.setBounds(28, 137, 200, 158);
		contentPane.add(textDetails);
		
		JLabel lblPlayerDetails = new JLabel("Player Details");
		lblPlayerDetails.setOpaque(true);
		lblPlayerDetails.setBounds(28, 112, 102, 14);
		contentPane.add(lblPlayerDetails);
		
		//create a JScrollpane and add JTable with column titles Skill Category, Skill, Rating, Notes and populate with a row for each skill,
		//setting SkillCategory and Skill for each one and setting rating as "0" and Notes as null as default
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 137, 356, 219);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Passing", "Standard", "0", null},
				{"Passing", "Spin", "0", null},
				{"Passing", "Pop", "0", null},
				{"Tackling", "Front", "0", null},
				{"Tackling", "Rear", "0", null},
				{"Tackling", "Side", "0", null},
				{"Tackling", "Scrabble", "0", null},
				{"Kicking", "Drop", "0", null},
				{"Kicking", "Punt", "0", null},
				{"Kicking", "Grubber", "0", null},
				{"Kicking", "Goal", "0", null},
			},
			new String[] {
				"Skill Category", "Skill", "Rating", "Notes"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		TableColumn ratingColumn = table.getColumnModel().getColumn(2);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox ratings = new JComboBox(nums);
		ratingColumn.setCellEditor(new DefaultCellEditor(ratings));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(263, 383, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(263, 417, 89, 23);
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
		 * When view details button is clicked check that the coach has selected a player from players
		 * <p>
		 * If player selected, set skill ratings and notes in table to correct figures for the selected player.
		 * <p>
		 * if no player selected, display message telling coach to ensure player is selected
		 */
		btnViewDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create variables and set to appropriate information
				currentPlayer = (String)comboPlayers.getSelectedItem();
				String[] selectedName = ((String)comboPlayers.getSelectedItem()).split(" ");
				String selectedPlayer = selectedName[0] + " " + selectedName[1];
				String playerName = "";
				String position = "";
				String guardianName = "";
				String team = "";
				int sruNum = Integer.parseInt(selectedName[2]);
				
				
				boolean inTeam = false;
				if (!selectedPlayer.equals("Select Player")) {
					//if selected player is senior
					if (theController.model.getPlayers().keySet().contains(sruNum)) {
						playerName = theController.model.getPlayers().get(sruNum).getName();
						position = theController.model.getPlayers().get(sruNum).getPosition();
						playerType = "Senior";
						for (int i = 0; i < theController.model.getSquadList().size(); i++) {
							for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
								if (theController.model.getSquadList().get(i).getSquad().get(j).getName().equals(selectedPlayer)) {
									inTeam = true;
									team = theController.model.getSquadList().get(i).getSquadName();
								}
							}
							
						}
						if (inTeam == false) {
							team = "No Team";
						}
						
						//clear skills to ensure that previous player skills are not still saved
						skills.clear();
						for (int i = 0; i < theController.model.getPlayers().get(sruNum).getSkillCategories().size(); i++) {
							skills.addAll(theController.model.getPlayers().get(sruNum).getSkillCategories().get(i).getSkills());
						}
						
						//loop through skills, setting table values to rating and notes of selected player at skill
						for (int i = 0; i < skills.size(); i++) {
							table.setValueAt(skills.get(i).getRating(), i, 2);
							table.setValueAt(skills.get(i).getNotes(), i, 3);
						}
						
						//set textDetails to display player name, position, team, player type and SRU number
						textDetails.setText("Player name: " + playerName + "\n" +
						"Position: " + position + "\n" +
						"Team: " + team + "\n" +
						"Player Type: " + playerType + "\n" +
						"SRU Number: " + sruNum);
						
					}
					//if selected player is junior
					else {
						playerName = theController.model.getJuniors().get(sruNum).getName();
						position = theController.model.getJuniors().get(sruNum).getPosition();
						playerType = "Junior";
						guardianName = theController.model.getJuniors().get(sruNum).getGuardianName();
						for (int i = 0; i < theController.model.getSquadList().size(); i++) {
							for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
								if (theController.model.getSquadList().get(i).getSquad().get(j).getName().equals(selectedPlayer)) {
									inTeam = true;
									team = theController.model.getSquadList().get(i).getSquadName();
								}
							}
						}
						if (inTeam == false) {
							team = "No Team";
						}
						
						//clear skills to ensure that previous player skills are not still saved
						skills.clear();
						for (int i = 0; i < theController.model.getJuniors().get(sruNum).getSkillCategories().size(); i++) {
							skills.addAll(theController.model.getJuniors().get(sruNum).getSkillCategories().get(i).getSkills());
						}
						
						//loop through skills, setting table values to rating and notes of selected player at skill
						for (int i = 0; i < skills.size(); i++) {
							table.setValueAt(skills.get(i).getRating(), i, 2);
							table.setValueAt(skills.get(i).getNotes(), i, 3);
						}
						
						//set textDetails to display player name, position, team, player type, SRU number and guardian name
						textDetails.setText("Player name: " + playerName + "\n" +
								"Position: " + position + "\n" +
								"Team: " + team + "\n" +
								"Player Type: " + playerType + "\n"+
								"SRU Number: " + sruNum + "\n" +
								"Guardian Name: " + guardianName);
					}
				}
			}
		});
		
		
		/**
		 * When submit button is clicked check that the player selected is the same as the one that was being viewed
		 * <p>
		 * If player selected is same as that being viewed, set skill ratings and notes to those entered in table.
		 * <p>
		 * if different or no player selected, display message telling coach to ensure correct player is selected
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selectedName = ((String)comboPlayers.getSelectedItem()).split(" ");
				if (currentPlayer.equals(selectedName[0] + " " + selectedName[1])){
					
					int playerNum = Integer.parseInt(selectedName[2]);
					//if senior player loop through skill categories setting skill rating and notes to that entered in table
					if (playerType.equals("Senior")) {
						for (int i = 0; i < skills.size(); i++) {
							for (int j = 0; j < theController.model.getPlayers().get(playerNum).getSkillCategories().size(); j++) {
								if (theController.model.getPlayers().get(playerNum).getSkillCategories().get(j).getSkills().contains(skills.get(i))){
									theController.model.getPlayers().get(playerNum).getSkillCategories().get(j).getSkill(skills.get(i).getSkillName()).setRating(Integer.parseInt((String)table.getValueAt(i, 2).toString()));
									theController.model.getPlayers().get(playerNum).getSkillCategories().get(j).getSkill(skills.get(i).getSkillName()).setNotes((String)table.getValueAt(i, 3));
								}
							}
							
						}
						theController.messageDialogue("Player updated");
					}
					//if junior player loop through skill categories setting skill rating and notes to that entered in table
					else {
						for (int i = 0; i < skills.size(); i++) {
							for (int j = 0; j < theController.model.getJuniors().get(playerNum).getSkillCategories().size(); j++) {
								if (theController.model.getJuniors().get(playerNum).getSkillCategories().get(j).getSkills().contains(skills.get(i))){
									theController.model.getJuniors().get(playerNum).getSkillCategories().get(j).getSkill(skills.get(i).getSkillName()).setRating(Integer.parseInt((String)table.getValueAt(i, 2).toString()));
									theController.model.getJuniors().get(playerNum).getSkillCategories().get(j).getSkill(skills.get(i).getSkillName()).setNotes((String)table.getValueAt(i, 3));
								}
							}
							
						}
						theController.messageDialogue("Player updated");
					}
					
				}
				else {
					theController.messageDialogue("Please select view player before making changes and submitting");
				}
				
			}
		});
		
		//If back button pressed, open menu sending coach userType(1) and theController. dispose of current JFrame
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu mn = new Menu(1 ,theController);
				mn.setVisible(true);
				dispose();
			}
		});
		
		//If delete button pressed, confirm delete. If confirmed, remove player from squad if they are in a squad and remove player from Controller players if a senior or juniors if a junior
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selectedName = ((String)comboPlayers.getSelectedItem()).split(" ");
				String selectedPlayer = selectedName[0] + " " + selectedName[1];
				int playerNum = Integer.parseInt((selectedName[2]));
				if (!selectedPlayer.equals("Select Player")) {
					int confirmDelete = JOptionPane.showConfirmDialog(contentPane, ("Are you sure you wish to delete " + selectedPlayer));
					
					if (confirmDelete == 0) {
						for (int i = 0; i < theController.model.getSquadList().size(); i++) {
							for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
								if (theController.model.getSquadList().get(i).getSquad().get(j).getName().equals(selectedPlayer)){
									theController.model.getSquadList().get(i).getSquad().remove(j);
								}
							}
						}
						if (theController.model.getPlayers().keySet().contains(playerNum)) {
							theController.model.removePlayer(playerNum);
							theController.messageDialogue("Player deleted");
							//open menu sending coach userType(1) and theController. dispose of current JFrame
							Menu mn = new Menu(1 ,theController);
							mn.setVisible(true);
							dispose();
							
						}
						else {
							theController.model.removeJunior(playerNum);;
							theController.messageDialogue("Player deleted");
							//open menu sending coach userType(1) and theController. dispose of current JFrame
							Menu mn = new Menu(1 ,theController);
							mn.setVisible(true);
							dispose();
						}
					}
					else {
						theController.messageDialogue("Player not deleted");
					}
				}
				else {
					theController.messageDialogue("No player selected");
				}			
				
				
			}
		});
	}
}
