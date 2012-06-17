/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V
 */

public abstract class Entity extends RenderableObject {
    
    protected int posX, posY, width, height, velX, velY, picX, picY, picWidth, picHeight;
    
    public Entity(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight) {
        
        this.posX=x;
        this.posY=y;
        this.picX=picX;
        this.picY=picY;
        this.picWidth=picWidth;
        this.picHeight=picHeight;
        this.width=width;
        this.height=height;
        this.id = Renderer.getNextRenderId();
        Renderer.addToRenderList(this);
        
    }

    // Getters and Setters
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public void delete() {
        Renderer.removeFromRenderList(this);
    }
    
    public abstract void draw();
    
    public int[] getPicDimensions() {
        int[] array = new int[4];
        array[0] = picX;
        array[1] = picY;
        array[2] = picWidth;
        array[3] = picHeight;
        return new int[0];
    }
    
}
