package brick;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import Objects.PowerUpType;
import gui.Play;

public class Brick {

	private int x, y;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 30;
	public static int numHit, totalHit;
	private Image image;
	public boolean hit;
	private int imageNum;
	private int hitIndex;
	public PowerUpType type = PowerUpType.NONE;
	
	private boolean isPowerUp;
	
	private Line top, bottom, left, right;

	public static String[][] images = {
			{ "res/greenbrick.jpg", "res/bluebrick.jpg", "res/redbrick.jpg", "res/purplebrick.jpg" },
			{ "res/greenbrick1.jpg", "res/bluebrick1.jpg", "res/redbrick1.jpg", "res/purplebrick1.jpg" },
			{ "res/greenbrick2.jpg", "res/bluebrick2.jpg", "res/redbrick2.jpg", "res/purplebrick2.jpg" } };

	private Random gen;

	public Brick(int x, int y) throws SlickException {
		this.x = x;
		this.y = y;
		hit = false;
		gen = new Random();
		imageNum = gen.nextInt(4);
		assignHitIndex();
		image = new Image(images[hitIndex][imageNum]);
		
		numHit = 0;
		totalHit = 0;
		if (powerUp()) {
			hitIndex = 2;
			image = new Image("res/powerupbrick.png");
			int rand = gen.nextInt(2);
			
			switch(rand){
			case 0: type = PowerUpType.FIRE;
				break;
			case 1: type = PowerUpType.LIVE;
				break;
			case 2: type = PowerUpType.STICKY;
				break;
			case 3: type = PowerUpType.FIRE;
				break;
			}
			
		} else {
			isPowerUp = false;
			imageNum = gen.nextInt(4);
			assignHitIndex();
			image = new Image(images[hitIndex][imageNum]);
		}
		top = new Line(x-1, y, x+WIDTH+1, y);
		bottom = new Line(x-1, y+HEIGHT, x+WIDTH+1, y+HEIGHT);
		left = new Line(x, y+1, x, y+HEIGHT-1);
		right = new Line(x+WIDTH, y+1, x+WIDTH, y+HEIGHT-1);
	}

	public void assignHitIndex() {
		if (Play.levelNum < 2) {
			hitIndex = 2;
		} else if (Play.levelNum < 4) {
			hitIndex = gen.nextInt(2);
		} else if (Play.levelNum < 6)
			hitIndex = gen.nextInt(3);
	}
	
	public static void newLevel(){
		numHit = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
	
	public Image getEpilepsy() throws SlickException{
		
		return new Image(images[gen.nextInt(2)][gen.nextInt(2)]);
	}
	
	public Line getTop(){
		return top;
	}

	public Line getBottom() {
		return bottom;
	}

	public Line getLeft() {
		return left;
	}

	public Line getRight() {
		return right;
	}
	
	public boolean powerUp() {
		if (gen.nextInt(37) == 12) {
			return true;
		}
		return false;
	}
	
	public boolean getPowerUp(){
		return isPowerUp;
	}
	
	public void drawLines(Graphics g){
		g.draw(top);
		g.draw(getBottom());
		g.draw(getRight());
		g.draw(getLeft());
	}
	public void drawTop(Graphics g){
		g.draw(top);
		
	}
	public void drawBot(Graphics g){
		
		g.draw(getBottom());
		
	}
	public void drawLeft(Graphics g){
		
		g.draw(getLeft());
	}
	public void drawRight(Graphics g){
		
		
		g.draw(getRight());
		
	}

	public void hit() throws SlickException {
		hitIndex++;
		if (hitIndex == 3) {
		//	System.out.println("BRICK being hit: (" + this.getX() + "," + this.getY()+")");
			numHit++;
			totalHit++;
			hit = true;
			
			Play.numHit++;
		} else {
			image = new Image(images[hitIndex][imageNum]);
		}
	}

	public boolean isHit() {
		return hit;
	}

}
