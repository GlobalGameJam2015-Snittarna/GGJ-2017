package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PowerUp extends GameObject {
	public enum Type {BARREL, BOMB};
	
	private Type type;
	
	public PowerUp(Vector2 position, Type type) {
		super(position, new Vector2(32, 32),  new Animation(new Sprite(AssetManager.getTexture(getImageName(type)))));
	}
	
	public static String getImageName(Type type) {
		return (type == Type.BARREL) ? "barrel" : "bomb";
	}
}
