package se.snittarna.eddington;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Squid extends GameObject {
	private float attackCount;
	private float maxAttackCount;
	
	private boolean hasAttackTeased;
	
	private ArrayList<Integer> armToUseIndexes;
	private ArrayList<SquidArm> arms;
	
	public Squid(Vector2 position, Vector2 size, Animation sprite) {
		super(position, size, sprite);
	}
	
	public void update(float dt) {
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).update(dt);
		}
		super.update(dt);
	}
	
	public void attack() {
		for(int i = 0; i < armToUseIndexes.size(); i++) {
			if(!hasAttackTeased) {
				arms.get(armToUseIndexes.get(i)).tease();
			} else {
				arms.get(armToUseIndexes.get(i)).attack();
			}
		}
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).draw(batch);
		}
	}
}
