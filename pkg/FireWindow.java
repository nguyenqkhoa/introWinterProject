package pkg;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

public class FireWindow extends Pane{
  private Data data = Data.getInstance();
  private FireGrid enemy = new FireGrid(){
    @Override
    public void injected(){
      fire.setDisable(false);
      nuke.setDisable(nukeUsed[data.turn]);
      self.refresh();
    }
  };
  private DisplayGrid self = new DisplayGrid();
  private Button fire = new Button("Confirm target");
	private Button nuke = new Button("Nuke");
  private boolean[] nukeUsed = {false, false};

  public FireWindow(){
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
    fire.setOnAction(e->{
      fire.setDisable(true);
      enemy.refresh();
      int sq = -1;
      if (data.mode == 2 && data.turn == 1){
        int[] res = data.AI.move(data.player[1], data.player[0]);
        System.out.println("AI:"+res[0] +"-"+ res[1]);
        data.player[1].display();
        data.player[1].displayEnemy(data.player[0]);
        sq = res[1]*10+res[0];
      } else {
      sq = this.enemy.point[0] + this.enemy.point[1]*10;
      }
      data.move(sq);
      data.player[data.turn].display();
      data.player[data.turn].displayEnemy(data.player[data.enemy]);
      data.count++;
      hasWon();
      data.switchPlayer();
      nuke.setDisable(true);

/**
      if (data.mode == 2 && data.turn == 1){
        fire.setDisable(false);
        fire.fire();
        self.refresh();
      }*/


    });

    nuke.setOnAction(e->{
      enemy.refresh();
      int sq = this.enemy.point[0] + this.enemy.point[1]*10;
      if (data.canMove(sq)&& sq/10 != 9 && sq/10 !=0
                          && sq%10 != 9 && sq%10 !=0){
        nukeSquare(sq);
        nukeUsed[data.turn] = true;
        data.count++;
        hasWon();
        data.switchPlayer();
        nuke.setDisable(nukeUsed[data.turn]);
        fire.setDisable(true);
      }
    });
  }

  private void hasWon(){
    if (data.player[data.turn].win(data.player[data.enemy])){
        System.out.println("Game over, " + data.turn + " wins");
        switchScene(3);
      }
  }

  private void nukeSquare(int sq){
    if (data.canMove(sq)){
      int[][] sqs = new int[3][3];
      for (int i = -1; i<=1; i++){
        for (int j = -1; j<=1; j++){
          sqs[i+1][j+1] = (this.enemy.point[0]+i) + (this.enemy.point[1]+j)*10;
        }
      }
      for (int k = 0; k<9; k++){
        data.move(sqs[k/3][k%3]);
      }
    }
  }

  public void switchScene(int target){}

  public void refresh(){
    self.refresh();
    enemy.refresh();
  }
}
