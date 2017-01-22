package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class PowerUp extends GameObject {
	public enum Type {BARREL, BOMB};
	
	public PowerUp(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public String getImageName(Type type) {
		return (type == Type.BARREL) ? "barrel" : "bomb";
	}
}
