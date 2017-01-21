package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Ocean extends GameObject {

	public Ocean() {
		super(new Vector2(-165, GameScene.OCEAN_LEVEL - 12.5f), new Vector2(330, 27), new Animation(new Sprite(AssetManager.getTexture("sea")), 2, 0, 5));
		// TODO Auto-generated constructor stub
	}
	
	public void update(float dt) {
		getSprite().animate(dt);
	}
}
