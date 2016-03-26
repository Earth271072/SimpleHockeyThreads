import org.json.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class JSONGrabber {
	public static HashMap<String, Game> gameKeys = new HashMap<String, Game>();

	public static void/*TreeMap<String, Team>*/ getStandings() {
		System.out.println("Getting league standings...");
		/*return */parseJSONStandings(getJSON("https://statsapi.web.nhl.com/api/v1/standings?expand=standings.record,standings.team,standings.division,standings.conference&season=20152016"));
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

	private static void/*TreeMap<String, Team>*/ parseJSONStandings(String s) {
//		TreeMap<String, Team> Team.teamKeys = Team.createTeams();
/*		for (String key : Team.teamKeys.keySet()) {
			StatGrabber.getStatsFile(Team.teamKeys.get(key));
			System.out.println("Getting team stats for " + Team.teamKeys.get(key).getFullTeamName());
		}*/

		System.out.println("Getting team records...");
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
		int leagueRank;
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
				leagueRank = teamsArray.getJSONObject(j).getInt("leagueRank");

				t = Team.teamKeys.get(abbreviation);
				t.setStats (wins, losses, OTL, division, venue, homeWins, homeLosses,
							homeOTL, awayWins, awayLosses, awayOTL, SOWins, SOLosses, lastTenWins,
							lastTenLosses, lastTenOTL, goalsAgainst, goalsScored, leagueRank);
			}
		}
		Team.CreateRankTree();
//		return Team.teamKeys;
	}

	public static void getJSONSchedule() {
		parseJSONSchedule(getJSON("https://statsapi.web.nhl.com/api/v1/schedule"));
	}

	public static void getTeamStats() {
		getTeamStats(getJSON("http://www.nhl.com/stats/rest/grouped/teams/season/teamsummary?cayenneExp=seasonId=20152016%20and%20gameTypeId=2"));
	}

	private static void getTeamStats(String s) {
		double faceoffPct = 0;
		int gamesPlayed;
		int goalsAgainst;
		double goalsAgainstPerGame;
		int goalsFor;
		double goalsForPerGame;
		double pkPct;
		double ppPct;
		double shotsAgainstPerGame;
		double shotsForPerGame;
		String teamAbbrev;
		JSONObject jObj = new JSONObject(s);
		JSONObject t;
		JSONArray a = jObj.getJSONArray("data");
		Team te;	
		
		for (int i = 0; i < a.length(); i++) {
			faceoffPct = a.getJSONObject(i).getDouble("faceoffWinPctg");
			gamesPlayed = a.getJSONObject(i).getInt("gamesPlayed");
			goalsAgainst = a.getJSONObject(i).getInt("goalsAgainst");
			goalsAgainstPerGame = a.getJSONObject(i).getDouble("goalsAgainstPerGame");
			goalsFor = a.getJSONObject(i).getInt("goalsFor");
			goalsForPerGame = a.getJSONObject(i).getDouble("goalsForPerGame");
			pkPct = a.getJSONObject(i).getDouble("pkPctg");
			ppPct = a.getJSONObject(i).getDouble("ppPctg");
			shotsAgainstPerGame = a.getJSONObject(i).getDouble("shotsAgainstPerGame");
			shotsForPerGame = a.getJSONObject(i).getDouble("shotsForPerGame");
			teamAbbrev = a.getJSONObject(i).getString("teamAbbrev");
			te = Team.teamKeys.get(teamAbbrev);
			te.setMiscStats(faceoffPct, gamesPlayed, goalsAgainst, goalsAgainstPerGame, goalsFor, goalsForPerGame, pkPct, ppPct, shotsAgainstPerGame, shotsForPerGame);
		
		}
		
	}
	
	
	
	private static void parseJSONSchedule(String s) {

//		g1 = new ArrayList<Game>();
//		HashMap<String, Game> gameList = new HashMap<String, Game>();
		Game g;
//		String d;
		String away;
		String home;
		String dateTime;
		String gameID;
		JSONObject jObj = new JSONObject(s);
//		d = jObj.getJSONArray("dates").getJSONObject(0).getString("date");

		JSONArray gameArray = jObj.getJSONArray("dates").getJSONObject(0).getJSONArray("games");
		for (int i = 0; i < gameArray.length(); i++) {
			home = gameArray.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getJSONObject("team").getString("name").toString();
			away = gameArray.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getJSONObject("team").getString("name").toString();
			dateTime = gameArray.getJSONObject(i).getString("gameDate");
			Integer id = new Integer(gameArray.getJSONObject(i).getInt("gamePk"));
			gameID = id.toString();
//			System.out.println(away + "@" + home + " @ " + dateTime);
			g = new Game(home, away, dateTime, gameID);
			gameKeys.put(home, g);
			Game.addGameToList(home, g);
		}
//		Game.listGames();
	}
}
