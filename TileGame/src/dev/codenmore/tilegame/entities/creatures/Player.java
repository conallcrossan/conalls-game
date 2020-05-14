package dev.codenmore.tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.crafting.Crafting;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.Text;
import dev.codenmore.tilegame.inventory.Inventory;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.states.State;

public class Player extends Creature{
	
	//animations
	private Animation animDown, animUp, animLeft, animRight, animStill,animAttackH,animVirticalAttack;
	
	//attack timer
	private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
	private Rectangle att;
	private int curX, curY;
	
	private int crystalCount;
	
	private Inventory inventory;
	
	private Crafting crafting;
	
	private int attackMulti = 1;
	
	
	private boolean helmet = false;
	private boolean chest = false;
	private boolean boots = false;
	private boolean arms = false;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 12;
		bounds.y = 30;
		bounds.width = 19;
		bounds.height = 24;
		
		animDown = new Animation(250,Assets.player_down);
		animUp = new Animation(250, Assets.player_up);
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		animStill = new Animation(250,Assets.player_still);
		
		animAttackH = new Animation(100, Assets.attackAnimH);
		animVirticalAttack = new Animation(100, Assets.horizontalAttackAnim);
		att = new Rectangle(0,0,0,0);
		
		inventory = new Inventory(handler);
		
		crafting = new Crafting(handler);
		
		health = 20;
		
		crystalCount = 0;
	}

	@Override
	public void tick() {
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animStill.tick();
		animAttackH.tick();
		animVirticalAttack.tick();
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		checkAttackMulti();
		checkHealthMulti();
		checkSpeedMulti();
		checkAttacks();
		inventory.tick(); 
		crafting.tick();
		checkWin();
		
		curX = (int) (x + bounds.x - handler.getGameCamera().getxOffset());
		curY = (int)(y + bounds.y - handler.getGameCamera().getyOffset()-32);
	}


	@Override
	public void render(Graphics g) {
		
		
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()),
				(int)(y - handler.getGameCamera().getyOffset()),width, height, null);

		
		g.drawImage(getCurrentAnimAttack(), (int)(x + att.x - handler.getGameCamera().getxOffset()),
					(int)(y + att.y - handler.getGameCamera().getyOffset()),att.width, att.height, null);
		
		g.setColor(Color.green);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
		(int) (y + bounds.y - handler.getGameCamera().getyOffset()-40),bounds.width+2,bounds.height - 10);
		
		Text.drawString(g,""+ health, (int)(x + bounds.x - handler.getGameCamera().getxOffset()+10),
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()-32),true,
				Color.black, Assets.font18);
		
//		g.setColor(Color.magenta);
//		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
		
//		g.setColor(Color.magenta);
//		g.drawRect((int) (x + att.x - handler.getGameCamera().getxOffset()),
//				(int) (y + att.y - handler.getGameCamera().getyOffset()),att.width,att.height);
		
	}

	public void postRender(Graphics g) {
		inventory.render(g);
		crafting.render(g);
	}
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		if(inventory.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		
		
		if(handler.getMouseManager().isLeftPressed()) {
			
			if(directionJ == 2) { //up
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			}
			else if(directionJ == 1) { //down
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + cb.height;
			}
			else if(directionJ == 3) { //left
				ar.x = cb.x - arSize;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}
			else if(directionJ == 4) { //right
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
			}
				
		}
		else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1 * attackMulti);
				return;
			}
		}
	}
	@Override
	public void die() {
		System.out.println("You Lose");
		
		//quit thread instead maybe
		
		State.setState(handler.getGame().loseState);
		
		
	}
	
	public void dieAnim(Graphics g) {
		
	}
	
	public void checkWin() {
		if(crystalCount >= 8) {
			State.setState(handler.getGame().winState);
		}
		//System.out.println(crystalCount);
	}
	
	public void checkAttackMulti() {
		for(Item i : getInventory().getInventoryItems()) {
			if(i.getId() == 10) {
				attackMulti = 10;
			}
		}
	}
	
	public void checkHealthMulti() {
		
		for(Item i : getInventory().getInventoryItems()) {
			if(i.getId() == 11 && !helmet) {
				health += 5;
				helmet = true;
			}
			if(i.getId() == 12 && !chest) {
				health += 10;
				chest = true;
			}
			if(i.getId() == 15 && !arms) {
				health += 3;
				arms = true;
			}
			
		}
	}
	
	public void checkSpeedMulti() {
		
		for(Item i : getInventory().getInventoryItems()) {
			if(i.getId() == 14 && !boots) {
				speed *= 2;
				boots = true;
			}
		}
		
	}
	
	
	
	
	//g and s
	private void getInput() {
		if(inventory.isActive())
			return;
		if(crafting.isActive()) {
			return;
		}
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
			directionJ = 2;
		else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			directionJ = 1;
		else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A))
			directionJ = 3;
		else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D))
			directionJ = 4;
	}
	
	
	

	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animRight.getCurrentFrame();
		}else if(yMove < 0) {
			return animUp.getCurrentFrame();
		}else if(yMove > 0) {
			return animDown.getCurrentFrame();
		}
		else {
			if(directionJ == 2) {
				
				return Assets.player_still[0];
			}
			else if(directionJ == 4) {
				
				return Assets.player_still[1];
			}
			else if(directionJ == 1) {
				
				return Assets.player_still[2];
			}
			else if(directionJ == 3) {
				
				return Assets.player_still[3];
			}
			else {
				return Assets.player_still[1];
			}
		}
	}

	
	//set a new variable for width and height to negative width and height accourding
	public BufferedImage getCurrentAnimAttack() {
		
		if((handler.getMouseManager().isLeftPressed())) {
			
			if(directionJ == 2) {//up
				att.x = 0;
				att.y = -20;
				att.width = 50;
				att.height = 50;
				return animAttackH.getCurrentFrame();
			}
			else if(directionJ == 4) {//right
				att.x = 10;
				att.y = 15;
				att.width = 50;
				att.height = 50;
				return animVirticalAttack.getCurrentFrame();
			}
			else if(directionJ == 1) {//down
				att.x = 50;
				att.y = 80;
				att.width = -50;
				att.height = -50;
				return animAttackH.getCurrentFrame();
			}
			else if(directionJ == 3) {//left
				att.x = 35;
				att.y = 55;
				att.width = -50;
				att.height = -50;
				return animVirticalAttack.getCurrentFrame();
			}
			else
				return null;
			
		}
		else
			return null;
		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Rectangle getAtt() {
		return att;
	}

	public int getCrystalCount() {
		return crystalCount;
	}

	public void setCrystalCount(int crystalCount) {
		this.crystalCount = crystalCount;
	}
	
	public int getPlayerX() {
		return curX;
	}

	public int getPlayerY() {
		return curY;
	}
	

	
	
	
}
