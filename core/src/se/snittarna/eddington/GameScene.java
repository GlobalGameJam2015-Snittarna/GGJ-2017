package se.snittarna.eddington;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScene extends Scene {
	
	public static final float OCEAN_LEVEL = -50;
	public static final float DEPTH_STEP = 15;
	public static final float GRAVITY = -15;
	
	/**
	 * 
	 * @param x
	 * @param depth
	 * @return the depth at x, including waves.
	 */
	public float getDynamicOceanLevel(float x, int depth) {
		float dist = getOceanLevel(depth);
		for (GameObject g : getObjects()) {
			if (g instanceof Wave) {
				float d = ((Wave)g).getHeightAt(x, depth);
				System.out.println("wave, h = " + d);
				System.out.println(getOceanLevel(depth));
				if (d > dist) dist = d;
			}
		}
		return dist;
	}
	
	/**
	 * get the ocean level (y) for a given game depth (z).
	 * @param depth 0 for center, -1 for away from camera, 1 for closer
	 */
	public static float getOceanLevel(int depth) {
		return OCEAN_LEVEL - depth * DEPTH_STEP;
	}
	
	public GameScene() {
		super();
		addObject(new Background());
		Player p = new Player();
		addObject(new Wave(0, 1, 0, 2));
		addObject(new Wave(-6, -1, 0, 1));
		addObject(new Squid(new Vector2(-170, 0)));
		//getCamera().setFollow(p, 6, 3);
		addObject(p);
	}
	
	public void update(float dt) {
		super.update(dt);
	}
	
	public void drawGame(SpriteBatch batch) {
		batch.draw(AssetManager.getTexture("ocean"), -8, getOceanLevel(-1), 16, -9);
		super.drawGame(batch);
	}
}
