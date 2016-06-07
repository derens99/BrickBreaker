package brick;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import gui.Play;

public class Brick {

	private int x, y;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 30;
	public static int numHit, totalHit;
	private Image image;
	public Shape boundingBoxTop;
	public Shape boundingBoxSide;
	public boolean hit;
	private int imageNum;
	private int hitIndex;

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
		boundingBoxTop = new Rectangle(x + 5, y - 5, WIDTH - 10, HEIGHT + 5);
		boundingBoxSide = new Rectangle(x - 5, y + 5, WIDTH + 5, HEIGHT - 5);
		numHit = 0;
		totalHit = 0;
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

	public Shape getTop() {
		return boundingBoxTop;
	}

	public Shape getSide() {
		return boundingBoxSide;
	}

	public void hit() throws SlickException {
		hitIndex++;
		if (hitIndex == 3) {
			numHit++;
			totalHit++;
			hit = true;
		} else {
			image = new Image(images[hitIndex][imageNum]);
		}
	}

	public boolean isHit() {
		return hit;
	}

}
