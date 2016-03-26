import java.util.Scanner;

public class Driver {

	public static void main (String args[]) {
		Team.createTeams();
		JSONGrabber.getJSONSchedule();
		JSONGrabber.getStandings();
		JSONGrabber.getTeamStats();
		FileOutputter.updateStats();

//		Team.teamKeys.get("NSH").getTeamLeaders();
		CreateGDTFile.insertAwayLines(Game.selectGame());
	}
























}
