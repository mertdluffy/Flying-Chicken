package dev.codenmore.thegame;

import dev.codenmore.thegame.gfx.GameCamera;
import dev.codenmore.thegame.input.KeyManager;
import dev.codenmore.thegame.states.State;
import dev.codenmore.thegame.worlds.World;

public class Handler {
	private Game game;
	private World world;
	public Game getGame() {
		return game;
	}
	
	public int getWidth() {
		return game.getWidth();
		
	}
	public void restart() {
		game = new Game("The bird",640,640);
		game.start();
		State.setState(this.getGame().gameState);
	}
	
	public int getHeight() {
		return game.getHeight();
		
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
		
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public Handler(Game game) {
		this.game = game;
		
	}

}
