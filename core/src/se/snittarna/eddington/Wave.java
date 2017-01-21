package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject {
	private int step;
	private int height;
	
	private float speed;
	
	public Wave(float x, float speed, int step, int height) {
		super(new Vector2(x, GameScene.getOceanLevel(step)), new Vector2(2.5f+(height*1.8f), 1+(height*1.3f)), new Animation(new Sprite(AssetManager.getTexture(getImageName(height)))));
		this.speed = speed;
	}
	
	public void update(float dt) {
		setPosition(getPosition().add(new Vector2(speed, 0).cpy().scl(dt)));
		super.update(dt);
	}
	
	public static String getImageName(int height) {
		return "wave" + height;
	}
}
