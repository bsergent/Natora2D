/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class TileDirt extends Tile {

    public TileDirt(int x, int y, Screen screen) {
        super(x, y, 32, 0, "Dirt", 5, screen);
          
    }
    
    public void breakTile() {
        screen.addToRenderList(new ItemDirt(posX+(randGen.nextInt(width-16)), posY+(randGen.nextInt(height-16)), screen));
        delete();
    }
    
}
