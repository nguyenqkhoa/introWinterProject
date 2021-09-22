
package pkg;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.application.Application;


public class Game {
  Scanner scanner = new Scanner( System.in );
  Input input = new Input();
  Data data = Data.getInstance();
  public Game(){
    //empty constructor, just in case
  }


  //overloading
  public boolean inputShips(int num, int[] data){
		Ship toAdd;
		toAdd = input.createShip(data);
		toAdd.setNumber(this.data.player[num].shipList.size());
		System.out.println(toAdd);
		if (this.data.player[num].collision(toAdd)){
			System.out.println("failed");
			return false;
		} else {
			this.data.player[num].addShip(toAdd);
			System.out.println("success");
			return true;
		}
	}

  public void inputShips(int nShip, int num){
   for (int i = 0; i < nShip; i++){
     Ship toAdd;
     toAdd = input.createShip();
     toAdd.setNumber(data.player[num].shipList.size());
     while (data.player[num].collision(toAdd)){
         System.out.println("That might not have worked... ");
         toAdd = input.createShip();
         toAdd.setNumber(data.player[num].shipList.size());
     }
     data.player[num].addShip(toAdd);
   }
  }




  public void mainLoop(int mode){
    System.out.println("Player(s), choose how many ships you want: ");
    int nShip;
    nShip = input.numShips();
    if (mode == 0){//PvP
      //inputing ships
      for (int i = 0; i < 2; i++){
        System.out.println("Player "+i+", please add the ships.");
        inputShips(nShip, i);
        }
      System.out.println("Finished setting up board.");
      }
     else if (mode == 1){//AI
      //inputing ships
      System.out.println("Player 1, please add the ships: ");
      inputShips(nShip, 0);
      data.player[1] = data.AI.boardGen(nShip);
      System.out.println("Finished setting up board.");
      }
      //battling starts here
      while (true){
        int[] toHit = {-1,-1};
        data.player[data.turn].display();
        System.out.println();
        System.out.println();
        if (data.turn == 0 || mode == 0 ){
          System.out.println("Player " + data.turn + " to move");
          toHit = input.Fire();
        }
        if (data.turn == 1 && mode == 1){
          toHit = data.AI.move(data.player[data.turn], data.player[data.enemy]);
          System.out.println("data.AI chooses " + toHit[0] +"-"+ toHit[1]);
        }
        int sq = toHit[0]*10+toHit[1];
        data.move(sq);
        data.player[data.turn].displayEnemy(data.player[data.enemy]);
        System.out.println();

        if (data.player[data.turn].win(data.player[data.enemy])){
          System.out.println("Game over, " + data.turn + " wins");
          break;
        }

        data.switchPlayer();
        }
  }

  public static void main(String[] args){
    Game game = new Game();
    Data data = Data.getInstance();
    data.mode = game.input.selectMode();
    if (!(data.mode == 2)){
      game.mainLoop(data.mode);
    }
  }

}
