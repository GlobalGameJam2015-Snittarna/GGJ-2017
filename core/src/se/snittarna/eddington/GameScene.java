package se.snittarna.eddington;

public class GameScene extends Scene {
	public GameScene() {
		super();
		addObject(new TestObject());
	}
	
	public void update(float dt) {
		super.update(dt);
		getCamera().zoom *= 1.001f;
	}
}
