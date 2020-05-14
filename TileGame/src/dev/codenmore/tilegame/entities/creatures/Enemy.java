  package dev.codenmore.tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.Text;
import dev.codenmore.tilegame.items.Item;

public class Enemy extends Creature{

	private Animation animLeft, animRight, animDeath;
	
	private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
	
	private long lastAnimTimer, animCooldown = 500, animTimer = 0;
	
	private boolean dead = false;
	
	private String directionJ;
	
	private float distance, diffX, diffY;
	
	private Rectangle arr;
	
	private Random r = new Random();
	private boolean shouldAtt = false;

	
	public Enemy(Handler handler, float x, float y){
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 7;
		bounds.width = 30;
		bounds.height = 40;
		
		animLeft = new Animation(100, Assets.jelly_left);
		animRight = new Animation(100, Assets.jelly_right);
		animDeath = new Animation(100, Assets.jelly_death);
		
		animDone = false;
		
		speed = 1f;
		
		arr = new Rectangle(0,0,0,0);
	}

	@Override
	public void tick() {
		animLeft.tick();
		animRight.tick();
		
		follow();
		move();
		
		checkAttacks();
	}

	public void follow() {
		
		diffX = x - (handler.getWorld().getEntityManager().getPlayer().getX() - 10);
		
		diffY = y - (handler.getWorld().getEntityManager().getPlayer().getY());
		
//		distance = (float) (Math.sqrt(((x - handler.getWorld().getEntityManager().getPlayer().getX()) *
//				(x - handler.getWorld().getEntityManager().getPlayer().getX()))+
//				((y - handler.getWorld().getEntityManager().getPlayer().getY()) * 
//				y - handler.getWorld().getEntityManager().getPlayer().getY())));
//		
//		xMove = (((-1 / distance) * diffX));
//		yMove = (((-1 / distance) * diffY));
		
		
		
//		diffX = (handler.getWorld().getEntityManager().getPlayer().getX())-x - width;
//		
//		diffY = (handler.getWorld().getEntityManager().getPlayer().getY())-y - height;
//		 
//		distance = (float) (Math.sqrt(((handler.getWorld().getEntityManager().getPlayer().getX() - x) *
//				(handler.getWorld().getEntityManager().getPlayer().getX() - x))+
//				((handler.getWorld().getEntityManager().getPlayer().getY() - y) * 
//				handler.getWorld().getEntityManager().getPlayer().getY() - y)));
//		
//		xMove = (((-1 / distance) * diffX));
//		yMove = (((-1 / distance) * diffY));
		xMove = 0;
		yMove = 0;
		
		if(diffX > 0) {
			xMove += -speed;
		}
		
		if(diffX < 0) {
			xMove += speed;
		}
		
		if(diffY > 0) {
			yMove = -speed;
		}
		
		if(diffY < 0) {
			yMove = speed;
		}
		
		
		
			if(xMove < 0) {
				
				directionJ = "Left";

			}
			if(xMove > 0) {
				directionJ = "Right";

			}
			if(yMove < 0) {
				directionJ = "Down";

			}
			if(yMove > 0) {
				directionJ = "Up";

				
			}
		
		
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()),
				(int)(y - handler.getGameCamera().getyOffset()),null);
		
		
		//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()+9),
//		(int) (y + bounds.y - handler.getGameCamera().getyOffset()-18),bounds.width-17,bounds.height - 24);
//		
//		Text.drawString(g,""+ health, (int)(x + bounds.x - handler.getGameCamera().getxOffset()+15),
//				(int)(y + bounds.y - handler.getGameCamera().getyOffset()-9),true,
//				Color.black, Assets.font18);
		
//		g.setColor(Color.red);
//		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
//		
//		g.drawRect((int)(arr.x - handler.getGameCamera().getxOffset()),
//				(int)(arr.y - handler.getGameCamera().getyOffset()), arr.width, arr.height);
	}

	private void checkAttacks() {
		if(!shouldAtt) {	
			attackTimer += System.currentTimeMillis() - lastAttackTimer;
			lastAttackTimer = System.currentTimeMillis();
			if(attackTimer < attackCooldown)
				return;
			
			
			Rectangle cb = getCollisionBounds(0,0);
			Rectangle ar = new Rectangle();
			
			ar.x = cb.x - 15;
			ar.y = cb.y - 5;
			
			ar.width = cb.width + 40;
			ar.height = cb.height + 20;
		
		
		
//		int arSize = 20; 
//		
//		ar.width = arSize;
//		ar.height = arSize;
//			
//			if(directionJ == "Up") { //up
//				ar.x = cb.x + cb.width / 2 - arSize / 2;
//				ar.y = cb.y - arSize;
//			}
//			else if(directionJ == "Down") { //down
//				ar.x = cb.x + cb.width / 2 - arSize / 2;
//				ar.y = cb.y + cb.height;
//			}
//			else if(directionJ == "Left") { //left
//				ar.x = cb.x - arSize;
//				ar.y = cb.y + cb.height / 2 - arSize / 2;
//			}
//			else if(directionJ == "Right") { //right
//				ar.x = cb.x + cb.width;
//				ar.y = cb.y + cb.height / 2 - arSize / 2;
//			}
				
		
		
		attackTimer = 0;
		
		
		
		Entity e = handler.getWorld().getEntityManager().getPlayer();
			if(e.equals(this))
				return;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				arr = ar;
				e.hurt(1);
				return;
			}
		}
	}
	
	@Override
	public void die() {
		dead = true;
		lastAnimTimer = System.currentTimeMillis();
		//handler.getWorld().getItemManager().addItem(Item.rageCrystal.createNew((int)x + r.nextInt(80), (int)y + r.nextInt(80)));

	}
	
	public void dieAnim(Graphics g) {
		
		if(dead) {	
			animTimer += System.currentTimeMillis() - lastAnimTimer;
			lastAnimTimer = System.currentTimeMillis();
			if(animTimer < animCooldown) {
				g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()),
						(int)(y - handler.getGameCamera().getyOffset()),null);
			}else {
			active = false;
			animTimer += System.currentTimeMillis() - lastAnimTimer;
			}
			shouldAtt = true;
		}
		else {
			return;
		}
	}	
	
	
	private BufferedImage getCurrentAnimationFrame() {
		if (dead) {
			return animDeath.getCurrentFrame();
		}
		else if(xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animRight.getCurrentFrame();
		}
		return Assets.jelly_left[0];
	}
}
