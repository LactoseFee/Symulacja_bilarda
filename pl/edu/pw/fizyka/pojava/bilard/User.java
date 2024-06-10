package pl.edu.pw.fizyka.pojava.bilard;

public class User {
    public enum nowTurning {YES, NO}
    String name;
    public int currentScore = 0;
    nowTurning currentTurn;

    public User(String playerName, nowTurning whoseTurn){
        name = playerName;
        currentTurn = whoseTurn;
    }
}
