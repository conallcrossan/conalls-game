package dev.codenmore.tilegame.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.tiles.Tile;

public class Rock extends StaticEntity{

	private Random r = new Random();
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y,Tile.TILEWIDTH *1 ,Tile.TILEWIDTH *1);
		
		bounds.x = 5;
		bounds.y = 25;
		bounds.width = width - 15;
		bounds.height = height - 41 ;
		
		health = 3;
	}

	
	
	@Override
	public void tick() {
		
		
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)x + r.nextInt(80), (int)y + r.nextInt(80)));
		active = false;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock,(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),width, height, null);
		
//		g.setColor(Color.red);
//		g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),(int)(y + bounds.y - handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
		
		
	}



	@Override
	public void dieAnim(Graphics g) {
		
	}
}

