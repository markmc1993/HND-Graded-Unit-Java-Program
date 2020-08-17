package simpleRugby;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * <h1>MySquad</h1>
 * This JFrame class is used by the team's coach to view their squad. Each player in squad and their position will be displayed in a table.
 * <p>
 * Coach can select player from squad and select view player to view selected player's most recent skill ratings and notes.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class MySquad extends JFrame {
	
	//setup variables to be used by class
	private JPanel contentPane;
	private Controller theController;
	private JTable tablePlayers;
	private JTable tableInfo;
	private LinkedList<String> players = new LinkedList<>();
	private String squadName;
	private int teamType;
	

	
	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public MySquad(Controller cont) {
		theController = cont;
		
		//loop through model squadList to get squad that the coach is assigned to. add each player name and SRU numbers to LinkedLists, assign squadName and teamType 
		players.add("Select Player");
		for (int i = 0; i < theController.model.getSquadList().size(); i++) {
			if (theController.model.getSquadList().get(i).getCoach().equals(theController.model.getUsername())) {
				squadName = theController.model.getSquadList().get(i).getSquadName();
				teamType = theController.model.getSquadList().get(i).getTeamType();
				for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
					players.add(theController.model.getSquadList().get(i).getSquad().get(j).getName() + " " + theController.model.getSquadList().get(i).getSquad().get(j).getSruNumber());
				}
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSquadName = new JLabel(squadName);
		lblSquadName.setOpaque(true);
		lblSquadName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSquadName.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSquadName.setBounds(174, 34, 300, 29);
		contentPane.add(lblSquadName);
		
		//create a JScrollpane and add JTable with column titles Name, Position
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 238, 270);
		contentPane.add(scrollPane);
		
		tablePlayers = new JTable();
		tablePlayers.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Position"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablePlayers.getColumnModel().getColumn(0).setPreferredWidth(100);
		scrollPane.setViewportView(tablePlayers);
		
		//create a JScrollpane and add JTable with column titles Skill Category, Skill, Rating, Notes and populate with a row for each skill,
		//setting SkillCategory and Skill for each one and setting rating as "0" and Notes as null as default
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(258, 176, 374, 210);
		contentPane.add(scrollPane_1);
		
		tableInfo = new JTable();
		tableInfo.setModel(new DefaultTableModel(
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
		tableInfo.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableInfo.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane_1.setViewportView(tableInfo);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setOpaque(true);
		lblPlayers.setBounds(31, 128, 48, 14);
		contentPane.add(lblPlayers);
		
		JLabel lblSelectedPlayerInfo = new JLabel("Selected Player Info");
		lblSelectedPlayerInfo.setOpaque(true);
		lblSelectedPlayerInfo.setBounds(258, 153, 130, 14);
		contentPane.add(lblSelectedPlayerInfo);
		
		//create combobox players in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboPlayers = new JComboBox(players.toArray());
		comboPlayers.setBounds(31, 95, 157, 22);
		contentPane.add(comboPlayers);
		
		JLabel lblSelectPlayer = new JLabel("Select Player");
		lblSelectPlayer.setOpaque(true);
		lblSelectPlayer.setBounds(31, 70, 100, 14);
		contentPane.add(lblSelectPlayer);
		
		JButton btnViewInfo = new JButton("View Info");
		btnViewInfo.setBounds(226, 95, 89, 23);
		contentPane.add(btnViewInfo);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(236, 427, 89, 23);
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
		DefaultTableModel model = (DefaultTableModel)tablePlayers.getModel();
		
		//get player position by looping through either model players or juniors depending on team type and finding player from SRU number
		if (teamType == 0) {
			for (Entry<Integer, Player> entry : theController.model.getPlayers().entrySet()) {
				for (int i = 1; i < players.size(); i++) {
					String[] pNameArray = players.get(i).split(" ");
					int sruNum = Integer.parseInt(pNameArray[2]);
					if (sruNum == entry.getValue().getSruNumber()) {
						model.addRow(new Object[] {players.get(i), entry.getValue().getPosition()});
					}
				}
			}
		}
		else {
			for (Entry<Integer, JuniorPlayer> entry : theController.model.getJuniors().entrySet()) {
				for (int i = 1; i < players.size(); i++) {
					String[] pNameArray = players.get(i).split(" ");
					int sruNum = Integer.parseInt(pNameArray[2]);
					if (sruNum == entry.getValue().getSruNumber()) {
						model.addRow(new Object[] {players.get(i), entry.getValue().getPosition()});
					}
				}
			}
		}
		
		//If back button pressed, open menu sending coach userType(1) and theController. dispose of current JFrame
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu mn = new Menu(1 ,theController);
				mn.setVisible(true);
				dispose();
			}
		});
		
		//When view info button clicked, loop through squads to get squad, loop through players within squad to get selected player and for each skill category, set rating and note for each skill
		btnViewInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPlayer = (String)comboPlayers.getSelectedItem();
				
				if (!selectedPlayer.equals("Select Player")) {
					for (int i = 0; i < theController.model.getSquadList().size(); i++) {
						if (theController.model.getSquadList().get(i).getSquadName().equals(squadName)) {
							for (int j = 0; j < theController.model.getSquadList().get(i).getSquad().size(); j++) {
								String[] pNameArray = selectedPlayer.split(" ");
								int sruNum = Integer.parseInt(pNameArray[2]);
								if (theController.model.getSquadList().get(i).getSquad().get(j).getSruNumber() == sruNum) {
									LinkedList<Skill> skills = new LinkedList<>();
									skills.addAll(theController.model.getSquadList().get(i).getSquad().get(j).getSkillCategories().get(0).getSkills());
									skills.addAll(theController.model.getSquadList().get(i).getSquad().get(j).getSkillCategories().get(1).getSkills());
									skills.addAll(theController.model.getSquadList().get(i).getSquad().get(j).getSkillCategories().get(2).getSkills());
									
									for (int k = 0; k < skills.size(); k++) {
										tableInfo.setValueAt(skills.get(k).getRating(), k, 2);
										tableInfo.setValueAt(skills.get(k).getNotes(), k, 3);
									}
								}
							}
						}
					}
				}
				
			}
		});
		
	}
}
