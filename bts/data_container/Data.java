package data_container;

public final class Data {// Singleton Object, holds all data for GameGUI
	private static Data instance = null;

	public static Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}
	private final BattleshipAI AI = new BattleshipAI();
	private int count = 0;
	private int enemy = 1;
	private int mode = -1;

	private int[] nShip = { 0, 0 };
	private Player[] player = new Player[2];

	private int turn = 0;

	private Data() {
		player[0] = new Player();
		player[1] = new Player();
	}

	/**
	 * check if a square is valid for firing at
	 * @param sq the square to test
	 * @return whether it should be shot at or not
	 */
	public boolean canMove(int sq) {
		if (player[turn].sqHit.contains(sq) || sq < 0 || sq > 99) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * converts text to number
	 * when exception occurs, value becomes -1
	 * @param toConvert the string to extract the number from
	 * @return the number extracted
	 */
	public int convert(String toConvert) {
		int res = 0;
		try {
			res = Integer.parseInt(toConvert);
		} catch (Exception e) {
			res = -1;
		}
		return res;
	}
	
	/**
	 * increases count by 1. Called each turn
	 */
	public void count() {
		count++;
	}
	
	/**
	 * @return the turn count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @return the number of ships for all players
	 */
	public int[] getNumShip() {
		return nShip;
	}

	/**
	 * @return the turn number
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * @return the enemy number
	 */
	public int getEnemy() {
		return enemy;
	}

	/**
	 * @return the player playing
	 */
	public Player getTurnPlayer() {
		return player[turn];
	}

	public Player getEnemyPlayer() {
		return player[enemy];
	}

	/**
	 * @return first player
	 */
	public Player getFirst() {
		return player[0];
	}
	
	/**
	 * @return second player
	 */
	public Player getSecond() {
		return player[0];
	}

	/**
	 * @param index index of player to return, 0 or 1
	 * @return the player
	 */
	public Player getTargetPlayer(int index) {
		return player[index];
	}
	
	/**
	 * @return the AI object
	 */
	public BattleshipAI getAI() {
		return AI;
	}
	/**
	 * @param toPlay pre-generated player
	 */
	public void setAI() {
		player[1] = AI.boardGen(nShip[1]);
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	/**
	 * sets number of ships for both players
	 * @param toSet the number of ships
	 */
	public void setNumShip(int toSet) {
		nShip[0] = toSet;
		nShip[1] = toSet;
	}

	/**
	 * switches current turn player and current enemy 
	 */
	public void switchPlayer() {
		enemy = turn;
		turn = (turn + 1) % 2;
		System.out.println("switched");
	}

	/**
	 * makes a move for turn player
	 * @param sq the square to fire at
	 */
	public void move(int sq) {
		if (!canMove(sq)) {
			// if making illegal move, gets another turn
			switchPlayer();
		} else {
			// otherwise, add new square to list
			player[turn].sqHit.add(sq);
		}
	}
	
}
