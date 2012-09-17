/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.Font;
import java.util.Random;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import java.awt.Rectangle;
/**
 *
 * @author Ben Sergent V
 */
public abstract class Tile {
    
    protected int picX, picY, picWidth, picHeight;
    protected static Texture texture;
    protected static Random randGen = new Random();
    protected static World world;
    protected boolean onScreen = true;
    protected Rectangle rect;
    protected int index;
    private boolean hover = false;
    
    public Tile(int index, int picX, int picY, World world, Rectangle rect) {
        this.picX=picX;
        this.picY=picY;
        this.picWidth=32;
        this.picHeight=32;
        this.world=world;
        this.rect=rect;
        this.index=index;
          
    }
    
    
    
    public abstract void breakTile();
    
    public abstract int getHardness();

    public abstract String getName();
    
    public abstract boolean isSolid();
    
    public void delete() {
        world.tiles[index] = new TileDirt(world, new Rectangle(rect.x, rect.y, 32, 32), index);
    }
    
    public void hover() {
        this.hover = true;
    }
    
    public void draw() {
        onScreen = ViewPort.checkOnScreen(this);
        if (onScreen) {
            if (texture == null) {
                texture = ResourceLoader.loadImage("Tiles", ".PNG");
            }
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Left
            glVertex2i(ViewPort.getViewX(rect.x), ViewPort.getViewY(rect.y));

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Right
            glVertex2i(ViewPort.getViewX(rect.x) + rect.width, ViewPort.getViewY(rect.y));

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Right
            glVertex2i(ViewPort.getViewX(rect.x)+rect.width, ViewPort.getViewY(rect.y)+rect.height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Left
            glVertex2i(ViewPort.getViewX(rect.x), ViewPort.getViewY(rect.y)+rect.height);
            glEnd();
            
            if (this.hover) {
                glDisable(GL_TEXTURE_2D);
                glColor4f(0.3f,0.3f,0.0f,0.3f);
                glBegin(GL_QUADS);
                glVertex2i(ViewPort.getViewX(rect.x), ViewPort.getViewY(rect.y));
                glVertex2i(ViewPort.getViewX(rect.x)+rect.width, ViewPort.getViewY(rect.y));
                glVertex2i(ViewPort.getViewX(rect.x)+rect.width, ViewPort.getViewY(rect.y)+rect.height);
                glVertex2i(ViewPort.getViewX(rect.x), ViewPort.getViewY(rect.y)+rect.height);
                glEnd();
                glColor4f(1.0f,1.0f,1.0f,1.0f);
                glEnable(GL_TEXTURE_2D);
                hover = false;
            }
        }
    }
}
