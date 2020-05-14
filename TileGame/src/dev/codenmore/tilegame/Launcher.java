package dev.codenmore.tilegame;


public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Tile Game",750,500);
		game.start();
		System.out.println("the game");
	}
}
