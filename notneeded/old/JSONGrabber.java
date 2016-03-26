import org.json.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.net.*;

public class JSONGrabber {
//	private static TreeMap<String, Game> gameKeys;

	public static TreeMap<String, Team> getStandings() {
		System.out.println("Getting standings...");
		return parseJSONStandings(getJSON("https://statsapi.web.nhl.com/api/v1/standings?expand=standings.record,standings.team,standings.division,standings.conference&season=20152016"));
	}

	private static String getJSON(String s) {
		URL statsURL;
		BufferedReader in;
		String json = "";

		try {
			statsURL = new URL(s);
			in = new BufferedReader(new InputStreamReader(statsURL.openStream()));
			String inputLine = "";

			while ((inputLine = in.readLine()) != null)
				json += inputLine + "\n";
			in.close();



		} catch (MalformedURLException e) {
			System.out.println("Got MalformedURLException");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Got IOException");
			e.printStackTrace();
			System.exit(1);
		}
		return json;
	}

	private static TreeMap<String, Team> parseJSONStandings(String s) {
//		TreeMap<String, Team> Team.teamKeys = Team.createTeams();
		for (String key : Team.teamKeys.keySet()) {
			StatGrabber.getStatsFile(Team.teamKeys.get(key));
			System.out.println("Getting " + key);
		}

		String teamName;
		String locationName;
		String venue;
		String abbreviation;
		String division;
		int wins;
		int losses;
		int OTL;
		int homeWins;
		int homeLosses;
		int homeOTL;
		int awayWins;
		int awayLosses;
		int awayOTL;
		int SOWins;
		int SOLosses;
		int lastTenWins;
		int lastTenLosses;
		int lastTenOTL;
		int goalsAgainst;
		int goalsScored;
		JSONObject jObj = new JSONObject(s);
		Team t;

		JSONArray recordArray = jObj.getJSONArray("records");
		for (int i = 0; i < recordArray.length(); i++) {
			JSONArray teamsArray = recordArray.getJSONObject(i).getJSONArray("teamRecords");
			for (int j = 0; j < teamsArray.length(); j++) {
				venue = teamsArray.getJSONObject(j).getJSONObject("team").getJSONObject("venue").getString("name");
				abbreviation = teamsArray.getJSONObject(j).getJSONObject("team").getString("abbreviation");
				teamName = teamsArray.getJSONObject(j).getJSONObject("team").getString("name");
				locationName = teamsArray.getJSONObject(j).getJSONObject("team").getString("locationName");
				division = teamsArray.getJSONObject(j).getJSONObject("team").getJSONObject("division").getString("name");
				wins = teamsArray.getJSONObject(j).getJSONObject("leagueRecord").getInt("wins");
				losses = teamsArray.getJSONObject(j).getJSONObject("leagueRecord").getInt("losses");
				OTL = teamsArray.getJSONObject(j).getJSONObject("leagueRecord").getInt("ot");
				homeWins = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(0).getInt("wins");
				homeLosses = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(0).getInt("losses");
				homeOTL = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(0).getInt("ot");
				awayWins = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(1).getInt("wins");
				awayLosses = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(1).getInt("losses");
				awayOTL = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(1).getInt("ot");
				SOWins = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(2).getInt("wins");
				SOLosses = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(2).getInt("losses");
				lastTenWins = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(3).getInt("wins");
				lastTenLosses = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(3).getInt("losses");
				lastTenOTL = teamsArray.getJSONObject(j).getJSONObject("records").getJSONArray("overallRecords").getJSONObject(3).getInt("ot");
				goalsAgainst = teamsArray.getJSONObject(j).getInt("goalsAgainst");
				goalsScored = teamsArray.getJSONObject(j).getInt("goalsScored");

				t = Team.teamKeys.get(abbreviation);
				t.setStats (wins, losses, OTL, division, venue, homeWins, homeLosses,
							homeOTL, awayWins, awayLosses, awayOTL, SOWins, SOLosses, lastTenWins,
							lastTenLosses, lastTenOTL, goalsAgainst, goalsScored);
			}
		}
		return Team.teamKeys;
	}

	public static void getJSONSchedule() {
		parseJSONSchedule(getJSON("https://statsapi.web.nhl.com/api/v1/schedule"));
	}

	private static void parseJSONSchedule(String s) {

//		g1 = new ArrayList<Game>();
		Game g;
		String d;
		String away;
		String home;
		String dateTime;
		JSONObject jObj = new JSONObject(s);
		d = jObj.getJSONArray("dates").getJSONObject(0).getString("date");

		JSONArray gameArray = jObj.getJSONArray("dates").getJSONObject(0).getJSONArray("games");
		for (int i = 0; i < gameArray.length(); i++) {
			home = gameArray.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getJSONObject("team").getString("name").toString();
			away = gameArray.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getJSONObject("team").getString("name").toString();
			dateTime = gameArray.getJSONObject(i).getString("gameDate");
			
			System.out.println(away + "@" + home + " @ " + dateTime);
			g = new Game(home, away, dateTime);
			Game.addGameToList(home, g);
		}
	}
}
