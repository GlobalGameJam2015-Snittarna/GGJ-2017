package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject {
	private float currentLevel;
	
	public Wave(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		super.update(dt);
	}
}
