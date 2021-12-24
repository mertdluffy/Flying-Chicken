package dev.codenmore.thegame.tiles;

import dev.codenmore.thegame.gfx.Assets;

public class FinishTile extends Tile{

	public FinishTile( int id) {
		super(Assets.finish, id);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean  isSolid() {
		return true;
	}
}
