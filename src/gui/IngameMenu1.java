package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Objects.Paddle;
import guiobjects.Button;

public class IngameMenu1 extends BasicGameState{
	
	private final Color color = new Color(125, 125, 125, 180);
	private Image background;
	private Image paddle;

	private Button exit;
	private Button resume;

	private Paddle pad;
	
	public IngameMenu1(int state){
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/background.jpg");
		paddle = new Image("res/platform.jpg");
		pad = new Paddle(Game.GAME_WIDTH / 2 - 100, Game.GAME_HEIGHT - 50);
		
		resume = new Button(new Image("res/play.png"), new Image("res/playSelect.png"), Game.GAME_WIDTH / 2 - 200,
				Game.GAME_HEIGHT / 2 - 150, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
		exit = new Button(new Image("res/quit.png"), new Image("res/quitSelect.png"), Game.GAME_WIDTH / 2 - 200,
				Game.GAME_HEIGHT / 2, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
		
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
		resume.drawButton(g);
		exit.drawButton(g);
		g.setColor(Color.white);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame sbg, int arg2) throws SlickException {
		if (resume.isClicked()) {
			Play.paused = false;
			sbg.enterState(Game.PLAY);
		}
		if (exit.isClicked()) {
			
			sbg.getContainer().reinit();
		}
		
	}

	@Override
	public int getID() {
		return 5;
	}
	
	
	
}
