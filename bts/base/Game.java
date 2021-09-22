package base;

import java.util.Scanner;

import data_container.Data;
import data_container.Ship;

public class Game {
	Scanner scanner = new Scanner(System.in);
	Input input = new Input();
	Data data = Data.getInstance();

	public Game() {
		// empty constructor, just in case
	}

	// overloading
	public boolean inputShips(int num, int[] data) {
		Ship toAdd;
		toAdd = input.createShip(data);
		toAdd.setNumber(this.data.getTargetPlayer(num).getShipListSize());
		System.out.println(toAdd);
		if (this.data.getTargetPlayer(num).collision(toAdd)) {
			System.out.println("failed");
			return false;
		} else {
			this.data.getTargetPlayer(num).addShip(toAdd);
			System.out.println("success");
			return true;
		}
	}

	public void inputShips(int nShip, int num) {
		for (int i = 0; i < nShip; i++) {
			Ship toAdd;
			toAdd = input.createShip();
			toAdd.setNumber(data.getTargetPlayer(num).getShipListSize());
			while (data.getTargetPlayer(num).collision(toAdd)) {
				System.out.println("That might not have worked... ");
				toAdd = input.createShip();
				toAdd.setNumber(data.getTargetPlayer(num).getShipListSize());
			}
			data.getTargetPlayer(num).addShip(toAdd);
		}
	}

	public void mainLoop(int mode) {
		System.out.println("Player(s), choose how many ships you want: ");
		int nShip;
		nShip = input.numShips();
		if (mode == 0) {// PvP
			// inputing ships
			for (int i = 0; i < 2; i++) {
				System.out.println("Player " + i + ", please add the ships.");
				inputShips(nShip, i);
			}
			System.out.println("Finished setting up board.");
		} else if (mode == 1) {// AI
			// inputing ships
			System.out.println("Player 1, please add the ships: ");
			inputShips(nShip, 0);
			data.setAI();
			System.out.println("Finished setting up board.");
		}
		// battling starts here
		while (true) {
			int[] toHit = { -1, -1 };
			data.getTurnPlayer().display();
			System.out.println();
			System.out.println();
			if (data.getTurn() == 0 || mode == 0) {
				System.out.println("Player " + data.getTurn() + " to move");
				toHit = input.fire();
			}
			if (data.getTurn() == 1 && mode == 1) {
				toHit = data.getAI().move(data.getTurnPlayer(), data.getEnemyPlayer());
				System.out.println("data.AI chooses " + toHit[0] + "-" + toHit[1]);
			}
			int sq = toHit[0] * 10 + toHit[1];
			data.move(sq);

			data.getTurnPlayer().displayEnemy(data.getEnemyPlayer());
			System.out.println();

			if (data.getTurnPlayer().win(data.getEnemyPlayer())) {
				System.out.println("Game over, " + data.getTurn() + " wins");
				break;
			}

			data.switchPlayer();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		Data data = Data.getInstance();
		data.setMode(game.input.selectMode());
		if ((data.getMode() != 2)) {
			game.mainLoop(data.getMode());
		}
	}

}
