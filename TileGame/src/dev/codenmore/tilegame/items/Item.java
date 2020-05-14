package dev.codenmore.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;

public class Item {

	public static Item[] items = new Item[256];
	public static Item woodItem = new Item(Assets.wood, "Wood", 0);
	public static Item stoneItem = new Item(Assets.stone, "Stone", 1);
	
	private Animation anim; 
	
	public static Item iceCrystal = new Item(Assets.iceCrystal[0],"Ice Crystal",2);
	private Animation animIce; 
	
	public static Item deathCrystal = new Item(Assets.deathCrystal[0],"Death Crystal",3);
	private Animation animDeath;
	
	public static Item stormCrystal = new Item(Assets.stormCrystal[0],"Storm Crystal",4);
	private Animation animStorm;
	
	public static Item fireCrystal = new Item(Assets.fireCrystal[0],"Fire Crystal",5);
	private Animation animFire;
	
	public static Item rageCrystal = new Item(Assets.rageCrystal[0],"Rage Crystal",6);
	private Animation animRage;
	
	public static Item soulCrystal = new Item(Assets.soulCrystal[0],"Soul Crystal",7);
	private Animation animSoul;
	
	public static Item luckCrystal = new Item(Assets.luckCrystal[39],"Luck Crystal",8);
	private Animation animLuck;

	public static Item invisCrystal = new Item(Assets.invisCrystal[38],"Invisibility Crystal",9);
	private Animation animInvis;
	
	public static Item swordItem = new Item(Assets.sword, "Sword",10);
	
	public static Item helmetItem = new Item(Assets.helmet, "Helmet",11);
	
	public static Item chestItem = new Item(Assets.chest, "Chest",12);
	
	public static Item legsItem = new Item(Assets.legs, "Legs",13);
	
	public static Item bootsItem = new Item(Assets.boots, "Boots",14);
	
	public static Item armsItem = new Item(Assets.arms, "Arms",15);
	
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x, y, count;
	protected boolean pickedUp = false;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		
		items[id] = this;
		
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		anim = new Animation(100, Assets.deathCrystal);
		
		animIce = new Animation(100, Assets.iceCrystal);
		animDeath = new Animation(100, Assets.deathCrystal);
		animStorm = new Animation(100, Assets.stormCrystal);
		animFire = new Animation(100, Assets.fireCrystal);
		animRage = new Animation(100, Assets.rageCrystal);
		animSoul = new Animation(100, Assets.soulCrystal);
		animLuck = new Animation(50, Assets.luckCrystal);
		animInvis = new Animation(50, Assets.invisCrystal);
			
	}
	
	public void tick() {
		anim.tick();
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
			if(this.id == 2 || this.id == 3 || this.id == 4 || this.id == 5 || this.id == 6 || this.id == 7 || this.id == 8 || this.id == 9) {
				handler.getWorld().getEntityManager().getPlayer().setCrystalCount(handler.getWorld().getEntityManager().getPlayer().getCrystalCount() + 1);
			}
			
	}
	}
	
	public void render(Graphics g) {
		
		if(handler == null)
			return;
		render(g,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
	}
	public void render(Graphics g, int x, int y) {
		g.drawImage(getCurrentAnimationFrame(), x, y,ITEMWIDTH,ITEMHEIGHT, null);
	}

	//testing
	public Item createNew(int count) {
		Item i = new Item(texture, name, id); 
		i.setPickedUp(true);
		i.setCount(count);
		return i;
	}
	
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id); 
		i.setPosition(x, y);
		return i;
	}
	
	public BufferedImage getCurrentAnimationFrame() {
		
		if(id == 2) {
			anim = animIce;
			return anim.getCurrentFrame();
		}
		else if(id == 3) {
			anim = animDeath;
			return anim.getCurrentFrame();
		}
		else if(id == 4) {
			anim = animStorm;
			return anim.getCurrentFrame();
		}
		else if(id == 5) {
			anim = animFire;
			return anim.getCurrentFrame();
		}
		else if(id == 6) {
			anim = animRage;
			return anim.getCurrentFrame();
		}
		else if(id == 7) {
			anim = animSoul;
			return anim.getCurrentFrame();
		}
		else if(id == 8) {
			anim = animLuck;
			return anim.getCurrentFrame();
		}
		else if(id == 9) {
			anim = animInvis;
			return anim.getCurrentFrame();
		}
		else {
		return texture;
		}
	}
	
	
	
	
	
	
	
	// g and s
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Animation getAnim() {
		return anim;
	}

	public void setAnim(Animation iceCrystalAnim) {
		this.anim = iceCrystalAnim;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int c) {
		this.count = c;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	
}

 