package dev.codenmore.thegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	// Static
	
	public static Tile[] tiles= new Tile[256];
	public static Tile grassTile = new SkyTile(0);
	public static Tile rockTile = new LogTile(1);
	public static Tile finishTile = new FinishTile(2);
	
	//class
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture,int id) {
		this.id = id;
		this.texture = texture;
		tiles[id] = this;
	}
	
	public int getId() {
		return id;
		
	}
	
	public void tick () {
		
	
	}
	
	public boolean isSolid() {
		
		return false;
	}
	
	public void render(Graphics g,int x ,int y) {
		g.drawImage(texture,x,y,TILEWIDTH, TILEHEIGHT,null);
		
		
	}
}
