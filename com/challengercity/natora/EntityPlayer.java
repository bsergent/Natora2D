/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
/**
 *
 * @author Ben Sergent V
 */
public class EntityPlayer extends Entity {
    
    private int dir = 0;
    private int ani = 0;
    private int aniDelay = 15;
    private String username;

    public EntityPlayer(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen sc, String username) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, sc);
        this.username = username;
        System.out.println("[Entity] Entity created - "+x+","+y+" - "+"Player - "+username);
    }
    
    public String getUsername() {
        return username;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getAni() {
        return ani;
    }

    public void setAni(int ani) {
        this.ani = ani;
    } 
    
    public void draw() {
        if (texture == null) {
            texture = ResourceLoader.loadImage("EntityPlayer", ".PNG");
        }
        texture.bind();
        
        glBegin(GL_QUADS);
        glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth), texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight), texture.getImageHeight()));  // Upper-Left
        glVertex2i(posX, posY);

        glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth)+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight), texture.getImageHeight()));  // Upper-Right
        glVertex2i(posX+width, posY);

        glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth)+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight)+picHeight, texture.getImageHeight()));  // Lower-Right
        glVertex2i(posX+width, posY+height);

        glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth), texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight)+picHeight, texture.getImageHeight()));  // Lower-Left
        glVertex2i(posX, posY+height);
        glEnd();
        
        if (aniDelay<=0) {
            aniDelay=15;
            if (ani>=4) {
                ani=0;
            }
            ani++;
        }
        aniDelay--;
    }
    
}