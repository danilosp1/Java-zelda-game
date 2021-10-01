package com.DSPStudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.DSPStudio.main.Game;
import com.DSPStudio.world.Camera;
import com.DSPStudio.world.World;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	public double speed = 1.7;
	public int dir_right = 0, dir_left = 1, dir_up = 2, dir_down = 3;
	public int dir = dir_down;
	
	public int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	
	public BufferedImage[] right_player;
	public BufferedImage[] left_player;
	public BufferedImage[] up_player;
	public BufferedImage[] down_player;
	
	private boolean moved = false;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		right_player = new BufferedImage[4];
		left_player = new BufferedImage[4];
		up_player = new BufferedImage[4];
		down_player = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			right_player[i] = Game.spritesheet.getSprite(32 + (i*16), 23, 16, 22);
		}
		for(int i = 0; i < 4; i++) {
			left_player[i] = Game.spritesheet.getSprite(96 + (i*16), 23, 16, 22);
		}
		for(int i = 0; i < 4; i++) {
			up_player[i] = Game.spritesheet.getSprite(96 + (i*16), 0, 16, 23);
		}
		for(int i = 0; i < 4; i++) {
			down_player[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 22);
		}
	}
	
	public void tick() {
		moved = false;
		if(right && World.isFree((int)(x+speed), this.getY())) {
			moved = true;
			dir = dir_right;
			x += speed;
		}
		else if(left && World.isFree((int)(x-speed), this.getY())) {
			moved = true;
			dir = dir_left;
			x -= speed;
		}
			
		if(up && World.isFree(this.getX(), (int)(y-speed))) {
			moved = true;
			dir = dir_up;
			if(right == true && World.isFree((int)(x+speed), (int)(y-speed)) == true) {
				y -= (speed/2);
			}else if(left == true && World.isFree((int)(x-speed), (int)(y-speed)) == true) {
				y -= (speed/2);
			}else {
				y -= speed;
			}
		}
		else if(down && World.isFree(this.getX(), (int)(y+speed))) {
			moved = true;
			dir = dir_down;
			if(right == true && World.isFree((int)(x+speed), (int)(y+speed)) == true) {
				y += (speed/2);
			}else if(left == true && World.isFree((int)(x-speed), (int)(y+speed)) == true) {
				y += (speed/2);
			}else {
				y += speed;
			}
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}else {
			index = 0;
		}
		
		Camera.x = Camera.clamp((this.getX() - (Game.WIDTH/2)), 0, (World.WIDTH*16 - Game.WIDTH));
		Camera.y = Camera.clamp((this.getY() - (Game.HEIGHT/2)), 0, (World.HEIGHT*16 - Game.HEIGHT));
		
	}
	
	public void render(Graphics g) {
		if(dir == dir_right) {
			g.drawImage(right_player[index], this.getX() - Camera.x, this.getY() - Camera.y, 16, 22, null);
		}else if(dir == dir_left) {
			g.drawImage(left_player[index], this.getX() - Camera.x, this.getY() - Camera.y, 16, 22, null);
		}else if(dir == dir_up) {
			g.drawImage(up_player[index], this.getX() - Camera.x, this.getY() - Camera.y, 16, 23, null);
		}else if(dir == dir_down) {
			g.drawImage(down_player[index], this.getX() - Camera.x, this.getY() - Camera.y, 16, 22, null);
		}
		
		
	}

}
