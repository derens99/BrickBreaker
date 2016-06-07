package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Objects.*;
import brick.Brick;
import brick.Level;
import brick.Score;

public class Play extends BasicGameState {

	private Image background;
	private Image paddleI;
	private Image ballI;
	private Shape wallY;
	private Shape wallX;
	private Shape eGame;

	// MouseController mouse = new MouseController

	private Paddle paddle;
	private Ball ball;
	public Level l;
	public static int levelNum;
	private Score s;

	public Play(int state) {

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/background.jpg");
		paddleI = new Image("res/platform.jpg");
		ballI = new Image("res/ball.jpg");
		// ballS = new Ellipse(ball.getX(),ball.getY(),10,10);
		paddle = new Paddle(Game.GAME_WIDTH / 2 - 100, Game.GAME_HEIGHT - 50);
		ball = new Ball();
		wallX = new Rectangle(0, 10, Game.GAME_WIDTH, 1);
		wallY = new Rectangle(2, -50, Game.GAME_WIDTH - 7, Game.GAME_HEIGHT + 50);
		l = new Level();
		levelNum = 1;
		s = new Score();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);

		g.drawImage(paddleI, paddle.getX(), Game.GAME_HEIGHT - 50);
		g.drawOval(ball.getX(), ball.getY(), 20, 20);

		g.draw(paddle.getBoundingBox());
		g.draw(ball.getBoundingBox());
		g.draw(wallX);
		g.draw(wallY);
		l.drawBricks(g);

		g.drawString(s.getScore(), 1480, 860);
		g.drawString("Level: " + levelNum, 10, 860);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		Input input = gc.getInput();

		ball.update();
		paddle.setX(Mouse.getX() - 80);

		if(Brick.totalHit % 4 == 0){
			ball.speedUp();
		}
		if (ball.intersects(paddle)) {
			ball.ballCollide(paddle);
		}
		if (ball.getBoundingBox().intersects(wallY)) {
			ball.hit(1);

		}
		if (ball.getBoundingBox().intersects(wallX)) {
			ball.hit(0);
		}

		for (int i = 0; i < l.getBricks().size(); i++) {
			if (!l.getBricks().get(i).isHit()) {
				if (ball.getBoundingBox().intersects(l.getBricks().get(i).getTop())) {
					ball.hit(0);
					l.getBricks().get(i).hit();
					s.add(10);
				}
				else if(ball.getBoundingBox().intersects(l.getBricks().get(i).getSide())){
					ball.hit(1);
					l.getBricks().get(i).hit();
					s.add(10);
				}
				if (Brick.numHit == l.getBricks().size()) {
					l = new Level();
					levelNum++;
					Brick.newLevel();
				}
			}
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {

		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			paddle.moveLeft();
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			paddle.moveRight();
		}

	}

	@Override
	public int getID() {
		return 1;
	}

}
