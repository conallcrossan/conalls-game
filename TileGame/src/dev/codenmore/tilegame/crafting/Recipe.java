package dev.codenmore.tilegame.crafting;

import java.util.ArrayList;

import dev.codenmore.tilegame.items.Item;

public class Recipe {
	//add to crafting constructor
	public static Recipe sword = new Recipe(2,Item.woodItem, 1, Item.stoneItem, 2,Item.swordItem);
	public static Recipe helmet = new Recipe(1, Item.stoneItem, 2, Item.helmetItem);
	public static Recipe chest = new Recipe(1,Item.stoneItem, 3, Item.chestItem);
	public static Recipe boots = new Recipe(2, Item.stoneItem, 1, Item.woodItem, 1, Item.bootsItem);
	public static Recipe arms = new Recipe(1, Item.stoneItem, 1, Item.armsItem);
	
	
	
	private int num;
	private Item inA;
	private int inCountA;
	private Item inB;
	private int inCountB;
	private Item out;
	
	public Recipe(int num,Item inA, int inCountA, Item out) {
		this.num = num;
		this.inA = inA;
		this.inCountA = inCountA;
		this.out = out;
	}
	public Recipe(int num, Item inA, int inCountA, Item inB, int inCountB, Item out) {
		this.num = num;
		this.inA = inA;
		this.inCountA = inCountA;
		this.inB = inB;
		this.inCountB = inCountB;
		this.out = out;
	}
	
	
	
	
	
	
	
	
	
	// g and s

	
	public static Recipe getSword() {
		return sword;
	}
	public static void setSword(Recipe sword) {
		Recipe.sword = sword;
	}
	public static Recipe getBoots() {
		return boots;
	}
	public static void setBoots(Recipe boots) {
		Recipe.boots = boots;
	}
	public static Recipe getChest() {
		return chest;
	}
	public static void setChest(Recipe chest) {
		Recipe.chest = chest;
	}
	public static Recipe getHelmet() {
		return helmet;
	}
	public static void setHelmet(Recipe helmet) {
		Recipe.helmet = helmet;
	}
	
	public static Recipe getArms() {
		return arms;
	}
	public static void setArms(Recipe arms) {
		Recipe.arms = arms;
	}
	
	
	
	
	
	
	//reg
	public Item getInA() {
		return inA;
	}
	public void setInA(Item inA) {
		this.inA = inA;
	}
	public int getInCountA() {
		return inCountA;
	}
	public void setInCountA(int inCountA) {
		this.inCountA = inCountA;
	}
	public Item getInB() {
		return inB;
	}
	public void setInB(Item inB) {
		this.inB = inB;
	}
	public int getInCountB() {
		return inCountB;
	}
	public void setInCountB(int inCountB) {
		this.inCountB = inCountB;
	}
	public Item getOut() {
		return out;
	}
	public void setOut(Item out) {
		this.out = out;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}	
}
