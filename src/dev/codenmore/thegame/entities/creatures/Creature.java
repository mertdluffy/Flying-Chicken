package dev.codenmore.thegame.entities.creatures;

import dev.codenmore.thegame.Game;
import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.entities.Entity;
import dev.codenmore.thegame.states.GameState;
import dev.codenmore.thegame.states.State;
import dev.codenmore.thegame.tiles.Tile;
import dev.codenmore.thegame.worlds.World;

public abstract class Creature extends Entity {
	
	protected int health;
	protected float speed;
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float xMove,yMove;
	
	

	public Creature(Handler handler,float x, float y,int width,int height) {
		super(handler,x, y,width,height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove= 0;
	}
	
	public void moveX() {
		if(xMove> 0) {
			int tx = (int)(x+xMove + bounds.x+ bounds.width)/Tile.TILEWIDTH;
			if(!collisionWithTile(tx,(int)(y+ bounds.y)/Tile.TILEHEIGHT) && !collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT) ){
				x += xMove;
			}
			
			
		}else if(xMove<0) {
			int tx = (int)(x+xMove + bounds.x)/Tile.TILEWIDTH;
			if(!collisionWithTile(tx,(int)(y+ bounds.y)/Tile.TILEHEIGHT) && !collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT) )
					x+=xMove;
		}
		
		
	}
	
	public void moveY() {
		if(yMove> 0) {
			int ty = (int)(y+yMove + bounds.y+ bounds.height)/Tile.TILEHEIGHT;
			if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILEWIDTH,ty) && !collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty) ){
				y += yMove;
			}
			
			
		}else if(yMove<0) {
			int ty = (int)(y+yMove + bounds.y)/Tile.TILEHEIGHT;
			if(!collisionWithTile((int)(x+ bounds.x)/Tile.TILEWIDTH,ty) && !collisionWithTile((int)(x+bounds.x+bounds.width)/Tile.TILEWIDTH,ty) )
				y += yMove;
		}
		
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove,0f))
			moveX();
		if(!checkEntityCollisions(0f,yMove))
			moveY();
		
	}
	
	protected boolean collisionWithTile(int x, int y) {
		if(handler.getWorld().getTile(x, y).getId() == 2) {
			handler.getGame().setGameState(null);
			handler.getGameCamera().setxOffset(0);
			handler.getGameCamera().setyOffset(0);
			handler.getWorld();
			if(World.level == 1) {
				State.setState(new GameState(handler,2,2));
			}
			else if(World.level == 2)
				State.setState(new GameState(handler,3,1));
			else{
				handler.getGame().gameoverStateCreator();
				State.setState(handler.getGame().gameoverState);
			}
			return true;
		}
		
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	

}
