package simpleRugby;

import java.util.LinkedList;
import java.io.Serializable;

/**
 * <h1>Squad</h1>
 * This class is a constructor used in the creation of squads to be stored in the app.
 * <p>
 * When creating a squad, squad name, coach name, and team type are required.
 * <p>
 * Players should get added once squad has been created, each including their constructors.
 * 
 * 
 * @author Mark McAllister
 * @since 01-05-2020
 *
 */

@SuppressWarnings("serial")
public class Squad implements Serializable {
	
	private String squadName;
	private String coach;
	private int teamType;
	private LinkedList<Player> squad = new LinkedList<>();

	public Squad(String squadName, String coach, int teamType) {
		this.squadName = squadName;
		this.coach = coach;
		this.teamType = teamType;
	}
	
	public String getSquadName() {
		return squadName;
	}



	public void setSquadName(String squadName) {
		this.squadName = squadName;
	}



	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public int getTeamType() {
		return teamType;
	}
	
	//method to return linkedlist of players when called
	public LinkedList<Player> getSquad() {
		return squad;
	}

	//method used to add players to squad
	public void addPlayer(Player player) {
		squad.add(player);
	}
	
	//toString to return string representations of details
	@Override
	public String toString() {
		return "Squad [squadName=" + squadName + ", coach=" + coach + ", teamType=" + teamType + "]";
	}
}
