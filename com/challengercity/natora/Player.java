/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V
 */
public class Player extends Entity {
    
    protected String username;

    public Player(float x, float y, int picX1, int picY1, int picX2, int picY2, String username) {
        super(x,y,picX1,picY1,picX2,picY2);
        this.username = username;
    }
    
    
    
}
