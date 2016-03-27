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
}
