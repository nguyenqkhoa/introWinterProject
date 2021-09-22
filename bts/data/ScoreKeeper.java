package bts.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class ScoreKeeper{
  public static class ScoreContainer {
    String name = "";
    int score = 0;
    public ScoreContainer(String name, int score){
      this.name = name;
      this.score = score;
    }
    public ScoreContainer compare(ScoreContainer toCompare){
      if (this.score >= toCompare.score){
        return this;
      } else return toCompare;
    }
  }

  public ScoreContainer getLastPlayerInfo(String fileName){
    try {
      BufferedReader file = new BufferedReader(new FileReader(fileName));
      String line = file.readLine();
      String[] info = line.split(",");
      return new ScoreContainer("",0);
    } catch (Exception e){
      return new ScoreContainer("",0);
    }
  }

  public String getBestPlayerInfo(String fileName){
    try {
      BufferedReader file = new BufferedReader(new FileReader(fileName));
      String line;
      do {
        line = file.readLine();

      } while (line != null);

      return "";
    } catch (Exception e){
      return "";
    }
  }


}
