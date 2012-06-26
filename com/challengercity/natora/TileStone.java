/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Rectangle;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class TileStone extends Tile {
    
    public TileStone(World world, Rectangle rect, int index) {
        super(index, 0, 0, "Stone", 5, true, world, rect);
          
    }
    
    public void breakTile() {
        world.screen.addToRenderList(new ItemStone(rect.x+(randGen.nextInt(rect.width-16)), rect.y+(randGen.nextInt(rect.height-16)), world.screen));
        delete();
    }
    
}
