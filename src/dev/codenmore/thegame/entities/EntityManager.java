package dev.codenmore.thegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.entities.creatures.Player;
import dev.codenmore.thegame.entities.statics.Smoke;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}


	public boolean checkPassed(int yOffset) {
		for(Entity e: entities) {
			if(e.equals(getPlayer()) || e.getClass().getSimpleName().equals("Egg") || e.getClass().getSimpleName().equals("Smoke"))
				continue;
			if(yOffset > e.getY() - e.getHeight()) {
				handler.getGame().sfx.get("pop").play();
				handler.getWorld().getEntityManager().addEntity(new Smoke(handler,(int)(e.getX()+e.getWidth()/2-10),(int)(e.getY()+e.getHeight()/2-10),e.getWidth(),e.getHeight()));
				entities.remove(e);
				handler.getWorld().health--;
				return true;
			}
			
		}
		return false;
	}
	
	public EntityManager(Handler handler, Player player) {
		this.handler=  handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		
	}
	
	public void tick() {
		for (int i = 0;i<entities.size();i++) {
			Entity  e = entities.get(i);
			e.tick();	
		}
		checkPassed((int)getPlayer().getY());
		
		
	}
	
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
			
		}
	}
	
	public void addEntity(Entity e) {
		
		entities.add(e);
	}

}
