package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Objects.Ball;
import Objects.Paddle;
import brick.Level;
import guiobjects.Button;

public class IngameMenu {

	private final Color color = new Color(125, 125, 125, 180);
	private Image background;
	private Image paddle;

	private Button exit;
	private Button resume;

	private Paddle pad;

	public IngameMenu() throws SlickException {
		background = new Image("res/background.jpg");
		paddle = new Image("res/platform.jpg");
		pad = new Paddle(Game.GAME_WIDTH / 2 - 100, Game.GAME_HEIGHT - 50);

		// MAKE IMAGES FOR EXIT AND RESUME
		resume = new Button(new Image("res/play.png"), new Image("res/playSelect.png"), Game.GAME_WIDTH / 2 - 200,
				Game.GAME_HEIGHT / 2 - 150, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
		exit = new Button(new Image("res/quit.png"), new Image("res/quitSelect.png"), Game.GAME_WIDTH / 2 - 200,
				Game.GAME_HEIGHT / 2, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);

	}

	public void drawIngameMenu(Graphics g, Level l, Ball b, Paddle pad) throws SlickException {
		if(Play.paused){
			resume = new Button(new Image("res/play.png"), new Image("res/playSelect.png"), Game.GAME_WIDTH / 2 - 200,
					Game.GAME_HEIGHT / 2 - 150, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
			exit = new Button(new Image("res/quit.png"), new Image("res/quitSelect.png"), Game.GAME_WIDTH / 2 - 200,
					Game.GAME_HEIGHT / 2, Menu.BUTTON_WIDTH, Menu.BUTTON_HEIGHT);
			g.drawImage(background, 0, 0);
			g.drawImage(paddle, pad.getX(), Game.GAME_HEIGHT - 50);
			
			b.drawBall(g);
			pad.drawPaddle(g);
			
			l.drawBricks(g);

			g.setColor(color);
			g.fillRect(0, 0, 1600, 900);
			
			
			resume.drawButton(g);
			exit.drawButton(g);
			g.setColor(Color.white);
		}else{
			resume = null;
			exit = null;
		}
		

	}

	public void updateIngameMenu(StateBasedGame sbg) throws SlickException {
		if (resume.isClicked()) {
			Play.paused = false;
		}
		if (exit.isClicked()) {
			
			sbg.enterState(1);
			sbg.getContainer().reinit();
		}
	}
}
