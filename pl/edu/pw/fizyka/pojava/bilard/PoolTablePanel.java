package pl.edu.pw.fizyka.pojava.bilard;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

public class PoolTablePanel extends JPanel {
	public enum player {player1, player2};
	ArrayList<Ball> playerOneBalls = new ArrayList<Ball>();
	ArrayList<Ball> playerTwoBalls = new ArrayList<Ball>();
	public boolean toCheck2 = false;
	public void setPanelBackgroundColor(Color backgroundPoolColor){panelBackgroundColor=backgroundPoolColor;}
	public void setAllColors(Color background, Color pool, Color sides, Color corners){
		panelBackgroundColor=background;
		poolColorGreen=pool;
		sidePoolColor=sides;
		cornerColor=corners;
	}
	public int ballRadius = 14;
	Color redColor = new Color(196, 27, 27, 255);
	Color yellowColor = new Color(232, 200, 25);
	Color colorPurple = new Color(148, 52, 171);
//	public Color ballColorList[] = new Color[]{ Color.yellow, Color.yellow, Color.red, Color.red, orangeColor, orangeColor,
//			colorBrown, Color.green, colorPurple, colorBrown, Color.green, colorPurple, Color.black, Color.blue, Color.blue};
	public ArrayList<Ball> ballList = new ArrayList<Ball>();
	public ArrayList<Ball> ballListWithWhiteBall = new ArrayList<Ball>();

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
	//wspolrzedne holes
	public int upperLeftHoleX = marginX+sideWidth+a-2*holeRadius;
	public int upperLeftHoleY = marginY+sideWidth+a-2*holeRadius;
	public int upperMiddleHoleX = marginX+sideWidth+tableWidth/2-holeRadius;
	public int upperMiddleHoleY = marginY+sideWidth+a-2*holeRadius-10;
	public int upperRightHoleX = marginX+tableWidth;
	public int upperRightHoleY = marginY+sideWidth+a-2*holeRadius;
	public int lowerLeftHoleX = marginX+sideWidth+a-2*holeRadius;
	public int lowerLeftHoleY = marginY+tableHeight;
	public int lowerMiddleHoleX = marginX+sideWidth+tableWidth/2-holeRadius;
	public int lowerMiddleHoleY = marginY+sideWidth+tableHeight-a+10;
	public int lowerRightHoleX = marginX+tableWidth;
	public int lowerRightHoleY = marginY+tableHeight;

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
//		g.setColor(sidePoolColor);
//		g.fillPolygon(leftSideX, leftSideY, 4);
//		g.fillPolygon(upperLeftSideX, upperLeftSideY, 4);
//		g.fillPolygon(upperRightSideX, upperRightSideY, 4);
//		g.fillPolygon(bottomLeftSideX, bottomLeftSideY, 4);
//		g.fillPolygon(bottomRightSideX, bottomRightSideY, 4);
//		g.fillPolygon(rightSideX, rightSideY, 4);
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

		for(Ball currentBall : ballListWithWhiteBall){
			g.setColor(currentBall.ballColor);
			g.fillOval((int) Math.round(currentBall.xPosition), (int) Math.round(currentBall.yPosition), 2* currentBall.radius, 2* currentBall.radius);
		}

	}

	public PoolTablePanel(){
		Color currentBallColor;
		for(int i = 0; i<15; i++){
			if(i%2 == 0){currentBallColor = Color.red;}else{currentBallColor = Color.yellow;}
			Ball currentBall = new Ball(ballRadius, 0, 0, currentBallColor);
			ballList.add(currentBall);
		}
		ballList.get(12).ballColor = Color.black;
		ballListWithWhiteBall = ballList;
		ballListWithWhiteBall.add(whiteBall);
		moveBallsToStartingPosition();
		moveWhiteBallToStartingPosition();
	}

	public void moveBallsToStartingPosition(){
		int space = 5;
		int startingTriangleSide = 10*ballRadius+4*space;//(10*ballRadius+4*space)
		int startingTriangleHeightHalf = (int)(Math.sqrt(3)*startingTriangleSide/4);//(sqrt(3)*startingTriangleSide/4)
		int moveLeftX = (int) (Math.sqrt(3)/2*(2*ballRadius+space));//sqrt(3)/2*(2*ballRadius+space)//26
		int moveDownY = (2*ballRadius+space)/2 ;//(2*ballRadius+space)/2//15
		int moveUpY = (2*ballRadius+space);//(2*ballRadius+space)//30
		ballList.get(0).xPosition = marginX+sideWidth+tableWidth/4+startingTriangleHeightHalf-ballRadius;
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

	public User hadnleUserTurns(User playerOne, User playerTwo, ArrayList<Ball> inTheHole){
		if (inTheHole.size() == 0){
			if (playerOne.currentTurn == User.nowTurning.YES){
				playerOne.currentTurn = User.nowTurning.NO;
				playerTwo.currentTurn = User.nowTurning.YES;
				return playerTwo;
			} else {
				playerOne.currentTurn = User.nowTurning.YES;
				playerTwo.currentTurn = User.nowTurning.NO;
				return playerOne;
			}
		}
		
		if(inTheHole.get(inTheHole.size()-1).ballColor == yellowColor){
			playerOne.currentTurn = User.nowTurning.YES;
			playerTwo.currentTurn = User.nowTurning.NO;
			return playerOne;
		} else if(inTheHole.get(inTheHole.size()-1).ballColor == redColor) {
			playerOne.currentTurn = User.nowTurning.NO;
			playerTwo.currentTurn = User.nowTurning.YES;
			return playerTwo;
		} else {
			return null;
		}
	}

	public User ballAnimation(double whiteBallInitialVX, double whiteBallInitialVY, User playerOne, User playerTwo){
		whiteBall.vX = whiteBallInitialVX;
		whiteBall.vY = whiteBallInitialVY;
//		User returnUser = new User("", User.nowTurning.NO);
		final User[] returnUser = {new User("", User.nowTurning.NO)};
		returnUser[0] = null;

		ArrayList<Ball> inTheHoleLocal = new ArrayList<>();
		ArrayList<Ball> inTheHole = new ArrayList<>();

		Thread animationThread = new Thread(() -> {
			int playerOneTurnScore = 0;
			int playerTwoTurnScore = 0;

			while(true) {
				//System.out.println("watek sie odpala");
				for(Ball currentBall : ballListWithWhiteBall){
					currentBall.move();
					currentBall.boundCollission(marginX+sideWidth,marginX+sideWidth+tableWidth-2*ballRadius,marginY+sideWidth, marginY+sideWidth+tableHeight-2*ballRadius, (int)(marginX+tableWidth/2-Math.sqrt(holeRadius*holeRadius-(a-holeRadius-10)*(a-holeRadius-10))),(int)(marginX+tableWidth/2+Math.sqrt(holeRadius*holeRadius-(a-holeRadius-10)*(a-holeRadius-10))), ballRadius);
					if(currentBall.isBallInTheHole(upperLeftHoleX+holeRadius, upperLeftHoleY+holeRadius, upperMiddleHoleX+holeRadius,
							upperMiddleHoleY+holeRadius, upperRightHoleX+holeRadius, upperRightHoleY+holeRadius, lowerLeftHoleX+holeRadius, lowerLeftHoleY+holeRadius,
							lowerMiddleHoleX+holeRadius, lowerMiddleHoleY+holeRadius, lowerRightHoleX+holeRadius, lowerRightHoleY+holeRadius, ballRadius, holeRadius) == true){
						inTheHoleLocal.add(currentBall);
						inTheHole.add(currentBall);

						System.out.println(currentBall.ballColor);

						if(currentBall.ballColor == Color.yellow){
							playerOneTurnScore += 1;
						}
						if(currentBall.ballColor == Color.red){
							playerTwoTurnScore += 1;
						}
					}
				}

				for(Ball ballToRemove : inTheHoleLocal){
					if(ballToRemove == whiteBall){
						//System.out.println("biala bila wpadla");
						//System.out.println(inTheHole.size());
						//System.out.println(inTheHoleLocal.size());
						//break;
					}
					ballListWithWhiteBall.remove(ballToRemove);
					ballToRemove.vX = 0;
					ballToRemove.vY = 0;
				}

				inTheHoleLocal.clear();

				for(int i = 0; i<ballListWithWhiteBall.size(); i++){
					Ball ball1 = ballListWithWhiteBall.get(i);
					for(int j = i+1; j<ballListWithWhiteBall.size(); j++ ){
						Ball ball2 = ballListWithWhiteBall.get(j);
						if(ball1.getDistanceBetweenBalls(ball2) > 2*ballRadius) {
							ball1.deleteFromInCollissionWith(ball2);
							ball2.deleteFromInCollissionWith(ball1);
						}else if(ball1.getDistanceBetweenBalls(ball2) <= 2*ballRadius && !ball1.isInCollissionWith(ball2) ){
							ball1.ballCollision(ball2);
							ballListWithWhiteBall.get(i).inCollissionWith.add(ballListWithWhiteBall.get(j));
							ballListWithWhiteBall.get(j).inCollissionWith.add(ballListWithWhiteBall.get(i));
						}
					}
				}

				if(!ballsStillMoving()){
					if(inTheHole != null){for(Ball currentBall : inTheHole){
						if(currentBall.ballColor == Color.white){
//							try {
//								Thread.sleep(1);
//								System.out.println("Game over");
//							} catch (InterruptedException e) {
//								System.out.println("Nie udalo sie zasnac");
//								Thread.currentThread().interrupt();
//								break;
//							}
							moveWhiteBallToStartingPosition();
							ballListWithWhiteBall.add(whiteBall);
						}
						if(currentBall.ballColor == Color.black){
							System.out.println(Color.black);
						}
					}}

					System.out.println("playerOneScore: " + playerOneTurnScore);
					System.out.println("playerTwoScore: " + playerTwoTurnScore);
					playerOne.currentScore += playerOneTurnScore;
					playerTwo.currentScore += playerTwoTurnScore;
					System.out.println("Score: " + playerOne.currentScore + " " + playerTwo.currentScore);
					System.out.println();
					returnUser[0] = hadnleUserTurns(playerOne, playerTwo, inTheHole);
					break;
				}

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}

				repaint();
			}

		});

		animationThread.start();
//		System.out.println("Wyszlo z watku");
//		System.out.println(inTheHole.size());

//		if(inTheHole.contains(whiteBall)){
//			System.out.println("Wykryto białą bilę jako wbitą");
//
//		}

//		if(playerOne.currentTurn == User.nowTurning.YES){return playerOne;}else{return playerTwo;}

		return returnUser[0];


//		scheduler.scheduleAtFixedRate(new Runnable() {
//
//			public void run() {
//				for(Ball currentBall : ballListWithWhiteBall){
//					currentBall.move();
//					currentBall.boundCollission(marginX+sideWidth,marginX+sideWidth+tableWidth-2*ballRadius,marginY+sideWidth, marginY+sideWidth+tableHeight-2*ballRadius, (int)(marginX+tableWidth/2-Math.sqrt(holeRadius*holeRadius-(a-holeRadius-10)*(a-holeRadius-10))),(int)(marginX+tableWidth/2+Math.sqrt(holeRadius*holeRadius-(a-holeRadius-10)*(a-holeRadius-10))), ballRadius);
//					if(currentBall.isBallInTheHole(upperLeftHoleX+holeRadius, upperLeftHoleY+holeRadius, upperMiddleHoleX+holeRadius,
//							upperMiddleHoleY+holeRadius, upperRightHoleX+holeRadius, upperRightHoleY+holeRadius, lowerLeftHoleX+holeRadius, lowerLeftHoleY+holeRadius,
//							lowerMiddleHoleX+holeRadius, lowerMiddleHoleY+holeRadius, lowerRightHoleX+holeRadius, lowerRightHoleY+holeRadius, ballRadius, holeRadius) == true){
//						inTheHoleLocal.add(currentBall);
//						inTheHole.add(currentBall);
//						if(whoseTurnNow == player.player1){playerOneBalls.add(currentBall);}else{playerTwoBalls.add(currentBall);}
//					}
//				}
//
//				for(Ball ballToRemove : inTheHoleLocal){
//					ballListWithWhiteBall.remove(ballToRemove);
//				}
//				inTheHoleLocal.clear();
//
//
//				for(int i = 0; i<ballListWithWhiteBall.size(); i++){
//					Ball ball1 = ballListWithWhiteBall.get(i);
//					for(int j = i+1; j<ballListWithWhiteBall.size(); j++ ){
//						Ball ball2 = ballListWithWhiteBall.get(j);
//						if(ball1.getDistanceBetweenBalls(ball2) > 2*ballRadius) {
//							ball1.deleteFromInCollissionWith(ball2);
//							ball2.deleteFromInCollissionWith(ball1);
//						}else if(ball1.getDistanceBetweenBalls(ball2) <= 2*ballRadius && !ball1.isInCollissionWith(ball2) ){
//							ball1.ballCollision(ball2);
//							ballListWithWhiteBall.get(i).inCollissionWith.add(ballListWithWhiteBall.get(j));
//							ballListWithWhiteBall.get(j).inCollissionWith.add(ballListWithWhiteBall.get(i));
//						}
//					}
//				}
//
//				repaint();
////				if(!ballsStillMoving()){
////					if(inTheHole != null){for(Ball currentBall : inTheHole){
////						if(currentBall.ballColor == Color.black){
////							System.out.println("Game over");
////						}
////					}}
////					if(inTheHole.contains(whiteBall)){moveWhiteBallToStartingPosition();}
////				}
//			}
//		},0,10, TimeUnit.MICROSECONDS);
	}



	public boolean ballsStillMoving(){
		if(whiteBall.vX !=0 || whiteBall.vY != 0) return true;
		for(int i=0; i< ballList.size();		 i++){
			if(ballList.get(i).vX != 0 || ballList.get(i).vY != 0){
				return true;
			}
		}
		return false;
	}



}
