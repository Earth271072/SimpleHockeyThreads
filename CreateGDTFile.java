import java.io.*;

public class CreateGDTFile {

	public static String getTemplate() {
	String inputLine = "";
	String templateString = "";
		try {
			File template = new File("default-hockeybot.txt");
			BufferedReader in = new BufferedReader(new FileReader(template));

			while ((inputLine = in.readLine()) != null)
				templateString += (inputLine + "\n");
		}
		catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return templateString;
	}

	public static void createGDTFile(Game g) {
		Team away = g.getAwayTeam();
		Team home = g.getHomeTeam();
		Line awayTeamLines = new Line(away.getLineCombinationsURL());
		Line homeTeamLines = new Line(home.getLineCombinationsURL());
		String templateString = getTemplate();
		String[][] forwardsAway;
		String[][] defenseAway;
		String[] goaliesAway;
		String sraway;
		String goalieStatsAway;
		String radioAway = away.getStatsURL();
		String[][] forwardsHome;
		String[][] defenseHome;
		String[] goaliesHome;
		String srhome;
		String goalieStatsHome;
		String radioHome = home.getStatsURL();
		forwardsAway = awayTeamLines.getForwards();
		defenseAway = awayTeamLines.getDefense();
		goaliesAway = awayTeamLines.getGoalies();
		sraway = away.getSubreddit();
		goalieStatsAway = away.getTeamGoalies();
		radioAway = radioAway.replace("stats.html", "RadioPlayer.htm");

		forwardsHome = homeTeamLines.getForwards();
		defenseHome = homeTeamLines.getDefense();
		goaliesHome = homeTeamLines.getGoalies();
		srhome = home.getSubreddit();
		goalieStatsHome = home.getTeamGoalies();
		radioHome = radioHome.replace("stats.html", "RadioPlayer.htm");

		templateString = templateString.replace("{league:rankings:Rank,redditicon,Team,W,L,OT,P,PP%,PK%,FO%}",
												getRanks(away, home));

		templateString = templateString.replace("{date:dayofweek}", g.getDayOfWeek());
		templateString = templateString.replace("{away:short}", away.getTeamName());
		templateString = templateString.replace("{away:stats:w}", away.getWins());
		templateString = templateString.replace("{away:stats:l}", away.getLosses());
		templateString = templateString.replace("{away:stats:ot}", away.getOTL());
		templateString = templateString.replace("{away:stats:rank}", away.getRank());

		templateString = templateString.replace("{home:short}", home.getTeamName());
		templateString = templateString.replace("{home:stats:w}", home.getWins());
		templateString = templateString.replace("{home:stats:l}", home.getLosses());
		templateString = templateString.replace("{home:stats:ot}", home.getOTL());
		templateString = templateString.replace("{home:stats:rank}", home.getRank());

		templateString = templateString.replace("{date:mmddyyyy}", g.getGameDate());
		templateString = templateString.replace("{home:arena}", home.getArenaName());
		templateString = templateString.replace("{home:arenaplace}", home.getTeamCity());

		templateString = templateString.replace("{game:nhlgameid}", g.getGameID());

		templateString = templateString.replace("{awayleaders}", away.getTeamLeaders());
		templateString = templateString.replace("{homeleaders}", home.getTeamLeaders());

		templateString = templateString.replace("{leaderheader}", Team.getSkaterHeader());
		templateString = templateString.replace("{goalieheader}", Team.getGoalieHeader());

		templateString = templateString.replace("{gametime:pst}", g.getPacific());
		templateString = templateString.replace("{gametime:mst}", g.getMountain());
		templateString = templateString.replace("{gametime:cst}", g.getCentral());
		templateString = templateString.replace("{gametime:est}", g.getEastern());

		templateString = templateString.replace("{away:oleft1}", forwardsAway[0][0]);
		templateString = templateString.replace("{away:ocenter1}", forwardsAway[0][1]);
		templateString = templateString.replace("{away:oright1}", forwardsAway[0][2]);

		templateString = templateString.replace("{away:oleft2}", forwardsAway[1][0]);
		templateString = templateString.replace("{away:ocenter2}", forwardsAway[1][1]);
		templateString = templateString.replace("{away:oright2}", forwardsAway[1][2]);

		templateString = templateString.replace("{away:oleft3}", forwardsAway[2][0]);
		templateString = templateString.replace("{away:ocenter3}", forwardsAway[2][1]);
		templateString = templateString.replace("{away:oright3}", forwardsAway[2][2]);

		templateString = templateString.replace("{away:oleft4}", forwardsAway[3][0]);
		templateString = templateString.replace("{away:ocenter4}", forwardsAway[3][1]);
		templateString = templateString.replace("{away:oright4}", forwardsAway[3][2]);

		templateString = templateString.replace("{away:dleft1}", defenseAway[0][0]);
		templateString = templateString.replace("{away:dright1}", defenseAway[0][1]);

		templateString = templateString.replace("{away:dleft2}", defenseAway[1][0]);
		templateString = templateString.replace("{away:dright2}", defenseAway[1][1]);

		templateString = templateString.replace("{away:dleft3}", defenseAway[2][0]);
		templateString = templateString.replace("{away:dright3}", defenseAway[2][1]);

		templateString = templateString.replace("{away:goalie1}", goaliesAway[0]);
		templateString = templateString.replace("{away:goalie2}", goaliesAway[1]);

		templateString = templateString.replace("{home:oleft1}", forwardsHome[0][0]);
		templateString = templateString.replace("{home:ocenter1}", forwardsHome[0][1]);
		templateString = templateString.replace("{home:oright1}", forwardsHome[0][2]);

		templateString = templateString.replace("{home:oleft2}", forwardsHome[1][0]);
		templateString = templateString.replace("{home:ocenter2}", forwardsHome[1][1]);
		templateString = templateString.replace("{home:oright2}", forwardsHome[1][2]);

		templateString = templateString.replace("{home:oleft3}", forwardsHome[2][0]);
		templateString = templateString.replace("{home:ocenter3}", forwardsHome[2][1]);
		templateString = templateString.replace("{home:oright3}", forwardsHome[2][2]);

		templateString = templateString.replace("{home:oleft4}", forwardsHome[3][0]);
		templateString = templateString.replace("{home:ocenter4}", forwardsHome[3][1]);
		templateString = templateString.replace("{home:oright4}", forwardsHome[3][2]);

		templateString = templateString.replace("{home:dleft1}", defenseHome[0][0]);
		templateString = templateString.replace("{home:dright1}", defenseHome[0][1]);

		templateString = templateString.replace("{home:dleft2}", defenseHome[1][0]);
		templateString = templateString.replace("{home:dright2}", defenseHome[1][1]);

		templateString = templateString.replace("{home:dleft3}", defenseHome[2][0]);
		templateString = templateString.replace("{home:dright3}", defenseHome[2][1]);

		templateString = templateString.replace("{home:goalie1}", goaliesHome[0]);
		templateString = templateString.replace("{home:goalie2}", goaliesHome[1]);

		templateString = templateString.replace("{away:redditicon}", "[](" + sraway + ")");
		templateString = templateString.replace("{home:redditicon}", "[](" + srhome + ")");

		templateString = templateString.replace("{awaygoalies}", goalieStatsAway);
		templateString = templateString.replace("{homegoalies}", goalieStatsHome);

		templateString = templateString.replace("{away:radio}", radioAway);
		templateString = templateString.replace("{home:radio}", radioHome);

		templateString = templateString.replace("{gametime:timezone}",
			timeZone(home.getTimeZone().replace("America/", "").replace("_", " ")));

		templateString = templateString.replace("{gametime:local}", g.getLocalGameTime());
		templateString = templateString.replace("{away:city}", away.getTeamCity());
		templateString = templateString.replace("{home:city}", home.getTeamCity());
		templateString = templateString.replace("{game:broadcast}", g.getBroadcast());

		writeFile(templateString, away, home, g);
	}

	private static String getRanks(Team away, Team home) {
		String s = "| Rank | | Team | W | L | OT | P | PP% | PK% | FO% |\n:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:\n";
		String awayString = "";
		String homeString = "";
		int awayRank = away.getIntRank();
		int homeRank = home.getIntRank();

		if (((awayRank - homeRank) == 1) || (awayRank - homeRank == -1)) {
			if (awayRank > homeRank) {
				if (homeRank == 1) {
					homeString += Team.teamRankTree.get(1).getStandings();
					awayString += Team.teamRankTree.get(2).getStandings();
					awayString += Team.teamRankTree.get(3).getStandings();
					awayString += Team.teamRankTree.get(4).getStandings();
				}
				else if (awayRank == 30) {
					homeString += Team.teamRankTree.get(27).getStandings();
					homeString += Team.teamRankTree.get(28).getStandings();
					homeString += Team.teamRankTree.get(29).getStandings();
					awayString += Team.teamRankTree.get(30).getStandings().replace("| ", "**").replace(" |", "**");
				}
				else {
					homeString += Team.teamRankTree.get(homeRank - 1).getStandings();
					homeString += Team.teamRankTree.get(homeRank).getStandings();
					awayString += Team.teamRankTree.get(awayRank).getStandings();
					awayString += Team.teamRankTree.get(awayRank + 1).getStandings();

				}
				s += homeString + awayString;
			}
			else {
				if (awayRank == 1) {
					awayString += Team.teamRankTree.get(1).getStandings();
					homeString += Team.teamRankTree.get(2).getStandings();
					homeString += Team.teamRankTree.get(3).getStandings();
					homeString += Team.teamRankTree.get(4).getStandings();
				}
				else if (homeRank == 30) {
					awayString += Team.teamRankTree.get(27).getStandings();
					awayString += Team.teamRankTree.get(28).getStandings();
					awayString += Team.teamRankTree.get(29).getStandings();
					homeString += Team.teamRankTree.get(30).getStandings();
				}
				else {
					awayString += Team.teamRankTree.get(awayRank - 1).getStandings();
					awayString += Team.teamRankTree.get(awayRank).getStandings();
					homeString += Team.teamRankTree.get(homeRank).getStandings();
					homeString += Team.teamRankTree.get(homeRank + 1).getStandings();
				}
				s += awayString + homeString;
			}

		}
		else if (((awayRank - homeRank) == 2) || (awayRank - homeRank == -2)) {
			if (awayRank > homeRank) {
				if (homeRank == 1) {
					homeString += Team.teamRankTree.get(1).getStandings();
					homeString += Team.teamRankTree.get(2).getStandings();
					awayString += Team.teamRankTree.get(3).getStandings();
					awayString += Team.teamRankTree.get(4).getStandings();
					awayString += Team.teamRankTree.get(5).getStandings();
				}
				else if (awayRank == 30) {
					homeString += Team.teamRankTree.get(26).getStandings();
					homeString += Team.teamRankTree.get(27).getStandings();
					homeString += Team.teamRankTree.get(28).getStandings();
					awayString += Team.teamRankTree.get(29).getStandings();
					awayString += Team.teamRankTree.get(30).getStandings();
				}
				s += homeString + awayString;
			}
			else {
				if (awayRank == 1) {
					awayString += Team.teamRankTree.get(1).getStandings();
					awayString += Team.teamRankTree.get(2).getStandings();
					homeString += Team.teamRankTree.get(3).getStandings();
					homeString += Team.teamRankTree.get(4).getStandings();
					homeString += Team.teamRankTree.get(5).getStandings();
				}
				else if (homeRank == 30) {
					awayString += Team.teamRankTree.get(26).getStandings();
					awayString += Team.teamRankTree.get(27).getStandings();
					awayString += Team.teamRankTree.get(28).getStandings();
					homeString += Team.teamRankTree.get(29).getStandings();
					homeString += Team.teamRankTree.get(30).getStandings();
				}
				s += awayString + homeString;
			}
		}

		else {
			if (awayRank > homeRank) {
				if (homeRank == 1) {
					homeString += Team.teamRankTree.get(1).getStandings().replace("| ", "| **").replace(" |", "** |");
					homeString += Team.teamRankTree.get(2).getStandings();
					homeString += Team.teamRankTree.get(3).getStandings();
				}
				else {
					homeString += Team.teamRankTree.get(homeRank - 1).getStandings();
					homeString += Team.teamRankTree.get(homeRank).getStandings().replace("| ", "| **").replace(" |", "** |");
					homeString += Team.teamRankTree.get(homeRank + 1).getStandings();
				}

				if (awayRank == 30) {
					awayString += Team.teamRankTree.get(28).getStandings();
					awayString += Team.teamRankTree.get(29).getStandings();
					awayString += Team.teamRankTree.get(30).getStandings().replace("| ", "| **").replace(" |", "** |");
				}
				else {
					awayString += Team.teamRankTree.get(awayRank - 1).getStandings();
					awayString += Team.teamRankTree.get(awayRank).getStandings().replace("| ", "| **").replace(" |", "** |");
					awayString += Team.teamRankTree.get(awayRank + 1).getStandings();
				}
				s += homeString + awayString;
			}
			else {
				if (awayRank == 1) {
					awayString += Team.teamRankTree.get(1).getStandings().replace("| ", "| **").replace(" |", "** |");
					awayString += Team.teamRankTree.get(2).getStandings();
					awayString += Team.teamRankTree.get(3).getStandings();
				}
				else {
					awayString += Team.teamRankTree.get(awayRank - 1).getStandings();
					awayString += Team.teamRankTree.get(awayRank).getStandings().replace("| ", "| **").replace(" |", "** |");
					awayString += Team.teamRankTree.get(awayRank + 1).getStandings();
				}

				if (homeRank == 30) {
					homeString += Team.teamRankTree.get(28).getStandings();
					homeString += Team.teamRankTree.get(29).getStandings();
					homeString += Team.teamRankTree.get(30).getStandings().replace("| ", "| **").replace(" |", "** |");
				}
				else {
					homeString += Team.teamRankTree.get(homeRank - 1).getStandings();
					homeString += Team.teamRankTree.get(homeRank).getStandings().replace("| ", "| **").replace(" |", "** |");
					homeString += Team.teamRankTree.get(homeRank + 1).getStandings();
				}
				s += awayString + homeString;
			}
		}
		return s;
	}


	private static void writeFile(String s, Team away, Team home, Game g) {
		try {
			File folder = new File("Generated");

			if (!folder.exists()) {
					System.out.println("Stats folder does not exist. Attempting to create...");
				if (folder.mkdir()) {
					System.out.println("Successfully created folder!");
				}
				else
					System.out.println("Failed to create stats folder...");
			}
			File f = new File(folder.getAbsolutePath(), ((away.getTeamName() + "@" + home.getTeamName() + "_" + (g.getGameDate().replace("/", "-")) + ".txt").replace(" ", "_")));

			BufferedWriter buf = new BufferedWriter(new FileWriter(f));
			buf.write(s);
			buf.flush();
			buf.close();


			if (System.getProperty("os.name").contains("Windows"))
				System.out.print("Successfully printed GDT at " + folder.getAbsolutePath() + "\\" + away.getTeamName() +
								 "-at-" + home.getTeamName() + "-" + g.getGameDate().replace("/", "-") + ".txt");
			else
				System.out.print("Successfully printed GDT at " + folder.getAbsolutePath() + "/" + away.getTeamName() +
								 "-at-" + home.getTeamName() + "-" + g.getGameDate().replace("/","-") + ".txt");

		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}




	private static String timeZone(String s) {
		if (s.equals("New_York"))
			return "ET";
		else if (s.equals("Chicago"))
			return "CT";
		else if (s.equals("Edmonton"))
			return "MT";
		else
			return "PT";
	}
}
