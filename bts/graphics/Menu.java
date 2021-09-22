package graphics;

import data_container.Data;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class Menu extends VBox {
	private Data data = Data.getInstance();
	private final Label modeLabel = new Label("Choose game mode:");
	private final ToggleGroup group = new ToggleGroup();
	private final RadioButton button1 = new RadioButton("PVP");
	private final RadioButton button2 = new RadioButton("AI");
	private final Label countLabel = new Label("Enter number of ships:");
	private final String countText = "Number of ships";
	private final TextField countTextField = new TextField();
	private final TextField nameP1 = new TextField();
	private final TextField nameP2 = new TextField();
	private final Button exeMenu = new Button("Enter");

	public Menu() {
		button1.setToggleGroup(group);
		button1.setSelected(true);
		button1.requestFocus();
		button2.setToggleGroup(group);

		VBox modeScreen = new VBox(modeLabel, button1, button2);
		this.getChildren().add(modeScreen);
		modeScreen.setSpacing(10);
		modeScreen.setPadding(new Insets(10));

		VBox nShipScreen = new VBox(countLabel, countTextField, exeMenu);
		countTextField.setMaxWidth(300);
		countTextField.setPromptText(countText);
		countTextField.setText(countText);
		this.getChildren().add(nShipScreen);
		nShipScreen.setSpacing(10);
		nShipScreen.setPadding(new Insets(10));

		VBox nameScreen = new VBox(nameP1, nameP2);
		nameP1.setPromptText("Enter player 1's name");
		nameP2.setPromptText("Enter player 2's name");
		this.getChildren().add(nameScreen);
		nameScreen.setSpacing(10);
		nameScreen.setPadding(new Insets(10));

		button1.setOnAction(e -> 
			nameP2.setVisible(true));

		button2.setOnAction(e ->
			nameP2.setVisible(false));

		exeMenu.setOnAction(e -> {
			data.setNumShip(data.convert(countTextField.getText()));
			if (group.getSelectedToggle() == button1 && valid()) {
				data.getFirst().setName(nameP1.getText());
				data.getSecond().setName(nameP2.getText());
				data.setMode(1);
				switchScene(1);
			} else if (group.getSelectedToggle() == button2 && valid()) {
				data.getFirst().setName(nameP1.getText());
				data.getSecond().setName("AI");
				data.setMode(2);
				data.setAI();
				switchScene(1);
			} else {
				countTextField.clear();
				nameP1.clear();
				nameP2.clear();
			}

		});
	}

	private boolean valid() {
		String name1 = nameP1.getText();
		String name2 = nameP2.getText();
		if (data.getNumShip()[0] <= 0 || data.getNumShip()[0] > 5 || name1.contains(",") || name2.contains(",")
				|| name1.length() > 10 || name2.length() > 10) {
			return false;
		} else
			return true;
	}

	/**
	 * method to switch scene, meant to be used by GameGUI
	 * @param target scene to switch to
	 */
	public void switchScene(int target) {
	}

}
