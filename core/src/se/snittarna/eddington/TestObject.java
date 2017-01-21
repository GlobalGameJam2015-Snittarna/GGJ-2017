package se.snittarna.eddington;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class TestObject extends GameObject {

	public TestObject() {
		super(new Vector2(100, 100), new Vector2(50, 50), new Animation(new Sprite(AssetManager.getTexture("test"))));
		// TODO Auto-generated constructor stub
	}

	public void update() {
		System.out.println(this.getSize());
		System.out.println(this.getSprite().getX());
	}
}
