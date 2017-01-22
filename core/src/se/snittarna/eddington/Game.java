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
	private Scene currentScene;
	
	
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
	public void setCurrentScene(Scene currentScene) {
		currentScene.onLeave();
		this.currentScene = currentScene;
		currentScene.onResume();
	}

	@Override
	public void create () {
		uiBatch = new SpriteBatch();
		gameBatch = new SpriteBatch();
		
		AssetManager.load();
		
		System.out.println(AssetManager.getTexture("test"));
		currentScene = new GameScene();
		
		uiCam = new OrthographicCamera(330, 180);
	}

	@Override
	public void render () {
		currentScene.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0, .5f, 1, 1);
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
