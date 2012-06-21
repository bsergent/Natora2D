/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ItemCoin extends Item {
    
    public ItemCoin(int x, int y, Screen sc) {
        super(x, y, 0, 0, "Coin", sc);
    }
    
    public void update(long delta) {
        for (int i = 0; i<screen.renderEntityList.size(); i++) {
            if (this.intersectsOffset((RenderableObject) screen.renderEntityList.get(i), (int)(velX*delta), (int)(velY*delta))) {
                if (screen.renderEntityList.get(i) instanceof EntityPlayer) {
                    EntityPlayer ep = (EntityPlayer) screen.renderEntityList.get(i);
                    ep.wealth++;
                    this.delete();
                }
            }
        }
    }
    
}
