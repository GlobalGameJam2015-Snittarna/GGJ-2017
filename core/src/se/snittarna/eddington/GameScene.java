package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScene extends Scene {
	
	public static final float OCEAN_LEVEL = -2;
	public static final float DEPTH_STEP = .5f;
	public static final float GRAVITY = -5;
	
	/**
	 * get the ocean level (y) for a given game depth (z).
	 * @param depth 0 for center, -1 for away from camera, 1 for closer
	 */
	public static float getOceanLevel(int depth) {
		return OCEAN_LEVEL - depth * DEPTH_STEP;
	}
	
	public GameScene() {
		super();
		addObject(new Player());
	}
	
	public void update(float dt) {
		super.update(dt);
	}
	
	public void drawGame(SpriteBatch batch) {
		batch.draw(AssetManager.getTexture("ocean"), -8, getOceanLevel(-1), 16, -9);
		super.drawGame(batch);
	}
}
