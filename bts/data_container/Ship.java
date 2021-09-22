package data_container;

public class Ship {
	// Constants for ships orientation
	public final int HORIZONTAL = 0;
	public final int VERTICAL = 1;

	// instance variables for a ship
	private int row;
	private int column;
	private int length;
	private int orientation;
	private int number;
	private int health;
	private int[] colx = new int[2];
	private int[] coly = new int[2];

	// constructors
	public Ship(int x, int y, int l, int o) {
		row = x;
		column = y;
		length = l;
		orientation = o;
		if (orientation == 0) {
			setColx(new int[] { row, row });
			setColy(new int[] { column, column + length - 1 });
		} else {
			setColx(new int[] { row, row + length - 1 });
			setColy(new int[] { column, column });
		}
	}

	public Ship() {
	}

	// getter and setter methods for the ship's row location
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		if (row < 1) {
			throw new IllegalArgumentException("Invalid ship location, enter a number between 1 and 10");
		}
		this.row = row;
	}

	// getter and setter methods for the ships column location
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		if (column < 1) {
			throw new IllegalArgumentException("Invalid ship location, enter a number between 1 and 10");

		}
		this.column = column;
	}

	// getter and setter methods for the ships length
	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		if (length <= 2) {
			throw new IllegalArgumentException("Invalid ship length, enter a number between 2 and 6");
		}
		this.length = length;
	}

	// getter and setter methods for the ships number
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// getter and setter methods for the ships vertical or horizonal orientation
	public int getOrientation() {
		return this.orientation;
	}

	public void setOrientation(int orientation) {
		if (orientation != HORIZONTAL && orientation != VERTICAL) {
			throw new IllegalArgumentException("Invalid ship orientation, enter either 0 or 1");
		} else if (orientation == HORIZONTAL) {
			this.orientation = orientation;
		} else if (orientation == VERTICAL) {
			this.orientation = orientation;
		}
	}

	/**
	 * Method to check if the current ship has been shot from coordinates
	 * 
	 * @param row
	 *            x coordinate, top left is (0,0)
	 * @param column
	 *            y coordinate
	 * @return whether a square of the ship has been hit or not, boolean
	 */
	public boolean isHit(int row, int column) {
		boolean res = false;
		// if the ship is horizontal, check if the guess for row is in the same row as
		// the enemy ship
		if (orientation == HORIZONTAL) {
			if (this.row == row) {
				// if the same row, then check if the column guess is in the range of the ship's
				// length
				if (column < this.column + length && column >= this.column) {
					res = true;
				} else {
					res = false;
				}
			} else {
				res = false;
			}
		} else if (orientation == VERTICAL) {
			// if the ship is vertical, check if the guess for column is in the same column
			// as the enemy ship
			if (this.column == column) {
				// check to see if the row guess is in the range of the ships length
				if (row < this.row + length && row >= this.row) {
					res = true;
				} else {
					res = false;
				}
			} else {
				res = false;
			}
		}
		return res;
	}

	/** 
	 * @param ship the ship to compare to
	 * @return whether the ships are equivalent or not
	 */
	public boolean equals(Ship ship) {
		if (ship == this) {
			return true;
		}
		if (ship == null) {
			return false;
		}
		if (this.getClass() != ship.getClass()) {
			return false;
		}
		Ship x = ship;

		if (this.row != x.row || this.column != x.column) {
			return false;
		}
		if (this.length != x.length) {
			return false;
		}
		if (this.orientation != x.orientation) {
			return false;
		}
		if (this.number != x.number) {
			return false;
		}
		if (this.health != x.health) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (this.getColx()[i] != x.getColx()[i] || this.getColy()[i] != x.getColy()[i]) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Updater, set coordinates for collision checks
	 */
	public void update() {
		if (orientation == 0) {
			setColx(new int[] { row, row });
			setColy(new int[] { column, column + length - 1 });
		} else {
			setColx(new int[] { row, row + length - 1 });
			setColy(new int[] { column, column });
		}
	}

	@Override
	public String toString() {
		return (row + " " + column + " " + length + " " + orientation + " " + number);
	}

	/**
	 * @return the x collision
	 */
	public int[] getColx() {
		return colx;
	}

	/**
	 * @param colx the new x collision
	 */
	public void setColx(int[] colx) {
		this.colx = colx;
	}

	/**
	 * @return the y collision
	 */
	public int[] getColy() {
		return coly;
	}

	/**
	 * @param coly the new y collision
	 */
	public void setColy(int[] coly) {
		this.coly = coly;
	}

}
