package brick;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Level {

	private Random r;
	private final int WIDTH = 85;
	private final int NUM_ROWS = 20;
	private final int HEIGHT = 35;
	private ArrayList<Brick> bricks;

	public Level() throws SlickException {
		r = new Random();
		bricks = new ArrayList<Brick>();
		fillScreen();
	}

	public void fillScreen() throws SlickException {
		int levelType = r.nextInt(6) + 1;
		switch (levelType) {
		case 1:
			generateRectangleCoordinates();
			// bricks.add(new Brick(800, 450));
			break;
		case 2:
			generateRandom();
			break;
		case 3:
			generateCheckered();
			break;
		case 4:
			generateColumns();
			break;
		case 5:
			generateRows();
			break;
		}

	}

	private void generateRows() throws SlickException {
		bricks = Generate.rows(bricks);
	}

	private void generateCheckered() throws SlickException {
		bricks = Generate.checkered(bricks);
	}

	private void generateColumns() throws SlickException {
		bricks = Generate.columns(bricks);
	}

	public void generateRandom() throws SlickException {
		bricks = Generate.random(bricks);
	}

	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	public void generateRectangleCoordinates() throws SlickException {
		int rows = (r.nextInt(6) + 4) + 7;
		int columns = (r.nextInt(3) + 3) + 5;
		// int rows = 1;
		// int columns = 3 ;

		int x = getStartingXCoordinate(rows);
		int y = getStartingYCoordinate(columns);
		System.out.println("X: " + x + "\nY: " + y);
		bricks = Generate.rectangle(x, y, bricks, rows, columns);
	}

	private int getStartingXCoordinate(int rows) {
		return WIDTH * getRowNumber(rows);

	}

	private int getStartingYCoordinate(int columns) {
		return 15 * getColumnNumber(columns);
	}

	private int getColumnNumber(int columns) {
		return (HEIGHT - columns) / 2;
	}

	private int getRowNumber(int rows) {
		return (NUM_ROWS - rows) / 2;
	}

	public void drawBricks(Graphics g) throws SlickException {
		g.setColor(Color.white);
		for (int i = 0; i < bricks.size(); i++) {
			if (!bricks.get(i).isHit()) {
				g.drawImage(bricks.get(i).getImage(), bricks.get(i).getX(), bricks.get(i).getY());
				g.setColor(Color.white);
				// bricks.get(i).drawLines(g);
			}
		}
	}

	public void drawEpilepsy(Graphics g) throws SlickException {
		g.setColor(Color.white);
		for (int i = 0; i < bricks.size(); i++) {
			if (bricks.get(i).isHit()==false) {
				if(bricks.get(i).getPowerUp() == false){
					g.drawImage(bricks.get(i).getEpilepsy(), bricks.get(i).getX(), bricks.get(i).getY());
				}
			}
			
			// System.out.println(bricks.get(i).getX() + " : " +
			// bricks.get(i).getY());
		}
	}

	public boolean hasBrickAbove(Brick b) {
		for (Brick tempB : bricks) {
			if (!tempB.hit) {
				if (tempB.getY() == b.getY() - 35 && tempB.getX()==b.getX()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasBrickBelow(Brick b) {
		
		for (Brick tempB : bricks) {
			if (!tempB.isHit()) {
			
				if (tempB.getY() == b.getY() + 35 && tempB.getX()==b.getX()) {
					System.out.println("temp "+tempB.getY());
					System.out.println("b" + b.getY());
					System.out.println("--END--");
					return true;
				}
			}
		}
	
		return false;
	}

	public boolean hasBrickLeft(Brick b) {
		for (Brick tempB : bricks) {
			if (!tempB.hit) {
				if (tempB.getX() == b.getX() - 85 && tempB.getY()==b.getY()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasBrickRight(Brick b) {
		for (Brick tempB : bricks) {
			if (!tempB.hit) {
				if (tempB.getX() == b.getX() + 85 && tempB.getY()==b.getY()) {
					return true;
				}
			}
		}
		return false;
	}

}
