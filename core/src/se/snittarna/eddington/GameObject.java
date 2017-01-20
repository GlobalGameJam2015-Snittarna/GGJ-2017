package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	private Vector2 position;
	
	public void update() {
		
	}
	
	public void draw(SpriteBatch batch) {
		
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
