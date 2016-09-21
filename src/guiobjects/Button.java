package guiobjects;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import gui.Game;

public class Button {
	
	private Image image;
	private Image imageSelect;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Rectangle rect;
	
	public Button(Image i, Image iS, int x, int y, int w, int h){
		image = i;
		imageSelect = iS;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		rect = new Rectangle(this.x, this.y, width, height);
	}
	
	public boolean isMouseOnButton(){
		int mouseX = Mouse.getX();
		int mouseY = 900 - Mouse.getY();
		
		if(rect.contains(mouseX, mouseY))
			return true;
		else
			return false;
	}
	
	public boolean isClicked(){
		if(isMouseOnButton()){
			if(Mouse.isButtonDown(0)){
				return true;
			}
		}
		return false;
	}
	
	public void drawButton(Graphics g){
		if(isMouseOnButton()){
			g.drawImage(imageSelect, x, y);
		}else{
			g.drawImage(image, x, y);
		}
	}
	

}
