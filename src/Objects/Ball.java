package Objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

import brick.Brick;
import brick.Level;
import gui.Play;

public class Ball extends Entity {

	int ballX, ballY, lastBallX, lastBallY;
	int ballWidth, ballHeight;
	int ballXVel, ballYVel;
	int ballSpeed;

	int lastDrawX, lastDrawY;
	public static Shape boundingBox;
	private Image image;
	
	
	
	public Shape lastBoundingBox;
	

	public Ball() throws SlickException {
		ballX = lastBallX = 800;
		ballY = lastBallY = 820;
		ballWidth = 20;
		ballHeight = 20;
		ballSpeed = 0;
		ballXVel = 0;
		ballYVel = 0;
		boundingBox = new Ellipse(ballX, ballY, 12, 12);
		image = new Image("res/ball.png");
		
	}

	public void start(){
		
		setSpeed(0);
		ballY = 830;
		boundingBox.setCenterX(ballX);
		boundingBox.setCenterY(ballY);
	}
	
	public void drawBall(Graphics g) {
		//g.drawOval(ballX, ballY, ballWidth, ballHeight);
		g.drawImage(image, ballX-12, ballY-12);
	//	g.draw(boundingBox);
		
	}

	public int getX() {
		return (int) boundingBox.getCenterX();
	}
	
	public int getLastX() {
		return lastBallX;
	}
	
	public int getLastY() {
		return lastBallY;
	}

	public void setX(int x) {
		ballX = x;
	}
	
	public void setVelX(int x) {
		ballXVel = x;
	}
	
	public void setVelY(int y) {
		ballYVel = y;
	}

	public void setY(int y) {
		ballY = y;
	}

	public int getY() {
		return (int) boundingBox.getCenterY();
	}

	
	
	
	public void updateBall(Paddle paddle, Shape wallX, Shape wallY){
		
		
		
	//System.out.println( System.currentTimeMillis()-startTime);
		
		if (Play.paused) {

		} else {
			handlePaddleCollision(paddle);
			handleWallCollisions(wallX, wallY);
			lastBallX = ballX;
			lastBallY = ballY;

			ballX += ballXVel;
			ballY += ballYVel;

			lastBoundingBox = boundingBox;
			
			boundingBox.setCenterX(ballX);
			boundingBox.setCenterY(ballY);
		}

	}
	
	private void handlePaddleCollision(Paddle paddle){
		if(boundingBox.intersects(paddle.top))
			ballCollide(paddle);
		else if(boundingBox.intersects(paddle.left)){
			hit(1);
			ballX-=4;
		}else if(boundingBox.intersects(paddle.right)){
			hit(1);
			ballX+=4;
		}
			
		
	}
	
	private void handleWallCollisions(Shape wallX, Shape wallY){
		if(this.getBoundingBox().intersects(wallY)){
			hit(1);
		}
		if(this.getBoundingBox().intersects(wallX)){
			hit(0);
		}
	}

	
	public void ballCollide(Paddle p) {
		
		if(Play.currentPowerUp==PowerUpType.STICKY){
			Play.start = true;
			start();
		}
		
		
	    double ballCenterX = ballX;
	    double paddleWidth = 170;
	    double paddleCenterX = p.top.getCenterX();
	  
	    
	    double speedX = ballXVel;
	    double speedY = ballYVel;

	   
	    
	  
	    double posX = 11*(ballCenterX - paddleCenterX) / (paddleWidth/2+12);
	   //.out.println((ballCenterX - paddleCenterX) / (paddleWidth/2+12));
	    //System.out.println(ballXVel);
	    Play.pos = (posX);
	    ballXVel = (int) Math.round(posX) ;
	    System.out.println(posX);
	    System.out.println(ballXVel);
	    ballY -=7;
		
		
		
		
		
		
		
	//	System.out.println("HIT");

		ballYVel *= -1;
	//	getAngle(p);
		// ballY *= -1;

	}

	@Override
	public Shape getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}

	public void hitBrick(int i) {
		if (i == 0) {
			/*	if (ballYVel > 0) {
					ballY -= 4;
				} else {
					ballY += 4;
				}
		*/
				boundingBox.setY(ballY);
				if(Play.currentPowerUp!=PowerUpType.FIRE)
					ballYVel *= -1;
		//		System.out.println("SWTICH Y");
			} else if (i == 1) {
				if(Play.currentPowerUp!=PowerUpType.FIRE)
					ballXVel *= -1;
			//	System.out.println("SWTICH X");
			}
			boundingBox.setCenterX(ballX);
			boundingBox.setCenterY(ballY);
	}
	public void hit(int i) {
		lastBoundingBox = boundingBox;
		if (i == 0) {
		/*	if (ballYVel > 0) {
				ballY -= 4;
			} else {
				ballY += 4;
			}
	*/
			boundingBox.setCenterY(ballY);
			
				ballYVel *= -1;
		//	System.out.println("SWTICH Y");
		} else if (i == 1) {
			System.out.println("switchX");
				ballXVel *= -1;
	//		System.out.println("SWTICH X");
		}
		boundingBox.setCenterX(ballX);
		boundingBox.setCenterY(ballY);
	}

	
	public void setSpeed(int s){
		ballSpeed =s;
		ballYVel = s;
	}

}