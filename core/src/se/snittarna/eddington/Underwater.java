package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Underwater extends GameObject {

	public Underwater() {
		super(new Vector2(-165, -90), new Vector2(330, 49), new Animation(new Sprite(AssetManager.getTexture("underwater")), 2, 0, 4));
		// TODO Auto-generated constructor stub
	}
	
	public void update(float dt) {
		getSprite().animate(dt);
		super.update(dt);
	}
}
