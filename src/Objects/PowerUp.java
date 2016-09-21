package Objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class PowerUp {

	private int x;
	private int y;
	public PowerUpType type;
	private Image image;
	public Shape boundingBox;
	String imageIndex;
	public PowerUp(PowerUpType t, int xcoord, int ycoord )throws SlickException{
		switch(t){
		case LIVE: imageIndex = "res/greenorb.png";
			break;
		case STICKY:imageIndex = "res/purpleorb.png";
			break;
		case FIRE:imageIndex = "res/redorb.png";
			break;
		}
		
				type = t;
				x = xcoord;
				y = ycoord;
				image = new Image(imageIndex);
				boundingBox = new Rectangle(x,y,20,20);
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
		
	}
	public void setY(int y){
		this.y = y;
	}
	
	
	
	public void move(){
		y+=12;
		boundingBox.setCenterY(y);
	}
	
	public void drawOrb(Graphics g) {
	
		g.drawImage(image, x, y);
	//	g.draw(boundingBox);
	
	}
	
	
	
	
	
}
