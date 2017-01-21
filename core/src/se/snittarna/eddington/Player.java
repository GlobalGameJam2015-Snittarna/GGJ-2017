package se.snittarna.eddington;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
	
	private final int ACCELERATION = 1;
	private final float X_DRAG = .05f;
	private final float Y_DRAG = .05f;
	
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
		
		if (velocity.x < 0) {
			getSprite().setFlip(true, false);
		} else {
			getSprite().setFlip(false, false);
		}
		
		
		/**
		 * step up and down
		 */
		if (Gdx.input.isKeyJustPressed(Keys.W))	{
			if (step > -1) step -= 1;
		} 
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			if (step < 1) step += 1;
		}
		
		if (getPosition().y < GameScene.getOceanLevel(step)) {
			/**
			 * keep depth
			 */
			float depth = GameScene.getOceanLevel(step) - getPosition().y;
			velocity.y += depth * 1f;
			velocity.y -= velocity.y * velocity.y * Y_DRAG * Math.signum(velocity.y);
			
			velocity.x -= velocity.x * velocity.x * X_DRAG * Math.signum(velocity.x);
			
			/** 
			 * move left and right
			 */
			if (Gdx.input.isKeyPressed(Keys.A)) {
				velocity.x -= ACCELERATION;
			}
			
			if (Gdx.input.isKeyPressed(Keys.D)) {
				velocity.x += ACCELERATION;
			}
		}
	}
}
