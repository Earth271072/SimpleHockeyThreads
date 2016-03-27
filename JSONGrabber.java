import org.json.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class JSONGrabber {
	public static HashMap<String, Game> gameKeys = new HashMap<String, Game>();

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

	
	public static void getStandings() {
		System.out.println("Getting league standings...");
		parseJSONStandings(getJSON("https://statsapi.web.nhl.com/api/v1/standings?expand=standings.record,standings.team,standings.division,standings.conference&season=20152016"));
	}

	private static void/*TreeMap<String, Team>*/ parseJSONStandings(String s) {

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
		int ID;
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
				ID = teamsArray.getJSONObject(j).getJSONObject("team").getInt("id");
				
				t = Team.teamKeys.get(abbreviation);
				t.setStats (wins, losses, OTL, division, venue, homeWins, homeLosses,
							homeOTL, awayWins, awayLosses, awayOTL, SOWins, SOLosses, lastTenWins,
							lastTenLosses, lastTenOTL, goalsAgainst, goalsScored, leagueRank, ID);
			}
		}
		Team.CreateRankTree();
	}

	public static void getJSONSchedule() {
		parseJSONSchedule(getJSON("https://statsapi.web.nhl.com/api/v1/schedule?expand=schedule.broadcasts.all"));
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
		
//		String json = getJSON("https://statsapi.web.nhl.com/api/v1/schedule?startDate=" + date + "&expand=schedule.broadcasts.all");
		String broadcast = "";
		String date;

		JSONArray gameArray = jObj.getJSONArray("dates").getJSONObject(0).getJSONArray("games");
		for (int i = 0; i < gameArray.length(); i++) {
			home = gameArray.getJSONObject(i).getJSONObject("teams").getJSONObject("home").getJSONObject("team").getString("name").toString();
			away = gameArray.getJSONObject(i).getJSONObject("teams").getJSONObject("away").getJSONObject("team").getString("name").toString();
			dateTime = gameArray.getJSONObject(i).getString("gameDate");
			Integer id = new Integer(gameArray.getJSONObject(i).getInt("gamePk"));
			gameID = id.toString();
			date = dateTime.replace("/", "-");
			broadcast = "";
			for (int j = 0; j < (gameArray.getJSONObject(i).getJSONArray("broadcasts").length()); j++) {
				broadcast += gameArray.getJSONObject(j).getJSONArray("broadcasts").getJSONObject(j).getString("name");
				if (j < (gameArray.getJSONObject(i).getJSONArray("broadcasts").length() - 1)) {
					broadcast += ", ";
				}
			}
			
			
			g = new Game(home, away, dateTime, gameID, broadcast);

			gameKeys.put(home, g);
			Game.addGameToList(home, g);
		}
	}
	
	public static void getStats () {
		final String URL ="https://statsapi.web.nhl.com/api/v1/teams?site=en_nhl&teamId=&expand=team.roster,roster.person,person.stats&stats=statsSingleSeason";
		String json = "";
		String id;
		for (String key: Team.teamKeys.keySet()) {
			id = Team.teamKeys.get(key).getID();		
			json = getJSON(URL.replace("teamId=", ("teamId=" + id)));
			getPlayerStats(key, json);
		}		
	}
	
	public static void getPlayerStats(String key, String json) {
		Integer integer;
		String string;
		Double doub;
		ArrayList<Object[]> skatersList = new ArrayList<Object[]>();
		ArrayList<Object[]> goaliesList = new ArrayList<Object[]>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		Object skaters[] = new Object[14];
		Object goalies[] = new Object[16];
		Skater s;
		Goalie g;
		Scanner scan = new Scanner(System.in);
		String answer;
		int number;
		String position;
		String name;

		int gamesPlayed;
		int goals;
		int assists;
		int points;
		int plusMinus;
		int pim;
		int pp;
		int sh;
		int gw;
		int shots;
		double shotPct;

		int gamesPlayedIn;
		int gamesStarted;
//		int minutes;
		double goalsAgainstAverage;
		int wins;
		int losses;
		int overtimeLosses;
		int shutouts;
		int shotsAgainst;
		int goalsAgainst;
		double savePercentage;
		int penaltyMinutes;
		
		JSONObject jObj = new JSONObject(json);
		JSONArray a = jObj.getJSONArray("teams").getJSONObject(0).getJSONObject("roster").getJSONArray("roster");
		JSONObject b;
		
		for (int i = 0; i < a.length(); i++) {
			b = a.getJSONObject(i).getJSONObject("person").getJSONArray("stats").getJSONObject(0);
			if (b.getJSONArray("splits").length() > 0) {
				
				b.getJSONArray("splits").length();
				b = b.getJSONArray("splits").getJSONObject(0).getJSONObject("stat");
				name = a.getJSONObject(i).getJSONObject("person").getString("fullName");
				number = a.getJSONObject(i).getJSONObject("person").getInt("primaryNumber");
				position = a.getJSONObject(i).getJSONObject("person").getJSONObject("primaryPosition").getString("code");
				if (position.equals("G")) {
	//				minutes = b.getString("timeOnIce").replace(":",;
					gamesPlayedIn = b.getInt("games");
					gamesStarted = b.getInt("gamesStarted");
	//				minutes = b.
					goalsAgainstAverage = b.getDouble("goalAgainstAverage");
					wins = b.getInt("wins");
					losses = b.getInt("losses");
					overtimeLosses = b.getInt("ot");
					shutouts = b.getInt("shutouts");
					shotsAgainst = b.getInt("shotsAgainst");
					goalsAgainst = b.getInt("goalsAgainst");
					savePercentage = b.getDouble("savePercentage");
	//				goals = b.getInt(
	//				assists = b.getJSONObject(i)
	//				penaltyMinutes = b.getJSONObject(i)			
					g = new Goalie (number, name, gamesPlayedIn, gamesStarted, goalsAgainstAverage, wins, losses, overtimeLosses, shutouts, shotsAgainst, goalsAgainst, savePercentage);
					playerList.add(g);
				}


				else {
					b = a.getJSONObject(i).getJSONObject("person").getJSONArray("stats").getJSONObject(0);
		
					b = b.getJSONArray("splits").getJSONObject(0).getJSONObject("stat");
					name = a.getJSONObject(i).getJSONObject("person").getString("fullName");
					number = a.getJSONObject(i).getJSONObject("person").getInt("primaryNumber");
					position = a.getJSONObject(i).getJSONObject("person").getJSONObject("primaryPosition").getString("code");
					gamesPlayed  = b.getInt("games"); 
					goals  = b.getInt("goals"); 
					assists  = b.getInt("assists"); 
					points = b.getInt("points");
					plusMinus = b.getInt("plusMinus"); 
					pim = b.getInt("pim"); 
					pp = b.getInt("powerPlayGoals"); 
					sh = b.getInt("shortHandedGoals"); 
					gw = b.getInt("gameWinningGoals"); 
					shots = b.getInt("shots"); 
					shotPct = b.getDouble("shotPct"); 
					s = new Skater(number, position, name, gamesPlayed, goals, assists, points, plusMinus, pim, pp, sh, gw, shots, shotPct);
					playerList.add(s);
				}
			}
			Team.teamKeys.get(key).addPlayerList(playerList);
		}
	}
}