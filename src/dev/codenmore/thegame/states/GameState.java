package dev.codenmore.thegame.states;

import java.awt.Graphics;

import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.worlds.World;

public class GameState extends State{
	
	private World world;
	
	
	public GameState(Handler handler,int level,int zorluk) {
		super(handler);
		world = new World(handler,"res/worlds/world1.txt",level,zorluk);
		handler.setWorld(world);
		
		
	}

	@Override
	public void tick() {
		handler.getGameCamera().move(0, 1);
		world.tick();
			if(handler.getWorld().health == 0) {
			handler.getGame().gameoverStateCreator();
			State.setState(handler.getGame().gameoverState);
			}
		}

	@Override
	public void render(Graphics g) {
		world.render(g);
		
	}
	
	

}