/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class TileRock extends Tile {

    public TileRock(int x, int y, Screen screen) {
        super(x, y, 0, 0, "Rock", 5, screen);
          
    }
    
    public void breakTile() {
        screen.addToRenderList(new ItemRock(posX+(randGen.nextInt(width-16)), posY+(randGen.nextInt(height-16)), screen));
        delete();
    }
    
}
