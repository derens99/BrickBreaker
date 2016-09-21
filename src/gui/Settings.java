package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import guiobjects.Button;
import guiobjects.CheckBox;

public class Settings extends BasicGameState{
	
	private Image background;
	
	private Button backButton;
	
	private Image epil;
	private CheckBox epilButton;
	
	public Settings(int state){
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/background.jpg");
		epil = new Image("res/epilepsy.png");
		epilButton = new CheckBox(new Image("res/epilepsyBox.png"), new Image("res/epilepsyBoxChecked.png"), 880, 580, 30, 30);
		backButton = new Button(new Image("res/back.png"), new Image("res/backSelect.png") ,10, 800, 250, 75);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		backButton.drawButton(g);
		g.drawString("X: " + Mouse.getX() + " Y: " + Mouse.getY(), 100, 10);
		g.drawImage(epil, 500, 500);
		epilButton.drawButton(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		if(backButton.isClicked()){
			sbg.enterState(Game.MENU);
		}
		if(epilButton.isClicked()){
			Play.epilepsy = true;
		}
	}

	@Override
	public int getID() {
		return 4;
	}
}
