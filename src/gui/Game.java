
package gui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public static final String NAME = "Brickbreaker";
	public static final int MENU = 1;
	public static final int PLAY = 2;
	public static final int TUTORIAL = 3;
	public static final int SETTINGS = 4;
	public static final int INGAMEMENU = 5;
	public static final int GAMEOVER = 6;

	public static final int GAME_WIDTH = 1600;
	public static final int GAME_HEIGHT = 900;

	public static Menu m = new Menu(MENU);
	
	public Game(String name) {
		super(NAME);
		this.addState(m);
		this.addState(new Play(PLAY));
		this.addState(new Settings(SETTINGS));
		this.addState(new Tutorial(TUTORIAL));
		this.addState(new IngameMenu1(INGAMEMENU));
		this.addState(new GameOver(GAMEOVER));
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MENU).init(gc, this);
		this.getState(PLAY).init(gc, this);
		this.getState(SETTINGS).init(gc, this);
		this.getState(TUTORIAL).init(gc, this);
		this.getState(GAMEOVER).init(gc, this);
		this.enterState(MENU);
		gc.setVSync(true);
		gc.setTargetFrameRate(60);
		gc.setSmoothDeltas(true);
		
	}

	public static void main(String[] args) throws Throwable {
		AppGameContainer container = new AppGameContainer(new Game(NAME));

		container.setDisplayMode(1600, 900, false);
		container.setAlwaysRender(true);

		container.start();

	}

}


