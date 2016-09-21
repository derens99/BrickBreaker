
package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import brick.Score;
import Objects.Paddle;
import guiobjects.Button;

public class GameOver extends BasicGameState{
	
	private final Color color = new Color(125, 125, 125, 180);
	private Image background;
	private Image paddle;

	private Button exit;

	private Paddle pad;
	
	private Score s;
	
	public static final int BUTTON_WIDTH = 400;
	public static final int BUTTON_HEIGHT = 100;
	
	public GameOver(int state){
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/background.jpg");
		paddle = new Image("res/platform.jpg");
		pad = new Paddle(Game.GAME_WIDTH / 2 - 100, Game.GAME_HEIGHT - 50);
	//	s = new Score();
		exit = new Button(new Image("res/quit.png"), new Image("res/quitSelect.png"), Game.GAME_WIDTH / 2 - 200,
				Game.GAME_HEIGHT / 2 + 200, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		g.drawImage(paddle, pad.getX(), Game.GAME_HEIGHT - 50);
		
		Play.ball.drawBall(g);
		pad.drawPaddle(g);
		
		Play.l.drawBricks(g);
		
		g.setColor(color);
		g.fillRect(0, 0, 1600, 900);
		exit.drawButton(g);
		g.setColor(Color.white);
		g.drawString(Play.s.getScore(), 740, 500);
		g.drawImage(new Image("res/gameOver.png"), 400, 250);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		if (exit.isClicked()) {
			try {
				Play.end = false;
				gc.reinit();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int getID() {
		return 6;
	}
	
	
	
}





