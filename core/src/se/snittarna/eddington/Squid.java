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
	
	private SquidArm[] arms;
	
	Random r = new Random();
	
	public Squid(Vector2 position) {
		super(position, new Vector2(45,26), new Animation(new Sprite(AssetManager.getTexture("squid"))));
		maxAddNewArmCount = 150;
		maxLevelCount = 200;
		maxAttackCount = 50;
		attackIndex = -1;
		
		arms = new SquidArm[10];
		newArm();
	}

	public void update(float dt) {
		addNewArmCount += 10*dt;
		levelCount += 10*dt;
		attackCount += 10*dt;
		
		if (attackCount >= maxAttackCount) {
			attackIndex = random(0, arms.length);
			attackCount = random(0, (int)maxAttackCount/2);
			
			int j = 0;
			for (int i = attackIndex; i >= 0; i--) {
				int bc = 0;
				j++;
				if (j >= arms.length) j = 0;
				while (arms[j] == null) {
					bc++;
					j++;
					if (j >= arms.length) {
						j = 0;
					}
					if (bc >= arms.length) {
						i = 0;
						j = -1;
						break;
					}
				}
			}
			if (j != -1) arms[j].startAttack();
		}
		
		if(levelCount >= maxLevelCount) {
			maxAddNewArmCount -= 10;
			level += 1;
			levelCount = 0;
		}
		
		if(addNewArmCount >= maxAddNewArmCount /*&& arms.size()-1 <= level+2*/) {
			newArm();
			addNewArmCount = 0;
		}
		
		for(int i = 0; i < arms.length; i++) {
			if (arms[i] == null) continue;
			arms[i].update(dt);
			for (GameObject g : getScene().getObjects()) {
				if (g instanceof Projectile) {
					if (g.getHitbox().collision(arms[i].getHitbox())) {
						getScene().removeObject(g);
						arms[i].setDying();
						GameScene.score += 5;
					}
				} else if (g instanceof Player) {
					if(!arms[i].getDying() && g.getHitbox().collision(arms[i].getHitbox())) {
						((Player) g).degrade();
						GameScene.score -= 10;
						arms[i].setDying();
					}
				}
			}
			if(arms[i].destroy) {
				arms[i] = null;
			}
		}
		//attack();
		super.update(dt);
	}
	
	public void newArm() {
		int index = r.nextInt(arms.length);
		System.out.println("new arm at " + index);
		int fin = 0;
		for (int i = 0; i < index; i++) {
			System.out.println("counting up, fin = " + fin);
			int bc = 0;
			
			fin++;
			if (fin >= arms.length) fin = 0;
			
			while (arms[fin] != null) {
				System.out.println(fin + " taken");
				bc++;
				fin++;
				if (fin >= arms.length) {
					fin = 0;
				}
				if (bc >= arms.length) {
					System.out.println("no space");
					return;
				}
			}
		}
		arms[fin] = new SquidArm(fin * 30 - 130, .1f);
	}
	
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		for(int i = 0; i < arms.length; i++) {
			if (arms[i] == null) continue;
			arms[i].draw(batch);
		}
	}
	
	public int random(int min, int max) {
		return r.nextInt((max - min) + 1) + min;
	}
}
