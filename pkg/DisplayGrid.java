package pkg;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class DisplayGrid extends GridPane{
  protected Data data = Data.getInstance();
  protected ObservableList<Node> labelList;
  public DisplayGrid(){
    char[] Letters = {'A', 'B', 'C','D', 'E', 'F', 'G', 'H', 'I', 'J'};
    for (int i = 1; i<11; i++){
      Label label = new Label ("\n      " + Letters[i-1]);
      this.setConstraints(label,i,0);
      this.getChildren().add(label);
    }

    for (int i = 1; i<11; i++){
      Label label = new Label(String.format("    %d    ",i));
      this.setConstraints(label,0,i);
      this.getChildren().add(label);
    }
    labelList = FXCollections.observableArrayList(this.getChildren());
    System.out.println(labelList.size());
    this.refresh();
  }

  public DisplayGrid(int[][] shipList){
    this();
  }

  //"abstract" method
  protected void addInteractible(int colIndex, int rowIndex){}

  public final Rectangle makeColorSquare(Color toFill){
    Rectangle res = new Rectangle(50,50);
    res.setFill(toFill);
    res.setStroke(Color.BLACK);
    return res;
  }

  public void highlight(Color color,int colIndex, int rowIndex){
    this.add(makeColorSquare(color), colIndex, rowIndex);
  }

  public void clear(){
    this.getChildren().clear();
    for (int i = 0; i < 10; i++){
      this.add(labelList.get(i),i+1,0);
      this.add(labelList.get(i+10),0,i+1);
    }
  }

  public void refresh(){
    clear();
    for (int i = 0; i<100; i++){
      if (data.player[data.enemy].sqHit.contains(i)
          && data.player[data.turn].sqNotHit.contains(i)){
            this.highlight(Color.RED,i%10+1,i/10+1);
          } else if (data.player[data.turn].sqNotHit.contains(i)){
            this.highlight(Color.BLUE,i%10+1,i/10+1);
          } else if (data.player[data.enemy].sqHit.contains(i)){
            this.highlight(Color.BLACK,i%10+1,i/10+1);
          } else {
            this.highlight(Color.WHITE,i%10+1,i/10+1);
            addInteractible(i%10+1,i/10+1);
          }
    }
  }


}
