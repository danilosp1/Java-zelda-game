package com.DSPStudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.DSPStudio.main.Game;
import com.DSPStudio.world.Camera;

public class Life extends Entity{
	
	public int frames = 0, maxFrames = 15, index = 0, maxIndex = 3;
	private boolean start = true;
	
	private BufferedImage[] spriteLifepk;

	public Life(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		spriteLifepk = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			spriteLifepk[i] = Game.spritesheet.getSprite(33 + (i*13), 47, 13, 11);
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
		g.drawImage(spriteLifepk[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
