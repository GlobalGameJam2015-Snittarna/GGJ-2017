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
	private float levelCount;
	private float maxLevelCount;
	private float addNewArmCount;
	private float maxAddNewArmCount;
	
	private int attackIndex;
	private int level;
	
	private boolean hasAttackTeased;
	
	private ArrayList<SquidArm> arms = new ArrayList<SquidArm>();
	
	Random r = new Random();
	
	public Squid(Vector2 position) {
		super(position, new Vector2(32, 32), new Animation(new Sprite(AssetManager.getTexture("squid"))));
		newArm();
		maxAddNewArmCount = 100;
		maxLevelCount = 200;
		maxAttackCount = 50;
		attackIndex = -1;
	}

	public void update(float dt) {
		addNewArmCount += 10*dt;
		levelCount += 10*dt;
		attackCount += 10*dt;
		
		r = new Random();
		
		if(arms.size() > 0 && attackCount >= maxAttackCount) {
			attackIndex = random(0, arms.size()-1);
			attackCount = random(0, (int)maxAttackCount/2);
		}
		
		if(levelCount >= maxLevelCount) {
			maxAddNewArmCount -= 10;
			level += 1;
			levelCount = 0;
		}
		
		if(addNewArmCount >= maxAddNewArmCount && arms.size()-1 <= level+2) {
			newArm();
			addNewArmCount = 0;
		}
		
		for(int i = 0; i < arms.size(); i++) {
			arms.get(i).update(dt);
			for (GameObject g : getScene().getObjects()) {
				if (g instanceof Projectile) {
					if(g.getHitbox().collision(arms.get(i).getHitbox())) {
						getScene().removeObject(g);
						arms.get(i).setDying();
					}
				} else if(g instanceof Player) {
					if(g.getHitbox().collision(arms.get(i).getHitbox())) {
						getScene().removeObject(g);
					}
				}
			}
			if(arms.get(i).destroy) {
				arms.remove(arms.get(i));
			}
		}
		attack();
		super.update(dt);
	}
	
	public void newArm() {
		arms.add(new SquidArm(random((-330/2)+32, (330/2)-32), 0.1f));
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
		return r.nextInt((max - min) + 1) + min;
	}
}
