package dev.codenmore.thegame.tiles;

import dev.codenmore.thegame.gfx.Assets;

public class LogTile extends Tile{

	public LogTile( int id) {
		super(Assets.log, id);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean  isSolid() {
		return true;
	}

}
