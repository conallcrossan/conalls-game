package dev.codenmore.tilegame.world;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.creatures.Enemy;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Rock;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.items.ItemManager;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int [][] tiles;
	
	private EntityManager playerManager;
	
	
	private ItemManager itemManager;
	
	public World(Handler handler,String path) {
		this.handler = handler;
		addEntities();
		addItems();
		
		loadWorld(path);
		
		playerManager.getPlayer().setX(spawnX);
		playerManager.getPlayer().setY(spawnY);
	}
	
	

	public void tick() {
		itemManager.tick();
		playerManager.tick();
		
	}
	
	public void render(Graphics g) {
		int xStart = (int)Math.max(0,handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH+1);
		int yStart = (int)Math.max(0,handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT+1);
		
		for(int y = yStart; y<yEnd; y++) {
			for(int x = xStart; x<xEnd; x++) {
				getTile(x,y).render(g,(int)( x* Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y* Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		itemManager.render(g);
		
		playerManager.render(g);
		
		
		
	}
	
	private void addEntities() {
		playerManager = new EntityManager(handler, new Player(handler, 500, 100));
		
		
		itemManager = new ItemManager(handler);
		
		playerManager.addEntity(new Tree(handler,100,200));
		playerManager.addEntity(new Rock(handler,100,300));
		playerManager.addEntity(new Tree(handler,100,500));
		playerManager.addEntity(new Rock(handler,500,300));
		playerManager.addEntity(new Tree(handler,500,500));
		playerManager.addEntity(new Rock(handler,1000,300));
		playerManager.addEntity(new Tree(handler,1000,500));
		playerManager.addEntity(new Enemy(handler, 250, 250));
		playerManager.addEntity(new Enemy(handler, 500, 250));
		playerManager.addEntity(new Enemy(handler, 550, 350));
		playerManager.addEntity(new Enemy(handler, 550, 450));
		playerManager.addEntity(new Enemy(handler, 550, 550));
		playerManager.addEntity(new Enemy(handler, 550, 650));
		
		for(int i = 500; i<2000; i += 96) {
			playerManager.addEntity(new Rock(handler,600,i));
		}
	}
	
	private void addItems() {
		getItemManager().addItem(Item.iceCrystal.createNew((int)700,(int)200));
		getItemManager().addItem(Item.deathCrystal.createNew((int)700,(int)235));
		getItemManager().addItem(Item.stormCrystal.createNew((int)700,(int)270));
		getItemManager().addItem(Item.fireCrystal.createNew((int)700,(int)305));
		getItemManager().addItem(Item.rageCrystal.createNew((int)700,(int)340));
		getItemManager().addItem(Item.soulCrystal.createNew((int)700,(int)375));
		getItemManager().addItem(Item.luckCrystal.createNew((int)700,(int)410));
		getItemManager().addItem(Item.invisCrystal.createNew((int)700,(int)445));
	}
	 
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y<height; y++) {
			for(int x = 0; x<width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width)+4]);
			}
		}
	}
	
	//g and s
	
public Tile getTile(int x, int y) {
		
		if(x < 0 || y < 0 || x >= width || y >= height )
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	public EntityManager getEntityManager() {
		return playerManager;
	}
	
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
}
