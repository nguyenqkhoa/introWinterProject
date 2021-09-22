package graphics;

import data_container.Ship;
import data_container.Data;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ShipWindow extends Pane {
	private Data data = Data.getInstance();
	private int[] nShip = data.getNumShip();
	private ShipGrid grid = new ShipGrid();
	private Button addShip = new Button("Add Ship");
	private Button endTurn = new Button("End Turn");
	private boolean isTurnEnd = false;
	private Button reset = new Button("Reset");

	public ShipWindow() {
		VBox v = new VBox();
		HBox h = new HBox();
		v.setSpacing(20);
		h.setSpacing(20);
		h.setPadding(new Insets(0, 0, 0, 50));
		v.getChildren().addAll(grid, h);
		h.getChildren().addAll(addShip, endTurn, reset);
		this.getChildren().addAll(v);
		endTurn.setDisable(true);
		addShip.setOnAction(e -> {
			endTurn.setDisable(true);
			grid.mouseStat = 0;
			Ship toAdd;
			if (grid.collision[0] == 0) {// vertical ship
				toAdd = new Ship(grid.point1[1], grid.point1[0], grid.collision[1] + 1, 1);
			} else if (grid.collision[1] == 0) {// horizontal ship
				toAdd = new Ship(grid.point1[1], grid.point1[0], grid.collision[0] + 1, 0);
			} else {
				toAdd = new Ship();
			}
			// debug
			toAdd.setNumber(data.getTurnPlayer().getShipListSize());
			System.out.println(toAdd);
			System.out.println("player " + data.getTurn());

			if (!data.getTurnPlayer().collision(toAdd)) {
				System.out.println("success");
				data.getTurnPlayer().addShip(toAdd);
			} else {
				nShip[data.getTurn()]++;
				System.out.println("failed");
			}
			grid.refresh();
			nShip[data.getTurn()]--;
			data.getTurnPlayer().display();
			System.out.println("remaining " + nShip[data.getTurn()]);

			if (nShip[data.getTurn()] <= 0) {
				addShip.setDisable(true);
				System.out.println("disabled");
				grid.refresh();
				endTurn.setDisable(false);

			}
		});

		endTurn.setOnAction(e -> {
			if (!isTurnEnd) {
				endTurn.setDisable(true);
				data.switchPlayer();
				reset.fire();
				addShip.setDisable(false);
				isTurnEnd = true;
				endTurn.setText("Finish");
				if (data.getMode() == 2) {
					nShip[1] = 0;
					endTurn.setDisable(false);
					endTurn.fire();
				}
			} else {
				data.switchPlayer();
				switchScene(2);
			}
		});

		reset.setOnAction(e -> {
			grid.refresh();
			grid.mouseStat = 0;
			grid.resetPoints();
		});
	}

	public void switchScene(int target) {
	}

}
