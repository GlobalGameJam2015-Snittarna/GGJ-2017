package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SquidArm extends GameObject {
	private int health;
	
	private float currentLevel;
	private float extendSpeed;
	private float waveCount;
	private float endAttackCount;
	
	private final float BASE_LEVEL = GameScene.getOceanLevel(11);
	private final float MAX_EXTENSION = GameScene.getOceanLevel(-3);
	
	private Sprite arm;
	
	private boolean goUp;
	private boolean attacking;
	
	public SquidArm(float x, float extendSpeed) {
		super(new Vector2(x, GameScene.getOceanLevel(11)), new Vector2(32, 32), new Animation(new Sprite(AssetManager.getTexture("hand"))));
		this.extendSpeed = extendSpeed;
	}
	
	public void update(float dt) {
		if(!attacking) tease(dt);
		else attack(dt);
		super.update(dt);
	}
	
	public void attack(float dt) {
		this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, MAX_EXTENSION, extendSpeed)));
		endAttackCount -= 10 * dt;
		if(attackEnded()) {
			attacking = false;
			goUp = true;
		}
	}
	
	public void tease(float dt) {
		if(!goUp) {
			waveCount += 10f * dt;
			this.setPosition(new Vector2(this.getPosition().cpy().x, this.getPosition().cpy().y + ((float)Math.sin(waveCount)*0.5f)));
		} else {
			this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, BASE_LEVEL, extendSpeed)));
			if(this.getPosition().cpy().y < BASE_LEVEL + 0.5f) goUp = false;
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		if(arm != null) arm.draw(batch);
	}

	public void startAttack() {
		if(!attacking) {
			attacking = true;
			endAttackCount = 10;
		}
	}
	
	public boolean attackEnded() {
		return endAttackCount <= 0;
	}
}
