package Objects;

import gui.Game;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import gui.*;
public class Ball extends Entity {

	
     int ballX, ballY, lastBallX, lastBallY;
     int ballWidth, ballHeight;
     int ballXVel, ballYVel;
     int ballSpeed;
     
     int lastDrawX, lastDrawY;
     public Shape boundingBox;
     public Ball()
     {
        ballX = lastBallX = 790;
        ballY = lastBallY = 700;
        ballWidth = 25;
        ballHeight = 25;
        ballSpeed = -7;
        ballXVel = 0;
        ballYVel = ballSpeed;
        boundingBox = new Ellipse(ballX, ballY, 10, 10);
     }
     
     public int getX(){
    	 return ballX;
     }
     public void setX(int x){
    	 ballX = x;
     }  public void setY(int y){
    	 ballY = y;
     }
     
     public int getY(){
    	 return ballY;
     }
     
     
     public void update()
     {
   	  
    	 
    	 
        lastBallX = ballX;
        lastBallY = ballY;
        
       ballX += ballXVel;
        ballY += ballYVel;
        
    
        
      
        
        boundingBox.setX(ballX);
        boundingBox.setY(ballY);

     }
     
     public void ballCollide(Paddle p){
    	 System.out.println("HIT");
    	 
    	 ballYVel *= -1;
    	getAngle(p);
    	 //	ballY *= -1;
    	 
    	 
     }

	@Override
	public Shape getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}
     
	public void hit(int i){
		if(i == 0){
			if(ballYVel>0){
				ballY -= 4;
			}else{
				ballY += 4;
			}
			boundingBox.setY(ballY);
			
			ballYVel *=-1;
		}
		else if(i == 1){
			if(ballXVel>0){
				ballX -= 3;
			}else{
				ballX += 3;
			}
			ballXVel *=-1;
		}
		 boundingBox.setX(ballX);
	     boundingBox.setY(ballY);
	}
	
	private void getAngle(Paddle p){
		int padX = p.getX();
		int x = ballX-padX;
		
			if(x<14)
			ballXVel = -5;
		else if(x<28)
			ballXVel = -4;
		else if(x<42)
			ballXVel = -3;
		else if(x<56)
			ballXVel = -2;
		else if(x<70)
			ballXVel = -1;
		else if(x<90)
			ballXVel = 0;
		else if(x<114)
			ballXVel = 1;
		else if(x<128)
			ballXVel = 2;
		else if(x<142)
			ballXVel = 3;
		else if(x<156)
			ballXVel = 4;
		else if(x<170)
			ballXVel = 5;
			
			
	}
	
}