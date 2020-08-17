package simpleRugby;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;


/**
 * <h1>Menu</h1>
 * This JFrame class is used by users to select what they wish to do in the app. The menu displayed will depend on the usertype of the user.
 * <p>
 * If the user is an admin (usertype 0) they will have 7 options on what they can do. These are to add user, view/edit users, add squad,
 * view/edit squads, add member, view/edit members, or logout. The button the admin clicks will open the correct JFrame and close the menu.
 * <p>
 * If the user is a coach (usertype 1) they will have 5 options on what they can do. These are to add player, view/edit players, my squad, 
 * view/edit squad, and logout. The button the coach clicks will open the correct JFrame and close the menu. However, if the coach is not
 * assigned to a squad, they will not have access to my squad or view/edit squad and will instead get a message telling them to request an
 * admin assign them to a squad.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
@SuppressWarnings("serial")
public class Menu extends JFrame {
	//setup variables to be used by class
	private JPanel contentPane;
	private Controller theController;
	

	/**
	 * This method creates the JFrame and creates each of the designed attributes needed and placing them in the desired area of the frame
	 * 
	 * @param cont This is the parameter of the Controller class
	 * 
	 */
	public Menu(int userType, Controller cont) {		
		theController = cont;
		
		//Every time menu runs, run serialize to ensure updates are saved
		theController.model.serialization();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(156, 133, 150, 40);
		contentPane.add(btnAddUser);
		
		JButton btnViewSquads = new JButton("View/Edit Squads");
		btnViewSquads.setBounds(364, 228, 150, 40);
		contentPane.add(btnViewSquads);
		
		JButton btnAddSquad = new JButton("Add Squad");
		btnAddSquad.setBounds(156, 228, 150, 40);
		contentPane.add(btnAddSquad);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.setBounds(156, 326, 150, 40);
		contentPane.add(btnAddMember);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setOpaque(true);
		lblMenu.setBackground(Color.LIGHT_GRAY);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMenu.setBounds(217, 38, 229, 29);
		contentPane.add(lblMenu);		
		
		JButton btnEditUsers = new JButton("View/Edit User");
		btnEditUsers.setBounds(364, 133, 150, 40);
		contentPane.add(btnEditUsers);
		
		JButton btnEditMember = new JButton("View/Edit Members");
		btnEditMember.setBounds(364, 326, 150, 40);
		contentPane.add(btnEditMember);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(555, 0, 89, 23);
		contentPane.add(btnLogout);
		
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
		
		
		//Switch statement, to display correct menu depending on usertype
		switch(userType) {
		case 0:
			//Set menu label to "Admin Menu"
			lblMenu.setText("Admin Menu");
			//When add user button is clicked, open AddUser sending theController, dispose of current JFrame
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddUser au = new AddUser(theController);
					au.setVisible(true);
					dispose();
				}
			});
			
			//When edit user button is clicked, open EditUser sending theController, dispose of current JFrame
			btnEditUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditUser ed = new EditUser(theController);
					ed.setVisible(true);
					dispose();
				}
			});
			
			//When Logout button is clicked, open Login, dispose of current JFrame
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login lg = new Login(theController);
					lg.setVisible(true);
					dispose();
				}
			});
			
			//When add squad button is clicked, open AddSquad sending theController, dispose of current JFrame
			btnAddSquad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddSquad as = new AddSquad(theController);
					as.setVisible(true);
					dispose();
				}
			});
			
			//When view squads button is clicked, open EditSquad sending theController, dispose of current JFrame
			btnViewSquads.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditSquad es = new EditSquad(theController);
					es.setVisible(true);
					dispose();
				}
			});
			
			//When add member button is clicked, open AddMember sending theController, dispose of current JFrame
			btnAddMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddMember am = new AddMember(theController);
					am.setVisible(true);
					dispose();
				}
			});
			
			//When edit member button is clicked, open EditMember sending theController, dispose of current JFrame
			btnEditMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditMember em = new EditMember(theController);
					em.setVisible(true);
					dispose();
				}
			});
			
			break;
		case 1:
			//Set menu lable to "Coach Menu" change button text to reflect coach options. Hide extra buttons
			lblMenu.setText("Coach Menu");
			btnAddUser.setText("Add Player");
			btnAddSquad.setText("My Squad");
			btnEditUsers.setText("View/Edit Players");
			btnViewSquads.setText("View/Edit Squad");
			btnAddMember.setVisible(false);
			btnEditMember.setVisible(false);
			
			//When Logout button is clicked, open Login, dispose of current JFrame
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login lg = new Login(theController);
					lg.setVisible(true);
					dispose();
				}
			});
			
			//When add user button is clicked, open AddPlayer sending theController, dispose of current JFrame
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddPlayer ap = new AddPlayer(theController);
					ap.setVisible(true);
					dispose();
				}
			});
			
			//When edit users button is clicked, open EditPlayer sending theController, dispose of current JFrame
			btnEditUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditPlayer ep = new EditPlayer(theController);
					ep.setVisible(true);
					dispose();
				}
			});
			
			//When add sqaud button is clicked, check coach is assigned to squad. If so open MySquad sending theController, dispose of current JFrame
			//If not, message displays advising to get admin to assign to squad
			btnAddSquad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean coach = false;
					for (int i = 0; i < theController.model.getSquadList().size(); i++) {
						if (theController.model.getSquadList().get(i).getCoach().equals(theController.model.getUsername())) {
							coach = true;
						}
					}
					
					if (coach) {
					MySquad ms = new MySquad(theController);
					ms.setVisible(true);
					dispose();
					}
					else {
						theController.messageDialogue("You are currently not a coach of any squad. Speak to an admin to add you to a squad");
					}
				}
			});
			
			//When view squads button is clicked, check coach is assigned to squad. If so open EditMySquad sending theController, dispose of current JFrame
			//If not, message displays advising to get admin to assign to squad
			btnViewSquads.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//if add squad button clicked
					boolean coach = false;
					for (int i = 0; i < theController.model.getSquadList().size(); i++) {
						if (theController.model.getSquadList().get(i).getCoach().equals(theController.model.getUsername())) {
							coach = true;
						}
					}
					
					if (coach) {
					EditMySquad ems = new EditMySquad(theController);
					ems.setVisible(true);
					dispose();
					}
					else {
						theController.messageDialogue("You are currently not a coach of any squad. Speak to an admin to add you to a squad");
					}
				}
			});
			
			break;
		}
		
	}
	
	/**
	 * 
	 * 
	 * This method is used to serialize data to files from variables in Model to be deserialized when app is next used.
	 * <p>
	 * If file doesn't already exist, create file. Otherwise write over file with stored data to update if changes occur.
	 * 
	 *
	 */
	public void serialization(HashMap<String, User> users, HashMap<String, Member> members, HashMap<Integer, Player> players, HashMap<Integer, JuniorPlayer> juniors, LinkedList<Squad> squadList) {
		try {			
            FileOutputStream fileOut = new FileOutputStream("users.ser", false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
		
		
		
		try {			
            FileOutputStream fileOut = new FileOutputStream("members.ser", false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(members);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
		
		try {			
            FileOutputStream fileOut = new FileOutputStream("players.ser", false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(players);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
		
		try {			
            FileOutputStream fileOut = new FileOutputStream("juniors.ser", false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(juniors);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
		
		
		try {			
            FileOutputStream fileOut = new FileOutputStream("squadList.ser", false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(squadList);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
	}
}