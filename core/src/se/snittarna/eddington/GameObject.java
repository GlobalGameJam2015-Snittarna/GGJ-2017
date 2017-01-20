package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	private Vector2 position, size, origin;
	
	private Animation sprite;
	
	public GameObject(Vector2 position, Vector2 size, Animation sprite) {
		this.sprite = sprite;
		setOriginCenter();
		setPosition(position);
		setSize(size);
	}
	
	public void update() {
		
	}
	
	protected void setOriginCenter() {
		getSprite().setOriginCenter();
		origin = new Vector2(sprite.getOriginX()/2, sprite.getOriginY()/2);
	}
	
	private Animation getSprite() {
		return sprite;
	}

	public void setSprite(TextureRegion texture) {
		sprite.setRegion(texture);
	}
	
	public void setSprite(Animation sprite) {
		this.setSprite(sprite, false);
	}
	
	public void setSprite(Animation sprite, boolean discardInfo) {
		this.sprite = sprite;
		if (!discardInfo) {
			setPosition(position);
			setSize(size);
			setOrigin(origin);
		}
	}
	
	public void setSize(Vector2 s) { 
		size = s;
		if (sprite != null) sprite.setSize(s.x, s.y); 
	}
	
	protected void setOrigin(Vector2 origin) {
		this.origin = origin;
		if (sprite != null) sprite.setOrigin(origin.x, origin.y);
	}
	
	public void draw(SpriteBatch batch) {
		if(sprite != null) sprite.draw(batch);
	}
	
	public void drawUi(SpriteBatch batch) {
		
	}
	
	public Vector2 getPosition() {
		return position.cpy();
	}
	
	public void setPosition(Vector2 position) {
		this.position = position.cpy();
	}
}
