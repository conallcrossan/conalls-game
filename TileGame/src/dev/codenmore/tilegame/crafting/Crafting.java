package dev.codenmore.tilegame.crafting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.Text;
import dev.codenmore.tilegame.inventory.Inventory;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;



public class Crafting {
	
	private Handler handler;
	private ArrayList<Recipe> recipeList;
	private boolean active = false;
	private boolean afford = false;
	
	private int playerX = 0;
	private int playerY = 0;
	
	private int selectedCraft = 0;

	private int craX = 120, craY = 88, craW = 512, craH = 290, outImgX = 225, outImgY = 210,
			outImgW = 96, outImgH = 96, inAX = 440, inAY = 180, inAW = 64, inAH = 64,
					inBX = 440, inBY = 280, inBW = 64, inBH = 64;

	
	
	public Crafting(Handler handler) {
		this.handler = handler;
		recipeList = new ArrayList<Recipe>();
		recipeList.add(Recipe.getSword());
		recipeList.add(Recipe.getChest());
		recipeList.add(Recipe.getHelmet());
		recipeList.add(Recipe.getBoots());
		recipeList.add(Recipe.getArms());
		

	}
	
	public void tick() {
		

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q))
			active = !active;
		if(!active)
			return;
		
		getPlayerX();
		getPlayerY();
		
		
		if(handler .getKeyManager().keyJustPressed(KeyEvent.VK_W))
			selectedCraft--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
			selectedCraft++;
		
		if(selectedCraft < 0)
			selectedCraft = recipeList.size() - 1;
		else if(selectedCraft >= recipeList.size())
			selectedCraft = 0;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			checkAfford();
			if(afford) {
				handler.getWorld().getItemManager().addItem
				(recipeList.get(selectedCraft).getOut().createNew(playerX,playerY));
				removeInputs();
				afford = false;
			
			}
		}
		
	}
	
	public void render(Graphics g) {

		if(!active)
			return;
		
		
		g.drawImage(Assets.craftBook, craX, craY, craW, craH, null);
		
		int len = recipeList.size();
		
		if(len == 0)
			return;
		
		//draw output
		Text.drawString(g, recipeList.get(selectedCraft).getOut().getName(), outImgX + 40, outImgX - 70, true, Color.black, Assets.font28);
		g.drawImage(recipeList.get(selectedCraft).getOut().getTexture(), outImgX, outImgY, outImgW, outImgH, null);
		
		//draw input
		Text.drawString(g,recipeList.get(selectedCraft).getInCountA()+"  "+recipeList.get(selectedCraft).getInA().getName(), inAX + 40, inAY - 20, true, Color.black, Assets.font28);
		g.drawImage(recipeList.get(selectedCraft).getInA().getTexture(), inAX, inAY, inAW, inAH, null);
		
		if(recipeList.get(selectedCraft).getNum() == 2) {
			Text.drawString(g,recipeList.get(selectedCraft).getInCountB()+"  "+recipeList.get(selectedCraft).getInB().getName(), inBX + 40, inBY - 20, true, Color.black, Assets.font28);
			g.drawImage(recipeList.get(selectedCraft).getInB().getTexture(), inBX, inBY, inBW, inBH, null);
		}


	}
	
	public void checkAfford() {
		boolean a = false;
		boolean b = false;
		for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems() ) {
			if(i.getId() == recipeList.get(selectedCraft).getInA().getId()) {
				if(i.getCount() >= recipeList.get(selectedCraft).getInCountA()) {
					a = true;
				}
			}
		}
		if(recipeList.get(selectedCraft).getNum() == 1 && a) {
			afford = true;
			return;
		}
		if(recipeList.get(selectedCraft).getNum() == 1) {
			return;
		}
		for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems() ) {
			if(i.getId() == recipeList.get(selectedCraft).getInB().getId()) {
				if(i.getCount() >= recipeList.get(selectedCraft).getInCountB()) {
					b = true;
				} 
			}
		}
		if(a && b)
			afford = true;
	}
	
	public void removeInputs() {
		for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems() ) {
			if(i.getId() == recipeList.get(selectedCraft).getInA().getId()) {
				if(i.getCount() >= recipeList.get(selectedCraft).getInCountA()) {
					i.setCount(i.getCount()-recipeList.get(selectedCraft).getInCountA());
				}
			}
		}
		if(recipeList.get(selectedCraft).getNum() == 1) {
			return;
		}
		for(Item i : handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems() ) {
			if(i.getId() == recipeList.get(selectedCraft).getInB().getId()) {
				if(i.getCount() >= recipeList.get(selectedCraft).getInCountB()) {
					i.setCount(i.getCount()-recipeList.get(selectedCraft).getInCountB());
				} 
			}
		}
	}
	
	public void getPlayerX() {
		playerX = (int)(handler.getGameCamera().getxOffset() + 375);
	}
	
	public void getPlayerY() {
		playerY = (int)(handler.getGameCamera().getyOffset() + 250);
	}
	
	//g and s
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}
}
