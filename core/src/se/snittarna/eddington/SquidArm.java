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
	
	private final float BASE_LEVEL = GameScene.getOceanLevel(3);
	private final float MAX_EXTENSION = GameScene.getOceanLevel(-3);
	
	private Sprite arm;
	
	private boolean goUp;
	private boolean attacking;
	
	public SquidArm(float x, float extendSpeed) {
		super(new Vector2(x, GameScene.getOceanLevel(3)), new Vector2(32, 32), new Animation(new Sprite(AssetManager.getTexture("hand"))));
		this.extendSpeed = extendSpeed;
	}
	
	public void update(float dt) {
		if(!attacking) tease();
		super.update(dt);
	}
	
	public void attack() {
		this.setPosition(new Vector2(this.getPosition().cpy().x, MathUtils.lerp(this.getPosition().cpy().y, MAX_EXTENSION, extendSpeed)));
	}
	
	public void tease() {
		waveCount += 0.1f;
		this.setPosition(new Vector2(this.getPosition().cpy().x, this.getPosition().cpy().y + ((float)Math.sin(waveCount)*0.5f)));
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		if(arm != null) arm.draw(batch);
	}
}
