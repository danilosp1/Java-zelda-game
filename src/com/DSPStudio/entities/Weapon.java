package com.DSPStudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.DSPStudio.main.Game;
import com.DSPStudio.world.Camera;

public class Weapon extends Entity{
	
	public int frames = 0, maxFrames = 25, index = 0, maxIndex = 1;
	private boolean start = true;
	
	private BufferedImage[] spriteWeapon;

	public Weapon(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		spriteWeapon = new BufferedImage[2];
		
		for(int i = 0; i < 2; i++) {
			spriteWeapon[i] = Game.spritesheet.getSprite(88 + (i*10), 45, 10, 14);
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
		g.drawImage(spriteWeapon[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	

}
