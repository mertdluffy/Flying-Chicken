package dev.codenmore.thegame.worlds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.entities.EntityManager;
import dev.codenmore.thegame.entities.creatures.Player;
import dev.codenmore.thegame.entities.statics.Cat;
import dev.codenmore.thegame.gfx.Assets;
import dev.codenmore.thegame.states.State;
import dev.codenmore.thegame.tiles.Tile;
import dev.codenmore.thegame.utils.Utils;
import java.util.Random;

public class World {
	
	private int width, height;
	private int spawnX,spawnY;
	private int[][] tiles;
	private Handler handler;
	private EntityManager entityManager;
	public static int point  = 0;
	public static int health  = 5;
	public static int level;
    private Font font;
    private Font font2;
    private int zorluk;
    
	public World(Handler handler, String path,int level,int zorluk) {
		this.level = level;
		this.handler = handler;	
		Random rand = new Random();
		font = new Font("Arial", Font.PLAIN, 32);
		font2 = new Font("Arial", Font.PLAIN, 25);
		this.zorluk = zorluk;
		entityManager = new EntityManager(handler,new Player(handler,100,100));
		for(int j = 5;j<29;j+=zorluk)
		for(int i = 0;i<1;i++) {
			int a = 32+rand.nextInt(32);
			entityManager.addEntity(new Cat(handler,64+rand.nextInt(440),j*64,a,a));
		}
		//entityManager.addEntity(new Egg(handler,0,0,15,15));
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		
		
		
		
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int [width][height];
		for(int i = 0;i<height;i++) {
			
			for(int j = 0; j<width;j++) {
				tiles[j][i] = Utils.parseInt(tokens[j+i*width+4]);
				
			}
		}
		
	}
	
	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public void tick() {
		entityManager.tick();
		if(health == 0)
			State.setState(handler.getGame().menuState);
		//handler.getGameCamera().move(0, 1);
		//handler.getWorld().getEntityManager().getPlayer().setyMove(handler.getWorld().getEntityManager().getPlayer().getSpeed());
	
		
	}
	
	public void render(Graphics g) {
		int xStart =Math.max(0,(int)handler.getGameCamera().getxOffset()/Tile.TILEWIDTH);
		int xEnd =(int) Math.min(width, (handler.getGameCamera().getxOffset()+handler.getWidth())/Tile.TILEWIDTH+1);
		int yEnd =(int) Math.min(height, (handler.getGameCamera().getyOffset()+handler.getHeight())/Tile.TILEHEIGHT+1);
		int yStart =Math.max(0,(int)handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT);
		for(int y = yStart;y<yEnd;y++) {
			
			for(int x = xStart; x<xEnd;x++) {
				getTile(x,y).render(g,(int)(x*Tile.TILEWIDTH- handler.getGameCamera().getxOffset()),(int)(y*Tile.TILEHEIGHT- handler.getGameCamera().getyOffset()));
				
			}
		}
		//drawing top bar
		g.drawImage(Assets.top_bar,0,0,640,64,  null);
		g.setFont(font);
        g.setColor(Color.black);
        g.drawString(health + "",30,40);
        g.setFont(font2);
        g.drawString("Point: " + point ,170,50);
        g.drawString("Level: " + level ,370,50);
        g.setColor(Color.black);
        g.drawRect(555, 30, 50,20);
        g.setColor(Color.green);
        g.fillRect(555, 30, 60 - entityManager.getPlayer().getCs(),20);
        //...........................
        
		entityManager.render(g);
		
	}
	public Tile getTile(int x,int y) {
		
		if(x<0 || y<0 || x>=width || y>=height )
			return Tile.grassTile;
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.grassTile;
		}
		return t;
		
	}
	
	public int getWidth() {
		return width;
		
	}
	public int getHeight() {
		return height;
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
		
	}
}
