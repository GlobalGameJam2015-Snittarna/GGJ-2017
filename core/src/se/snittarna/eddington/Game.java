package se.snittarna.eddington;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	/**
	 *  used for rendering the scene.
	 */
	private SpriteBatch gameBatch; 
	 /**
	 *  for rendering ui (on top of the scene)
	 **/
	private SpriteBatch uiBatch;
	
	/**
	 * the currently active scene being updated and drawn.
	 */
	public static Scene currentScene;
	
	public static int highscore = 15;
	
	public static void checkHighscore(int score) {
		if(score > highscore) highscore = score;
	}
	
	private OrthographicCamera uiCam;
	
	public Scene getCurrentScene() {
		return currentScene;
	}

	/**
	 * sets a new scene and notifies both the old one and new one.
	 * @param currentScene
	 * @see Scene#onLeave()
	 * @see Scene#onResume()
	 */
	public static void setCurrentScene(Scene newScene) {
		currentScene.onLeave();
		Game.currentScene = newScene;
		currentScene.onResume();
	}

	@Override
	public void create () {
		uiBatch = new SpriteBatch();
		gameBatch = new SpriteBatch();
		
		AssetManager.load();
		
		System.out.println(AssetManager.getTexture("test"));
		currentScene = new StartScreen(); // new GameScene();
		
		uiCam = new OrthographicCamera(330, 180);
	}

	@Override
	public void render () {
		currentScene.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(43/255f, 84/255f, 117/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		currentScene.getCamera().update();
		gameBatch.setProjectionMatrix(currentScene.getCamera().combined);
		gameBatch.begin();
		currentScene.drawGame(gameBatch);
		gameBatch.end();
		
		uiBatch.setProjectionMatrix(uiCam.combined);
		uiBatch.begin();
		currentScene.drawUi(uiBatch);
		uiBatch.end();
	}
	
	@Override
	public void dispose () {
		gameBatch.dispose();
		uiBatch.dispose();
	}
}
