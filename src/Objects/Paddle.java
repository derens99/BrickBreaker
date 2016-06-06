package Objects;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import gui.Game;

public class Paddle extends Entity{
	
	private int x;
	private int y;
	public Shape boundingBox;
	
	public Paddle(int x, int y){
		this.x = x;
		this.y = y;
		boundingBox = new Rectangle(x,Game.GAME_HEIGHT-50,160,30);
	}
	
	public void moveRight(){
		if(x >= Game.GAME_WIDTH-164){
			
		}else{

			x+=8;
		}
		boundingBox.setX(x);
		
	}
	
	public void moveLeft(){
		if(x <= 0){
		}else{
			x-=8;
		}
		boundingBox.setX(x);
		
	}
	
	public int getX(){
		return x;
	}
	public int setX(int i){
		boundingBox.setX(i);
	
	
		return x= i;
	}

	@Override
	public Shape getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}

	
	
	
	
}
