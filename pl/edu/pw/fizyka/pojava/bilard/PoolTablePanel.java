package pl.edu.pw.fizyka.pojava.bilard;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

public class PoolTablePanel extends JPanel {

	public void setPanelBackgroundColor(Color backgroundPoolColor){panelBackgroundColor=backgroundPoolColor;}
	public void setAllColors(Color background, Color pool, Color sides, Color corners){
		panelBackgroundColor=background;
		poolColorGreen=pool;
		sidePoolColor=sides;
		cornerColor=corners;
	}
	public int ballRadius = 14;
	Color orangeColor = new Color(219, 90, 0);
	Color colorBrown = new Color(148, 52, 0);
	Color colorPurple = new Color(148, 52, 171);
	public Color ballColorList[] = new Color[]{ Color.yellow, Color.yellow, Color.red, Color.red, orangeColor, orangeColor,
			colorBrown, Color.green, colorPurple, colorBrown, Color.green, colorPurple, Color.black, Color.blue, Color.blue};
	public ArrayList<Ball> ballList = new ArrayList<Ball>();
	public Ball whiteBall = new Ball(ballRadius, 0, 0, Color.white);

	public int panelWidth = this.getWidth();
	public int panelHeight = this.getHeight();
	Color panelBackgroundColor = new Color(224,224,225);
	Color poolColorGreen = new Color(88, 152, 67);
	Color cornerColor = new Color(87, 76, 65);
	Color sidePoolColor = new Color(86, 234, 50);
	public int tableWidth = 937; ////(int) (0.9/1.06*panelWidth)
	public int tableHeight = 468;// (int)(tableWidth/2)
	public int marginX = 55;// (int)(panelWidth*0.05)
	public int marginY = 47; //(int)((panelHeight-tableHeight)/2)
	public int sideWidth = 28;// (int)(0.03*tableWidth);
	public int cornerRounding = 20;
	public int holeRadius = 19;// (int)(5.715/254*tableWidth)
	public int a = 27;// (int)(1.8*holeRadius/Math.sqrt(2))
	public int sidePoolWidth = 14;// (int)(0.015*tableWidth)
	public int middleHoleSideCorrectionX = 9; //(int)(0.01*tableWidth)
	public int middleHoleSideCorrectionY = 10; //(int)(sideWidth/3)
	//wspolrzedne Pool sides
	public int leftSideX[] = {marginX+sideWidth, marginX+sideWidth,marginX+sideWidth+sidePoolWidth, marginX+sideWidth+sidePoolWidth};
	public int leftSideY[] = {marginY+sideWidth+a, marginY+sideWidth+tableHeight-a, marginY+sideWidth+tableHeight-a-sidePoolWidth, marginY+sideWidth+a+sidePoolWidth};
	public int upperLeftSideX[] = {marginX+sideWidth+a,
			marginX+sideWidth+tableWidth/2-((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a))),
			marginX+sideWidth+tableWidth/2-((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a)))- middleHoleSideCorrectionX,
			marginX+sideWidth+a+sidePoolWidth
			};
	public int upperLeftSideY[] = {marginY+sideWidth, marginY+sideWidth, marginY+sideWidth+sidePoolWidth, marginY+sideWidth+sidePoolWidth};
	public int upperRightSideX[] = {marginX+sideWidth+tableWidth/2+((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a))),
			marginX+sideWidth+tableWidth-a,
			marginX+sideWidth+tableWidth-a-sidePoolWidth,
			marginX+sideWidth+tableWidth/2+((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a)))+middleHoleSideCorrectionX
			};
	public int[] upperRightSideY = {marginY+sideWidth, marginY+sideWidth, marginY+sideWidth+sidePoolWidth, marginY+sideWidth+sidePoolWidth};
	public int[] bottomLeftSideX = {marginX+sideWidth+a,
			marginX+sideWidth+tableWidth/2-((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a))),
			marginX+sideWidth+tableWidth/2-((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a)))- middleHoleSideCorrectionX,
			marginX+sideWidth+a+sidePoolWidth
			};
	public int[] bottomLeftSideY = {marginY+sideWidth+tableHeight, marginY+sideWidth+tableHeight, marginY+sideWidth+tableHeight-sidePoolWidth, marginY+sideWidth+tableHeight-sidePoolWidth};
	public int[] bottomRightSideX = {marginX+sideWidth+tableWidth/2+((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a))),
			marginX+sideWidth+tableWidth-a,
			marginX+sideWidth+tableWidth-a-sidePoolWidth,
			marginX+sideWidth+tableWidth/2+((int)Math.sqrt(holeRadius*holeRadius-(middleHoleSideCorrectionY+2*holeRadius-a)))+middleHoleSideCorrectionX
			};
	public int[] bottomRightSideY = {marginY+sideWidth+tableHeight, marginY+sideWidth+tableHeight, marginY+sideWidth+tableHeight-sidePoolWidth, marginY+sideWidth+tableHeight-sidePoolWidth};
	public int[] rightSideX = {marginX+sideWidth+tableWidth, marginX+sideWidth+tableWidth, marginX+sideWidth+tableWidth-sidePoolWidth, marginX+sideWidth+tableWidth-sidePoolWidth};
	public int[] rightSideY = {marginY+sideWidth+a, marginY+sideWidth+tableHeight-a, marginY+sideWidth+tableHeight-a-sidePoolWidth, marginY+sideWidth+a+sidePoolWidth};
	
	public void paintComponent(Graphics g) {

		g.setColor(panelBackgroundColor);
		g.fillRect(0,0,getWidth(), getHeight());
		g.setColor(cornerColor);
		g.fillRoundRect(marginX, marginY, tableWidth+2*sideWidth, tableHeight+2*sideWidth, cornerRounding, cornerRounding);
		g.setColor(cornerColor);
		//corners
		g.fillRoundRect(marginX, marginY, sideWidth+a, sideWidth+a, cornerRounding, cornerRounding);
		g.fillRoundRect(marginX, marginY+tableHeight+sideWidth-a, sideWidth+a, sideWidth+a, cornerRounding, cornerRounding);
		g.fillRoundRect(marginX+tableWidth+sideWidth-a, marginY, sideWidth+a, sideWidth+a, cornerRounding, cornerRounding);
		g.fillRoundRect(marginX+tableWidth+sideWidth-a, marginY+tableHeight+sideWidth-a, sideWidth+a, sideWidth+a, cornerRounding, cornerRounding);
		//sides
		g.setColor(Color.GRAY);
		g.fillRect(marginX+sideWidth+a, marginY, tableWidth-2*a, sideWidth);
		g.fillRect(marginX+sideWidth+a, marginY+sideWidth+tableHeight, tableWidth-2*a, sideWidth);
		g.fillRect(marginX, marginY+sideWidth+a, sideWidth, tableHeight-2*a);
		g.fillRect(marginX+sideWidth+tableWidth, marginY+sideWidth+a, sideWidth, tableHeight-2*a);
		//main pool
		g.setColor(poolColorGreen);
		g.fillRect(marginX+sideWidth, marginY+sideWidth, tableWidth, tableHeight);
		//pool sides
		g.setColor(sidePoolColor);
		g.fillPolygon(leftSideX, leftSideY, 4);
		g.fillPolygon(upperLeftSideX, upperLeftSideY, 4);
		g.fillPolygon(upperRightSideX, upperRightSideY, 4);
		g.fillPolygon(bottomLeftSideX, bottomLeftSideY, 4);
		g.fillPolygon(bottomRightSideX, bottomRightSideY, 4);
		g.fillPolygon(rightSideX, rightSideY, 4);
		//holes
		g.setColor(Color.black);
		g.fillOval(marginX+sideWidth+a-2*holeRadius, marginY+sideWidth+a-2*holeRadius, 2*holeRadius, 2*holeRadius);
		g.fillOval(marginX+sideWidth+a-2*holeRadius, marginY+tableHeight, 2*holeRadius, 2*holeRadius);
		g.fillOval(marginX+tableWidth, marginY+sideWidth+a-2*holeRadius, 2*holeRadius, 2*holeRadius);
		g.fillOval(marginX+tableWidth, marginY+tableHeight, 2*holeRadius, 2*holeRadius);
		g.fillOval(marginX+sideWidth+tableWidth/2-holeRadius, marginY+sideWidth+a-2*holeRadius-10, 2*holeRadius, 2*holeRadius);
		g.fillOval(marginX+sideWidth+tableWidth/2-holeRadius, marginY+sideWidth+tableHeight-a+10, 2*holeRadius, 2*holeRadius);
//		g.setColor(Color.red);
//		g.fillOval(marginX+sideWidth+tableWidth/4+64-14,marginY+sideWidth+tableHeight/2-14, 28, 28);

		for(Ball currentBall : ballList){
			g.setColor(currentBall.ballColor);
			g.fillOval(currentBall.xPosition, currentBall.yPosition, 2*ballRadius, 2*ballRadius);
		}
		g.setColor(whiteBall.ballColor);
		g.fillOval(whiteBall.xPosition, whiteBall.yPosition, whiteBall.radius*2, whiteBall.radius*2);
	}

	public PoolTablePanel(){
		for(int i = 0; i<15; i++){
			Ball currentBall = new Ball(ballRadius, 0, 0, ballColorList[i]);
			ballList.add(currentBall);
		}
		moveBallsToStartingPosition();
		moveWhiteBallToStartingPosition();
	}

	public void moveBallsToStartingPosition(){
		int space = 2;
		int moveLeftX = 26;//sqrt(3)/2*(2*ballRadius+space)
		int moveDownY = 15;//(2*ballRadius+space)/2
		int moveUpY = 30;//(2*ballRadius+space)
		ballList.get(0).xPosition = marginX+sideWidth+tableWidth/4+64-ballRadius;
		ballList.get(0).yPosition = marginY+sideWidth+tableHeight/2-ballRadius;
		for(int i = 1; i<5; i++){
			ballList.get(i).xPosition = (ballList.get(i-1).xPosition-moveLeftX);
			ballList.get(i).yPosition = (ballList.get(i-1).yPosition+moveDownY);
		}
		for(int i = 5; i< 9; i++){
			ballList.get(i).xPosition = (ballList.get(i-1).xPosition);
			ballList.get(i).yPosition = (ballList.get(i-1).yPosition-moveUpY);
		}
		int counter = 0;
		for(int i = 9; i <12; i++){
			counter++;
			ballList.get(i).xPosition = (ballList.get(3).xPosition);
			ballList.get(i).yPosition = (ballList.get(3).yPosition-moveUpY*counter);
		}
		ballList.get(12).xPosition = (ballList.get(2).xPosition);
		ballList.get(12).yPosition = (ballList.get(2).yPosition-moveUpY);

		ballList.get(13).xPosition = (ballList.get(2).xPosition);
		ballList.get(13).yPosition = (ballList.get(2).yPosition-moveUpY*2);

		ballList.get(14).xPosition = (ballList.get(1).xPosition);
		ballList.get(14).yPosition = (ballList.get(1).yPosition-moveUpY);
	}

	public void moveWhiteBallToStartingPosition(){
		whiteBall.xPosition = (marginX+sideWidth+(int)(tableWidth*3/4)-ballRadius);
		whiteBall.yPosition = (marginY+sideWidth+tableHeight/2-ballRadius);
	}




}
