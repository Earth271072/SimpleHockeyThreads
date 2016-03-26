public class Driver {

	public static void main (String args[]) {
		Team.createTeams();
		JSONGrabber.getJSONSchedule();
		JSONGrabber.getStandings();
		for (String key: Team.teamKeys.keySet()) {
			System.out.println("Getting stats for " + Team.teamKeys.get(key).getFullTeamName() + "...");
			StatGrabber.getStatsFile(Team.teamKeys.get(key));
		}

		CreateGDTFile.insertAwayLines(Team.teamKeys.get("NSH"), Team.teamKeys.get("EDM"));
	}
























}
