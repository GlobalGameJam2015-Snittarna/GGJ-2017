package se.snittarna.eddington;

import java.awt.color.ColorSpace;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Background extends GameObject {
	
	private float scrollSpeed;
	
	//private 
	
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
		//Random r = new Random();
		//getSprite().setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 255));
		setPosition(getPosition().add(new Vector2(-scrollSpeed * dt, 0)));
		if (getPosition().x < 3 * -getSize().x / 2) {
			setPosition(new Vector2(165, -90));
			System.out.println("resetting background");
		}
	}
}
