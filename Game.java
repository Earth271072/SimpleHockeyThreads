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
	private String gameBroadcast;
	public static ArrayList<Game> gameList = new ArrayList<Game>();

	public Game (String home, String away, String inputDate, String ID, String broadcast) {
		homeTeamName = home;
		awayTeamName = away;
		originalDate = inputDate;
		gameID = ID;
		setTime();
		t = Team.teamAliases.get(homeTeamName);
		timeGame = new TimeGame(originalDate, t);
		tg = timeGame;
		gameBroadcast = broadcast;
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

	public String getBroadcast() {
		return gameBroadcast;
	}

	public static void listGames() {
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
		return gameList.get(i);
	}

	private static int numberGame() {
			Scanner s = new Scanner(System.in);
			int i = -1;
			boolean err = true;

			while (err) {
				s = new Scanner(System.in);
				if (s.hasNextInt()) {
					i = s.nextInt();
					if (i < 1 || i > gameList.size()) {
						System.out.print("Not a selection. Try again: ");
						continue;
					}
					else {
						err = false;
					}
				}
				else {
					System.out.print("\nInvalid input! Please try again! ");
					continue;
				}
			}

			return (i - 1);

	}
}