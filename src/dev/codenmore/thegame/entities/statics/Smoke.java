package dev.codenmore.thegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.gfx.Assets;

public class Smoke extends StaticEntity{
	private int animation=0;
	public Smoke(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		animation++;
		if(animation%2 == 1) {
			y--;
			width--;
			height--;
			}
		if(width == 0 && height == 0)
			handler.getWorld().getEntityManager().getEntities().remove(this);
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.smoke,(int)(x- handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()), width,height,null);
	}

}
