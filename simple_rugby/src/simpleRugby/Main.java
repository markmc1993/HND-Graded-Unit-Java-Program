package simpleRugby;

/**
 * 
 * <h1>Simply Rugby</h1>
 * The simpleRugby program is an application that is a skill tracker system that would be intended to be implemented into a larger application
 * for amateur rugby teams.
 * <p>
 * The app can be accessed by either an admin or a coach user. A different set of options would be available depending on the type of account
 * that logs in. If an admin logs in, they are displayed a menu with the option to add a user (admin or coach), edit users, add a squad,
 * edit squads, add a member, and edit members.
 * <p>
 * If a coach logs in, they are displayed a menu with the options to add a player, edit players, view their squad or edit their squad. If the coach
 * is not assigned to a squad, they will not have access to view squad or edit squad.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */
public class Main {
	
	boolean running = false;
	/**
	 * This is the main method which runs the Controller class
	 * @param args unused
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//run controller class
		@SuppressWarnings("unused")
		Controller myController = new Controller();
	}

}
