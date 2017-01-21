package se.snittarna.eddington;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
	
	private final int ACCELERATION = 8;
	private final float X_DRAG = 2f;
	private final float Y_DRAG = 3f;
	
	public enum State {
		BOAT ("boat", new Vector2(0.8f, 0.4f)),
		SWIM ("player", new Vector2(0.4f, 0.4f));
		
		public TextureRegion texture;
		public Vector2 size;
		State(String texture, Vector2 size) {
			this.texture = AssetManager.getTexture(texture);
			this.size = size;
		}
	};
	
	private State state;
	
	private Vector2 velocity;
	private int step;
	
	public Player() {
		super(new Vector2(), new Vector2(0.2f, 0.2f), new Animation(new Sprite(AssetManager.getTexture("boat"))));
		velocity = new Vector2();
		setState(State.BOAT);
	}
	
	public void setState(State state) {
		System.out.println("setting state");
		this.state = state;
		getSprite().setRegion(state.texture);
		getSprite().setSize(state.size.x, state.size.y);
	}

	
	public void update(float dt) {
		/**
		 * debug
		 */
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			setState(State.SWIM);
			velocity.y += 5;
		}
		
		super.update(dt);
		
		velocity.add(0, GameScene.GRAVITY * dt);
		setPosition(getPosition().add(velocity.cpy().scl(dt)));
		
		if (velocity.x < 0) {
			getSprite().setFlip(true, false);
		} else {
			getSprite().setFlip(false, false );
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
			velocity.y += depth * 10f * dt;
			velocity.y -= velocity.y * velocity.y * Y_DRAG * Math.signum(velocity.y) * dt;
			
			velocity.x -= velocity.x * velocity.x * X_DRAG * Math.signum(velocity.x) * dt;
			
			/** 
			 * move left and right
			 */
			if (Gdx.input.isKeyPressed(Keys.A)) {
				velocity.x -= ACCELERATION * dt;
			}
			
			if (Gdx.input.isKeyPressed(Keys.D)) {
				velocity.x += ACCELERATION * dt;
			}
		}
	}
}
