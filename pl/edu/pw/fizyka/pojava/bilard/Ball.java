package pl.edu.pw.fizyka.pojava.bilard;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ball {
    public double xPosition, yPosition;
    public int radius;
    public double vX, vY;
    public Color ballColor;
    public boolean toCheck;


    public Set<Ball> inCollissionWith;

    public Ball(int rad, double vxx, double vyy, Color ballCol) {
        radius = rad;
        vX = vxx;
        vY = vyy;
        ballColor = ballCol;
        inCollissionWith = new HashSet<Ball>();
        toCheck = false;
    }

    public double getDistanceBetweenBalls(Ball ball1) {
        return Math.sqrt((this.xPosition - ball1.xPosition) * (this.xPosition - ball1.xPosition) + (this.yPosition - ball1.yPosition) * (this.yPosition - ball1.yPosition));
    }

    public boolean isInCollissionWith(Ball ball1) {
        return (this.inCollissionWith.contains(ball1) || ball1.inCollissionWith.contains(this));
    }

    public void deleteFromInCollissionWith(Ball ball1) {
        this.inCollissionWith.remove(ball1);
    }

    public void move() {
        double acceleration = 0.001;
        this.xPosition += this.vX;
        this.yPosition += this.vY;
//        if (this.vX > 0) {
//            this.vX -= acceleration;
//        } else if (this.vX < 0) {
//            this.vX += acceleration;
//        }
//        if (this.vY > 0) {
//            this.vY -= acceleration;
//        } else if (this.vY < 0) {
//            this.vY += acceleration;
//        }
        this.vX = this.vX*0.998;
        this.vY = this.vY*0.998;

        if ((Math.abs(this.vX) <= 0.01) && (Math.abs(this.vY) <= 0.01)) {
            this.vX = 0;
            this.vY = 0;
        }
//        if(Math.abs(this.vY) <= 0.001){
//            this.vY = 0;
//        }
    }

    public void boundCollission(int leftBound, int rightBound, int upperBound, int lowerBound, int middleHoleLeftX, int middleHoleRightX, int ballRadius) {
        if (this.xPosition >= rightBound) {
            this.xPosition = rightBound;
            this.vX = -1 * this.vX;
        } else if (this.xPosition <= leftBound) {
            this.xPosition = leftBound;
            if (this.vX < 0) this.vX = -1 * this.vX;
        }

        if (this.yPosition >= lowerBound) {
            this.yPosition = lowerBound;
            if (this.vY > 0) this.vY = -1 * this.vY;
        } else if (this.yPosition <= upperBound) {
            this.yPosition = upperBound;
            if (this.vY < 0) this.vY = -1 * this.vY;
        }

    }

    public double getTeta(Ball ball) {
        if (this.xPosition < ball.xPosition && this.yPosition < ball.yPosition) {
            return Math.atan((double) (ball.yPosition - this.yPosition) / (ball.xPosition - this.xPosition));
        }
        if (this.xPosition == ball.xPosition && this.yPosition < ball.yPosition) {
            return Math.PI / 2;
        }
        if (this.xPosition > ball.xPosition && this.yPosition < ball.yPosition) {
            return (Math.atan((double) (this.xPosition - ball.xPosition) / (ball.yPosition - this.yPosition)) + Math.PI / 2);
        }
        if (this.xPosition > ball.xPosition && this.yPosition == ball.yPosition) {
            return Math.PI;
        }
        if (this.xPosition > ball.xPosition && this.yPosition > ball.yPosition) {
            return Math.atan((double) (this.yPosition - ball.yPosition) / (this.xPosition - ball.xPosition)) + Math.PI;
        }
        if (this.xPosition == ball.xPosition && this.yPosition > ball.yPosition) {
            return (double) 3 / 2 * Math.PI;
        }
        if (this.xPosition < ball.xPosition && this.yPosition > ball.yPosition) {
            return (Math.atan((double) (ball.xPosition - this.xPosition) / (this.yPosition - ball.yPosition)) + (double) 3 / 2 * Math.PI);
        }
        if (this.xPosition < ball.xPosition && this.yPosition == ball.yPosition) {
            return 0;
        } else {
            return 0;
        }
    }

    public void ballCollision(Ball ball1) {
        double teta = this.getTeta(ball1);
        double ballOneVXPrime, ballOneVYPrime, ballTwoVXPrime, ballTwoVYPrime;
        ballOneVXPrime = ball1.vX * Math.cos(teta) + ball1.vY * Math.sin(teta);
        ballOneVYPrime = -this.vX * Math.sin(teta) + this.vY * Math.cos(teta);
        ballTwoVXPrime = this.vX * Math.cos(teta) + this.vY * Math.sin(teta);
        ballTwoVYPrime = -ball1.vX * Math.sin(teta) + ball1.vY * Math.cos(teta);
        teta = 2 * Math.PI - teta;
        this.vX = ballOneVXPrime * Math.cos(teta) + ballOneVYPrime * Math.sin(teta);
        this.vY = -ballOneVXPrime * Math.sin(teta) + ballOneVYPrime * Math.cos(teta);
        ball1.vX = ballTwoVXPrime * Math.cos(teta) + ballTwoVYPrime * Math.sin(teta);
        ball1.vY = -ballTwoVXPrime * Math.sin(teta) + ballTwoVYPrime * Math.cos(teta);
    }

    public double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public boolean isBallInTheHole(int upperLeftX, int upperLeftY, int upperMiddleX, int upperMiddleY, int upperRightX, int upperRightY, int lowerLeftX, int lowerLeftY, int lowerMiddleX, int lowerMiddleY, int lowerRightX, int lowerRightY,
                                   int ballRadius, int holeRadius) {
        if (getDistanceBetweenPoints(this.xPosition + ballRadius, this.yPosition + ballRadius, upperLeftX, upperLeftY) <= holeRadius) {
            return true;
        } else if (getDistanceBetweenPoints(this.xPosition + ballRadius, this.yPosition + ballRadius, upperMiddleX, upperMiddleY) <= holeRadius) {
            return true;
        } else if (getDistanceBetweenPoints(this.xPosition + ballRadius, this.yPosition + ballRadius, upperRightX, upperRightY) <= holeRadius) {
            return true;
        } else if (getDistanceBetweenPoints(this.xPosition + ballRadius, this.yPosition + ballRadius, lowerLeftX, lowerLeftY) <= holeRadius) {
            return true;
        } else if (getDistanceBetweenPoints(this.xPosition + ballRadius, this.yPosition + ballRadius, lowerMiddleX, lowerMiddleY) <= holeRadius) {
            return true;
        } else if (getDistanceBetweenPoints(this.xPosition + ballRadius, this.yPosition + ballRadius, lowerRightX, lowerRightY) <= holeRadius) {
            return true;
        } else {
            return false;
        }
    }

}
