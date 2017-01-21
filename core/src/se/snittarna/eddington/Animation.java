package se.snittarna.eddington;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animation extends Sprite {
	private int maxFrame;
	private int minFrame;
	private int currentFrame;
	private float maxAnimationCount;
	private float animationCount;
	
	public Animation(Sprite sprite) {
		super(sprite);
	}
	
	public Animation(Sprite sprite, int maxFrame, int minFrame, float maxAnimationCount) {
		super(sprite);
		this.maxFrame = maxFrame;
		this.minFrame = minFrame;
		this.maxAnimationCount = maxAnimationCount;
	}
	
	public Animation(TextureRegion region, Vector2 size) {
		this.setRegion(region);
		this.setSize(size.x, size.y);
	}
	
	public Animation(Texture sprite) {
		super(sprite);
	}
	
	public void animate(float dt) {
		animationCount += 1 * dt;
		
		if(animationCount > maxAnimationCount) {
			currentFrame += 1*dt;
			scroll(1 + getRegionWidth() * currentFrame + currentFrame, 0);
			if(currentFrame > maxFrame) {
				currentFrame = 0;
				this.setRegionX(minFrame * getRegionWidth());
			}
			animationCount = 0;
		}
	}
}
