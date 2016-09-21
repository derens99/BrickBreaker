package gui;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import guiobjects.Button;

public class Tutorial extends BasicGameState {

	private Image background;

	private Button backButton;
	private Image tutorial1;

	public Tutorial(int state) {
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		background = new Image("res/background.jpg");

		backButton = new Button(new Image("res/back.png"), new Image("res/backSelect.png"), 10, 800, 250, 75);
		//tutorial1 = new Image("res/tutorial1.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		backButton.drawButton(g);
		g.drawString("X: " + Mouse.getX() + " Y: " + Mouse.getY(), 100, 10);
		//g.drawImage(tutorial1, 200, 50);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame sbg, int arg2) throws SlickException {
		if(backButton.isClicked()){
			sbg.enterState(Game.MENU);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
