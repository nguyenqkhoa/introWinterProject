package bts.data;

public final class Data{//Singleton Object, holds all data for GameGUI
  private static Data instance = null;

  public int mode = -1;
	public BattleshipAI AI = new BattleshipAI();
	public Player[] player = new Player[2];
  private int[] nShip = {0,0};
  public int turn = 0, enemy = 1;
  public int count = 0;

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
    System.out.println("switched");
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

  /**Mode getter
  *@return game mode that was chosen
  */
  public int getMode(){
    return mode;
  }

  /**AI getter
  @return The AI for the game if the mode was choosen
  */
  public BattleshipAI getAI(){
    return AI;
  }

  /**enemy getter
  @return enemy for signalling that the enemy exists in the game
  */
  public int getEnemy(){
    return enemy;
  }

  /**count getter
  @return count of the amount of turns passed for text file input
  */
  public int getCount(){
    return count;
  }

  /**turn getter
  @return Turn for determining who fires, the player or the enemy
  */
  public int getTurnPlayerNumber(){
    return turn;
  }

  /**player getter
  @return the player's ship information, when it would be their turn, status of their board
  */
  public Player getTurnPlayer(){
    return player[turn];
  }

  /**enemy getter
  @return the enemy's ship information, when it would be their turn, status of their board
  */
  public Player getEnemyPlayer(){
    return player[enemy];
  }

  /**setter for number of ships
  @param toSet a list of ships to belongs to either the enemy or player
  */
  public void setNumShip(int toSet){
    nShip[0] = toSet;
    nShip[1] = toSet;
  }

  /**number of ships getter
  *@return the number of ships that the player and enemy has
  */
  public int[] getNumShip(){
    return nShip;
  }
}
