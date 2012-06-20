/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.Font;
import java.util.Random;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Ben Sergent V
 */
public abstract class Tile extends RenderableObject {
    
    protected int velX, velY;
    protected int picX, picY, picWidth, picHeight;
    protected Texture texture;
    protected Random randGen = new Random();
    protected Screen screen;
    protected String name;
    protected int hardness;
    
    public Tile(int x, int y, int picX, int picY, String name, int hardness, Screen screen) {
        
        this.posX=x;
        this.posY=y;
        this.width=32;
        this.height=32;
        this.picX=picX;
        this.picY=picY;
        this.picWidth=32;
        this.picHeight=32;
        this.name=name;
        this.hardness=hardness;
        this.screen=screen;
        this.id=screen.getRenderId();
          
    }
    
    public float getX() {
        return posX;
    }
    
    public float getY() {
        return posY;
    }
    
    public abstract void breakTile();
    
    public int getHardness() {
        return hardness;
    }

    public String getName() {
        return name;
    }
    
    public void delete() {
        screen.removeFromRenderList(this);
    }
    
    public void draw() {
        if (visible) {
            if (texture == null) {
                texture = ResourceLoader.loadImage("Tiles", ".PNG");
            }
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Left
            glVertex2i(posX, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Right
            glVertex2i(posX+width, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Right
            glVertex2i(posX+width, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Left
            glVertex2i(posX, posY+height);
            glEnd();
        }
    }
}
