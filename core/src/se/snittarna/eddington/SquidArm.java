package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SquidArm extends GameObject {
	private float currentLevel;
	private float extendSpeed;
	private float waveCount;
	private float endAttackCount;
	
	private final float BASE_LEVEL = GameScene.getOceanLevel(11)-120;
	private final float MAX_EXTENSION = GameScene.getOceanLevel(8);
	
	private Sprite arm;
	
	private boolean goUp;
	private boolean attacking;
	private boolean dying;
	public boolean destroy;
	
	public SquidArm(float x, float extendSpeed) {
		super(new Vector2(x, GameScene.getOceanLevel(11)-180), new Vector2(19, 180), new Animation(new Sprite(AssetManager.getTexture("longHand"))));
		this.extendSpeed = extendSpeed;
	}
	
	public void update(float dt) {
		if(!dying) {
			if(!attacking) {
				if(this.getPosition().cpy().y <= BASE_LEVEL-2) {
					this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, BASE_LEVEL, 0.05f)));
				} else 
					tease(dt);
			}
			else attack(dt);
		}
		else {
			this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, -300, 0.04f)));
			this.getSprite().setColor(1, 0, 0, 1);
			if(this.getPosition().cpy().y <= -300+5.5f) destroy = true;
		}
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
	
	public void setDying() {
		dying = true;
	}
	
	public boolean getDying() {
		return dying;
	}
	
	public boolean attackEnded() {
		return endAttackCount <= 0;
	}
}
