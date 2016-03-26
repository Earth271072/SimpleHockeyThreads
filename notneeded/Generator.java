import java.util.*;
import java.io.*;

public class Generator {

	public static void main (String[] args) {
		// gets team stats
		Team.createTeams();
//		TreeMap<String, Team> mapOfTeams = JSONGrabber.getStandings();
//		TreeMap<String, Team> teamAliases = Team.CreateTeamAliasMap(mapOfTeams);
		JSONGrabber.getJSONSchedule();

//		for (String key: mapOfTeams.keySet()) {
//			StatGrabber.getStatsFile(mapOfTeams.get(key));
		}
/*		for (String key: teamAliases.keySet()) {
			System.out.println(teamAliases.get(key).getFullTeamName());
		}*/
//		mapOfFullTeams.get("Nashville Predators").printGoalies();
	}



}
