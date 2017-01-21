package se.snittarna.eddington;

import com.badlogic.gdx.math.Vector2;

public class Background extends GameObject {

	public Background() {
		super(new Vector2(-165, -90), new Vector2(330, 180), new Animation(AssetManager.getTexture("background"), new Vector2(330, 180)));
		// TODO Auto-generated constructor stub
	}
	
}
