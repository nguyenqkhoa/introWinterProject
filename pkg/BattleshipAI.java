package pkg;
import java.util.Random;
import java.util.ArrayList;


public class BattleshipAI {
  public BattleshipAI(){//empty constructor
  }



//shooting player as player
  public int[] move(Player from, Player to){
    Random rand = new Random();
    int num = rand.nextInt(300), x = -1, y = -1;
    while (x == -1 && y == -1
          && from.sqHit.contains(x*10+y)){
    if (num < 1){
      if (!(from.sqHit.contains(num))){
        x = num/10;
        y = num%10;
      }
    } else if (num < 1){
      if (!(from.sqHit.contains(num-100))){
        x = (num - 100)/10;
        y = (num - 100)%10;
      }
    } else {
      int target = rand.nextInt(to.sqNotHit.size());
      if (!(from.sqHit.contains(target))){
      x = target/10;
      y = target%10;
	    }
	   }
    }
	int[] res = {x,y};
  return res;
  }

  /**board generator, add ships to board randomly
  * AI will have the same amount of ships as the player
  * @param numShips the number of ships to be added
  */
  public Player boardGen(int numShips){
    Player result = new Player();
    Random rand = new Random();
    int i = 0;
    while (i < numShips){
      int num = rand.nextInt(100);
      int x = num/10;
      int y = num%10;
      int o = rand.nextInt(2);
      int l = rand.nextInt(3)+2;
      Ship toAdd = new Ship(x,y,l,o);
      toAdd.setNumber(result.shipList.size());
      if (result.collision(toAdd)){
        continue;
      }
      result.addShip(toAdd);
      i++;
    }
    return result;
  }
}
