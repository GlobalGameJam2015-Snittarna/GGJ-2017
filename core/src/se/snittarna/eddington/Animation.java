package se.snittarna.eddington;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animation extends Sprite {
	public Animation(Sprite sprite) {
		super(sprite);
	}
	
	public Animation(TextureRegion region, Vector2 size) {
		this.setRegion(region);
		this.setSize(size.x, size.y);
	}
	
	public Animation(Texture sprite) {
		super(sprite);
	}
}
