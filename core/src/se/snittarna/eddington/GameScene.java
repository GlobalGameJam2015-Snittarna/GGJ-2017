package se.snittarna.eddington;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScene extends Scene {
	
	public static final float OCEAN_LEVEL = -30;
	public static final float DEPTH_STEP = 8;
	public static final float GRAVITY = -45;
	
	private float restartCounter;
	
	public static int score = 0;
	
	public static boolean gameOver;
	
	private Random random;
	
	float timeSinceWave, timeSincePowerup;
	
	private Music music;
	
	/**
	 * 
	 * @param x
	 * @param depth
	 * @return the depth at x, including waves.
	 */
	public float getDynamicOceanLevel(float x, int depth) {
		float dist = getOceanLevel(depth);
		for (GameObject g : getObjects()) {
			if (g instanceof Wave) {
				float d = ((Wave)g).getHeightAt(x, depth);
				//System.out.println("wave, h = " + d);
				//System.out.println(getOceanLevel(depth));
				if (d > dist) dist = d;
			}
		}
		return dist;
	}
	
	/**
	 * get the ocean level (y) for a given game depth (z).
	 * @param depth 0 for center, -1 for away from camera, 1 for closer
	 */
	public static float getOceanLevel(int depth) {
		return OCEAN_LEVEL - depth * DEPTH_STEP;
	}
	
	public GameScene() {
		super();
		addObject(new Background("background", 5, -1));
		addObject(new Background("background", 5, 1));
		//addObject(new Background("mountain", 5, -1));
		//addObject(new Background("mountain", 5, 1));
		addObject(new Mountain());
		addObject(new Background("background1", 10f, -1));
		addObject(new Background("background1", 10f, 1));
		addObject(new Underwater());
		addObject(new Ocean());
		Player p = new Player();
		addObject(new Squid(new Vector2(-170, -90)));
		//getCamera().setFollow(p, 6, 3);
		addObject(p);
		
		random = new Random();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("music/song-intro.mp3"));
		music.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(Music m) {
				music = Gdx.audio.newMusic(Gdx.files.internal("music/song-loop.mp3"));
				music.setLooping(true);
				music.play();
			}
		});
		music.play();
	}
	
	public void onLeave() {
		System.out.println("stopping music 1");
		if (music != null) {
			System.out.println("stopping music");
			music.setLooping(false);
			music.stop();
		}
	}
	
	public void update(float dt) {
		dt *= 1.8f;
		super.update(dt);
		Game.checkHighscore(score);
		if(gameOver) {
			restartCounter += 5*dt;
			
			if(restartCounter >= 20) {
				Game.setCurrentScene(new StartScreen());
				gameOver = false;
				restartCounter = 0;
			}
		}
		
		timeSinceWave += dt;
		
		if (random.nextDouble() < timeSinceWave * .001) {
			System.out.println("new wave");
			addObject(new Wave(180, -15, random.nextInt(3) - 1, random.nextInt(3)));
			timeSinceWave = 0;
		}
		
		timeSincePowerup += dt;
		
		boolean spawnBoatParts = false;
		
		for(GameObject g : getObjects()) {
			if(g instanceof Player) {
				spawnBoatParts = ((Player) g).isBoat();
			}
		}
		
		if (random.nextDouble() < timeSincePowerup * .0025) {
			int depth = random.nextInt(3) - 1;
			addObject(new PowerUp(new Vector2(170, getOceanLevel(depth)), spawnBoatParts ? PowerUp.Type.BARREL : PowerUp.Type.PLANK, depth));
			timeSincePowerup = 0;
		}
	}
	
	public void drawUi(SpriteBatch uiBatch) {
		if(gameOver) AssetManager.font.draw(uiBatch, "GAME OVER", 0, 0);
		if(score != Game.highscore) {
			AssetManager.font.draw(uiBatch, "HIGHSCORE: " + Game.highscore, -50, 180/2);
		} else {
			AssetManager.font.setColor(Color.GOLD);
			AssetManager.font.draw(uiBatch, "HIGHSCORE: " + Game.highscore, -50, 180/2);
			AssetManager.font.setColor(Color.WHITE);
		}
		super.drawUi(uiBatch);
	}
	
	public void drawGame(SpriteBatch batch) {
		batch.draw(AssetManager.getTexture("ocean"), -8, getOceanLevel(-1), 16, -9);
		
		ArrayList<GameObject> toSort = new ArrayList<GameObject>();
		
		for (GameObject g : getObjects()) {
			if (!(g instanceof Depthable)) {
				g.draw(batch);
			} else {
				toSort.add(g);
			}
		}
		
		toSort.sort(new Comparator<GameObject>() {
			
			@Override
			public int compare(GameObject o1, GameObject o2) {
				if (((Depthable)o1).getDepth() == ((Depthable)o2).getDepth()) {
					if (o1 instanceof Player) return 1;
					if (o2 instanceof Player) return -1;
				}
				return ((Depthable)o1).getDepth() - ((Depthable)o2).getDepth();
			}
		});
		
		for (GameObject g : toSort) {
			g.draw(batch);
		}
	}
}
