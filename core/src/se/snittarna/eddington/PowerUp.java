package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PowerUp extends GameObject implements Depthable {
	public enum Type {BARREL, PLANK};
	
	private Type type;
	private int depth;
	
	public int getDepth() {
		return depth;
	}
	
	public PowerUp(Vector2 position, Type type, int depth) {
		super(position, new Vector2(AssetManager.getTexture(getImageName(type)).getRegionWidth(), AssetManager.getTexture(getImageName(type)).getRegionHeight()),  new Animation(new Sprite(AssetManager.getTexture(getImageName(type)))));
		this.type = type;
		this.depth = depth;
	}
	
	public void update(float dt) {
		for (GameObject g : getScene().getObjects()) {
			if (g instanceof Player) {
				if(g.getHitbox().collision(getHitbox()) && ((Depthable)g).getDepth() == depth) {
					if(type == Type.BARREL) {
						if(((Player)g).isBoat()) ((Player) g).addAmmo();
					} else {
						if(!((Player)g).isBoat()) ((Player) g).addBoatParts();
					}
					getScene().removeObject(this);
				}
			}
		}
		
		setPosition(getPosition().add(new Vector2(-10 * dt, 0)));
		System.out.println("powerup" + getPosition().x);
		super.update(dt);
	}
	
	public static String getImageName(Type type) {
		return (type == Type.BARREL) ? "barrel" : "plank";
	}
}
