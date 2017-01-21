package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject {
	private int step;
	
	private float speed;
	
	public Wave(float x, int step, Vector2 size, Animation sprite) {
		super(new Vector2(x, GameScene.getOceanLevel(step)), size, sprite);
	}
	
	public void update(float dt) {
		this.getPosition().cpy().add(new Vector2(speed, 0));
		super.update(dt);
	}
}
