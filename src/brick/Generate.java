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
				x += Brick.WIDTH + 5;
			}
			y += Brick.HEIGHT + 5;
			x = startingX;
		}
		return bricks;
	}

	public static ArrayList<Brick> random(ArrayList<Brick> bricks)
			throws SlickException {
		Random r = new Random();
		for (int i = 10; i < 450; i += 35) {
			for (int j = 30; j + 80 < 1600; j += 85) {
				if (r.nextInt(2) == 0) {
					bricks.add(new Brick(j, i));
				}
			}
		}
		return bricks;
	}

	public static ArrayList<Brick> columns(ArrayList<Brick> bricks)
			throws SlickException {
		for (int i = 70; i <= 350; i += 35) {
			for (int j = 30; j < 1520; j += 160) {
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
		for (int i = 10; i < 450; i += 35) {
			if (tab) {
				j = 90;
				tab = false;
			} else {
				tab = true;
				j = 10;
			}

			for (; j + 80 < 1600; j += 160) {
				bricks.add(new Brick(j, i));
			}
		}
		return bricks;
	}

	public static ArrayList<Brick> rows(ArrayList<Brick> bricks)
			throws SlickException {
		for (int i = 90; i < 390; i += 60) {
			for (int j = 5; j + 80 < 1600; j += 84) {
				bricks.add(new Brick(j, i));
			}
		}
		return bricks;
	}
}
