package pkg;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;



public class Input{
	private Scanner scanner = new Scanner( System.in );
	private int X = 10, Y = 10;
	private String INVALID = "Not within board area, please input valid number: ";

	//Empty constructor
	public Input(){
	}


	/**Method takes input from console
	* and get the number of ships to be placed
	* @return the number of ships, integer
	*/
	public int numShips() {
		int numShips;
		System.out.print("How many ships do you want? ");
		numShips = scanner.nextInt();
		while (numShips < 1) {
			System.out.println("No");
			numShips = scanner.nextInt();
		}
		return numShips;
	}


	public int numShips(int num) {
		return num;
	}

	/**Method asks the user to enter a coordinate
	* to fire at
	* does not check for duplicates
	* @return the coordinates as an int array of size 2 i.e. {x,y}
	*/
	public int[] Fire() {
		int row = -1;
		int column = -1;
		int[] res = new int[2];
		//Asks user input for the selected column
		System.out.print("Select x: ");
		column = scanner.nextInt();
		while ( column > Y || column < 0)
		{
			System.out.print(INVALID);
			column = scanner.nextInt();
		}
		//Asks user input for the selected row
		System.out.print("Select y: ");
		row = scanner.nextInt();
		while (row > X || row < 0)
		{
			System.out.print(INVALID);
			row = scanner.nextInt();
		}
		res[0] = row;
		res[1] = column;
		return res;
	}


	public int[] Fire(int row, int column) {
		int[] res = new int[2];
		res[0] = row;
		res[1] = column;
		return res;
}


	/**Ask user for ship length, row number, column number,
	* and orientation for each ship
	* @return the ship with all of its data, except number
	*/
	public Ship createShip() {
		int length = 0;
		int row = -1;
		int column = -1;
		int orientation = -1;
		Ship t = new Ship();
			//Ask for the length of the ship
			System.out.print("Please input the size of the ship between 2-5: ");
			length = scanner.nextInt();
			while (length > 5 || length < 2) {
				System.out.print ("Not valid length, try again: ");
				length = scanner.nextInt();
				}
			t.length = length;
				//Ask for the row and column of where the ship is placed
			System.out.print("Select a row: ");
			row = scanner.nextInt();
			while (row > X || row < 0) {
			//Checks if in board's size
				System.out.print(INVALID);
				row = scanner.nextInt();
				}
			t.row = row;
			System.out.print("Select a column: ");
			column = scanner.nextInt();
			while ( column > Y || column < 0 ) {
				//Checks if in board's size
				System.out.print(INVALID);
				column = scanner.nextInt();
				}
			t.column = column;
			//Asks for ship's orientation
			System.out.print("Select an orientation,\n" +
			"0 for horizontal or 1 for vertical: ");
			orientation = scanner.nextInt();
			while (orientation < 0 || orientation > 1) {
				System.out.print("Not valid orientation");
				orientation = scanner.nextInt();
			}
			t.orientation = orientation;
			t.update();
			return t;
	}


	/**
	* @param r,c,l,o takes in arguments for row, column,length and orientation,
	* and sets those values to a Ship object
	*/
	public Ship createShip(int[] data) {
		Ship s = new Ship();
		s.row = data[0];
		s.column = data[1];
		s.length = data[2];
		s.orientation = data[3];
		s.update();
		return s;
	}

	/**Method asks for the mode to play, or to quit.
	* @return one of {0,1,2} for PvP, PvAI, or to quit
	*/
	public int selectMode() {
			int mode = -1;
			System.out.print("Type 0 to play against Player,\n1 to play against AI,\nor 2 to quit: ");
			mode = scanner.nextInt();
			while ( mode > 2 || mode < 0) {
				System.out.print("Please enter 0, 1, or 2: ");
				mode = scanner.nextInt();
			}
			return mode;
	}


	public int selectMode(int mode) {
		return mode;
	}
}
