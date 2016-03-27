import java.util.*;

public class Team {

	private String teamCity;
	private String teamName;
	private int teamWins;
	private int teamLosses;
	private int teamOvertimeLosses;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private String teamStatsURL;
	private String teamLineCombinationsURL;
	private String teamConference;
	private String teamArena;
	private int teamHomeWins;
	private int teamHomeLosses;
	private int teamHomeOTL;
	private int teamAwayWins;
	private int teamAwayLosses;
	private int teamAwayOTL;
	private int teamSOWins;
	private int teamSOLosses;
	private int teamLastTenWins;
	private int teamLastTenLosses;
	private int teamLastTenOTL;
	private int teamGoalsAgainst;
	private int teamGoalsScored;
	private Line l;
	private String teamSubreddit;
	private String teamTimeZone;
	private String teamFriendlyTimeZone;
	public static TreeMap<String, Team> teamKeys;
	public static TreeMap<String, Team> teamAliases;
	public static TreeMap<Integer, Team> teamRankTree;
	private TimeGame gameTime;
	private TimeGame gameDate;
	private int teamLeagueRank;
	private double teamFaceoffPct;
	private int teamGamesPlayed;
	private double teamGoalsAgainstPerGame;
	private int teamGoalsFor;
	private double teamGoalsForPerGame;
	private double teamPkPct;
	private double teamPpPct;
	private double teamShotsAgainstPerGame;
	private double teamShotsForPerGame;
	private int teamID;

	public Team (String city, String name, String statsURL, String lineCombinationsURL, String subreddit, String timeZone) {
		teamCity = city;
		teamName = name;
		teamStatsURL = statsURL;
		teamLineCombinationsURL = lineCombinationsURL;
		teamSubreddit = subreddit;
		teamTimeZone = timeZone;
		setFriendlyTimeZone();
	}

	public void setStats (int wins, int losses, int overtimeLosses, String conference,
					String arena, int homeWins, int homeLosses, int homeOTL, int awayWins, int awayLosses,
					int awayOTL, int SOWins, int SOLosses, int lastTenWins,	int lastTenLosses,
					int lastTenOTL, int goalsAgainst, int goalsScored, int leagueRank, int ID) {
		teamWins = wins;
		teamLosses = losses;
		teamOvertimeLosses = overtimeLosses;
		teamConference = conference;
		teamArena = arena;
		teamHomeWins = homeWins;
		teamHomeLosses = homeLosses;
		teamHomeOTL = homeOTL;
		teamAwayWins = awayWins;
		teamAwayLosses = awayLosses;
		teamAwayOTL = awayOTL;
		teamSOWins = SOWins;
		teamSOLosses = SOLosses;
		teamLastTenWins = lastTenWins;
		teamLastTenLosses = lastTenLosses;
		teamLastTenOTL = lastTenOTL;
		teamGoalsAgainst = goalsAgainst;
		teamGoalsScored = goalsScored;
		teamLeagueRank = leagueRank;
		teamID = ID;
	}

	public void setMiscStats (double faceoffPct, int gamesPlayed, int goalsAgainst, double goalsAgainstPerGame, int goalsFor, 
							double goalsForPerGame, double pkPct, double ppPct, double shotsAgainstPerGame, double shotsForPerGame) {
		 teamFaceoffPct = faceoffPct;
		 teamGamesPlayed = gamesPlayed;
		 teamGoalsAgainst = goalsAgainst;
		 teamGoalsAgainstPerGame = goalsAgainstPerGame;
		 teamGoalsFor = goalsFor;
		 teamGoalsForPerGame = goalsForPerGame;
		 teamPkPct = pkPct;
		 teamPpPct = ppPct;
		 teamShotsAgainstPerGame = shotsAgainstPerGame;
		 teamShotsForPerGame = shotsForPerGame;
	}


	public int getPoints() {
		return ((2 * teamWins) + (teamOvertimeLosses));
	}

	public String getTeamCity() {
		return teamCity;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getFullTeamName() {
		return (teamCity + " " + teamName);
	}

	public String getSubreddit() {
		return teamSubreddit;
	}

	public void printTeamName() {
		System.out.println(teamCity + " " + teamName);
	}

	public void addPlayerList(ArrayList<Player> players) {
		playerList = players;
	}

	public String getArenaName() {
		return teamArena;
	}

	public void printSkaters() {
		boolean printed = false;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i) instanceof Skater) {
				if (!printed) {
					StatGrabber.printSkaterHeader();
					printed = true;
				}
				System.out.println(playerList.get(i).toString());
			}
		}
	}

	public void printGoalies() {
		boolean printed = false;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i) instanceof Goalie) {
				if (!printed) {
					StatGrabber.printGoalieHeader();
					printed = true;
				}
			System.out.println(playerList.get(i).toString());
			}
		}
	}

	public void printPlayers() {
		printSkaters();
		System.out.println();
		printGoalies();
	}
	public String getStatsURL() {
		return teamStatsURL;
	}

	public String getLineCombinationsURL() {
		return teamLineCombinationsURL;
	}

	public void getLines() {
		l = new Line(teamLineCombinationsURL);
	}

	private void setFriendlyTimeZone() {
		if (teamTimeZone.equals("America/New_York"))
			teamFriendlyTimeZone = "Eastern";
		else if (teamTimeZone.equals("America/Chicago"))
			teamFriendlyTimeZone = "Central";
		else if (teamTimeZone.equals("America/Edmonton"))
			teamFriendlyTimeZone = "Mountain";
		else
			teamFriendlyTimeZone = "Pacific";
	}

	public String getTimeZone() {
		return teamTimeZone;
	}

	public void setTime (TimeGame time) {
		gameTime = time;
	}

	public String getTime() {
		return gameTime.getGameTime();
	}

	public String getDate() {
		return gameTime.getGameDate();
	}

	public String getWins() {
		return String.valueOf(teamWins);
	}

	public String getLosses() {
		return String.valueOf(teamLosses);
	}

	public String getOTL() {
		return String.valueOf(teamOvertimeLosses);
	}

	public String getRank() {
		return String.valueOf(teamLeagueRank);
	}

	public int getIntRank() {
		return teamLeagueRank;
	}
	
	public String getID() {
		return String.valueOf(teamID);
	}

	public String getTeamLeaders() {
		Skater leader1 = null;
		Skater leader2 = null;
		Skater leader3 = null;
		Skater tempPlayer = null;
		int tempPoints = 0;
		int leader1Points = 0;
		int leader2Points = 0;
		int leader3Points = 0;
		ArrayList<Skater> sk = new ArrayList<Skater>();
		for (int i = 0; i < playerList.size(); i++)
			if (playerList.get(i) instanceof Skater)
				sk.add((Skater)playerList.get(i));
		leader1 = sk.get(0);
		leader1Points = sk.get(0).getPoints();
		leader2 = sk.get(1);
		leader2Points = sk.get(1).getPoints();
		leader3 = sk.get(2);
		leader3Points = sk.get(2).getPoints();
/*		System.out.println(leader1.toString());
		System.out.println(leader2.toString());
		System.out.println(leader3.toString());	*/

		for (int i = 3; i < sk.size(); i++) {
			tempPlayer = sk.get(i);
			tempPoints = tempPlayer.getPoints();

			if (tempPoints >= leader3Points) {
				if (tempPoints > leader1Points) {
					leader3 = leader2;
					leader3Points = leader2Points;
					leader2 = leader1;
					leader2Points = leader1Points;
					leader1 = tempPlayer;
					leader1Points = tempPoints;
				}

				else if (tempPoints == leader1Points) {
					if (tempPlayer.getGoals() > leader1.getGoals()) {
						leader3 = leader2;
						leader3Points = leader2Points;
						leader2 = leader1;
						leader2Points = leader1Points;
						leader1 = tempPlayer;
						leader1Points = tempPoints;
					}

					else {
						leader3 = leader2;
						leader3Points = leader2Points;
						leader2 = tempPlayer;
						leader2Points = tempPoints;
					}
				}

				else if (tempPoints > leader2Points) {
					leader3 = leader2;
					leader3Points = leader2Points;
					leader2 = tempPlayer;
					leader2Points = tempPoints;
				}

				else if (tempPoints == leader2Points && tempPlayer.getGoals() > leader2.getGoals()) {
						leader3 = leader2;
						leader3Points = leader2Points;
						leader2 = tempPlayer;
						leader2Points = tempPoints;
				}

				else if (tempPoints == leader2Points && tempPlayer.getGoals() < leader2.getGoals()) {
						leader3 = tempPlayer;
						leader3Points = tempPoints;
				}

				else if (tempPoints == leader3Points) {
//					System.out.println(tempPlayer.getGoals());
//					System.out.println(leader3.getGoals());
					if (tempPlayer.getGoals() > leader3.getGoals()) {
						leader3 = tempPlayer;
						leader3Points = tempPoints;
					}
				}
			}
		}	// endfor
		return ("| " + "[](" + teamSubreddit + ")" + " | " + leader1.toString() + "\n" + "| " + "[](" + teamSubreddit + ")" + " | " + leader2.toString() + "\n" + "| " + "[](" + teamSubreddit + ")" + " | " + leader3.toString());

	}
	public String getTeamGoalies() {
		String gString = "";	// ha ha ha
		ArrayList<Goalie> gl = new ArrayList<Goalie>();

		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i) instanceof Goalie) {
				gl.add((Goalie)playerList.get(i));
			}
		}

		for (int i = 0; i < gl.size(); i++) {
			gString = gString + "| [](" + teamSubreddit + ") | " + gl.get(i).toString() + " |";
			if (i != gl.size() - 1) {
				gString = gString + "\n";
			}
		}

		return gString;
	}

	public String getStandings() {
		return ("| " + String.valueOf(teamLeagueRank) + " | [](" + teamSubreddit + ") | " + teamCity + " " +
				teamName + " | " + String.valueOf(teamWins) + " | " + String.valueOf(teamLosses) +
				" | " + String.valueOf(teamOvertimeLosses) + " | " + String.valueOf(getPoints()) +
				" | " + String.valueOf(teamPpPct) + " | " + String.valueOf(teamPkPct) + " | " +
				teamFaceoffPct + " |\n");
	}

	public static void createTeams() {
		HashMap<String, Team> teamNames = new HashMap<String, Team>();
		Team ANA = new Team("Anaheim", "Ducks", "http://ducks.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/13/anaheim-ducks/", "/r/anaheimducks", "America/Vancouver");
			teamNames.put("ANA", ANA);
		Team ARI = new Team("Arizona", "Coyotes", "http://coyotes.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/35/arizona-coyotes", "/r/coyotes", "America/Phoenix");
			teamNames.put("ARI", ARI);
		Team BOS = new Team("Boston", "Bruins", "http://bruins.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/15/boston-bruins/", "/r/bostonbruins", "America/New_York");
			teamNames.put("BOS", BOS);
		Team BUF = new Team("Buffalo", "Sabres", "http://sabres.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/16/buffalo-sabres/", "/r/sabres", "America/New_York");
			teamNames.put("BUF", BUF);
		Team CGY = new Team("Calgary", "Flames", "http://flames.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/17/calgary-flames", "/r/calgaryflames", "America/Edmonton");
			teamNames.put("CGY", CGY);

		Team CAR = new Team("Carolina", "Hurricanes", "http://hurricanes.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/18/carolina-hurricanes", "/r/canes", "America/New_York");
			teamNames.put("CAR", CAR);
		Team CHI = new Team("Chicago", "Blackhawks", "http://blackhawks.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/19/chicago-blackhawks", "/r/hawks", "America/Chicago");
			teamNames.put("CHI", CHI);
		Team COL = new Team("Colorado", "Avalanche", "http://avalanche.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/20/colorado-avalanche", "/r/coloradoavalanche", "America/Edmonton");
			teamNames.put("COL", COL);
		Team CBJ = new Team("Columbus", "Blue Jackets", "http://bluejackets.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/21/columbus-blue-jackets", "/r/bluejackets", "America/Chicago");
			teamNames.put("CBJ", CBJ);
		Team DAL = new Team("Dallas", "Stars", "http://stars.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/22/dallas-stars", "/r/dallasstars", "America/Chicago");
			teamNames.put("DAL", DAL);

		Team DET = new Team("Detroit", "Red Wings", "http://redwings.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/23/detroit-red-wings", "/r/detroitredwings", "America/New_York");
			teamNames.put("DET", DET);
		Team EDM = new Team("Edmonton", "Oilers", "http://oilers.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/24/edmonton-oilers", "/r/edmontonoilers", "America/Edmonton");
			teamNames.put("EDM", EDM);
		Team FLA = new Team("Florida", "Panthers", "http://panthers.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/25/florida-panthers", "/r/floridapanthers", "America/New_York");
			teamNames.put("FLA", FLA);
		Team LAK = new Team("Los Angeles", "Kings", "http://kings.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/26/los-angeles-kings", "/r/losangeleskings", "America/Vancouver");
			teamNames.put("LAK", LAK);
		Team MIN = new Team("Minnesota", "Wild", "http://wild.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/27/minnesota-wild", "/r/wildhockey", "America/Chicago");
			teamNames.put("MIN", MIN);

		Team MTL = new Team("Montreal", "Canadiens", "http://canadiens.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/28/montreal-canadiens", "/r/habs", "America/New_York");
			teamNames.put("MTL", MTL);
		Team NSH = new Team("Nashville", "Predators", "http://predators.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/29/nashville-predators", "/r/predators", "America/Chicago");
			teamNames.put("NSH", NSH);
		Team NJD = new Team("New Jersey", "Devils", "http://devils.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/30/new-jersey-devils", "/r/devils", "America/New_York");
			teamNames.put("NJD", NJD);
		Team NYI = new Team("New York", "Islanders", "http://islanders.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/31/new-york-islanders", "/r/newyorkislanders", "America/New_York");
			teamNames.put("NYI", NYI);
		Team NYR = new Team("New York", "Rangers", "http://rangers.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/32/new-york-rangers", "/r/rangers", "America/New_York");
			teamNames.put("NYR", NYR);

		Team OTT = new Team("Ottawa", "Senators", "http://senators.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/33/ottawa-senators", "/r/ottawasenators", "America/New_York");
			teamNames.put("OTT", OTT);
		Team PHI = new Team("Philadelphia", "Flyers", "http://flyers.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/34/philadelphia-flyers", "/r/flyers", "America/New_York");
			teamNames.put("PHI", PHI);
		Team PIT = new Team("Pittsburgh", "Penguins", "http://penguins.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/36/pittsburgh-penguins", "/r/penguins", "America/New_York");
			teamNames.put("PIT", PIT);
		Team SJS = new Team("San Jose", "Sharks", "http://sharks.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/37/san-jose-sharks", "/r/sanjosesharks", "America/Vancouver");
			teamNames.put("SJS", SJS);
		Team STL = new Team("St. Louis", "Blues", "http://blues.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/38/st-louis-blues", "/r/stlouisblues", "America/Chicago");
			teamNames.put("STL", STL);

		Team TBL = new Team("Tampa Bay", "Lightning", "http://lightning.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/39/tampa-bay-lightning", "/r/tampabaylightning", "America/New_York");
			teamNames.put("TBL", TBL);
		Team TOR = new Team("Toronto", "Maple Leafs", "http://mapleleafs.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/40/toronto-maple-leafs", "/r/leafs", "America/New_York");
			teamNames.put("TOR", TOR);
		Team VAN = new Team("Vancouver", "Canucks", "http://canucks.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/41/vancouver-canucks", "/r/canucks", "America/Vancouver");
			teamNames.put("VAN", VAN);
		Team WSH = new Team("Washington", "Capitals", "http://capitals.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/42/washington-capitals", "/r/caps", "America/New_York");
			teamNames.put("WSH", WSH);
		Team WPG = new Team("Winnipeg", "Jets", "http://jets.nhl.com/club/stats.htm", "http://www2.dailyfaceoff.com/teams/lines/14/winnipeg-jets", "/r/winnipegjets", "America/Chicago");
			teamNames.put("WPG", WPG);
		teamKeys = new TreeMap<String, Team>(teamNames);
		CreateTeamAliasMap();
/*		for (String key : teamKeys) {
			String value = teamNames.get(key);
		}*/
	}

	public static void CreateTeamAliasMap() {
		HashMap<String, Team> teamNames = new HashMap<String, Team>();
		for (String key: teamKeys.keySet()) {
			teamNames.put(teamKeys.get(key).getFullTeamName(), teamKeys.get(key));
		}
		teamAliases = new TreeMap<String, Team>(teamNames);
	}

	public static void CreateRankTree() {
		HashMap<Integer, Team> teamRanks = new HashMap<Integer, Team>();

		for (String key: teamKeys.keySet()) {
			teamRanks.put(teamKeys.get(key).getIntRank(), teamKeys.get(key));
		}
		teamRankTree = new TreeMap<Integer, Team>(teamRanks);
/*		for (Integer key : teamRankTree.keySet())
			System.out.println("#" + key + " " + teamRankTree.get(key).getTeamName()); */
	}

	public static String getSkaterHeader() {
		return ("| # | Pos | Player | GP | G | A | P | +/- | PIM | PP | SH | GW | S | S%\n:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:");
	}

	public static String getGoalieHeader() {
		return ("| # | Goalie | GPI | GS | Min | GAA | W | L | OT | SO | SA | GA | Sv% | G | A | PIM\n:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:");
	}
	


}

