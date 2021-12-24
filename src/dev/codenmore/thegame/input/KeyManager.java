package dev.codenmore.thegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	public boolean space,left,right,y,n,s,h;
	
	private boolean[] keys;
	
	public KeyManager() {
		keys = new boolean[256];
		
	}
	
	public void tick(){
		s = keys[KeyEvent.VK_S];
		h = keys[KeyEvent.VK_H];
		y = keys[KeyEvent.VK_Y];
		n = keys[KeyEvent.VK_N];
		space = keys[KeyEvent.VK_SPACE];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

}
