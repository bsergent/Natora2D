/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Ben Sergent V
 */

public abstract class Entity extends RenderableObject {
    
    protected int posX, posY, width, height, picX, picY, picWidth, picHeight;
    protected float velX, velY;
    protected Texture texture;
    protected Screen screen;
    
    public Entity(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen sc) {
        
        this.posX=x;
        this.posY=y;
        this.picX=picX;
        this.picY=picY;
        this.picWidth=picWidth;
        this.picHeight=picHeight;
        this.width=width;
        this.height=height;
        this.screen = sc;
        this.id = screen.getRenderId();
        
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

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public void move(long delta) {
        if (posX+velX*delta>0&&posX+velX*delta+width<Natora.screenWidth && posY+velY*delta>0&&posY+velY*delta+height<Natora.screenHeight) {
            posX = posX + (int)(velX*delta);
            posY = posY + (int)(velY*delta);
        }
    }
    
    public void delete() {
        screen.removeFromRenderList(this);
    }
    
    public void kill(Entity ent) {
        // Drop & killed by
        screen.removeFromRenderList(this);
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
