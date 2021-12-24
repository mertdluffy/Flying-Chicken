package dev.codenmore.thegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.gfx.Assets;

public class Cat extends StaticEntity {

	public Cat(Handler handler, float x, float y,int width,int height) {
		super(handler, x, y, width,height,true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
			g.drawImage(Assets.cat,(int)(x- handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()), width,height,null);
	}
	
	

}
