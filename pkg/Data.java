package pkg;

public final class Data{//Singleton Object, holds all data for GameGUI
  private static Data instance = null;

  int mode = -1;
	BattleshipAI AI = new BattleshipAI();
	Player[] player = new Player[2];
  int[] nShip = {0,0};
  int turn = 0, enemy = 1;
  int count = 0;

  private Data(){
    player[0] = new Player();
    player[1] = new Player();
  }

  public static Data getInstance(){
    if (instance == null){
      instance = new Data();
    }
    return instance;
  }

  public void switchPlayer(){
		enemy = turn;
		turn = (turn+1)%2;
	}

  public int convert(String toConvert){
		int res = 0;
		try {
			res = Integer.parseInt(toConvert);
		} catch (Exception e){
			res = -1;
		}
		return res;
	}

  public boolean canMove(int sq){
    if (player[turn].sqHit.contains(sq) || sq < 0 || sq > 99){
      return false;
    } else {
      return true;
    }
  }

  public void move(int sq){
    if (!canMove(sq)){
      //if making illegal move, gets another turn
      switchPlayer();
    } else {
      //otherwise, add new square to list
      player[turn].sqHit.add(sq);
    }
  }


}
