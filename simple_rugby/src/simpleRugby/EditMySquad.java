package simpleRugby;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * <h1>Edit My Squad</h1>
 * This JFrame class is used by coaches to edit their squad stored in the app. The coach will select a player not in the squad to add to squad or
 * select a player in the squad to remove from squad. Coach can only access this if they are assigned to a squad.
 * <p>
 * Each player in the squad is displayed in a table showing their name, position, SRU number, and average skill rating for each skill category
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class EditMySquad extends JFrame {
	
	//setup variables to be used by class
	private JPanel contentPane;
	private JTable tablePlayers;
	private Controller theController;
	private LinkedList<String> players = new LinkedList<>();
	private LinkedList<String> availablePlayers = new LinkedList<>();
	private String squadName;
	private int teamType;

	

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public EditMySquad(Controller cont) {		
		theController = cont;
		
		//loop through the Model players and add name and SRU number to players if they are a member of this squad
		players.add("Select Player");
		availablePlayers.add("Select Player");
		for (int i = 0; i < theController.model.getSquadList().size(); i++) {
			if (theController.model.getSquadList().get(i).getCoach().equals(theController.model.getUsername())) {
				squadName = theController.model.getSquadList().get(i).getSquadName();
				teamType = theController.model.getSquadList().get(i).getTeamType();
				for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
					players.add(theController.model.getSquadList().get(i).getSquad().get(j).getName() + " " + theController.model.getSquadList().get(i).getSquad().get(j).getSruNumber());
				}
			}
		}
		
		//loop through Model players or juniors depending on team type and add name and SRU number to availablePlayers if they are available
		if (teamType == 0) {
			boolean available = false;
			LinkedList<Player> plays = new LinkedList<>(); 
			plays.addAll(theController.model.getPlayers().values());
			for (int i = 0; i < plays.size(); i++) {
				
				for (int j = 0; j < theController.model.getSquadList().size(); j++) {
					if (!theController.model.getSquadList().get(j).getSquad().contains(plays.get(i))) {
						available = true;
					}
				}
				if (available) {
					availablePlayers.add(plays.get(i).getName() + " " + plays.get(i).getSruNumber());
				}
			}
		}
		else {
			boolean available = false;
			LinkedList<JuniorPlayer> plays = new LinkedList<>(); 
			plays.addAll(theController.model.getJuniors().values());
			for (int i = 0; i < plays.size(); i++) {
				
				for (int j = 0; j < theController.model.getSquadList().size(); j++) {
					if (!theController.model.getSquadList().get(j).getSquad().contains(plays.get(i))) {
						available = true;
					}
				}
				if (available) {
					availablePlayers.add(plays.get(i).getName() + " " + plays.get(i).getSruNumber());
				}
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//create a JScrollpane and add JTable with column titles Player, Position, SRU Number, Passing, Tackling, Kicking and populate with object[][]
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 42, 566, 275);
		contentPane.add(scrollPane);
		
		tablePlayers = new JTable();
		tablePlayers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tablePlayers.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Player", "Position", "SRU Number", "Passing", "Tackling", "Kicking"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class, Object.class, Object.class, Object.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tablePlayers);
		
		//create combobox using availablePlayers in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboAddPlayers = new JComboBox(availablePlayers.toArray());
		comboAddPlayers.setBounds(79, 353, 160, 22);
		contentPane.add(comboAddPlayers);
		
		//create combobox using players in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboRemovePlayers = new JComboBox(players.toArray());
		comboRemovePlayers.setBounds(441, 353, 160, 22);
		contentPane.add(comboRemovePlayers);
		
		JLabel lblAddPlayer = new JLabel("Add Player");
		lblAddPlayer.setOpaque(true);
		lblAddPlayer.setBounds(79, 328, 90, 14);
		contentPane.add(lblAddPlayer);
		
		JLabel lblRemovePlayer = new JLabel("Remove Player");
		lblRemovePlayer.setOpaque(true);
		lblRemovePlayer.setBounds(441, 328, 90, 14);
		contentPane.add(lblRemovePlayer);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(106, 386, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(471, 386, 89, 23);
		contentPane.add(btnRemove);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(258, 414, 89, 23);
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
		
		//for each player in the squad, add a row to tablePlayers with player name, position, sru number, average passing, average tackling, average kicking
		DefaultTableModel model = (DefaultTableModel)tablePlayers.getModel();
		
		//loop through squadlist to get squad with same name
		for (int i = 0; i < theController.model.getSquadList().size(); i++) {
			if (theController.model.getSquadList().get(i).getSquadName().equals(squadName)) {
				//loop through squad getting information of each player in squad
				for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
					String player = theController.model.getSquadList().get(i).getSquad().get(j).getName();
					String position = theController.model.getSquadList().get(i).getSquad().get(j).getPosition();
					int sruNum = theController.model.getSquadList().get(i).getSquad().get(j).getSruNumber();
					SkillCategory pass = theController.model.getSquadList().get(i).getSquad().get(j).getSkillCategories().get(0);
					SkillCategory tackle = theController.model.getSquadList().get(i).getSquad().get(j).getSkillCategories().get(1);
					SkillCategory kick = theController.model.getSquadList().get(i).getSquad().get(j).getSkillCategories().get(2);
					int passing = (pass.getSkill("Standard").getRating() + pass.getSkill("Spin").getRating() + pass.getSkill("Pop").getRating()) / 3;
					int tackling = (tackle.getSkill("Front").getRating() + tackle.getSkill("Rear").getRating() + tackle.getSkill("Side").getRating() + tackle.getSkill("Scrabble"). getRating()) / 4;
					int kicking = (kick.getSkill("Drop").getRating() + kick.getSkill("Punt").getRating() + kick.getSkill("Grubber").getRating() + kick.getSkill("Goal").getRating()) /4;
					
					model.addRow(new Object[] {player, position, sruNum, passing, tackling, kicking});
							
					
				}
			}
		}
		
		/**
		 * When add button is clicked check that the coach has selected a player from availablePlayers
		 * <p>
		 * If player selected, add player to squad
		 * <p>
		 * if no player selected, display message telling coach to ensure player is selected
		 */
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selectedPlayer = ((String)comboAddPlayers.getSelectedItem()).split(" ");
				String selectedName= selectedPlayer[0] + " " + selectedPlayer[1];
				int playerNum = Integer.parseInt(selectedPlayer[2]);
				//loop through squadList and if senior squad find player in players in model or juniors if junior squad. Add player to squad
				if (!selectedName.equals("Select Player")) {
					if (teamType == 0) {
						for (int i = 0; i < theController.model.getSquadList().size(); i++) {
							
							if(theController.model.getSquadList().get(i).getSquadName().equals(squadName)){
								theController.model.getSquadList().get(i).addPlayer(theController.model.getPlayers().get(playerNum));
								theController.messageDialogue("Player added to squad");
								//open menu sending coach userType(1) and theController. dispose of current JFrame
								Menu mn = new Menu(1 ,theController);
								mn.setVisible(true);
								dispose();
							}
						}
					}
					else {
						for (int i = 0; i < theController.model.getSquadList().size(); i++) {
							if(theController.model.getSquadList().get(i).getSquadName().equals(squadName)){
								theController.model.getSquadList().get(i).addPlayer(theController.model.getJuniors().get(playerNum));
								theController.messageDialogue("Player added to squad");
								//open menu sending coach userType(1) and theController. dispose of current JFrame
								Menu mn = new Menu(1 ,theController);
								mn.setVisible(true);
								dispose();
							}
						}
					}
				}
				else {
					theController.messageDialogue("Select Player");
				}
				
			}
		});
		
		/**
		 * When remove button is clicked check that the coach has selected a player from players
		 * <p>
		 * If player selected, remove player from squad
		 * <p>
		 * if no player selected, display message telling coach to ensure player is selected
		 */
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selectedPlayer = ((String)comboRemovePlayers.getSelectedItem()).split(" ");
				String selectedName= selectedPlayer[0] + " " + selectedPlayer[1];
				if (!selectedName.equals("Select Player")) {
					int confirmDelete = JOptionPane.showConfirmDialog(contentPane, ("Are you sure you wish to delete " + selectedName));
					
					//loop through squadList and if squadList i is squadName loop through squad to find player and remove from squad
					if (confirmDelete == 0) {
						for (int i = 0; i < theController.model.getSquadList().size(); i++) {
							if (theController.model.getSquadList().get(i).getSquadName().equals(squadName)) {
								for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
									if (theController.model.getSquadList().get(i).getSquad().get(j).getName().equals(selectedName)) {
										theController.model.getSquadList().get(i).getSquad().remove(j);
										theController.messageDialogue("Player " + selectedName + " removed from squad");
										//open menu sending coach userType(1) and theController. dispose of current JFrame
										Menu mn = new Menu(1 ,theController);
										mn.setVisible(true);
										dispose();
									}
								}
							}
						}
					}
					else {
						theController.messageDialogue("Player not removed");
					}
				}
				else {
					theController.messageDialogue("Select a player");
				}
			}
		});
		
		//If back button pressed, open menu sending caoch userType(1) and theController. dispose of current JFrame
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu mn = new Menu(1 ,theController);
				mn.setVisible(true);
				dispose();
			}
		});
		
	}

}
