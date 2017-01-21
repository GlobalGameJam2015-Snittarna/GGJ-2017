package se.snittarna.eddington;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Squid extends GameObject {
	private float attackCount;
	private float maxAttackCount;
	
	private boolean hasAttackTeased;
	
	private ArrayList<Integer> armToUseIndexes = new ArrayList<Integer>();
	private ArrayList<SquidArm> arms = new ArrayList<SquidArm>();
	
	public Squid(Vector2 position) {
		super(position, new Vector2(32, 32),  new Animation(new Sprite(AssetManager.getTexture("squid"))));
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
