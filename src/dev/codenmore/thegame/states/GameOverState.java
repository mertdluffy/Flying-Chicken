package dev.codenmore.thegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.gfx.Assets;

public class GameOverState extends State{
	private Font font;
	public GameOverState( final Handler handler) {
		super(handler);
		font = new Font("Arial", Font.PLAIN, 42);
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().y){
			handler.getGame().sfx.get("selection").play();
			handler.getGame().bgMusic.stop();
			handler.getGame().bgMusic.play();
			GameOverState.getState().
			handler.getGameCamera().setxOffset(0);
			handler.getGameCamera().setyOffset(0);
			handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX());
			handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY());	
			handler.getWorld().health = 5;
			handler.getWorld().point= 0;
			State.setState(new GameState(handler,1,3));
			//handler.getGame().setGameState(new GameState(handler,1,3));
			//State.setState(handler.getGame().gameState);
		}
		else if(handler.getKeyManager().n)
			System.exit(0);
		

	}

	@Override
	public void render(Graphics g) {
		if(handler.getWorld().health == 0)
			g.drawImage(Assets.gameover[0],0,0,640,640,  null);
		else
			g.drawImage(Assets.gameover[1],0,0,640,640,  null);
		g.setFont(font);
        g.setColor(Color.black);
        g.drawString(handler.getWorld().point + "",410,165);
	}

}
