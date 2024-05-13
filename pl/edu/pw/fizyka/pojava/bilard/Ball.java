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
        inCollissionWith = new ArrayList<Ball>();
    }
    public double getDistanceBetweenBalls(Ball ball1){
        return Math.sqrt((this.xPosition-ball1.xPosition)*(this.xPosition-ball1.xPosition)+(this.yPosition-ball1.yPosition)*(this.yPosition-ball1.yPosition));
    }
    public boolean isInCollissionWith(Ball ball1){
        if(this.inCollissionWith == null) return false;
        return (this.inCollissionWith.contains(ball1) && ball1.inCollissionWith.contains(this));
    }
    public void deleteFromInCollissionWith(Ball ball1){
        this.inCollissionWith.remove(ball1);
    }

    public void move(){
        this.xPosition = (int) (this.xPosition + this.vX);
        this.yPosition = (int) (this.yPosition + this.vY);
    }

    public void boundCollission(int leftBound, int rightBound, int upperBound, int lowerBound){
        if(this.xPosition >= rightBound){
            this.xPosition = rightBound;
            this.vX = -1*this.vX;
        }else if(this.xPosition<= leftBound){
            this.xPosition = leftBound;
            this.vX = -1*this.vX;
        }
        if(this.yPosition >= lowerBound){
            this.yPosition = lowerBound;
            this.vY = -1*this.vY;
        }else if(this.yPosition<= upperBound){
            this.yPosition = upperBound;
            this.vY = -1*this.vY;
        }
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

    public double[] ballCollision(Ball ball1){
        double[] velocitiesTableAfterCollision = {0,0,0,0};
        double teta = this.getTeta(ball1);
        double ballOneVXPrime, ballOneVYPrime, ballTwoVXPrime, ballTwoVYPrime;
        ballOneVXPrime = this.vX*Math.cos(teta) + this.vY*Math.sin(teta);
        ballOneVYPrime = -this.vX * Math.sin(teta) + this.vY*Math.cos(teta);
        ballTwoVXPrime = ball1.vX * Math.cos(teta) + ball1.vY * Math.sin(teta);
        ballTwoVYPrime = -ball1.vX * Math.sin(teta) + ball1.vY * Math.cos(teta);
        double tmpV = ballOneVXPrime;
        ballOneVXPrime = ballTwoVXPrime;
        ballTwoVXPrime = tmpV;
        teta = 2*Math.PI-teta;
        velocitiesTableAfterCollision[0] = (ballOneVXPrime*Math.cos(teta)+ballOneVYPrime*Math.sin(teta));
        velocitiesTableAfterCollision[1] = (-ballOneVXPrime*Math.sin(teta)+ballOneVYPrime*Math.cos(teta));
        velocitiesTableAfterCollision[2] = (ballTwoVXPrime*Math.cos(teta)+ballTwoVYPrime*Math.sin(teta));
        velocitiesTableAfterCollision[3] = (-ballTwoVXPrime*Math.sin(teta)+ballTwoVYPrime*Math.cos(teta));

        return velocitiesTableAfterCollision;
    }



}
