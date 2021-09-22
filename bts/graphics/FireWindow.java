package graphics;

import data_container.Data;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class FireWindow extends Pane {
	private Data data = Data.getInstance();
	private FireGrid enemy = new FireGrid() {
		@Override
		public void injected() {
			fire.setDisable(false);
			nuke.setDisable(nukeUsed[data.getTurn()]);
			self.refresh();
		}
	};
	private DisplayGrid self = new DisplayGrid();
	private Button fire = new Button("Confirm target");
	private Button nuke = new Button("Nuke");
	private boolean[] nukeUsed = { false, false };

	public FireWindow() {
		VBox full = new VBox();
		HBox grids = new HBox();
		HBox buttons = new HBox();
		full.setSpacing(20);
		grids.setSpacing(20);
		buttons.setSpacing(20);
		grids.getChildren().addAll(self, enemy);
		buttons.getChildren().addAll(fire, nuke);
		full.getChildren().addAll(grids, buttons);
		this.getChildren().addAll(full);
		fire.setDisable(true);
		// fire button registers the point on click, attempts to shoot at that point
		// then check for any victory
		// and switch to next player
		fire.setOnAction(e -> {
			fire.setDisable(true);
			enemy.refresh();
			int sq = -1;
			if (data.getMode() == 2 && data.getTurn() == 1) {
				int[] res = data.getAI().move(data.getSecond(), data.getFirst());
				System.out.println("AI:" + res[0] + "-" + res[1]);
				sq = res[0] * 10 + res[1];
				data.move(sq);
				data.getTurnPlayer().display();
				data.getTurnPlayer().displayEnemy(data.getEnemyPlayer());
				System.out.println("executed1");
				data.count();
				hasWon();
				System.out.println(data.getTurn());
				System.out.println(data.getEnemy());
				data.switchPlayer();
				System.out.println(data.getTurn());
				System.out.println(data.getEnemy());
				nuke.setDisable(true);
			} else {
				sq = this.enemy.point[0] + this.enemy.point[1] * 10;
				data.move(sq);
				data.getTurnPlayer().display();
				data.getTurnPlayer().displayEnemy(data.getEnemyPlayer());
				System.out.println("executed2");
				data.count();
				hasWon();
				data.switchPlayer();
				nuke.setDisable(true);
			}

			if (data.getMode() == 2 && data.getTurn() == 1) {
				fire.setDisable(false);
				fire.fire();
				self.refresh();
			}

		});

		// nuke button nukes a square instead of firing at it
		// uses nukeSquare
		nuke.setOnAction(e -> {
			enemy.refresh();
			int sq = this.enemy.point[0] + this.enemy.point[1] * 10;
			if (data.canMove(sq) && sq / 10 != 9 && sq / 10 != 0// cannot nuke edges
					&& sq % 10 != 9 && sq % 10 != 0) {
				nukeSquare(sq);
				nukeUsed[data.getTurn()] = true;
				data.count();
				hasWon();
				data.switchPlayer();
				nuke.setDisable(nukeUsed[data.getTurn()]);
				fire.setDisable(true);
			}
		});
	}

	/**
	 * checks if a player has won, also switch to next window
	 */
	private void hasWon() {
		if (data.getTurnPlayer().win(data.getEnemyPlayer())) {
			switchScene(3);
		}
	}

	/**
	 * tries to hit every square around and including target square
	 * 
	 * @param sq
	 *            the target square
	 */
	private void nukeSquare(int sq) {
		if (data.canMove(sq)) {
			int[][] sqs = new int[3][3];
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					sqs[i + 1][j + 1] = (this.enemy.point[0] + i) + (this.enemy.point[1] + j) * 10;
				}
			}
			for (int k = 0; k < 9; k++) {
				data.move(sqs[k / 3][k % 3]);
			}
		}
	}

	/**
	 * sends window to target scene
	 * 
	 * @param target
	 *            the scene to switch to
	 */
	public void switchScene(int target) {
	}

	/**
	 * refresh the two displaying grids
	 */
	public void refresh() {
		self.refresh();
		enemy.refresh();
	}
}
