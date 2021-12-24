package dev.codenmore.thegame.entities.statics;

import java.awt.Graphics;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.gfx.Assets;

public class Egg extends StaticEntity {

	public Egg(Handler handler, float x, float y,int width,int height) {
		super(handler, x, y, width,height,false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
			if(!checkEntityCollisions(0f,2))
				this.y+=2;
			
		
			
		
	}

	@Override
	public void render(Graphics g) {
			g.drawImage(Assets.egg,(int)(x- handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()), width,height,null);
	}
	
	

}

