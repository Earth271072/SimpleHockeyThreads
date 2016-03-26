import java.io.*;
import java.util.*;

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

	public static void insertAwayLines(Team away, Team home) {
		Line awayTeamLines = new Line(away.getLineCombinationsURL());
		Line homeTeamLines = new Line(home.getLineCombinationsURL());
		String templateString = getTemplate();
		String[][] forwardsAway;
		String[][] defenseAway;
		String[] goaliesAway;
		String[] IRAway;
		String sraway;
		String leadersAway;
		String goalieStatsAway;
		String radioAway = away.getStatsURL();

		String[][] forwardsHome;
		String[][] defenseHome;
		String[] goaliesHome;
		String[] IRHome;
		String srhome;
		String leadersHome;
		String goalieStatsHome;
		String radioHome = home.getStatsURL();
		
		forwardsAway = awayTeamLines.getForwards();
		defenseAway = awayTeamLines.getDefense();
		goaliesAway = awayTeamLines.getGoalies();
		IRAway = awayTeamLines.getIR();
		sraway = away.getSubreddit();
		leadersAway = away.getTeamLeaders();
		goalieStatsAway = away.getTeamGoalies();
		radioAway = radioAway.replace("stats.html", "RadioPlayer.htm");
		
		forwardsHome = homeTeamLines.getForwards();
		defenseHome = homeTeamLines.getDefense();
		goaliesHome = homeTeamLines.getGoalies();
		IRHome = homeTeamLines.getIR();
		srhome = home.getSubreddit();
		leadersHome = home.getTeamLeaders();
		goalieStatsHome = home.getTeamGoalies();
		radioHome = radioHome.replace("stats.html", "RadioPlayer.htm");
		
		

/*		for (int i = 0;file:///C:/Users/Earth/Desktop/GameTimeX%20NHL14-15%20v1.0.14.5/Data/today-schedule.html i < forwards.length; i++) {
			for (int j = 0; j < forwards[i].length; j++)
				System.out.print(forwards[i][j] + " ");
			System.out.println();
		}

		for (int i = 0; i < defense.length; i++) {
			for (int j = 0; j < defense[i].length; j++)
				System.out.print(defense[i][j] + " ");
			System.out.println();
		}*/


	templateString = templateString.replace("{home:arena}", home.getArenaName());
	templateString = templateString.replace("{home:arenaplace}", home.getTeamCity());

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
	
	templateString = templateString.replace("{awayleaders}", leadersAway);
	templateString = templateString.replace("{homeleaders}", leadersHome);
	
//	templateString = templateString.replace("{date:mmddyyyy}", Game.gameList.get(home).getGameDate());
	templateString = templateString.replace("{gametime:timezone}", 
		home.getTimeZone().replace("America/", "").replace("_", " "));
	System.out.print(templateString);
/*	System.out.println("\nAway Injuries");
	for (int i = 0; i < IRAway.length; i++)
		if (!IRAway[i].equals(""))
			System.out.println(IRAway[i]);


	System.out.println("\nHome Injuries");
	for (int i = 0; i < IRHome.length; i++)
		if (!IRHome[i].equals(""))
			System.out.println(IRHome[i]);*/
	}
}
