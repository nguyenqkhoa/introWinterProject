package bts.data;

import java.util.ArrayList;

public class Player{
  public int num = -1;
  public ArrayList<Integer> sqNotHit = new ArrayList<Integer>();
  public ArrayList<Integer> sqHit = new ArrayList<Integer>();
  public ArrayList<Ship> shipList = new ArrayList<Ship>();
  public int[][] board = new int[10][10];
  public String name = "Anonymous";

  public Player(){
    for (int i = 0; i<100; i++){
      board[i/10][i%10] = -1;
    }
  }

  public Player(int num){
    this();
    this.num = num;
  }

  public Player(int num, int mode){
    this(num);
    if (mode == 0){
    }
  }

  public Player(String name){
    this();
    this.name = name;
  }

  public void setName(String newName) {
    if (!newName.equals("")
        && !newName.contains(",")
        && !newName.equals(null)){
      this.name = newName;
    }
  }

  public boolean collision(Ship ship){
    try {
    if (ship.colx[0] > ship.colx[1]
      || ship.coly[0] > ship.coly[1]
      || ship.length < 2){
      //prevent negative ships, loop doesn't run so check returns false (not colliding)
      return true;
    }
    for (int i = ship.colx[0]; i <= ship.colx[1]; i++){
      for (int j = ship.coly[0]; j <= ship.coly[1]; j++){
        if (!(board[i][j] == ship.number || board[i][j] == -1)){
          return true;
        }
      }
    }
    return false;
  } catch (Exception e){//most important to return true when out of bounds
    return true;
    }
  }


  public void addShip(Ship ship){
    for (int i = ship.colx[0]; i <= ship.colx[1];i++){
      for (int j = ship.coly[0]; j <= ship.coly[1];j++){
        board[i][j] = ship.number;
      }
    }
    shipList.add(ship);
    boardUpdate();
  }


  public void display(){
    System.out.println();
    for (int i = 0; i<10; i++){
      for (int j = 0; j<10; j++){
        if (board[i][j] == -1){
          System.out.print("  .");
        } else
          System.out.print("  " + board[i][j]);
      }
      System.out.println();
    }
    for (Integer i : sqNotHit){
      System.out.println(i);
    }
    System.out.println();
    for (Integer i : sqHit){
      System.out.println(i);
    }
  }


  public void displayEnemy(Player player){
    System.out.println();
    for (int i = 0; i<10; i++){
      for (int j = 0; j<10; j++){
        if (this.sqHit.contains(i*10+j) && player.sqNotHit.contains(i*10+j)){
        System.out.print("  H");
      } else if (this.sqHit.contains(i*10+j)){
        System.out.print("  x");
      } else {
        System.out.print("  .");
          }
        }
      System.out.println();
    }
  }


  public boolean win(Player player){
    for (int i = 0; i < player.sqNotHit.size(); i++){
      if (!(this.sqHit.contains(player.sqNotHit.get(i)))){
        return false;
      }
    }
    return true;
  }


  public void boardUpdate(){
    int len = this.shipList.size();
    for (int i = 0; i<100; i++){
      if (this.sqNotHit.contains(i)){
        continue;
      }
      for (int j = 0; j<len; j++){
        boolean hit = this.shipList.get(j).isHit(i/10,i%10);
        if (hit){
          this.sqNotHit.add(i);
        }
      }
    }
  }
}
