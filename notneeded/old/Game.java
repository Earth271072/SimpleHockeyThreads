import java.util.*;

public class Game {

	private String homeTeamName;
	private String awayTeamName;
	private String originalDate;
	private String gameTime;
	private String gameDate;
	private static HashMap<String, Game> gameList;

	public Game (String home, String away, String inputDate) {
		homeTeamName = home;
		awayTeamName = away;
		originalDate = inputDate;
		setTime();
	}

	public String getAway() {	// you scary!
		return awayTeamName;
	}

	public String getHome() {
		return homeTeamName;
	}

	public String getTime() {
		return gameTime;
	}

	public String getGameDate() {
		return gameDate;
	}
	
	private void setTime() {
		TimeGame gameTime = new TimeGame(originalDate, Team.teamAliases.get(homeTeamName));
		Team.teamAliases.get(homeTeamName).setTime(gameTime);
	}

	public static void addGameToList(String home, Game g) {
		gameList.put(home, g);
	}
	
//	public static void makeGameList(HashMap<String, Game> g) {
//		gameKeys = new TreeMap<String, Game>(g);
//	}
/*	private void setDate() {
		TimeGame gameDate = new TimeGame(originalDate, Team.teamAliases.get(homeTeamName));
		Team.teamAliases.get(homeTeamName).setGameDate(gameDate);
	}*/
}
