package pkg;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

public class ShipWindow extends Pane{
  private Data data = Data.getInstance();
  private ShipGrid grid = new ShipGrid();
  private Button addShip = new Button("Add Ship");
	private Button endTurn = new Button("End Turn");
  private boolean isTurnEnd = false;
  private Button reset = new Button("Reset");
	private Label instruction = new Label("Press Add Ship after each ship");

  public ShipWindow(){
  	VBox v = new VBox();
  	HBox h = new HBox();
  	v.setSpacing(20);
  	h.setSpacing(20);
    h.setPadding(new Insets(0,0,0,50));
  	v.getChildren().addAll(grid, h);
  	h.getChildren().addAll(addShip, endTurn, reset);
    this.getChildren().addAll(v);
    endTurn.setDisable(true);
    addShip.setOnAction(e -> {
      endTurn.setDisable(true);
      grid.mouseStat = 0;
      Ship toAdd;
      if (grid.collision[0] == 0){//vertical ship
          toAdd = new Ship(grid.point1[1],grid.point1[0],
          grid.collision[1]+1, 1);
        } else if (grid.collision[1] == 0){//horizontal ship
          toAdd = new Ship(grid.point1[1],grid.point1[0],
          grid.collision[0]+1, 0);
        } else {
          toAdd = new Ship();
        }
      //debug
      toAdd.setNumber(data.player[data.turn].shipList.size());
      System.out.println(toAdd);
      System.out.println("player " + data.turn);

      if (!data.player[data.turn].collision(toAdd)) {
          System.out.println("success");
          data.player[data.turn].addShip(toAdd);
        } else {
          data.nShip[data.turn]++;
          System.out.println("failed");
        }
      grid.refresh();
      data.nShip[data.turn]--;
      data.player[data.turn].display();
      System.out.println("remaining " + data.nShip[data.turn]);

      if (data.nShip[data.turn] <= 0){
          addShip.setDisable(true);
          System.out.println("disabled");
          grid.refresh();
          endTurn.setDisable(false);

        }
      });

    endTurn.setOnAction(e -> {
      if (!isTurnEnd){
        endTurn.setDisable(true);
        data.switchPlayer();
        reset.fire();
        addShip.setDisable(false);
        isTurnEnd = true;
        endTurn.setText("Finish");
        if (data.mode == 2){
          data.nShip[1] = 0;
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
      });
  }

  
  public void switchScene(int target){}


}
