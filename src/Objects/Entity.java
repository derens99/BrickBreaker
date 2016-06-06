package Objects;

import org.newdawn.slick.geom.Shape;

public abstract class Entity {

	private Shape boundingBox;
	
	public abstract Shape getBoundingBox();
	

	public  boolean intersects(Entity entity) {
	    if (this.getBoundingBox() == null) {
	        return false;
	    }
	    return this.getBoundingBox().intersects(entity.getBoundingBox());
	    
	}
	
}
