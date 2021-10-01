package com.DSPStudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.DSPStudio.main.Game;
import com.DSPStudio.world.Camera;

public class Rock extends Entity{

	public static BufferedImage spriteRock = Game.spritesheet.getSprite(0, 16, 32, 26);

	public Rock(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void render(Graphics g) {
		g.drawImage(spriteRock, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
}
