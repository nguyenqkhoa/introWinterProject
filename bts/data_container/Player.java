package data_container;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private int num = -1;
	public List<Integer> sqNotHit = new ArrayList<Integer>();
	public List<Integer> sqHit = new ArrayList<Integer>();
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	private int[][] board = new int[10][10];
	private String name = "Anonymous";

	public Player() {
		for (int i = 0; i < 100; i++) {
			board[i / 10][i % 10] = -1;
		}
	}

	public Player(int num) {
		this();
		this.num = num;
	}

	public Player(String name) {
		this();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * changes name for this player
	 * @param newName the new name to set
	 */
	public void setName(String newName) {
		if (!newName.equals("") && !newName.contains(",") && (newName!=null)) {
			this.name = newName;
		}
	}

	/**
	 * checks if the ship can be added to the current board or not
	 * @param ship the ship to check
	 * @return whether it is a valid ship to add to ship list
	 */
	public boolean collision(Ship ship) {
		try {
			if (ship.getColx()[0] > ship.getColx()[1] || ship.getColy()[0] > ship.getColy()[1] || ship.getLength() < 2) {
				// prevent negative ships, loop doesn't run so check returns false (not
				// colliding)
				return true;
			}
			for (int i = ship.getColx()[0]; i <= ship.getColx()[1]; i++) {
				for (int j = ship.getColy()[0]; j <= ship.getColy()[1]; j++) {
					if (!(board[i][j] == ship.getNumber() || board[i][j] == -1)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {// most important to return true when out of bounds
			return true;
		}
	}

	/**
	 * adds a ship to ship list
	 * updates board configuration for playing as well
	 * @param ship
	 */
	public void addShip(Ship ship) {
		for (int i = ship.getColx()[0]; i <= ship.getColx()[1]; i++) {
			for (int j = ship.getColy()[0]; j <= ship.getColy()[1]; j++) {
				board[i][j] = ship.getNumber();
			}
		}
		shipList.add(ship);
		boardUpdate();
	}

	/**
	 * displays board for this player
	 */
	public void display() {
		System.out.println();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] == -1) {
					System.out.print("  .");
				} else
					System.out.print("  " + board[i][j]);
			}
			System.out.println();
		}
		for (Integer i : sqNotHit) {
			System.out.println(i);
		}
		System.out.println();
		for (Integer i : sqHit) {
			System.out.println(i);
		}
	}

	/**
	 * displays board against enemy player
	 * @param player the enemy to check for hits
	 */
	public void displayEnemy(Player player) {
		System.out.println();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (this.sqHit.contains(i * 10 + j) && player.sqNotHit.contains(i * 10 + j)) {
					System.out.print("  H");
				} else if (this.sqHit.contains(i * 10 + j)) {
					System.out.print("  x");
				} else {
					System.out.print("  .");
				}
			}
			System.out.println();
		}
	}

	/**
	 * checks if successfully destroyed all ships of target player
	 * @param player to check against
	 * @return whether current player has successfully destroyed all ships or not
	 */
	public boolean win(Player player) {
		for (int i = 0; i < player.sqNotHit.size(); i++) {
			if (!(this.sqHit.contains(player.sqNotHit.get(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * updates lists to check for a victory
	 */
	public void boardUpdate() {
		int len = this.shipList.size();
		for (int i = 0; i < 100; i++) {
			if (this.sqNotHit.contains(i)) {
				continue;
			}
			for (int j = 0; j < len; j++) {
				boolean hit = this.shipList.get(j).isHit(i / 10, i % 10);
				if (hit) {
					this.sqNotHit.add(i);
				}
			}
		}
	}

	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
	/**
	 * get size of ship list, used for numbering
	 * @return current number of ships in ship list
	 */
	public int getShipListSize() {
		return shipList.size();
	}
	

}
