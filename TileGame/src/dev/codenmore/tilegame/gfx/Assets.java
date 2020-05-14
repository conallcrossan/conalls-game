package dev.codenmore.tilegame.gfx;

import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static Font font28, font18;
	
	public static BufferedImage brick, dirt, grass, meat, rockTile, tree, rock, wood, stone, sword,
		helmet, chest, legs, boots, arms, craftBook;
	public static BufferedImage winScreen,loseScreen;
	public static BufferedImage[] player_down, player_up, player_left, player_right,player_still;
	public static BufferedImage[] btn_start,btn_exit;
	public static BufferedImage[] attackAnimH, horizontalAttackAnim;
	public static BufferedImage[] jelly_left, jelly_right, jelly_death;
	public static BufferedImage[] iceCrystal, deathCrystal, stormCrystal, fireCrystal,
		rageCrystal, soulCrystal,luckCrystal, invisCrystal;
	public static BufferedImage inventoryScreen;
	
	public static void init() {	
		font28 = FontLoader.loadFont("res/fonts/DroidSans.ttf.ttf", 28);
		font18 = FontLoader.loadFont("res/fonts/DroidSans.ttf.ttf",18);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheetV1.png"));
		SpriteSheet test = new SpriteSheet(ImageLoader.loadImage("/textures/test.png"));
		SpriteSheet jelly = new SpriteSheet(ImageLoader.loadImage("/textures/jellyEnemy.png"));
		SpriteSheet crystal = new SpriteSheet(ImageLoader.loadImage("/textures/crystals.png"));
		SpriteSheet crystal2 = new SpriteSheet(ImageLoader.loadImage("/textures/crystals2.png"));
		
		
		winScreen = ImageLoader.loadImage("/textures/win.png");
		loseScreen = ImageLoader.loadImage("/textures/lost.png");
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		craftBook = ImageLoader.loadImage("/textures/craftBook.png");
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_still = new BufferedImage[4];
		
		player_down[0] = test.getSprite(1, 3, 90, 126);
		player_down[1] = test.getSprite(3, 3, 90, 126);

		player_up[0] = test.getSprite(1, 1, 90, 126);
		player_up[1] = test.getSprite(3, 1, 90, 126);

		player_right[0] = test.getSprite(1, 2, 90, 126);
		player_right[1] = test.getSprite(3, 2, 90, 126);

		player_left[0] = test.getSprite(1, 4, 90, 126);
		player_left[1] = test.getSprite(3, 4, 90, 126);

		player_still[0] = test.getSprite(2, 1, 90, 126);
		player_still[1] = test.getSprite(2, 2, 90, 126);
		player_still[2] = test.getSprite(2, 3, 90, 126);
		player_still[3] = test.getSprite(2, 4, 90, 126);
		
		jelly_left = new BufferedImage[8];
		jelly_right = new BufferedImage[8];
		jelly_death = new BufferedImage[8];
		
		
		jelly_left[0] = jelly.crop(19, 16, 82, 68);
		jelly_left[1] = jelly.crop(99, 11, 82, 68);
		jelly_left[2] = jelly.crop(175, 15, 82, 68);
		jelly_left[3] = jelly.crop(251, 14, 82, 68);
		jelly_left[4] = jelly.crop(15, 87, 82, 68);
		jelly_left[5] = jelly.crop(96, 90, 82, 68);
		jelly_left[6] = jelly.crop(177, 89, 82, 68);
		jelly_left[7] = jelly.crop(256, 88, 82, 68);
		
		jelly_right[0] = jelly.crop(356, 290, 82, 68);
		jelly_right[1] = jelly.crop(277, 288, 82, 68);
		jelly_right[2] = jelly.crop(199, 290, 82, 68);
		jelly_right[3] = jelly.crop(122, 289, 82, 68);
		jelly_right[4] = jelly.crop(359, 362, 82, 68);
		jelly_right[5] = jelly.crop(278, 361, 82, 68);
		jelly_right[6] = jelly.crop(196, 361, 82, 68);
		jelly_right[7] = jelly.crop(115, 361, 82, 68);
		
		jelly_death[0] = jelly.crop(353, 82, 82, 68);
		
		btn_start = new BufferedImage[2];
		btn_exit = new BufferedImage[2];
		
		btn_start[0] = sheet.crop(384, 125, 348, 125);
		btn_start[1] = sheet.crop(384, 0, 348, 125);
		
		btn_exit[0] = sheet.crop(384, 375, 348, 125);
		btn_exit[1] = sheet.crop(384, 250, 348, 125);
		
		attackAnimH = new BufferedImage[4];
		
		attackAnimH[0] = sheet.getSprite(1, 1, 96, 96);
		attackAnimH[1] = sheet.getSprite(2, 1, 96, 96);
		attackAnimH[2] = sheet.getSprite(3, 1, 96, 96);
		attackAnimH[3] = sheet.getSprite(1, 2, 96, 96);
		
		horizontalAttackAnim = new BufferedImage[4];
		
		horizontalAttackAnim[0] = rotate(attackAnimH[0]);
		horizontalAttackAnim[1] = rotate(attackAnimH[1]);
		horizontalAttackAnim[2] = rotate(attackAnimH[2]);
		horizontalAttackAnim[3] = rotate(attackAnimH[3]);
		
		iceCrystal = new BufferedImage[14];
		
		iceCrystal[0] = crystal.getSprite(1, 1, 32, 32);
		iceCrystal[1] = crystal.getSprite(2, 1, 32, 32);
		iceCrystal[2] = crystal.getSprite(3, 1, 32, 32);
		iceCrystal[3] = crystal.getSprite(4, 1, 32, 32);
		iceCrystal[4] = crystal.getSprite(5, 1, 32, 32);
		iceCrystal[5] = crystal.getSprite(6, 1, 32, 32);
		iceCrystal[6] = crystal.getSprite(7, 1, 32, 32);
		iceCrystal[7] = crystal.getSprite(8, 1, 32, 32);
		iceCrystal[8] = crystal.getSprite(9, 1, 32, 32);
		iceCrystal[9] = crystal.getSprite(10, 1, 32, 32);
		iceCrystal[10] = crystal.getSprite(11, 1, 32, 32);
		iceCrystal[11] = crystal.getSprite(12, 1, 32, 32);
		iceCrystal[12] = crystal.getSprite(13, 1, 32, 32);
		iceCrystal[13] = crystal.getSprite(14, 1, 32, 32);
		
		deathCrystal = new BufferedImage[7];
		
		deathCrystal[0] = crystal.getSprite(1, 2, 32, 32);
		deathCrystal[1] = crystal.getSprite(2, 2, 32, 32);
		deathCrystal[2] = crystal.getSprite(3, 2, 32, 32);
		deathCrystal[3] = crystal.getSprite(4, 2, 32, 32);
		deathCrystal[4] = crystal.getSprite(5, 2, 32, 32);
		deathCrystal[5] = crystal.getSprite(6, 2, 32, 32);
		deathCrystal[6] = crystal.getSprite(7, 2, 32, 32);
		
		stormCrystal = new BufferedImage[14];
		
		stormCrystal[0] = crystal.getSprite(1, 3, 32, 32);
		stormCrystal[1] = crystal.getSprite(2, 3, 32, 32);
		stormCrystal[2] = crystal.getSprite(3, 3, 32, 32);
		stormCrystal[3] = crystal.getSprite(4, 3, 32, 32);
		stormCrystal[4] = crystal.getSprite(5, 3, 32, 32);
		stormCrystal[5] = crystal.getSprite(6, 3, 32, 32);
		stormCrystal[6] = crystal.getSprite(7, 3, 32, 32);
		stormCrystal[7] = crystal.getSprite(8, 3, 32, 32);
		stormCrystal[8] = crystal.getSprite(9, 3, 32, 32);
		stormCrystal[9] = crystal.getSprite(10, 3, 32, 32);
		stormCrystal[10] = crystal.getSprite(11, 3, 32, 32);
		stormCrystal[11] = crystal.getSprite(12, 3, 32, 32);
		stormCrystal[12] = crystal.getSprite(13, 3, 32, 32);
		stormCrystal[13] = crystal.getSprite(14, 3, 32, 32);
		
		fireCrystal = new BufferedImage[14];
		
		fireCrystal[0] = crystal.getSprite(1, 4, 32, 32);
		fireCrystal[1] = crystal.getSprite(2, 4, 32, 32);
		fireCrystal[2] = crystal.getSprite(3, 4, 32, 32);
		fireCrystal[3] = crystal.getSprite(4, 4, 32, 32);
		fireCrystal[4] = crystal.getSprite(5, 4, 32, 32);
		fireCrystal[5] = crystal.getSprite(6, 4, 32, 32);
		fireCrystal[6] = crystal.getSprite(7, 4, 32, 32);
		fireCrystal[7] = crystal.getSprite(8, 4, 32, 32);
		fireCrystal[8] = crystal.getSprite(9, 4, 32, 32);
		fireCrystal[9] = crystal.getSprite(10, 4, 32, 32);
		fireCrystal[10] = crystal.getSprite(11, 4, 32, 32);
		fireCrystal[11] = crystal.getSprite(12, 4, 32, 32);
		fireCrystal[12] = crystal.getSprite(13, 4, 32, 32);
		fireCrystal[13] = crystal.getSprite(14, 4, 32, 32);
		
		rageCrystal = new BufferedImage[14];
		
		rageCrystal[0] = crystal.getSprite(1, 5, 32, 32);
		rageCrystal[1] = crystal.getSprite(2, 5, 32, 32);
		rageCrystal[2] = crystal.getSprite(3, 5, 32, 32);
		rageCrystal[3] = crystal.getSprite(4, 5, 32, 32);
		rageCrystal[4] = crystal.getSprite(5, 5, 32, 32);
		rageCrystal[5] = crystal.getSprite(6, 5, 32, 32);
		rageCrystal[6] = crystal.getSprite(7, 5, 32, 32);
		rageCrystal[7] = crystal.getSprite(8, 5, 32, 32);
		rageCrystal[8] = crystal.getSprite(9, 5, 32, 32);
		rageCrystal[9] = crystal.getSprite(10, 5, 32, 32);
		rageCrystal[10] = crystal.getSprite(11, 5, 32, 32);
		rageCrystal[11] = crystal.getSprite(12, 5, 32, 32);
		rageCrystal[12] = crystal.getSprite(13, 5, 32, 32);
		rageCrystal[13] = crystal.getSprite(14, 5, 32, 32);
		
		soulCrystal = new BufferedImage[14];
		
		soulCrystal[0] = crystal.getSprite(1, 6, 32, 32);
		soulCrystal[1] = crystal.getSprite(2, 6, 32, 32);
		soulCrystal[2] = crystal.getSprite(3, 6, 32, 32);
		soulCrystal[3] = crystal.getSprite(4, 6, 32, 32);
		soulCrystal[4] = crystal.getSprite(5, 6, 32, 32);
		soulCrystal[5] = crystal.getSprite(6, 6, 32, 32);
		soulCrystal[6] = crystal.getSprite(7, 6, 32, 32);
		soulCrystal[7] = crystal.getSprite(8, 6, 32, 32);
		soulCrystal[8] = crystal.getSprite(9, 6, 32, 32);
		soulCrystal[9] = crystal.getSprite(10, 6, 32, 32);
		soulCrystal[10] = crystal.getSprite(11, 6, 32, 32);
		soulCrystal[11] = crystal.getSprite(12, 6, 32, 32);
		soulCrystal[12] = crystal.getSprite(13, 6, 32, 32);
		soulCrystal[13] = crystal.getSprite(14, 6, 32, 32);
		
		luckCrystal = new BufferedImage[40];
		
		
		for(int i = 0; i<40; i++) {
			luckCrystal[i] = crystal2.getSprite(i + 1, 1, 32, 32); 
		}
		
		invisCrystal = new BufferedImage[39];
		
		
		for(int i = 0; i<39; i++) {
			invisCrystal[i] = crystal2.getSprite(i + 1, 2, 32, 32); 
		}
		
		brick = sheet.getSprite(2, 2, 96,96);
		dirt = sheet.getSprite(3, 2, 96,96);
		grass = sheet.getSprite(1, 3, 96,96);
		rockTile = sheet.getSprite(4, 2, 96,96);
		meat = sheet.getSprite(3, 3, 96,96);
		tree = sheet.getSprite(1, 4, 96,96);
		rock = sheet.getSprite(4, 1, 96, 96);
		stone = sheet.getSprite(4, 3, 96, 96);
		wood = sheet.getSprite(2, 3, 96, 96);
		sword = sheet.getSprite(2,4, 96, 96);
		helmet = sheet.crop(0,500,96,96);
		chest = sheet.crop(96, 500, 96, 96);
		legs = sheet.crop(192, 500, 96, 96);
		boots = sheet.crop(288, 500, 96, 96);
		arms = sheet.crop(384, 500, 96, 96);
	}
	
	public static BufferedImage rotate(BufferedImage image) {
		final double rads = Math.toRadians(90);
		final double sin = Math.abs(Math.sin(rads));
		final double cos = Math.abs(Math.cos(rads));
		final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
		final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
		final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		final AffineTransform at = new AffineTransform();
		at.translate(w / 2, h / 2);
		at.rotate(rads,0, 0);
		at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		rotateOp.filter(image,rotatedImage);
		
		return rotatedImage;
	}
}
