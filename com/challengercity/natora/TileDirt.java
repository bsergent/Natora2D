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
public class TileDirt extends Tile {

    public TileDirt(World world, Rectangle rect, int index) {
        super(index, 32, 0, "Dirt", 5, false, world, rect);
          
    }
    
    public void breakTile() {
    }
    
}
