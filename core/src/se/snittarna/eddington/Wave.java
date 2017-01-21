package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject {
	private int step;
	private int height;
	
	private float speed;
	
	public Wave(float x, int step, int height) {
		super(new Vector2(x, GameScene.getOceanLevel(step)), new Vector2(1+(height*0.5f), 1+(height*0.3f)), new Animation(new Sprite(AssetManager.getTexture("wave" + height))));
	}
	
	public void update(float dt) {
		this.getPosition().cpy().add(new Vector2(speed, 0));
		super.update(dt);
	}
}
