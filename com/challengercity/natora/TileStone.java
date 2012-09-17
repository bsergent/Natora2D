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
        super(index, 0, 0, world, rect);
          
    }
    
    public int getHardness() {
        return 5;
    }
    
    public String getName() {
        return "Stone";
    }
    
    public boolean isSolid() {
        return true;
    }
    
    public void breakTile() {
        world.screen.addToRenderList(new ItemStone(rect.x+(randGen.nextInt(rect.width-16)), rect.y+(randGen.nextInt(rect.height-16)), world.screen));
        delete();
    }
    
}
