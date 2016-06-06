package brick;


public class Vector {

	private int x, y;

	public Vector(){
		x = 0;
		y = 0;
		
	}
	public Vector(int xpos, int ypos){
		x = xpos;
		y = ypos;
		
	}
	
	public void addVector(Vector v){
		x += v.getX();
		y += v.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}




