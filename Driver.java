import java.util.Scanner;

public class Driver {

	public static void main (String args[]) {
		Team.createTeams();
		JSONGrabber.getJSONSchedule();
		JSONGrabber.getStandings();
		JSONGrabber.getTeamStats();
		System.out.println("Updating stats now!");
//		FileOutputter.updateStats();
		JSONGrabber.getStats();
		System.out.println("All stats updated!");
//		Team.teamKeys.get("NSH").getTeamLeaders();
		CreateGDTFile.insertAwayLines(Game.selectGame());
	}
























}
