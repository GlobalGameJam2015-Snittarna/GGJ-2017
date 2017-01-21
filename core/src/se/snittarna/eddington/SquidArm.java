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
	
	private boolean goUp;
	private boolean attacking;
	
	public SquidArm(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		if(!attacking) currentLevel = MathUtils.lerp(currentLevel, BASE_LEVEL, 0.1f);
		super.update(dt);
	}
	
	public void extend() {
		currentLevel += extendSpeed;
	}
	
	public void attack() {
		MathUtils.lerp(currentLevel, MAX_EXTENSION, 0.1f);
	}
	
	public void tease() {
		float highTease = BASE_LEVEL + 0.5f;
		float lowTease = BASE_LEVEL + 0.5f;
		
		if(goUp) {
			MathUtils.lerp(currentLevel, highTease, 0.1f);
			goUp = (currentLevel >= highTease-0.1f); 
		} else {
			MathUtils.lerp(currentLevel, lowTease, 0.1f);
			goUp = (currentLevel <= lowTease+0.1f); 
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		if(arm != null) arm.draw(batch);
	}
}
