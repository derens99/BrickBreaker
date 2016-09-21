package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import guiobjects.Button;

public class Menu extends BasicGameState{
	
	private Image play;
	private Image quit;
	private Image tutorial;
	private Image settings;
	
	private Button playButton;
	private Button quitButton;
	private Button tutorialButton;
	private Button settingsButton;
	
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
		playButton = new Button(new Image("res/play.png"), new Image("res/playSelect.png"), Game.GAME_WIDTH/2-200, Game.GAME_HEIGHT/2-150, BUTTON_WIDTH, BUTTON_HEIGHT);
		quitButton = new Button(new Image("res/quit.png"), new Image("res/quitSelect.png"), Game.GAME_WIDTH/2-425, Game.GAME_HEIGHT/2+150, BUTTON_WIDTH, BUTTON_HEIGHT);
		tutorialButton = new Button(new Image("res/tutorial.png"), new Image("res/tutorialSelect.png"), Game.GAME_WIDTH/2-200, Game.GAME_HEIGHT/2, BUTTON_WIDTH, BUTTON_HEIGHT);
		settingsButton = new Button(new Image("res/settings.png"), new Image("res/settingsSelect.png"), Game.GAME_WIDTH/2+25, Game.GAME_HEIGHT/2+150, BUTTON_WIDTH, BUTTON_HEIGHT);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		g.drawImage(background, 0, 0);
		
		playButton.drawButton(g);
		quitButton.drawButton(g);
		tutorialButton.drawButton(g);
		settingsButton.drawButton(g);
		
		meteor = new Image("res/meteorL.png");
		
		g.drawImage(meteor, pos, 20);
		pos++;
		if(pos >= Game.GAME_WIDTH){
			pos=-200;
		}
		
		g.drawString("X: " + x + " Y: " + y, 100, 10);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		if(playButton.isClicked()){
			sbg.enterState(Game.PLAY);
		}
		if(quitButton.isClicked()){
			System.exit(0);
		}
		if(tutorialButton.isClicked()){
			sbg.enterState(Game.TUTORIAL);
		}
		if(settingsButton.isClicked()){
			sbg.enterState(Game.SETTINGS);
		}
		
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
