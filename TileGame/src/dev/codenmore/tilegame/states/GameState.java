package dev.codenmore.tilegame.states;

import java.awt.Graphics;


import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.world.World;

public class GameState extends State {
	
	
	private World world;
	
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler,"res/worlds/textTiled.txt");
		handler.setWorld(world);
		
	}
	
	
	public void tick() {
		world.tick();
		
		
	}
	
	public void render(Graphics g) {
		world.render(g);
		
		
	}
}
