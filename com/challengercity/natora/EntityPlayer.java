/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.Font;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.*;
/**
 *
 * @author Ben Sergent V
 */
public class EntityPlayer extends Entity {
    
    private int dir = 0;
    private int ani = 0;
    protected TrueTypeFont font;
    private int aniDelay = 10;
    public int wealth = 0;
    private String username;

    public EntityPlayer(int x, int y, int width, int height, Screen sc, String username) {
        super(x, y, width, height, 0, 0, 32, 32, sc);
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
    
    public void updateMovement(long delta) {
        if (posX+velX*delta>0&&posX+velX*delta+width<Natora.screenWidth && posY+velY*delta>0&&posY+velY*delta+height<Natora.screenHeight) {
            boolean clear = true;
            for (int i = 0; i<screen.renderTileList.size(); i++) {
                if (this.futureIntersects((RenderableObject) screen.renderTileList.get(i), posX + (int)(velX*delta), posY + (int)(velY*delta))) {
                    clear = false;
                }
            }
            if (clear) {
                posX = posX + (int)(velX*delta);
                posY = posY + (int)(velY*delta);
            }
        }
        for (int i = 0; i<screen.renderEntityList.size(); i++) {
            RenderableObject ro = screen.renderEntityList.get(i);
            if (this.intersects(ro)&&ro instanceof EntityMonster) {
                EntityMonster em = (EntityMonster) ro;
                em.kill(this);
            }
        }
        if (velX==0&&velY==0) {
            ani=0;
        }
    }
    
    public void draw() {
        if (font == null) {
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, 12),true);
            }
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
        
        int strPosX = posX+width/2-(font.getWidth(username)/2);
        int strPosY = posY+height+2;
        font.drawString(strPosX, strPosY, username);
        
        
        if (aniDelay<=0) {
            aniDelay=10;
            if (ani>=4) {
                ani=0;
            }
            ani++;
        }
        aniDelay--;
    }
    
}
