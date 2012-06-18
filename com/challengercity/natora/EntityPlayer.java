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
public class Player extends Entity {
    
    private int dir = 0;
    private int ani = 0;
    private int aniDelay = 20;
    private String username;

    public Player(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, EnumGameState gs, String username) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, gs);
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
    
    public float getTextureFloat(int num) {
        return ((float)num)/64;
    }
    
    public void draw() {
        if (texture == null) {
            texture = ResourceLoader.loadImage("EntityPlayer", ".PNG");
        }
        texture.bind();
        
        glBegin(GL_QUADS);
        glTexCoord2f(getTextureFloat(picX+(ani*picWidth)), getTextureFloat(picY+(dir*picHeight)));  // Upper-Left
        glVertex2i(posX, posY);

        glTexCoord2f(getTextureFloat(picX+(ani*picWidth)+picWidth), getTextureFloat(picY+(dir*picHeight)));  // Upper-Right
        glVertex2i(posX+width, posY);

        glTexCoord2f(getTextureFloat(picX+(ani*picWidth)+picWidth), getTextureFloat(picY+(dir*picHeight)+picHeight));  // Lower-Right
        glVertex2i(posX+width, posY+height);

        glTexCoord2f(getTextureFloat(picX+(ani*picWidth)), getTextureFloat(picY+(dir*picHeight)+picHeight));  // Lower-Left
        glVertex2i(posX, posY+height);
        glEnd();
        
        aniDelay--;
        if (aniDelay<=0) {
            aniDelay=20;
            if (ani>=4) {
                ani=0;
            }
            ani++;
        }
    }
    
}
