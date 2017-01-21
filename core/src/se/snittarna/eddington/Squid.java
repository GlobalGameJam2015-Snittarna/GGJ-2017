package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class Squid extends GameObject {
	private float attackCount;
	private float maxAttackCount;
	
	private int armToUseIndex;
	
	private boolean hasAttackTeased;
	
	public Squid(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		super.update(dt);
	}
	
	public void attack() {
		if(!hasAttackTeased) {
			
		}
	}
}
