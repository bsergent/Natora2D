/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class World {

    private final int worldWidth = 32000;
    private final int worldHeight = 32000;
    public final Tile TILE_AIR,TILE_STONE,TILE_DIRT;
    public final int tileCount = 10000;
    private Tile[] tiles = new Tile[tileCount];

    public World() {
        TILE_AIR = new TileAir();
        TILE_STONE = new TileStone();
        TILE_DIRT = new TileDirt();
        Gen.genMain(this);
    }
    
    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldWidth() {
        return worldWidth;
    }
    
    public Tile getTile(int realX, int realY) {
        
    }
    
    public Tile getTile(int arrayIndex) {
        
    }
    
    public void setTile(int x, int y, Tile newTile) {
        
    }
    
    public void setTile(int arrayIndex, Tile newTile) {
        
    }
    
    public int convertTilePosToArrayPos(int tileX, int tileY) {
        return tileX*tileY;
    }
    
    public int[] convertArrayPosToTilePos(int arrayIndex) {
        return new int[2]; // TODO Not finished...
    }
    
    public int[] convertRealPosToTilePos(int realX, int realY) {
        int[] tempPos = new int[2];
        tempPos[0] = realX/32;
        tempPos[1] = realY/32;
        return tempPos;
    }
    
    public int[] convertTilePisToRealPos(int tileX, int tileY) {
        int[] tempPos = new int[2];
        tempPos[0] = tileX*32;
        tempPos[1] = tileY*32;
        return tempPos;
    }
    
}
