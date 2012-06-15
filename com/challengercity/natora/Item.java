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

    private int countdown = 60;
    
    public Item(float x, float y, int picX1, int picY1, int picX2, int picY2) {
        super(x,y,picX1,picY1,picX2,picY2);
    }
    
}
