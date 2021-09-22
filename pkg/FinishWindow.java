package pkg;


import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class FinishWindow extends Pane{
  private Data data = Data.getInstance();
  private Label victoryCall = new Label();

  public FinishWindow(){
    this.getChildren().addAll(victoryCall);
    this.setOnMousePressed(e -> {
      exit();
      });
  }

  public void updateVictoryCall(){

    victoryCall.setText("Player "+ data.turn
    + ", " + data.player[data.turn].name + " wins in "+ data.count +" turn(s)");
  }

  public void exit(){}

}
