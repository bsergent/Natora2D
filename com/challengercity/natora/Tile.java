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
    protected Random randGen = new Random();
    protected World world;
    protected String name;
    protected int hardness;
    protected boolean visible = true;
    protected Rectangle rect;
    protected boolean isSolid;
    protected int index;
    
    public Tile(int index, int picX, int picY, String name, int hardness, boolean isSolid, World world, Rectangle rect) {
        this.picX=picX;
        this.picY=picY;
        this.picWidth=32;
        this.picHeight=32;
        this.name=name;
        this.hardness=hardness;
        this.world=world;
        this.rect=rect;
        this.isSolid=isSolid;
        this.index=index;
          
    }
    
    public abstract void breakTile();
    
    public int getHardness() {
        return hardness;
    }

    public String getName() {
        return name;
    }
    
    public void delete() {
        world.tiles[index] = new TileDirt(world, new Rectangle(rect.x, rect.y, 32, 32), index);
    }
    
    public void draw() {
        if (visible) {
            if (texture == null) {
                texture = ResourceLoader.loadImage("Tiles", ".PNG");
            }
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Left
            glVertex2i(rect.x, rect.y);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Right
            glVertex2i(rect.x + rect.width, rect.y);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Right
            glVertex2i(rect.x+rect.width, rect.y+rect.height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Left
            glVertex2i(rect.x, rect.y+rect.height);
            glEnd();
        }
    }
}
