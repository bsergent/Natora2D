/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Item extends Entity {

    private int countdown = 60000;
    
    public Item(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, EnumGameState gs) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, gs);
    }
    
    public void draw() {
        
    }
    
}
