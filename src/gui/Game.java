package gui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	
	public static final String NAME = "Brickbreaker";
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int INGAMEMENU = 2;
	
	public static final int GAME_WIDTH  = 1600;
	public static final int GAME_HEIGHT  = 900;
	
	public Game(String name) {
		super(NAME);
		this.addState(new Menu(MENU));
		this.addState(new Play(PLAY));
		this.addState(new IngameMenu(INGAMEMENU));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(MENU).init(gc, this);
		this.getState(PLAY).init(gc, this);
		this.getState(INGAMEMENU).init(gc, this);
		this.enterState(MENU);
		gc.setVSync(true);
		gc.setTargetFrameRate(60);
	}
	
	public static void main(String[] args) throws Throwable {
		AppGameContainer container;
		try{
			container = new AppGameContainer(new Game(NAME));
			container.setDisplayMode(1600,  900, false);
			container.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
	
}
