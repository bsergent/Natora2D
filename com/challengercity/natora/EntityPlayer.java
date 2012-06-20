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
    public boolean performingAction = false;
    private Texture texture2;
    public int wealth = 0;
    public int items = 0;
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
    
    public void performAction() {
        performingAction = true;
        switch (dir) {
            case 0:
                for (int i = 0; i<screen.renderTileList.size(); i++) {
                    if (this.offsetIntersects(screen.renderTileList.get(i), 16, 0)) {
                        screen.renderTileList.get(i).breakTile();
                    }
                }
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.offsetIntersects(ro, 16, 0)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.kill(this);
                    }
                }
                break;
            case 1:
                for (int i = 0; i<screen.renderTileList.size(); i++) {
                    if (this.offsetIntersects(screen.renderTileList.get(i), 0, -16)) {
                        screen.renderTileList.get(i).breakTile();
                    }
                }
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.offsetIntersects(ro, 0, -16)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.kill(this);
                    }
                }
                break;
            case 2:
                for (int i = 0; i<screen.renderTileList.size(); i++) {
                    if (this.offsetIntersects(screen.renderTileList.get(i), -16, 0)) {
                        screen.renderTileList.get(i).breakTile();
                    }
                }
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.offsetIntersects(ro, -16, 0)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.kill(this);
                    }
                }
                break;
            case 3:
                for (int i = 0; i<screen.renderTileList.size(); i++) {
                    if (this.offsetIntersects(screen.renderTileList.get(i), 0, 16)) {
                        screen.renderTileList.get(i).breakTile();
                    }
                }
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.offsetIntersects(ro, 0, 16)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.kill(this);
                    }
                }
                break;
        }
    }
    
    public void updateMovement(long delta) {
        if (posX+velX*delta>0&&posX+velX*delta+width<Natora.screenWidth && posY+velY*delta>0&&posY+velY*delta+height<Natora.screenHeight&&!performingAction) {
            boolean clear = true;
            for (int i = 0; i<screen.renderTileList.size(); i++) {
                if (this.offsetIntersects((RenderableObject) screen.renderTileList.get(i), (int)(velX*delta), (int)(velY*delta))) {
                    clear = false;
                }
            }
            if (clear) {
                posX = posX + (int)(velX*delta);
                posY = posY + (int)(velY*delta);
            }
        }
        if (velX==0&&velY==0) {
            ani=0;
        }
    }
    
    public void draw() {
        if (font == null) { // Load Resources
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, 12),true);
            }
        if (texture == null) {
            texture = ResourceLoader.loadImage("EntityPlayer", ".PNG");
        }
        if (texture2 == null) {
            texture2 = ResourceLoader.loadImage("Action", ".PNG");
        }
        
        /* Main Animation and Texture */
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
        
        /* Action Animation */
        if (performingAction) {
            texture2.bind();
            glBegin(GL_QUADS);
            switch (dir) {
            case 0:
                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Left
                glVertex2i(posX+16, posY);

                glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Right
                glVertex2i(posX+width+16, posY);

                glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Right
                glVertex2i(posX+width+16, posY+height);

                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Left
                glVertex2i(posX+16, posY+height);
                break;
            case 1:
                glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Left
                glVertex2i(posX, posY-20);

                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Right
                glVertex2i(posX+width, posY-20);

                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Right
                glVertex2i(posX+width, posY+height-20);

                glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Left
                glVertex2i(posX, posY+height-20);
                break;
            case 2:
                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Left
                glVertex2i(posX-16, posY);

                glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Right
                glVertex2i(posX+width-16, posY);

                glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Right
                glVertex2i(posX+width-16, posY+height);

                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Left
                glVertex2i(posX-16, posY+height);
                break;
            case 3:
                glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Left
                glVertex2i(posX, posY+20);

                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Right
                glVertex2i(posX+width, posY+20);

                glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Right
                glVertex2i(posX+width, posY+height+20);

                glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Left
                glVertex2i(posX, posY+height+20);
                break;
            }
            glEnd();
        }
        
        /* Name String */
        int strPosX = posX+width/2-(font.getWidth(username)/2);
        int strPosY = posY+height+2;
        font.drawString(strPosX, strPosY, username);
        
        /* Cycle Animation */
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
