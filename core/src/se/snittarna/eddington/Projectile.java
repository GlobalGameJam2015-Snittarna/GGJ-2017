package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class Projectile extends GameObject {
	private float angle;
	private float speed;
	private float fallSpeed;
	
	public Projectile(Vector2 position, Vector2 size, Animation sprite, float angle, float speed) {
		super(position, size, sprite);
		this.angle = angle;
		this.speed = speed;
	}
	
	public void update(float dt) {
		this.setPosition(new Vector2(this.getPosition().cpy().x + getVelocity().cpy().x*dt, this.getPosition().cpy().y + getVelocity().cpy().y*dt));
		fallSpeed += 1 * dt;
	}
	
	public Vector2 getVelocity() {
		return new Vector2((float)Math.cos(angle)*speed, (float)Math.sin(angle)*speed);
	}
}
