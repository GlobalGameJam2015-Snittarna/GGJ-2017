package se.snittarna.eddington;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a game scene.
 * @author Johannes
 *
 */
public abstract class Scene {
	
	private ArrayList<GameObject> toAdd, toRemove, objects;
	
	/**
	 * objects to be added.
	 * @return
	 */
	public ArrayList<GameObject> getToAdd() {
		return toAdd;
	}

	/** objects to be removed.
	 * @return
	 */
	public ArrayList<GameObject> getToRemove() {
		return toRemove;
	}

	/**
	 * 
	 * @return All currently active objects, ie. the ones being updated and drawn.
	 */
	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public Scene() {
		toAdd = new ArrayList<GameObject>();
		toRemove = new ArrayList<GameObject>();
		objects = new ArrayList<GameObject>();
	}
	
	/**
	 * calls update() on all objects and updates the object lists.
	 */
	public void update() {
		for (GameObject g : toAdd) {
			objects.add(g);
		}
		for (GameObject g : toRemove) {
			objects.remove(g);
		}
		toAdd.clear();
		toRemove.clear();
		
		for (GameObject g : objects) {
			g.update();
		}
	}
	
	/**
	 * add a game object to the active object list. (next update cycle).
	 * @param g
	 */
	public void addObject(GameObject g) {
		toAdd.add(g);
	}
	
	/**
	 * remove an object from the active object list. (next cycle).
	 * @param g
	 */
	public void removeObject(GameObject g) {
		toRemove.add(g);
	}
	
	/**
	 * called when the game switches away from this scene
	 * @see Game#setCurrentScene(Scene)
	 */
	public void onLeave() {
		
	}
	
	/**
	 * called when the game switches to this scene
	 * @see Game#setCurrentScene(Scene)
	 */
	public void onResume() {
		
	}

	/**
	 * calls drawUi() on all active game objects.
	 * @param uiBatch
	 */
	public void drawUi(SpriteBatch uiBatch) {
		for (GameObject g : objects) {
			g.drawUi(uiBatch);
		}
	}

	/** draws all game objects.
	 * 
	 * @param gameBatch
	 */
	public void drawGame(SpriteBatch gameBatch) {
		for (GameObject g : objects) {
			//g.draw();
		}
	}
}
