package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 * 
 * <h1>Add Player</h1>
 * This JFrame class is used by coaches to add players to the app. The coach will fill out the form, selecting if player being entered is a junior
 * or senior. Checks will occur to ensure that the coach has entered a first name, last name, sru number and a guardian name if a junior player.
 * <p>
 * The option is there to set inital skills and notes for the player being added but if these are not entered, they will be added as a rating
 * of 0 and notes will be left blank, to be set later by a coach.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class AddPlayer extends JFrame {
	
	//setup variables to be used by class
	private JPanel contentPane;
	private Controller theController;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textGuardian;
	private JTable table;
	private JTextField textSRUNum;
	private String[] nums = {"1","2","3","4","5"};
	private String[] teamType = {"Select Team Type", "Senior", "Junior"};
	
	
	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public AddPlayer(Controller cont) {
		theController = cont;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//create a JScrollpane and add JTable with column titles Skill Category, Skill, Rating, and Notes and populate with object[][]
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 124, 506, 204);
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
				String.class, Object.class, Integer.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		TableColumn ratingColumn = table.getColumnModel().getColumn(2);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox ratings = new JComboBox(nums);
		ratingColumn.setCellEditor(new DefaultCellEditor(ratings));
		
		
		
		textFirstName = new JTextField();
		textFirstName.setBounds(59, 36, 110, 20);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setBounds(241, 36, 110, 20);
		contentPane.add(textLastName);
		textLastName.setColumns(10);
		
		textSRUNum = new JTextField();
		textSRUNum.setBounds(400, 36, 107, 20);
		contentPane.add(textSRUNum);
		textSRUNum.setColumns(10);
		
		JLabel lblEnterNum = new JLabel("Enter SRU number");
		lblEnterNum.setOpaque(true);
		lblEnterNum.setBounds(400, 11, 107, 14);
		contentPane.add(lblEnterNum);
		
		//create combobox using teamType in the form of array
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboTeamType = new JComboBox(teamType);
		comboTeamType.setBounds(153, 91, 110, 22);
		contentPane.add(comboTeamType);
		
		JLabel lblFirstName = new JLabel("Enter First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblFirstName.setOpaque(true);
		lblFirstName.setBounds(59, 11, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Enter Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLastName.setOpaque(true);
		lblLastName.setBounds(241, 11, 110, 14);
		contentPane.add(lblLastName);
		
		JLabel lblSelectTeamType = new JLabel("Select Team Type");
		lblSelectTeamType.setOpaque(true);
		lblSelectTeamType.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectTeamType.setBounds(153, 67, 110, 14);
		contentPane.add(lblSelectTeamType);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboPosition = new JComboBox(theController.model.getPositions());
		comboPosition.setBounds(325, 91, 110, 22);
		contentPane.add(comboPosition);
		
		JLabel lblSelectPosition = new JLabel("Select Position");
		lblSelectPosition.setOpaque(true);
		lblSelectPosition.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectPosition.setBounds(325, 67, 110, 14);
		contentPane.add(lblSelectPosition);
		
		textGuardian = new JTextField();
		textGuardian.setBounds(29, 383, 110, 20);
		contentPane.add(textGuardian);
		textGuardian.setColumns(10);
		
		JLabel lblGuardianName = new JLabel("Enter Guardan Name *Junior Only*");
		lblGuardianName.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuardianName.setOpaque(true);
		lblGuardianName.setBounds(29, 360, 200, 14);
		contentPane.add(lblGuardianName);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(29, 414, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Menu");
		btnBack.setBounds(246, 414, 89, 23);
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
		 * When submit button is clicked check that the user has entered information into textFirstName and textLastName.
		 * if both contain information, check for sruNumber. If valid sru number entered ensure valid team and position selected.
		 * <p>
		 * If all valid, and player type is senior check to see if player exists and if not, create player from information entered and
		 * set skills, add to skill classes and add to user. Player is then added to players hashmap with sruNumber being the key and 
		 * Player being the value.
		 * <p>
		 * If junior player, check a guardian name entered. If so, Create JuniorPlayer from information entered and
		 * set skills, add to skill classes and add to user. JuniorPlayer is then added to players hashmap with sruNumber being the key and 
		 * JuniorPlayer being the value.
		 * <p>
		 * if information not entered or invalid, exception occurs with a message reminding the user to enter a first, last name, sru number. 
		 * Reminds to add guardian for junior player
		 */
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//validate textFirstName and textLastName
				try {
					theController.checkString(textFirstName.getText());
					theController.checkString(textLastName.getText());
					//validate textSRUNum
					try {
						theController.checkNumber(textSRUNum.getText());
						String comboTeam = (String)comboTeamType.getSelectedItem();
						String position = (String)comboPosition.getSelectedItem();
						String fName = textFirstName.getText().substring(0,1).toUpperCase() + textFirstName.getText().substring(1);
						String lName = textLastName.getText().substring(0,1).toUpperCase() + textLastName.getText().substring(1);
						//check team type
						if (!comboTeam.equals("Select Team Type") && !position.equals("Select Positon")) {
							if (comboTeam.equals("Junior")) {
								//validate textGuardian
								try {
									theController.checkString(textGuardian.getText());
									boolean exists = false;
									//loop juniors hashmap checking if sruNumber already exists
									for (Entry<Integer, JuniorPlayer> entry : theController.model.getJuniors().entrySet()) {
										if (entry.getValue().getSruNumber() == Integer.parseInt(textSRUNum.getText())) {
											exists = true;
										}
									}
									//if it doesn't exist
									if (!exists) {
										//get entered table values and set each skill to enter into a skill category and then be stored in the junior player in juniors in controller
										System.out.println(table.getValueAt(0, 2));
										Skill sk1 = new Skill((String)table.getValueAt(0, 1),  Integer.parseInt((String)table.getValueAt(0, 2)), (String)table.getValueAt(0, 3));
										Skill sk2 = new Skill((String)table.getValueAt(1, 1),  Integer.parseInt((String)table.getValueAt(1, 2)), (String)table.getValueAt(1, 3));
										Skill sk3 = new Skill((String)table.getValueAt(2, 1),  Integer.parseInt((String)table.getValueAt(2, 2)), (String)table.getValueAt(2, 3));
										SkillCategory sc1 = new SkillCategory((String)table.getValueAt(0, 0));
										sc1.addSkill(sk1);
										sc1.addSkill(sk2);
										sc1.addSkill(sk3);
										
										Skill sk4 = new Skill((String)table.getValueAt(3, 1),  Integer.parseInt((String)table.getValueAt(3, 2)), (String)table.getValueAt(3, 3));
										Skill sk5 = new Skill((String)table.getValueAt(4, 1),  Integer.parseInt((String)table.getValueAt(4, 2)), (String)table.getValueAt(4, 3));
										Skill sk6 = new Skill((String)table.getValueAt(5, 1),  Integer.parseInt((String)table.getValueAt(5, 2)), (String)table.getValueAt(5, 3));
										Skill sk7 = new Skill((String)table.getValueAt(6, 1),  Integer.parseInt((String)table.getValueAt(6, 2)), (String)table.getValueAt(6, 3));
										SkillCategory sc2 = new SkillCategory((String)table.getValueAt(3, 0));
										sc2.addSkill(sk4);
										sc2.addSkill(sk5);
										sc2.addSkill(sk6);
										sc2.addSkill(sk7);
										
										Skill sk8 = new Skill((String)table.getValueAt(7, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(7, 3));
										Skill sk9 = new Skill((String)table.getValueAt(8, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(8, 3));
										Skill sk10 = new Skill((String)table.getValueAt(9, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(9, 3));
										Skill sk11 = new Skill((String)table.getValueAt(10, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(10, 3));
										SkillCategory sc3 = new SkillCategory((String)table.getValueAt(7, 0));
										sc3.addSkill(sk8);
										sc3.addSkill(sk9);
										sc3.addSkill(sk10);
										sc3.addSkill(sk11);
										
										JuniorPlayer jp1 = new JuniorPlayer(fName, lName, position,  Integer.parseInt(textSRUNum.getText()), textGuardian.getText());
										jp1.addSkillCategory(sc1);
										jp1.addSkillCategory(sc2);
										jp1.addSkillCategory(sc3);
										theController.model.addJunior(jp1);
										theController.messageDialogue(jp1.getName() + " added to junior players");
										Menu mn = new Menu(1 ,theController);
										mn.setVisible(true);
										dispose();
										}
										else {
											theController.messageDialogue("Player already in system");
										}									
									
								}catch(Exception o) {
									theController.messageDialogue(o.toString() + ", ensure guardian name is entered");
								}
							}
							else {
								boolean exists = false;
								//loop players hashmap checking if sruNumber already exists
								for (Entry<Integer, Player> entry : theController.model.getPlayers().entrySet()) {
									if (entry.getValue().getSruNumber() == Integer.parseInt(textSRUNum.getText())) {
										exists = true;
									}
								}
								//get entered table values and set each skill to enter into a skill category and then be stored in the junior player in players in controller
								if (!exists) {
									Skill sk1 = new Skill((String)table.getValueAt(0, 1),  Integer.parseInt((String)table.getValueAt(0, 2)), (String)table.getValueAt(0, 3));
									Skill sk2 = new Skill((String)table.getValueAt(1, 1),  Integer.parseInt((String)table.getValueAt(1, 2)), (String)table.getValueAt(1, 3));
									Skill sk3 = new Skill((String)table.getValueAt(2, 1),  Integer.parseInt((String)table.getValueAt(2, 2)), (String)table.getValueAt(2, 3));
									SkillCategory sc1 = new SkillCategory((String)table.getValueAt(0, 0));
									sc1.addSkill(sk1);
									sc1.addSkill(sk2);
									sc1.addSkill(sk3);
									
									Skill sk4 = new Skill((String)table.getValueAt(3, 1),  Integer.parseInt((String)table.getValueAt(3, 2)), (String)table.getValueAt(3, 3));
									Skill sk5 = new Skill((String)table.getValueAt(4, 1),  Integer.parseInt((String)table.getValueAt(4, 2)), (String)table.getValueAt(4, 3));
									Skill sk6 = new Skill((String)table.getValueAt(5, 1),  Integer.parseInt((String)table.getValueAt(5, 2)), (String)table.getValueAt(5, 3));
									Skill sk7 = new Skill((String)table.getValueAt(6, 1),  Integer.parseInt((String)table.getValueAt(6, 2)), (String)table.getValueAt(6, 3));
									SkillCategory sc2 = new SkillCategory((String)table.getValueAt(3, 0));
									sc2.addSkill(sk4);
									sc2.addSkill(sk5);
									sc2.addSkill(sk6);
									sc2.addSkill(sk7);
									
									Skill sk8 = new Skill((String)table.getValueAt(7, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(7, 3));
									Skill sk9 = new Skill((String)table.getValueAt(8, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(8, 3));
									Skill sk10 = new Skill((String)table.getValueAt(9, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(9, 3));
									Skill sk11 = new Skill((String)table.getValueAt(10, 1),  Integer.parseInt((String)table.getValueAt(7, 2)), (String)table.getValueAt(10, 3));
									SkillCategory sc3 = new SkillCategory((String)table.getValueAt(7, 0));
									sc3.addSkill(sk8);
									sc3.addSkill(sk9);
									sc3.addSkill(sk10);
									sc3.addSkill(sk11);
									
									Player p1 = new Player(fName, lName, position, Integer.parseInt(textSRUNum.getText()));
									p1.addSkillCategory(sc1);
									p1.addSkillCategory(sc2);
									p1.addSkillCategory(sc3);
									
									theController.model.addPlayer(p1);;
									theController.messageDialogue(p1.getName() + " added to players");
									//open menu sending coach userType(1) and theController. dispose of current JFrame
									Menu mn = new Menu(1 ,theController);
									mn.setVisible(true);
									dispose();
								}
								else {
									theController.messageDialogue("Player already in system");
								}
							}
						}
						else {
							theController.messageDialogue("Ensure team type and position have been entered");
						}
					}catch(Exception n) {
						theController.messageDialogue(n.toString());
					}				
				}catch(Exception m) {
					theController.messageDialogue(m.toString() + ", ensure both first and last name are entered");
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
		
		
		
	}
}
