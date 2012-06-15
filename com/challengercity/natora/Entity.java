/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V
 */

public class Entity {
    
    private float posX, posY, velX, velY;
    private int picX1, picY1, picX2, picY2;
    
    public Entity(float x, float y, int picX1, int picY1, int picX2, int picY2) {
        
        this.posX=x;
        this.posY=y;
        this.picX1=picX1;
        this.picY1=picY1;
        this.picX2=picX2;
        this.picY2=picY1;
        
    }
    
    public float getX() {
        return posX;
    }
    
    public float getY() {
        return posY;
    }
    
    public void setX(float newX) {
        posX = newX;
    }
    
    public void setY(float newY) {
        posY = newY;
    }
    
}
