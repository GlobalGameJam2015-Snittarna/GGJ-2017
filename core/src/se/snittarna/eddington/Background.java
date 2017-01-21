package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class Background extends GameObject {
	
	private float scrollSpeed;
	
	/**
	 * 
	 * @param scrollSpeed
	 * @param pos -1 for on screen, +1 for right off the screen
	 */
	public Background(String texture, float scrollSpeed, int pos) {
		super(new Vector2(pos * 165, -90), new Vector2(330, 180), new Animation(AssetManager.getTexture(texture), new Vector2(330, 180)));
		this.scrollSpeed = scrollSpeed;
	}
	
	public void update(float dt) {
		setPosition(getPosition().add(new Vector2(-scrollSpeed * dt, 0)));
		if (getPosition().x < 3 * -getSize().x / 2) {
			setPosition(new Vector2(165, -90));
			System.out.println("resetting background");
		}
	}
}
