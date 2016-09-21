package Objects;



import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import gui.Game;
import gui.Play;

public class Paddle extends Entity{
	
	public int x;
	public int y;

	
	public Line top;
	public Line left;
	public Line right;
	
	private Image image;
	static int WIDTH = 160;
	static int HEIGHT = 30;
	
	public Paddle(int xc, int yc) throws SlickException{
	
		x = xc;
		y = yc;
		top = new Line(x-5,y,x+170,y);
		
		left = new Line(x-5,850,x-5,900);
		right = new Line(x+170,y,x+170,900);
		image = new Image("res/platform.jpg");
		
	}
	
	public void drawPaddle(Graphics g){
		g.drawImage(image, x, y);
	//	g.draw(top);
	//	g.draw(left);
	//	g.draw(right);
		
		
	}
	
	public void updatePaddle(){
		if(Play.paused){
			
		}else{
		//	x = Mouse.getX()-80;
			top.setCenterX(x+80);;
			left.setCenterX(x-7);
			right.setCenterX(x+WIDTH+7);
		}
		
	}
	
	public int getX(){
		return x;
	}
	public void changeX(int xc){
		x = xc;
	
	//	System.out.println("X: " + x +"y: " + y);	
		//System.out.println("X: " + top.getX() +"y: " + top.getY());
	}

	@Override
	public Shape getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
	
}
