package dev.codenmore.thegame.states;

import java.awt.Graphics;
import dev.codenmore.thegame.Handler;
import dev.codenmore.thegame.gfx.Assets;

public class MenuState extends State{
	
	int animation=0,which=0;



	public MenuState( final Handler handler) {
		super(handler);
        
	}

	@Override
	public void tick() {
		animation++;
		if(animation>=110)
			animation = 0;
		if(handler.getKeyManager().h){
			if(which == 0)
				handler.getGame().sfx.get("selection").play();
			which = 1;
		}
		else if(handler.getKeyManager().s) {
			State.setState(handler.getGame().gameState);
			handler.getGame().sfx.get("selection").play();
		}
	}

	@Override
	public void render(Graphics g) {
		if(which == 1)
			g.drawImage(Assets.howtoplay,0,0,640,640,  null);
		else
			g.drawImage(Assets.mainmenu,0,0,640,640,  null);
		if(which == 0) {
			int a;
			if(animation>=60)
				a = (110-animation)/10;
			else
				a= animation/10;
			g.drawImage(Assets.chicken[a], 230, 250, 200,200,null);
		}
			
	}

}
