package guiobjects;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class CheckBox {
	
	private Image image;
	private Image imageChecked;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean checked;
	
	public boolean isChecked(){
		return isChecked();
	}
	
	private Rectangle rect;
	
	public CheckBox(Image i, Image iS, int x, int y, int w, int h){
		image = i;
		imageChecked = iS;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		rect = new Rectangle(this.x, this.y, width, height);
		checked = false;
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
				checked = !checked;
				return true;
			}
		}
		return false;
	}
	
	public void drawButton(Graphics g){
		if(checked){
			g.drawImage(imageChecked, x, y);
		}else{
			g.drawImage(image, x, y);
		}
	}

}
