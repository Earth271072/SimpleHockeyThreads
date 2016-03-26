public class Skater extends Player {

	private int playerGamesPlayed;
	private int playerGoals;
	private int playerAssists;
	private int playerPoints;
	private int playerPlusMinus;
	private int playerPIM;
	private int playerPP;
	private int playerSH;
	private int playerGW;
	private int playerShots;
	private double playerShotPct;


	public Skater (int number, String position, String name, int gamesPlayed, int goals, int assists, int points,
				   int plusMinus, int pim, int pp, int sh, int gw, int shots, double shotPct) {
		super (number, position, name);
		playerGamesPlayed = gamesPlayed;
		playerGoals = goals;
		playerAssists = assists;
		playerPoints = points;
		playerPlusMinus = plusMinus;
		playerPIM = pim;
		playerPP = pp;
		playerSH = sh;
		playerGW = gw;
		playerShots = shots;
		playerShotPct = shotPct;
	}

	public int getGamesPlayed() {
		return playerGamesPlayed;
	}

	public int getGoals() {
		return playerGoals;
	}

	public int getAssists() {
		return playerAssists;
	}

	public int getPoints() {
		return playerPoints;
	}

	public int getPlusMinus() {
		return playerPlusMinus;
	}

	public int getPIM() {
		return playerPIM;
	}

	public int getPP() {
		return playerPP;
	}

	public int getSH() {
		return playerSH;
	}

	public int getGW() {
		return playerGW;
	}
	public int getShots() {
		return playerShots;
	}
	public double getShotPct() {
		return playerShotPct;
	}

	//override toString method
	public String toString() {
		return(super.getNumber() + " | " + super.getPosition() + " | " + super.getName() + " | " + playerGamesPlayed + " | " + playerGoals + " | " + playerAssists + " | " + playerPoints
			+ " | " + playerPlusMinus + " | " + playerPIM + " | " + playerPP + " | " + playerSH + " | " + playerGW + " | " + playerShots + " | " + playerShotPct);
	}



}

