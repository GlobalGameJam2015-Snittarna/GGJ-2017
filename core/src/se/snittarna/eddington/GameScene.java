package se.snittarna.eddington;

public class GameScene extends Scene {
	
	public static final float OCEAN_LEVEL = -3;
	public static final float GRAVITY = -5;
	
	public GameScene() {
		super();
		addObject(new Player());
	}
	
	public void update(float dt) {
		super.update(dt);
	}
}
