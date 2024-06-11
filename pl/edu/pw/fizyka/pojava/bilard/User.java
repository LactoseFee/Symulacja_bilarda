package pl.edu.pw.fizyka.pojava.bilard;

import java.util.ArrayList;

public class User {
    public enum nowTurning {YES, NO}
    String name;
    public int currentScore = 0;
    nowTurning currentTurn;
    boolean mayWin = false;
    public ArrayList<Ball> ballsCollected = new ArrayList<>();

    public User(String playerName, nowTurning whoseTurn){
        name = playerName;
        currentTurn = whoseTurn;
    }
}
