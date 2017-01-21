package se.snittarna.eddington;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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
	}

	@Override
	public void render () {
		currentScene.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameBatch.begin();
		currentScene.drawGame(gameBatch);
		gameBatch.end();
		
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
