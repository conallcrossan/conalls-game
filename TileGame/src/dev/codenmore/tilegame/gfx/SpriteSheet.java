package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	//row and column is backwords
	
	public BufferedImage getSprite(int row, int column, int width,int height){
		  int x = (row*width)-width;
		  int y = (column*height)-height;
		  return sheet.getSubimage(x, y, width, height);
		 }
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
