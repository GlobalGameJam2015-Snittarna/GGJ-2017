package se.snittarna.eddington;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Squid extends GameObject {
	private float attackCount;
	private float maxAttackCount;
	private float maxLevelCount;
	
	private int attackIndex;
	private int level;
	
	private boolean hasAttackTeased;
	
	private ArrayList<SquidArm> arms = new ArrayList<SquidArm>();
	
	public Squid(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(new Sprite(AssetManager.getTexture("squid"))));
		arms.add(new SquidArm(random((-330/2)+32, (330/2)-32), 0.1f));
		attackIndex = -1;
	}

	public void update(float dt) {
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).update(dt);
			for (GameObject g : getScene().getObjects()) {
				if (g instanceof Projectile) {
					if(g.getHitbox().collision(arms.get(i).getHitbox())) {
						getScene().removeObject(g);
						arms.remove(arms.get(i));
					}
				}
			}
		}
		attack();
		super.update(dt);
	}
	
	public void attack() {
		if(attackIndex != -1) arms.get(attackIndex).startAttack();
		attackIndex = -1;
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).draw(batch);
		}
	}
	
	public int random(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
}
