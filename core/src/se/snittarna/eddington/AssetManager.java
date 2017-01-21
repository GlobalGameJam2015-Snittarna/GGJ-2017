package se.snittarna.eddington;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
	/**
	 * loads the spritesheet (assets/spritesheet.png) and the list of textures (assets/textures.txt)
	 */
	public static void load() {
		textureRegions = new HashMap<String, TextureRegion>();
		spriteSheet = new Texture("spritesheet.png");
		
		for (String s : Gdx.files.internal("textures.txt").readString().split("\n")) {
			System.out.println(s);
			String[] vals = s.split(",");
			textureRegions.put(vals[0], new TextureRegion(spriteSheet, Integer.parseInt(vals[1]), Integer.parseInt(vals[2]), Integer.parseInt(vals[3]), Integer.parseInt(vals[4].trim())));
		}
	}
	
	/**
	 * a list of all texture regions on the sprite sheet.
	 */
	private static HashMap<String, TextureRegion> textureRegions;
	
	/**
	 * the spritesheet containing all textures.
	 */
	private static Texture spriteSheet;
	
	public static TextureRegion getTexture(String name) {
		return textureRegions.get(name);
	}
}
