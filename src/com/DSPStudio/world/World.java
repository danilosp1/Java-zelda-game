package com.DSPStudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.DSPStudio.entities.Arrow;
import com.DSPStudio.entities.Enemy;
import com.DSPStudio.entities.Entity;
import com.DSPStudio.entities.Life;
import com.DSPStudio.entities.Rock;
import com.DSPStudio.entities.Weapon;
import com.DSPStudio.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH;
	public static int HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {	
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					
					tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR); //chão
					
					if(pixelAtual == 0xFF000000) { //preto / chão
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF) { //branco / parede
						tiles[xx +(yy*WIDTH)] = new WallTile(xx*16, yy*16, Tile.TILE_WALL);
					}else if(pixelAtual == 0xFFFF00DC) { //rosa / vida
						Game.entities.add(new Life(xx*16, yy*16, 13, 11, Entity.LIFE_EN));
					}else if(pixelAtual == 0xFFFFD800) { //amarelo / munição de arco
						Game.entities.add(new Arrow(xx*16, yy*16, 14, 13, Entity.ARROW_EN));
					}else if(pixelAtual == 0xFFFF6A00) { //laranja / arco
						Game.entities.add(new Weapon(xx*16, yy*16, 10, 14, Entity.WEAPON_EN));
					}else if(pixelAtual == 0xFFFF0000) { //vermelho / inimigo
						Enemy en = new Enemy(xx*16, yy*16, 16, 21, Game.spritesheet.getSprite(32, 59, 16, 21));
						Game.entities.add(en);
						Game.enemies.add(en);
					}else if(pixelAtual == 0xFF0020D8) { //azul / player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFF7F3300) { //marrom / pedra
						Game.entities.add(new Rock(xx*16, yy*16, 32, 26, Rock.spriteRock));
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int nextX, int nextY) {
		int x1 = nextX / TILE_SIZE;
		int y1 = nextY / TILE_SIZE;
		
		int x2 = (nextX+TILE_SIZE-1) / TILE_SIZE;
		int y2 = nextY / TILE_SIZE;
		
		int x3 = nextX / TILE_SIZE;
		int y3 = (nextY+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (nextX+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (nextY+TILE_SIZE-1) / TILE_SIZE;
		
		return !(tiles[x1 + (y1*World.WIDTH)] instanceof WallTile ||
				tiles[x2 + (y2*World.WIDTH)] instanceof WallTile ||
				tiles[x3 + (y3*World.WIDTH)] instanceof WallTile ||
				tiles[x4 + (y4*World.WIDTH)] instanceof WallTile); //verificando se o pixel é uma instancia de walltile
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
