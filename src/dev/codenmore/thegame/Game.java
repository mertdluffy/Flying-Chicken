package dev.codenmore.thegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;


import Audio.AudioPlayer;
import dev.codenmore.thegame.display.Display;
import dev.codenmore.thegame.gfx.Assets;
import dev.codenmore.thegame.gfx.GameCamera;
import dev.codenmore.thegame.input.KeyManager;
import dev.codenmore.thegame.states.GameOverState;
import dev.codenmore.thegame.states.GameState;
import dev.codenmore.thegame.states.MenuState;
import dev.codenmore.thegame.states.State;

public class Game implements Runnable {
	private Display display;
	private int width, height;
	private Thread thread;
	boolean running = false;
	public String title;
	private Handler handler;
	public AudioPlayer bgMusic;
	public HashMap<String, AudioPlayer> sfx;
	
	
	private BufferStrategy bs;
	private Graphics g;
	
	//states
	public State gameState;
	public State gameoverState;
	public State menuState;
	
	public void setDisplay(Display display) {
		this.display = display;
	}

	private KeyManager keyManager;
	//camera
	private GameCamera gameCamera;
	
	public Game(String title,int width,int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();

	bgMusic = new AudioPlayer("/music/bg2.wav");
        
        sfx = new HashMap<>();
        sfx.put("selection", new AudioPlayer("/sfx/selection.wav"));
        sfx.put("attack", new AudioPlayer("/sfx/attack.wav"));
        sfx.put("pop", new AudioPlayer("/sfx/pop.wav"));
	}
	private void init() {
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler,0,0);
		gameState = new GameState(handler,1,3);
		menuState = new MenuState(handler);

		State.setState(menuState);
	}
	public void gameoverStateCreator() {
	gameoverState = new GameOverState(handler);
	}
	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	int x = 0;
	public int getWidth() {
		return width;
		
	}
	
	public int getHeight() {
		return height;
		
	}
	
	
	private void tick() {
                bgMusic.loop();
		keyManager.tick();
		if(State.getState() != null)
			 State.getState().tick();
		
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
		
	}
	
	private void render() {
		bs= display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		// Draw here
		if(State.getState() != null)
			 State.getState().render(g);
		//End Drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks= 0;
		
		
		
		while(running) {
			now = System.nanoTime();
			delta+= (now-lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime= now;
			
			if(delta>=1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer>=1000000000) {
				ticks = 0;
				timer = 0;
				
			}
		}
		stop();
	}
	public KeyManager getKeyManager() {
		return keyManager;
		
	}
	public synchronized void start() {
		if (running )
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		try {
		thread.join();		
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}
