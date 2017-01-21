package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SquidArm extends GameObject {
	private int health;
	
	private float currentLevel;
	private float extendSpeed;
	
	private final float BASE_LEVEL = GameScene.getOceanLevel(3);
	private final float MAX_EXTENSION = GameScene.getOceanLevel(-3);
	
	private Sprite arm;
	
	private boolean goUp;
	private boolean attacking;
	
	public SquidArm(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		if(!attacking) this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, BASE_LEVEL, 0.01f)));
		super.update(dt);
	}
	
	public void extend() {
		currentLevel += extendSpeed;
	}
	
	public void attack() {
		this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, MAX_EXTENSION, 0.01f)));
	}
	
	public void tease() {
		float highTease = BASE_LEVEL + 0.5f;
		float lowTease = BASE_LEVEL - 0.5f ;
		
		if(goUp) {
			this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, highTease, 0.01f)));
			goUp = (currentLevel >= highTease-0.01f); 
		} else {
			this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, lowTease, 0.01f)));
			goUp = (currentLevel <= lowTease+0.01f); 
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		if(arm != null) arm.draw(batch);
	}
}
