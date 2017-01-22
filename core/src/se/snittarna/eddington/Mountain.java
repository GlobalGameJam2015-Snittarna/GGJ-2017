package se.snittarna.eddington;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Mountain extends GameObject {

	public Mountain() {
		super(new Vector2(-73, -77), new Vector2(147, 154), new Animation(new Sprite(AssetManager.getTexture("mountain")), 3, 0, 10));
		// TODO Auto-generated constructor stub
	}
	
	public void update(float dt) {
		super.update(dt);
		getSprite().animate(dt);
	}
}
