public class Driver {

	public static void main (String args[]) {
		Team.createTeams();
		JSONGrabber.getJSONSchedule();
		JSONGrabber.getStandings();
		JSONGrabber.getTeamStats();
		System.out.println("Updating stats now!");
		JSONGrabber.getStats();
		System.out.println("All stats updated!");
		CreateGDTFile.createGDTFile(Game.selectGame());
	}
























}
