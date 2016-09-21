package gui;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Objects.Ball;
import Objects.Paddle;
import Objects.PowerUp;
import Objects.PowerUpType;
import brick.Brick;
import brick.Level;
import brick.Score;

public class Play extends BasicGameState {

	private Image background;

	private Shape wallY;
	private Shape wallX;
	private Shape eGame;

	public static int numLives;

	private final String[] backgrounds = { "res/background.jpg", "res/invert.png" };

	private boolean hit = false;
	public static boolean paused;
	public static boolean start = true;
	public static boolean end = false;
	
	private boolean isPlaying=false;

	public ArrayList<PowerUp> pUps = new ArrayList<PowerUp>();

	private Paddle paddle;
	public static Ball ball;
	public static Level l;
	public static int levelNum;
	public static boolean epilepsy = false;
	public static Score s;
	private Random r;
	public static int numHit;
	public Side side = null;
	double startTime;
	public DecimalFormat df = new DecimalFormat("#.00");

	public static PowerUpType currentPowerUp;
	TrueTypeFont font;
	TrueTypeFont font2;

	private Sound sandstorm;
	private Sound music;

	public static double pos = 0;

	// load a default java font

	public Play(int state) {

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Font awtFont = new Font("Serif", Font.BOLD, 40);
		font = new TrueTypeFont(awtFont, false);

		paused = false;
		background = new Image("res/background.jpg");
		paddle = new Paddle(720, Game.GAME_HEIGHT - 50);
		ball = new Ball();
		wallX = new Rectangle(0, 10, Game.GAME_WIDTH, 1);
		wallY = new Rectangle(2, -50, Game.GAME_WIDTH - 7, Game.GAME_HEIGHT + 50);
		l = new Level();
		levelNum = 1;
		s = new Score();
		start = true;
		numHit = 0;
		eGame = new Line(0, 895, 1600, 895);
		numLives = 1;
		r = new Random();

		sandstorm = new Sound("res/darudestandstorm.wav");
		music = new Sound("res/music.wav");
		isPlaying = true;
		
	

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (Play.epilepsy) {
			g.drawImage(new Image(backgrounds[r.nextInt(2)]), 0, 0);
			
		} else {
			g.drawImage(background, 0, 0);
		}

		// Ball Stuff
		ball.drawBall(g);
		paddle.drawPaddle(g);

		if (Play.epilepsy) {
			l.drawEpilepsy(g);

		} else {
			l.drawBricks(g);
		}

		// g.draw(paddle.left);
		// g.draw(paddle.right);

		g.drawString(s.getScore(), 1480, 860);
		g.drawString("Level: " + levelNum, 10, 860);
		g.drawString("Lives: " + numLives, 10, 840);
		if (start) {
			font.drawString(Game.GAME_WIDTH / 2 - 100, 650, "Press Space");
		}
		if (System.currentTimeMillis() - startTime < 15000 && System.currentTimeMillis() - startTime > 0) {
			g.drawString("Time Left: " + df.format(15 - (System.currentTimeMillis() - startTime) / 1000),
					Game.GAME_WIDTH / 2 - 100, 550);
		}
		//g.drawString("" + pos, 800, 800);

		// g.draw(eGame);

		if (paused) {
			sbg.enterState(Game.INGAMEMENU);
		}

		for (PowerUp p : pUps) {
			p.drawOrb(g);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// System.out.println(Mouse.getX());
		loop();
		if (end) {
			sbg.enterState(Game.GAMEOVER);
			music.stop();
			isPlaying = false;
			currentPowerUp= PowerUpType.NONE;
			startTime = 0;
		}
		Input input = gc.getInput();
		boolean shouldEnd = false;
		if ((ball.boundingBox.intersects(paddle.left) || ball.boundingBox.intersects(paddle.right))
				&& !ball.boundingBox.intersects(paddle.top)) {
			shouldEnd = true;
		}
		if (ball.boundingBox.intersects(eGame) || shouldEnd) {

			if (numLives == 1) {
				end = true;
				ball.start();
				l = new Level();
				levelNum = 1;
				Brick.newLevel();
				numHit = 0;
				start = true;
			} else {
				ball.start();
				start = true;
				numLives--;
			}
		}

		if (input.isKeyDown((Input.KEY_A)) && paddle.getX() > 0) {
			if (input.isKeyDown((Input.KEY_LSHIFT)))
				paddle.changeX(paddle.getX() - 6);
			else
				paddle.changeX(paddle.getX() - 13);
			
			 if(!music.playing()){
				music.loop(1f, .5f);
				isPlaying = true;
			}

			
		}

		if (input.isKeyDown((Input.KEY_D)) && paddle.getX() < 1440) {

			if (input.isKeyDown((Input.KEY_LSHIFT)))
				paddle.changeX(paddle.getX() + 6);
			else
				paddle.changeX(paddle.getX() + 13);
			
			 if(!music.playing()){
				music.loop(1f, .5f);
				isPlaying = true;
			}

			
		}
		if (start && input.isKeyPressed((Input.KEY_SPACE))) {
			 if(!music.playing()){
					music.loop(1f, .5f);
					isPlaying = true;
				}
			start = false;
			ball.setSpeed(-7);
			ball.setVelX(0);
		}
		if (start) {
			ball.setX(paddle.getX() + 80);
		} else {
			ball.updateBall(paddle, wallX, wallY);
		}

		hit = false;

		paddle.updatePaddle();

		collide();

		if (Brick.totalHit % 4 == 0) {
			// ball.speedUp();
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			paused = !paused;
			if(music.playing()){
			sandstorm.stop();
			music.stop();
			isPlaying = false;
			
			}			
		}

		if (currentPowerUp != PowerUpType.NONE) {
			if (System.currentTimeMillis() - startTime < 15000) {

			} else {
				currentPowerUp = PowerUpType.NONE;
			}

		}

		for (int i = 0; i < pUps.size(); i++) {
			System.out.println(pUps.get(i));
			PowerUp p = pUps.get(i);
			if (p.boundingBox.intersects(paddle.top)) {

				pUps.remove(i);
				currentPowerUp = p.type;

				switch (p.type) {
				case FIRE:
					startTime = System.currentTimeMillis();
					break;
				case STICKY:
					startTime = System.currentTimeMillis();
					break;
				case LIVE:
					startTime = 0;
					numLives++;
					break;
				case NONE:
					break;

				}

			}
			p.move();
		}

	}
	
	private void loop(){
		
		if(music.playing() && epilepsy == true){
			sandstorm.loop(1.0f, 1.0f);
			
			isPlaying = false;
		}else{
			return;
		}
		
	}

	private void collide() throws SlickException {

		boolean done = false;
		for (int i = 0; i < l.getBricks().size(); i++) {

			if (done) {
				break;
			}
			Brick brick = l.getBricks().get(i);

			if (!brick.isHit()) {
				// IF any collision with brick occurs
				if (ball.getBoundingBox().intersects(brick.getTop())
						|| ball.getBoundingBox().intersects(brick.getBottom())
						|| ball.getBoundingBox().intersects(brick.getLeft())
						|| ball.getBoundingBox().intersects(brick.getRight())) {
					if (brick.type != PowerUpType.NONE)
						pUps.add(new PowerUp(brick.type, brick.getX() + 35, brick.getY()));

					int radius = 1;

					while (radius <= 12) {
						Shape temp = new Ellipse(ball.boundingBox.getCenterX(), ball.boundingBox.getCenterY(), radius,
								radius);

						/// System.out.println("radius:"+radius);

						// System.out.println("BALL" +
						// ball.boundingBox.getCenterX());
						// System.out.println("TEMP X: " + temp.getCenterX());
						// System.out.println("TEMP Y: " + temp.getCenterY());

						// TOP
						if (temp.intersects(brick.getTop()) && !l.hasBrickAbove(brick)
								&& ball.lastBoundingBox.getCenterY() <= brick.getTop().getMaxY()) {
							// System.out.println("TOP");
							// System.out.println(brick.getTop().getCenterY());
							// if(!hit)
							ball.hitBrick(0);
							brick.hit();
							s.add(10);
							done = true;
							side = Side.TOP;

							break;

							// BOT
						} else if (temp.intersects(brick.getBottom()) && !l.hasBrickBelow(brick)
								&& ball.getLastY() >= brick.getBottom().getMaxY()) {
							// System.out.println("BOT");
							// System.out.println(brick.getBottom().getCenterY());

							// if(!hit)
							ball.hitBrick(0);
							brick.hit();
							s.add(10);
							done = true;
							side = Side.BOTTOM;
							break;

							// LEFT
						} else if (temp.intersects(brick.getLeft()) && !l.hasBrickLeft(brick)) {
							// System.out.println("LEFT");
							// System.out.println(brick.getLeft().getCenterX());

							// if(!hit)
							ball.hitBrick(1);
							brick.hit();
							s.add(10);
							done = true;
							side = Side.LEFT;
							break;

							// RIGHT
						} else if (temp.intersects(brick.getRight()) && !l.hasBrickRight(brick)) {
							// System.out.println("RIGHT");
							// System.out.println(brick.getRight().getCenterX());

							// if(!hit)
							ball.hitBrick(1);
							brick.hit();
							s.add(10);
							done = true;
							side = Side.RIGHT;
							break;
						} else {
							radius++;
						}

					}

				}
				hit = true;

			}
		}
		if (numHit == l.getBricks().size()) {
			ball.start();
			l = new Level();
			levelNum++;
			Brick.newLevel();
			numHit = 0;
			start = true;
		}

	}

	@Override
	public int getID() {
		return 2;
	}

}
