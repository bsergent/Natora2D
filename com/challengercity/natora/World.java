/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Rectangle;
import java.util.Random;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class World {

    public final int worldTilesWidth = 1000;
    public final int worldTilesHeight = 1000;
    private final int PAN_RIGHT = 0, PAN_UP = 1, PAN_LEFT = 2, PAN_DOWN = 3;
    public final int tileCount = worldTilesWidth*worldTilesHeight; // 250x250 Tiles
    public Tile[] tiles = new Tile[tileCount];
    public ScreenGame screen;
    private Random rand = new Random();

    public World(ScreenGame screen) {
        this.screen = screen;
    }
    
    public void generate() {
        System.out.println("[World-Gen] Generating World... ");
        Gen.genMain(this);
        System.out.println("[World-Gen] Finished Main ");
        Gen.genOres(this);
        Gen.genTunnels(this);
        System.out.println("[World-Gen] Finished Tunnels ");
        Gen.genSpawn(this);
        System.out.println("[World-Gen] Finished Spawn ");
        Gen.genEdgeBorder(this);
        System.out.println("[World-Gen] Finsihed Generating.");
    }
    
    public void spawnPlayer() {
        int middleTileX = tiles[tileCount/2+worldTilesWidth/2].rect.x;
        int middleTileY = tiles[tileCount/2+worldTilesWidth/2].rect.y;
        tiles[tileCount/2+worldTilesWidth/2].breakTile();
        //screen.addToRenderList(screen.nt.thePlayer = new EntityPlayer(middleTileX,middleTileY,32,32,screen,screen.nt.username));
        screen.addToRenderList(screen.nt.thePlayer = new EntityPlayer(Natora.screenWidth/2-16, Natora.screenHeight/2-16,32,32,screen,screen.nt.username));
        //ViewPort.centerView(middleTileX+16, middleTileY+16);
    }
    
    public void spawnMobs() {
//        if (rand.nextInt(1000)<=100) {
//            System.out.println("[World] Spawning Mobs...");
//            for (int num = 0; num <= rand.nextInt(9)+1; num++) {
//                int x = rand.nextInt(worldTilesWidth);
//                int y = rand.nextInt(worldTilesHeight);
//                if (!tiles[convertPixelCoordToArrayIndex(x*32,y*32)].isSolid()) {
//                    screen.addToRenderList(new EntityMonster(x*32,y*32,32,32,screen));
//                }
//            }
//        }
    }
    
    public int getWorldHeight() {
        return worldTilesHeight*32;
    }

    public int getWorldWidth() {
        return worldTilesWidth*32;
    }
    
    public int getXFromArrayIndex(int index) {
        return (index%worldTilesWidth)*32;
    }
    
    public int getYFromArrayIndex(int index) {
        return (index/worldTilesWidth)*32;
    }
    
    public int convertPixelCoordToArrayIndex(int x, int y) {
        int sx = x/32*32;
        int sy = y/32*32;
        if (sx+(worldTilesWidth*sy)>=0 && sx+(worldTilesWidth*sy)<=tileCount) {
            return sx + (worldTilesWidth * sy);
        } else {
            return 0;
        }
    }
    
    public boolean intersects(RenderableObject ro) { // Checks if clear
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].onScreen) {
                if (tiles[i].rect.intersects(new Rectangle(ro.getHitbox())) && tiles[i].isSolid()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean intersectsOffset(RenderableObject ro, int offsetX, int offsetY) { // Checks if offset is clear
        Rectangle offsetHitbox = new Rectangle(ro.getHitbox().x+offsetX, ro.getHitbox().y+offsetY, ro.getHitbox().width, ro.getHitbox().height);
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].onScreen) {
                if (tiles[i].rect.intersects(offsetHitbox) && tiles[i].isSolid()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void breakTile(int x, int y, int width, int height) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].rect.intersects(new Rectangle(x, y, width, height)) && tiles[i].isSolid()) {
                tiles[i].breakTile();
            }
        }
    }
    
    public void placeTile(int x, int y) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].rect.contains(x, y)) {
                tiles[i] = new TileStone(this, new Rectangle(tiles[i].rect.x, tiles[i].rect.y, 32, 32), i);
            }
        }
    }
    
    public void hoverTile(int x, int y) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].rect.contains(x, y)) {
                tiles[i].hover();
            }
        }
    }
    
    public void draw() {
        for (Tile tile: tiles) {
            tile.draw();
        }
        spawnMobs();
    }
}
