package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SquidArm extends GameObject {
	private int health;
	
	private float currentLevel;
	private float extendSpeed;
	
	private final float BASE_LEVEL = 1;
	private final float MAX_EXTENSION = 100f;
	
	private Sprite arm;
	
	public SquidArm(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		super.update(dt);
	}
	
	public void extend() {
		currentLevel += extendSpeed;
	}
	
	public void tease() {
		float highTease = BASE_LEVEL + 0.5f;
		float lowTease = BASE_LEVEL + 0.5f;
		
		//MathUtils.lerp(fromValue, toValue, progress);
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		if(arm != null) arm.draw(batch);
	}
}
