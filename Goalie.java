public class Goalie extends Player {

	private int gamesPlayedIn;
	private int gamesStarted;
	private int minutes;
	private double goalsAgainstAverage;
	private int wins;
	private int losses;
	private int overtimeLosses;
	private int shutouts;
	private int shotsAgainst;
	private int goalsAgainst;
	private double savePercentage;
	private int goals;
	private int assists;
	private int penaltyMinutes;

	// goalies
	public Goalie (int number, String name, int GPI, int GS, int min, double GAA, int W, int L, int OT, int SO, int SA, int GA, double savepct, int G, int A, int PIM) {
		super(number, name);
		gamesPlayedIn = GPI;
		gamesStarted = GS;
		minutes = min;
		goalsAgainstAverage = GAA;
		wins = W;
		losses = L;
		overtimeLosses = OT;
		shutouts = SO;
		shotsAgainst = SA;
		goalsAgainst = GA;
		savePercentage = savepct;
		goals = G;
		assists = A;
		penaltyMinutes = PIM;
	}

	public int getGamesPlayedIn() {
		return gamesPlayedIn;
	}
	public int getGamesStarted() {
		return gamesStarted;
	}
	public int getMinutes() {
		return minutes;
	}
	public double getGoalsAgainstAverage() {
		return goalsAgainstAverage;
	}
	public int getWins() {
		return wins;
	}
	public int getLosses() {
		return losses;
	}
	public int getOvertimeLosses() {
		return overtimeLosses;
	}
	public int getShutouts() {
		return shutouts;
	}
	public int getShotsAgainst() {
		return shotsAgainst;
	}
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	public double getSavePercentage() {
		return savePercentage;
	}
	public int getGoals() {
		return goals;
	}
	public int getAssists() {
		return assists;
	}
	public int getPenaltyMinutes() {
		return penaltyMinutes;
	}

	// override toString() method
	public String toString() {
		return(super.getNumber() + " | " + super.getName() + " | "/*+ super.getPosition() + " | "*/ + gamesPlayedIn + " | " + gamesStarted + " | " + minutes + " | " + goalsAgainstAverage
		+ " | " + wins + " | " + losses + " | " + overtimeLosses + " | " + shutouts + " | " + shotsAgainst + " | " + goalsAgainst + " | " + savePercentage + " | " + goals + " | " + assists
		+ " | " + penaltyMinutes);
	}

}


