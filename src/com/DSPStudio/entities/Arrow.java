package com.DSPStudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.DSPStudio.main.Game;
import com.DSPStudio.world.Camera;

public class Arrow extends Entity{
	
	public int frames = 0, maxFrames = 25, index = 0, maxIndex = 1;
	
	private BufferedImage[] spriteArrow;
	private boolean start = true;

	public Arrow(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		spriteArrow = new BufferedImage[2];
		
		for(int i = 0; i < 2; i++) {
			spriteArrow[i] = Game.spritesheet.getSprite(112 + (i*14), 46, 14, 13);
		}
	}
	
	public void tick() {
		if(start) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(spriteArrow[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
