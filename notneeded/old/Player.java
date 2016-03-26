public abstract class Player {

	private int playerNumber;
	private String playerName;
	private String pos;

	public Player() {
		playerNumber = 00;
		playerName = "Barb Murphy";
	}

	public Player (int number, String position, String name) {
		playerNumber = number;
		playerName = name;
		pos = position;
	}

	public Player (int number, String name) {
		playerNumber = number;
		playerName = name;
		pos = "G";
	}

	public int getNumber() {
		return playerNumber;
	}

	public String getName() {
		return playerName;
	}

	public String getPosition() {
		return pos;
	}

/*	public static void printSkaterHeader() {
		System.out.println("#\tPos\tPlayer\t\tGP\tG\tA\tP\t+/-\tPIM\tPP\tSH\tGW\tS\tS%");
	}

	public static void printGoalieHeader() {
		System.out.println("#\tGoalie\t\tGPI\tGS\tMin\tGAA\tW\tL\tOT\tSO\tSA\tGA\tSv%\tG\tA\tPIM");
	}
*/



/*	// skaters
	public Player (int number, String position, String name, int gamesPlayed, int goals, int assists, int points, int plusMinus, int pim, int pp, int sh, int gw, int shots, double shotpct) {
		playerNumber = number;
		playerName = name;
		pos = position;
	}

	// goalies
	public Player (int number, String name, int GPI, int GS, int min, int GAA, int W, int L, int OT, int SO, int SA, int GA, double savepct, int G, int A, int PIM) {
		playerNumber = number;
		playerName = name;
		pos = "G";
	}
*/


}
