package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends GameObject {
	private float angle;
	private float speed;
	
	public Projectile(Vector2 position, float angle, float speed) {
		super(position, new Vector2(10, 14), new Animation(new Sprite(AssetManager.getTexture("projectile")), 2, 0, 2));
		this.angle = angle;
		this.speed = speed;
	}
	
	public void update(float dt) {
		setPosition(getPosition().add(getVelocity().cpy().scl(dt)));
		speed += 0.1f * dt;
		getSprite().animate(dt);
		super.update(dt);
	}
	
	public Vector2 getVelocity() {
		return new Vector2((float)Math.cos(angle)*speed, (float)Math.sin(angle)*speed);
	}
}
