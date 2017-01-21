package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class SquidArm extends GameObject {
	private int health;
	
	private float currentLevel;
	private float extendSpeed;
	
	private final float MAX_EXTENSION = 100f;
	
	public SquidArm(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		super.update(dt);
	}
	
	public void extend() {
		currentLevel += extendSpeed;
	}
}
