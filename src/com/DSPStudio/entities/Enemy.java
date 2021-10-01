package com.DSPStudio.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.DSPStudio.main.Game;
import com.DSPStudio.world.Camera;
import com.DSPStudio.world.World;

public class Enemy extends Entity{
	
	public double speed = 1;
	
	public int frames = 0, maxFrames = 4, index = 0, maxIndex = 3;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick(){
		if((int)x < (Game.player.getX() - 15) && World.isFree((int)(x + speed), this.getY())
				&& !isColliding((int)(x + speed), this.getY())) {
			x+=speed;
		}else if((int)x > (Game.player.getX() + 15) && World.isFree((int)(x - speed), this.getY())
				&& !isColliding((int)(x - speed), this.getY())) {
			x-=speed;
		}
		
		if((int)y < (Game.player.getY() - 22) && World.isFree(this.getX(), (int)(y + speed))
				&& !isColliding(this.getX(), (int)(y + speed))) {
			y+=speed;
		}else if((int)y > (Game.player.getY() + 22) && World.isFree(this.getX(), (int)(y - speed))
				&& !isColliding(this.getX(), (int)(y - speed))) {
			y-=speed;
		}
	}
	
	public boolean isColliding(int xnext, int ynext) {
		Rectangle currentEnemy = new Rectangle(xnext, ynext, 16, 21);
		
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), 16, 21);
			if(currentEnemy.intersects(targetEnemy)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		super.render(g);
		//g.setColor(Color.blue); //vizualização da mascara de colisão
		//g.fillRect(this.getX() - Camera.x, this.getY() - Camera.y, 16, 21);
	}

}
