import java.util.*;

public class Game {

	private String homeTeamName;
	private String awayTeamName;
	private String originalDate;
	private String gameTime;
	private String gameID;
	private TimeGame tg;
	private TimeGame timeGame;
	private Team t;
//	public static HashMap<String, Game> gameList = new HashMap<String, Game>();
	public static ArrayList<Game> gameList = new ArrayList<Game>();

	public Game (String home, String away, String inputDate, String ID) {
		homeTeamName = home;
		awayTeamName = away;
		originalDate = inputDate;
		gameID = ID;
		setTime();
		t = Team.teamAliases.get(homeTeamName);
		timeGame = new TimeGame(originalDate, t);
		tg = timeGame;
/*		System.out.println(tg.getGameTime());
		System.out.println(tg.getGameDate());
		System.out.println(tg.getEastern());
		System.out.println(tg.getCentral());
		System.out.println(tg.getMountain());
		System.out.println(tg.getPacific());*/
	}

	public String getAway() {	// you scary!
		return awayTeamName;
	}

	public String getHome() {
		return homeTeamName;
	}

	public Team getAwayTeam() {
		return Team.teamAliases.get(awayTeamName);
	}

	public Team getHomeTeam() {
		return Team.teamAliases.get(homeTeamName);
	}

	public String getTime() {
		return gameTime;
	}

	public String getGameID() {
		return gameID;
	}

	private void setTime() {
		TimeGame gameTime = new TimeGame(originalDate, Team.teamAliases.get(homeTeamName));
		Team.teamAliases.get(homeTeamName).setTime(gameTime);
	}

	public static void addGameToList(String home, Game g) {
		gameList.add(g);
	}
	public String getLocalGameTime() {
		return tg.getGameTime();
	}

	public String getGameDate() {
		return tg.getGameDate();
	}

	public String getEastern() {
		return tg.getEastern();
	}

	public String getCentral() {
		return tg.getCentral();
	}

	public String getMountain() {
		return tg.getMountain();
	}

	public String getPacific() {
		return tg.getPacific();
	}

	public String getDayOfWeek() {
		return tg.getDayOfWeek();
	}
	public static void listGames() {
/*		for (String key: gameList.keySet()) {
			System.out.println(gameList.get(key).getAway() + " @ " +
			gameList.get(key).getHome() + " " + gameList.get(key).getLocalGameTime() +
			" " + gameList.get(key).getGameDate());
		}*/
		for (int i = 0; i < gameList.size(); i++) {
			System.out.println( (i+1) + ". " + gameList.get(i).getAway() + " @ " +
					gameList.get(i).getHome());
		}
	}

	public static Game selectGame() {
		int i = 0;
		listGames();
		System.out.print("Please select a game: ");
		i = numberGame();

		System.out.println(i);
		return gameList.get(i);
	}

	private static int numberGame() {
			Scanner s = new Scanner(System.in);
			int i = -1;
			boolean err = true;
//			System.out.println("Input now! ");

			while (err) {
				s = new Scanner(System.in);
				if (s.hasNextInt()) {
					i = s.nextInt();
					err = false;
				}
				else {
					System.out.print("\nInvalid input! Please try again! ");
					continue;
				}
				if (i < 1 || i > gameList.size()) {
					System.out.print("Not a selection. Try again: ");
				}
			}

			return (i - 1);

	}
}

//	public static void makeGameList(HashMap<String, Game> g) {
//		gameKeys = new TreeMap<String, Game>(g);
//	}
/*	private void setDate() {
		TimeGame gameDate = new TimeGame(originalDate, Team.teamAliases.get(homeTeamName));
		Team.teamAliases.get(homeTeamName).setGameDate(gameDate);
	}*/
