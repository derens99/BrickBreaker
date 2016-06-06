package brick;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class Generate {

	public static ArrayList<Brick> rectangle(int startingX, int startingY,
			ArrayList<Brick> bricks, int r, int c) throws SlickException {
		int x = startingX;
		int y = startingY;
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				bricks.add(new Brick(x, y));
				x += Brick.WIDTH;
			}
			y += Brick.HEIGHT;
			x = startingX;
		}
		return bricks;
	}

	public static ArrayList<Brick> random(ArrayList<Brick> bricks)
			throws SlickException {
		Random r = new Random();
		for (int i = 0; i < 450; i += 30) {
			for (int j = 0; j < 1600; j += 80) {
				if (r.nextInt(2) == 0) {
					bricks.add(new Brick(j, i));
				}
			}
		}
		return bricks;
	}

	public static ArrayList<Brick> columns(ArrayList<Brick> bricks)
			throws SlickException {
		for (int i = 0; i < 450; i += 30) {
			for (int j = 0; j < 1600; j += 160) {
				bricks.add(new Brick(j, i));
			}
		}
		
		/*for(int i = 30; i < 450; i+= 30){
			bricks.add(new Brick(800, i));
		}
		*/
		return bricks;
	}

	public static ArrayList<Brick> checkered(ArrayList<Brick> bricks)
			throws SlickException {
		int j = 0;
		boolean tab = false;
		for (int i = 0; i < 450; i += 30) {
			if (tab) {
				j = 80;
				tab = false;
			} else {
				tab = true;
				j = 0;
			}

			for (; j < 1600; j += 160) {
				bricks.add(new Brick(j, i));
			}
		}
		return bricks;
	}

	public static ArrayList<Brick> rows(ArrayList<Brick> bricks)
			throws SlickException {
		for (int i = 0; i < 450; i += 60) {
			for (int j = 0; j < 1600; j += 80) {
				bricks.add(new Brick(j, i));
			}
		}
		return bricks;
	}

	public static ArrayList<Brick> x(ArrayList<Brick> bricks)
			throws SlickException {
		int x = 0;
		for (int i = 0; i < 630; i += 30) {
			bricks.add(new Brick(1520-x, i));
			bricks.add(new Brick(x, i));
			System.out.println(x);
			x+=80;
		}
		return bricks;
	}
}
