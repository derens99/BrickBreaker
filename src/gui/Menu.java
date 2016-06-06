package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	
	private Image play;
	private Image quit;
	private Image tutorial;
	private Image settings;
	
	private Image background;
	
	private Image meteor;
	private int pos=20;
	
	public static final int BUTTON_WIDTH = 400;
	public static final int BUTTON_HEIGHT = 100;
	
	public Menu(int state){
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/background.jpg");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		play = new Image("res/play.png");
		quit = new Image("res/quit.png");
		tutorial = new Image("res/tutorial.png");
		settings = new Image("res/settings.png");
		
		meteor = new Image("res/meteorL.png");
		
		g.drawImage(background, 0, 0);
		
		g.drawImage(play,Game.GAME_WIDTH/2-200, Game.GAME_HEIGHT/2-150);
		g.drawImage(quit,Game.GAME_WIDTH/2-425, Game.GAME_HEIGHT/2+150);
		g.drawImage(tutorial,Game.GAME_WIDTH/2-200, Game.GAME_HEIGHT/2);
		g.drawImage(settings,Game.GAME_WIDTH/2+25, Game.GAME_HEIGHT/2+150);
		
		g.drawImage(meteor, pos, 20);
		pos++;
		if(pos >= Game.GAME_WIDTH){
			pos=-200;
		}
		
		g.drawString("X: " + x + " Y: " + y, 100, 10);
		
		highlightEffect(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		//Play button
		if((x > 600 && x < 1000) && (y < 600 && y > 500)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
			
		}
		//Quit button
		if(x > 375 && x < 775 && y<300 && y > 200){
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}
	
	private void highlightEffect(Graphics g){
		int x = Mouse.getX();
		int y = Mouse.getY();
		if((x > 600 && x < 1000) && (y < 600 && y > 500)){
			try {
				play = new Image("res/playSelect.png");
				g.drawImage(play,Game.GAME_WIDTH/2-200, Game.GAME_HEIGHT/2-150);
			} catch (SlickException e) {
			}
		}
		if((x > 375 && x < 775)&&(y < 300 && y > 200)){
			try {
				quit = new Image("res/quitSelect.png");
			} catch (SlickException e) {
			}
			g.drawImage(quit,Game.GAME_WIDTH/2-425, Game.GAME_HEIGHT/2+150);
		}
		if(x > 600 && x < 1000 && y < 450 && y > 350){
			try {
				tutorial = new Image("res/tutorialSelect.png");
			} catch (SlickException e) {
			}
			g.drawImage(tutorial,Game.GAME_WIDTH/2-200, Game.GAME_HEIGHT/2);
		}
		if(x > 825 && x < 1225 && y < 300 && y > 200){
			try {
				settings = new Image("res/settingsSelect.png");
			} catch (SlickException e) {
			}
			g.drawImage(settings,Game.GAME_WIDTH/2+25, Game.GAME_HEIGHT/2+150);
		}
	}
	
}
