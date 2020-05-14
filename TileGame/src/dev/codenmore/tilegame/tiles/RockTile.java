package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class RockTile extends Tile {
	
	public RockTile(int id) {
		super(Assets.rockTile, id);
		
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
