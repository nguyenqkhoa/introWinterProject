
package pkg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


public class GameGUI extends Application {
	Stage window;
	Scene[] scene = new Scene[4];


	Menu menu;
	ShipWindow shipWindow;
	FireWindow fireWindow;
	FinishWindow finishWindow;

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		menu = new Menu(){
			@Override
			public void switchScene(int target){
				window.setScene(scene[target]);
			}
		};
		shipWindow = new ShipWindow(){
			@Override
			public void switchScene(int target){
				window.setScene(scene[target]);
				fireWindow.refresh();
			}
		};
		fireWindow = new FireWindow(){
			@Override
			public void switchScene(int target){
				window.setScene(scene[target]);
				finishWindow.updateVictoryCall();
			}
		};
		finishWindow = new FinishWindow();
		scene[0] = new Scene(menu, 300, 350);
		scene[1] = new Scene(shipWindow, 600, 600);
		scene[2] = new Scene(fireWindow, 1200, 600);
		scene[3] = new Scene(finishWindow, 400, 25);

		window.setScene(scene[0]);
		window.setTitle("Battleship");
		window.show();
	}

	public static void main(String[] args){
		Application.launch(args);
	}

}
