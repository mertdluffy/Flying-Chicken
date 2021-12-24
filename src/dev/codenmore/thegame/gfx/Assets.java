package dev.codenmore.thegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage egg,cat,sky,log,finish,smoke;
	public static BufferedImage top_bar;
	public static BufferedImage []chicken;
	public static BufferedImage mainmenu,howtoplay;
	public static BufferedImage[] gameover;
	public static void init() {
		gameover = new BufferedImage[2];
		chicken = new BufferedImage[8];
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/chickens.png"));
		chicken[0] = sheet.crop(0, 0, 128, 128);
		chicken[1]= sheet.crop(128, 0, 128, 128);
		chicken[2]= sheet.crop(128*2, 0,  128, 128);
		chicken[3]= sheet.crop(128*3, 0,  128, 128);
		chicken[4]= sheet.crop(128*0, 128,  128, 128);
		chicken[5]= sheet.crop(128*1, 128,  128, 128);
		chicken[6]= sheet.crop(128*2, 128,  128, 128);
		chicken[7]= sheet.crop(128*3, 128,  128, 128);
		gameover[0] = ImageLoader.loadImage("/textures/gameover_lose.jpg");
		gameover[1] = ImageLoader.loadImage("/textures/gameover_won.jpg");
		egg =  ImageLoader.loadImage("/textures/egg.png");
		finish =  ImageLoader.loadImage("/textures/finish.jpg");
		top_bar = ImageLoader.loadImage("/textures/top_bar.png");
		sky = ImageLoader.loadImage("/textures/sky.jpg");
		log = ImageLoader.loadImage("/textures/tree_log.jpg");
		cat = ImageLoader.loadImage("/textures/cat.png");
		mainmenu = ImageLoader.loadImage("/textures/mainmenu.jpg");
		howtoplay = ImageLoader.loadImage("/textures/howtoplay.jpg");
		smoke  = ImageLoader.loadImage("/textures/smoke.png");
	}
	

}
