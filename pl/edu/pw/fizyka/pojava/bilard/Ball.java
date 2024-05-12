package pl.edu.pw.fizyka.pojava.bilard;

import java.awt.*;
import java.util.ArrayList;

public class Ball {
    public int xPosition, yPosition, radius;
    public double vX, vY;
    public Color ballColor;

    public ArrayList<Ball> inCollissionWith;

    public Ball(int rad, double vxx, double vyy, Color ballCol){
        radius = rad;
        vX = vxx;
        vY = vyy;
        ballColor = ballCol;
    }
    public double getDistanceBetweenBalls(Ball ball1){
        return Math.sqrt((this.xPosition-ball1.xPosition)*(this.xPosition-ball1.xPosition)+(this.yPosition-ball1.yPosition)*(this.yPosition-ball1.yPosition));
    }
    public boolean isInCollissionWith(Ball ball1){
        return (this.inCollissionWith.contains(ball1) && ball1.inCollissionWith.contains(this));
    }
    public void deleteFromInCollissionWith(Ball ball1){
        this.inCollissionWith.remove(ball1);
    }
    public void move(){
        this.xPosition = (int) (this.xPosition + this.vX);
        this.yPosition = (int) (this.yPosition + this.vY);
    }
    public void boundCollission(){

    }
    public double getTeta(Ball ball1) {
        if (this.vX < ball1.vX && this.vY < ball1.vY) {
            return Math.atan((double) (ball1.vY - this.vY) / (ball1.vX - this.vX));
        }
        if (this.vX == ball1.vX && this.vY < ball1.vY) {
            return Math.PI / 2;
        }
        if (this.vX > ball1.vX && this.vY < ball1.vY) {
            return (Math.atan((double) (this.vX - ball1.vX) / (ball1.vY - this.vY)) + Math.PI / 2);
        }
        if (this.vX > ball1.vX && this.vY == ball1.vY) {
            return Math.PI;
        }
        if (this.vX > ball1.vX && this.vY > ball1.vY) {
            return Math.atan((double) (this.vY - ball1.vY) / (this.vX - ball1.vX) + Math.PI);
        }
        if (this.vX == ball1.vX && this.vY > ball1.vY) {
            return (double) 3/2 * Math.PI;
        }
        if (this.vX < ball1.vX && this.vY > ball1.vY) {
            return (Math.atan((double) (ball1.vX - this.vX) /(this.vY-ball1.vY)) + (double) 3/2 * Math.PI);
        }
        if (this.vX < ball1.vX && this.vY == ball1.vY) {
            return 0;
        }
        return 0;
    }



}
