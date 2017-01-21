package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
	
	private Vector2 velocity;
	private int step;
	
	public Player() {
		super(new Vector2(), new Vector2(1, 1), new Animation(new Sprite(AssetManager.getTexture("player"))));
		velocity = new Vector2();
	}

	
	public void update(float dt) {
		super.update(dt);
		
		velocity.add(0, GameScene.GRAVITY * dt);
		setPosition(getPosition().add(velocity.cpy().scl(dt)));
		
		if (getPosition().y < GameScene.getOceanLevel(step)) {
			float depth = GameScene.getOceanLevel(step) - getPosition().y;
			velocity.y += depth * 1f;
			velocity.sub(velocity.cpy().scl(.1f));
		}
	}
}
