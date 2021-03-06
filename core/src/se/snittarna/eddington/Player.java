package se.snittarna.eddington;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject implements Depthable {
	private int currentAmmo;
	private int boatParts;
	
	private float fireRate;
	
	private final int ACCELERATION = 200;
	private final float X_DRAG = .08f;
	private final float Y_DRAG = .3f;
	private final float BOUYANCY = 30f;
	
	private float scoreCounter;
	
	private Random random;
	
	public int getDepth() {
		return step;
	}
	
	public enum State {
		BOAT ("boat", new Vector2(18, 10)),
		SWIM ("player", new Vector2(9, 11));
		
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
		currentAmmo = 5;
		random = new Random();
	}
	
	public void setState(State state) {
		System.out.println("setting state");
		this.state = state;
		if(state == State.SWIM) getSprite().setAnimation(3, 3);
		else getSprite().setAnimation(0, 0);
		getSprite().setRegion(state.texture);
		getSprite().setSize(state.size.x, state.size.y);
	}
	
	public void addAmmo() {
		currentAmmo += 1;
	}
	
	public void addBoatParts() {
		boatParts += 1;
	}
	
	public boolean isBoat() {
		return state == State.BOAT;
	}
	
	public void update(float dt) {
		scoreCounter += dt;
		if (scoreCounter > 1f) {
			GameScene.score ++;
			scoreCounter -= 1f;
		}
		if(this.state == State.SWIM) getSprite().animate(dt);
		/**
		 * debug
		 */
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && fireRate <= 0 && currentAmmo >= 3 && state == State.BOAT) {
			this.getScene().addObject(new Projectile(this.getPosition(), -(float)Math.PI/2, 50));
			currentAmmo -= 3;
			fireRate = 10;
		}
		
		if(boatParts >= 3) {
			setState(State.BOAT);
			boatParts = 0;
		}
		
		if(fireRate > 0) {
			fireRate -= 10 * dt;
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

		if (getPosition().y < ((GameScene)getScene()).getDynamicOceanLevel(getPosition().x, step)) {
			 /**
			 *
			 * keep depth
			 */
			
			float depth = ((GameScene)getScene()).getDynamicOceanLevel(getPosition().x, step) - getPosition().y;
			velocity.y += depth * BOUYANCY * dt;
			velocity.y -= velocity.y * velocity.y * Y_DRAG * Math.signum(velocity.y) * dt;
			
			velocity.x -= velocity.x * velocity.x * X_DRAG * Math.signum(velocity.x) * dt;
			
			//setPosition(new Vector2(getPosition().x, ((GameScene)getScene()).getDynamicOceanLevel(getPosition().x, step)));
			/** 
			 * move left and right
			 */
			if (Gdx.input.isKeyPressed(Keys.A) && getPosition().x > (-330/2)+50 || getPosition().x > 145) {
				velocity.x -= ACCELERATION * dt;
			}
			
			if (Gdx.input.isKeyPressed(Keys.D) && getPosition().x < (330/2)-50 || getPosition().x < -145) {
				velocity.x += ACCELERATION * dt;
			}
			
			if (Math.abs(velocity.x) > 5 && state == State.BOAT) {
				for (int i = 0; i < 8; i++) {
					float angle = (float) (i * .1 + .3 + random.nextDouble() * .4);
					getScene().addObject(new Particle(velocity.x > 0 ? getPosition() : new Vector2(getPosition().x + state.size.x, getPosition().y), new Vector2(- Math.signum(velocity.x) * (float)Math.cos(angle), (float) Math.sin(angle)).scl(Math.abs(velocity.x)), new Color(0x2B5575)));
				}
			}
		}
	}
	
	public void degrade() {
		if(state == State.BOAT) {
			setState(State.SWIM);
			AssetManager.getSound("Skadad").play();
			currentAmmo = 0;
		} else {
			getScene().removeObject(this);
			AssetManager.getSound("Game over").play();
			GameScene.gameOver = true;
		}
	}
	
	public void drawUi(SpriteBatch batch) {
		if (state == State.BOAT) {
			AssetManager.font.draw(batch, " x" + (currentAmmo/3) + " (" + currentAmmo % 3 + "/3)", -135, 60);
			batch.draw(AssetManager.getTexture("projectile"), -150, 46, 10, 14);
		} else {
			AssetManager.font.draw(batch, boatParts + "/3", -130, 60);
			batch.draw(AssetManager.getTexture("plank"), -150, 50, 14, 10);
		}
		AssetManager.font.draw(batch, "Score: " + GameScene.score, -150, 80);
		super.drawUi(batch);
	}
}
