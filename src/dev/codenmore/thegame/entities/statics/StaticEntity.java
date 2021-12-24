package dev.codenmore.thegame.entities.statics;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.entities.Entity;

public abstract class StaticEntity extends Entity {
	public StaticEntity(Handler handler, float x, float y, int width, int height,boolean isSolid) {
		super(handler,x,y,width,height);
		this.isSolid = isSolid;
		
	}
	public boolean isSolid() {
		return isSolid;
	}
}
