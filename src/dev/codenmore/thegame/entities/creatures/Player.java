package dev.codenmore.thegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.codenmore.thegame.Game;
import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.entities.statics.Egg;
import dev.codenmore.thegame.gfx.Assets;

public class Player extends Creature {
	private int cs;
	private int animation= 0;
	public int getCs() {
		return cs;
	}

	public int health = 5;
	
	public Player(Handler handler,float x, float y) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 18;
		bounds.y = 9;
		bounds.width = 27;
		bounds.height = 44;
		cs = 60;
	}

	@Override
	public void tick() {
		animation++;
		if(animation>=110)
			animation = 0;
		cs++;
		getInput();
		yMove = 1;
		move();
		
		//handler.getGameCamera().centerOnEntity(this);
		
		
	}
	
	private void getInput() {
		xMove = 0;
		if(handler.getKeyManager().right)
			xMove = speed;
		if(handler.getKeyManager().left)
			xMove = -1*speed;
		
		if(handler.getKeyManager().space && cs>60) {
			cs = 0;
			handler.getGame().sfx.get("attack").play();
			handler.getWorld().getEntityManager().addEntity(new Egg(handler,(int)(x+width/2-10),(int)(y+height-10),15,20));
		}
		
		
	}
	
	@Override
	public void render(Graphics g) {
		if(handler.getKeyManager().right)
			g.drawImage(Assets.chicken[6], (int)(x- handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width,height,null);
		else if(handler.getKeyManager().left)
			g.drawImage(Assets.chicken[7], (int)(x- handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width,height,null);
		else {
			int a;
			if(animation>=60)
				a = (110-animation)/10;
			else
				a= animation/10;
			g.drawImage(Assets.chicken[a], (int)(x- handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width,height,null);
		}
	
		
		//g.setColor(Color.red);
		//g.fillRect((int)(x + bounds.x- handler.getGameCamera().getxOffset()),(int)(y + bounds.y- handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
	}

}
