package graphics;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import data_container.Data;

public class FinishWindow extends Pane {
	private Data data = Data.getInstance();
	private Label victoryCall = new Label();

	public FinishWindow() {
		this.getChildren().addAll(victoryCall);
		this.setOnMousePressed(e -> exit());
	}

	public void updateVictoryCall() {

		victoryCall.setText(
				"Player " + data.getTurn() + ", " + data.getTurnPlayer().getName() + " wins in " + data.getCount() + " turn(s)");
	}

	public void exit() {
	}

}
