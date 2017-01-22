package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject implements Depthable {
	private static final Vector2[] points = new Vector2[] {
		new Vector2(43, 24),
		new Vector2(40, 31),
		new Vector2(50, 51)
	};	
	
	public int getDepth() {
		return step;
	}
	
	private int step;
	private int height;
	
	private float speed;
	
	/**
	 * where the tip of the wave is.
	 */
	private Vector2 tip; 
	
	
	public Vector2 getTip() {
		return tip.cpy();
	}

	public Wave(float x, float speed, int step, int height) {
		super(new Vector2(x, GameScene.getOceanLevel(step)), 
				new Vector2(AssetManager.getTexture(("wave" + height)).getRegionWidth(), AssetManager.getTexture(("wave" + height)).getRegionHeight()), 
				new Animation(new Sprite(AssetManager.getTexture(("wave" + height))), 2, 0, 1));
		this.speed = speed;
		this.tip = points[height];
		this.step = step;
		this.height = height;
	}
	
	public void update(float dt) {
		setPosition(getPosition().add(new Vector2(speed, 0).cpy().scl(dt)));
		if(height == 1) getSprite().animate(dt);
		super.update(dt);
	}

	public float getHeightAt(float x, int step) {
		if (step != this.step || x < getPosition().x || x > getPosition().x + getSize().x) {
			return Integer.MIN_VALUE;
		} else {
			if (x < getPosition().x + tip.x) {
				return MathUtils.lerp(GameScene.getOceanLevel(step), GameScene.getOceanLevel(step) + tip.y, (x - getPosition().x) / tip.x);
			} else {
				return tip.y + MathUtils.lerp(GameScene.getOceanLevel(step) + tip.y, GameScene.getOceanLevel(step), (x - getPosition().x) / (getSize().x - tip.x));
			}
		}
	}
}
