package se.snittarna.eddington;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Squid extends GameObject {
	private float attackCount;
	private float maxAttackCount;
	
	private int attackIndex;
	
	private boolean hasAttackTeased;
	
	private ArrayList<SquidArm> arms = new ArrayList<SquidArm>();
	
	public Squid(Vector2 position) {
		super(position, new Vector2(32, 32),  new Animation(new Sprite(AssetManager.getTexture("squid"))));
		arms.add(new SquidArm(100, 0.1f));
		
		attackIndex = 0;
	}
	
	public void update(float dt) {
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).update(dt);
		}	
		attack();
		super.update(dt);
	}
	
	public void attack() {
		if(arms.get(attackIndex) != null && attackIndex != -1) arms.get(attackIndex).startAttack();
		attackIndex = -1;
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).draw(batch);
		}
	}
}
