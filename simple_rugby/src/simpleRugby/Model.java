package simpleRugby;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

public class Model {
	//setup variables to be used by class
	private String username;
	private HashMap<String, User> users = new HashMap<>();
	private HashMap<String, Member> members = new HashMap<>();
	private HashMap<Integer, Player> players = new HashMap<>();
	private HashMap<Integer, JuniorPlayer> juniors = new HashMap<>();
	private LinkedList<Squad> squadList = new LinkedList<>();
	private String[] positions = {"Select Positon","Full-back", "Left Wing", "Right Wing", "Inside Centre", "Outside Centre" ,"Fly-half", "Scrum-half", "Number Eight", "Blindside Flanker", "Openside Flanker", "Second Row",  "Hooker", "Loosehead Prop", "Tighthead Prop"};
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public HashMap<String, User> getUsers() {
		return users;
	}
	public void setUsers(HashMap<String, User> users) {
		this.users.putAll(users);
	}
	public HashMap<String, Member> getMembers() {
		return members;
	}
	public void setMembers(HashMap<String, Member> members) {
		this.members.putAll(members);;
	}
	public HashMap<Integer, Player> getPlayers() {
		return players;
	}
	public void setPlayers(HashMap<Integer, Player> players) {
		this.players.putAll(players);
	}
	public HashMap<Integer, JuniorPlayer> getJuniors() {
		return juniors;
	}
	public void setJuniors(HashMap<Integer, JuniorPlayer> juniors) {
		this.juniors.putAll(juniors);
	}
	public LinkedList<Squad> getSquadList() {
		return squadList;
	}
	public void setSquadList(LinkedList<Squad> squadList) {
		this.squadList.addAll(squadList);
	}
	
	public String[] getPositions() {
		return positions;
	}
	
	public void addUser(User user) {
		users.put(user.getUserName(), user);
	}
	
	public void removeUser(String userName) {
		users.remove(userName);
	}
	
	public void addMember(Member member) {
		members.put(member.getName(), member);
	}
	
	public void removeMember(String name) {
		members.remove(name);
	}
	
	public void addPlayer(Player player) {
		players.put(player.getSruNumber(), player);
	}
	
	public void removePlayer(int sruNum) {
		players.remove(sruNum);
	}
	
	public void addJunior(JuniorPlayer player) {
		juniors.put(player.getSruNumber(), player);
	}
	
	public void removeJunior(int sruNum) {
		juniors.remove(sruNum);
	}
	
	public void addSquad(Squad squad) {
		squadList.add(squad);
	}
	
	public void removeSquad(String squadName) {
		for (int i = 0; i < squadList.size(); i++) {
			if (squadList.get(i).getSquadName().equals(squadName)) {
				squadList.remove(i);
			}
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
	public void serialization() {
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
	
	/**
	 * 
	 * 
	 * This method is used to deserialize data from files to variables in Model to be used in app.
	 * <p>
	 * Check if a file exists, if not then create instance of DummyData and run to create an admin to be able to login.
	 * 
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deserialization(){
		File userFile = new File("users.ser");
		
		if (userFile.exists()) {
			try {
                FileInputStream fileIn = new FileInputStream("users.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                users = (HashMap) in.readObject();
                in.close();
            }catch(IOException i) {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c) {
                System.out.println(".User class not found.");
                c.printStackTrace();
                return;
            }
			
			
			
			try {
                FileInputStream fileIn = new FileInputStream("members.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                members = (HashMap) in.readObject();
                in.close();
            }catch(IOException i) {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c) {
                System.out.println(".Member class not found.");
                c.printStackTrace();
                return;
            }
			
			try {
				
                FileInputStream fileIn = new FileInputStream("players.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                players = (HashMap) in.readObject();
                in.close();
            }catch(IOException i) {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c) {
                System.out.println(".Player class not found.");
                c.printStackTrace();
                return;
            }
			
			try {
                FileInputStream fileIn = new FileInputStream("juniors.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                juniors = (HashMap) in.readObject();
                in.close();
            }catch(IOException i) {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c) {
                System.out.println(".JuniorPlayer class not found.");
                c.printStackTrace();
                return;
            }
			
			
			try {
                FileInputStream fileIn = new FileInputStream("squadlist.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                squadList = (LinkedList) in.readObject();
                in.close();
            }catch(IOException i) {
                i.printStackTrace();
                return;
            }catch(ClassNotFoundException c) {
                System.out.println("class not found.");
                c.printStackTrace();
                return;
            }
		}
		else {
			addData();
		}
	}
	
	
	/**
	 * 
	 * This method is used to create an initial admin for the app who would login and create other users.
	 * <p>
	 * Creates an instance of User and sets a first name, last name, username, password and user type then creates an
	 * instance of Serialize and runs to create files for persistent data
	 * 
	 * 
	 */
	public void addData() {
		
		User admin = new User("John", "Smith", "js1" , "admin1", 0);
		addUser(admin);
		
		
		
		serialization();
	}
	
}
