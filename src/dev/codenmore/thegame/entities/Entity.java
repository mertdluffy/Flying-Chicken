package dev.codenmore.thegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.entities.statics.Smoke;

public abstract class Entity {

	protected float x;
	protected float y;
	protected Handler handler;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean isSolid;
	protected boolean exists;
	
	
	public Entity(Handler handler,float x, float y,int width,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.exists = true;
		
		bounds = new Rectangle(0,0,width,height);
		
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)) && e.isSolid && e.exists) {
				if(this.getClass().getSimpleName().equals("Egg")) {
					handler.getGame().sfx.get("pop").play();
					handler.getWorld().point += e.height ;
					handler.getWorld().getEntityManager().getEntities().remove(this);
					handler.getWorld().getEntityManager().addEntity(new Smoke(handler,(int)(e.getX()+e.getWidth()/2-10),(int)(e.getY()+e.getHeight()/2-10),e.getWidth(),e.getHeight()));
					handler.getWorld().getEntityManager().getEntities().remove(e);
					return true;
				}
				else if (this.getClass().getSimpleName().equals("Player"))
				{
					handler.getGame().sfx.get("pop").play();
					handler.getWorld().health--;
					handler.getWorld().getEntityManager().addEntity(new Smoke(handler,(int)(e.getX()+e.getWidth()/2-10),(int)(e.getY()+e.getHeight()/2-10),e.getWidth(),e.getHeight()));
					handler.getWorld().getEntityManager().getEntities().remove(e);
				}
				
				
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset,float yOffset) {
		return new Rectangle((int)(x+bounds.x+ xOffset),(int)(y+bounds.y+yOffset),bounds.width,bounds.height);
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g) ;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
