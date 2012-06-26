/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;
import java.awt.Rectangle;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class World {

    private final int worldTilesWidth = 200;
    private final int worldTilesHeight = 200;
    private final int PAN_RIGHT = 0, PAN_UP = 1, PAN_LEFT = 2, PAN_DOWN = 3;
    public final int tileCount = worldTilesWidth*worldTilesHeight; // 250x250 Tiles
    public Tile[] tiles = new Tile[tileCount];
    public ScreenGame screen;

    public World(ScreenGame screen) {
        this.screen = screen;
    }
    
    public void generate() {
        Gen.genMain(this);
        Gen.genTunnels(this);
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
        return sx + (worldTilesWidth * sy);
    }
    
    public boolean intersects(RenderableObject ro) { // Checks if clear
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].rect.intersects(new Rectangle(ro.getHitbox())) && tiles[i].isSolid) {
                return false;
            }
        }
        return true;
    }
    
    public boolean intersectsOffset(RenderableObject ro, int offsetX, int offsetY) { // Checks if offset is clear
        Rectangle offsetHitbox = new Rectangle(ro.getHitbox().x+offsetX, ro.getHitbox().y+offsetY, ro.getHitbox().width, ro.getHitbox().height);
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].rect.intersects(offsetHitbox) && tiles[i].isSolid) {
                return false;
            }
        }
        return true;
    }
    
    public void breakTile(int x, int y, int width, int height) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].rect.intersects(new Rectangle(x, y, width, height)) && tiles[i].isSolid) {
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
    
    public void draw() {
        for (Tile tile: tiles) {
            tile.draw();
        }
    }
    
    public void navigateWorld(float velX, float velY, long delta) {
        int dir = 0;
        if (velY>0) {
            dir=3;
        } else if (velY<0) {
            dir=1;
        }
        if (velX>0) {
            dir=0;
        } else if (velX<0){
            dir=2;
        }
        switch (dir) {
            case PAN_RIGHT:
                for (Tile tile: tiles) {
                    tile.rect.x = tile.rect.x + (int)(velX*delta);
                }
                for (Entity ent: screen.renderEntityList) {
                    ent.posX = ent.posX + (int)(velX*delta);
                }
                break;
            case PAN_UP:
                for (Tile tile: tiles) {
                    tile.rect.y = tile.rect.y + (int)(velY*delta);
                }
                for (Entity ent: screen.renderEntityList) {
                    ent.posY = ent.posY + (int)(velY*delta);
                }
                break;
            case PAN_LEFT:
                for (Tile tile: tiles) {
                    tile.rect.x = tile.rect.x + (int)(velX*delta);
                }
                for (Entity ent: screen.renderEntityList) {
                    ent.posX = ent.posX + (int)(velX*delta);
                }
                break;
            case PAN_DOWN:
                for (Tile tile: tiles) {
                    tile.rect.y = tile.rect.y + (int)(velY*delta);
                }
                for (Entity ent: screen.renderEntityList) {
                    ent.posY = ent.posY + (int)(velY*delta);
                }
                break;
        }
    }
    
}
