/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.Font;
import java.awt.Rectangle;
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
    private int maxHealth = 20;
    public int health = 20;
    private Texture healthTexture;
    public int wealth = 0;
    public int items = 10;
    private String username;

    public EntityPlayer(int x, int y, int width, int height, Screen sc, String username) {
        super(x, y, width, height, 0, 0, 32, 32, sc);
        this.username = username;
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
    
    public Rectangle getHitbox(){
        hitbox.setBounds(posX+4, posY, width-8, height);
        return hitbox;
    }
    
    public void performAction1() { // Attack/Mine
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
                        em.health=em.health-5;
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
                        em.health=em.health-5;
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
                        em.health=em.health-5;
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
                        em.health=em.health-5;
                    }
                }
                break;
        }
    }
    
    public void performAction2() { // Place/Use
        boolean clear = true;
        TileStone tr;
        switch (dir) {
            case 0:
                if (items>0) {
                    tr = new TileStone(posX+width, posY, screen);
                    clear = true;
                    for (int i = 0; i<screen.renderTileList.size(); i++) {
                        if (tr.intersects(screen.renderTileList.get(i))) {
                            clear = false;
                        }
                    }
                    for (int i = 0; i<screen.renderEntityList.size(); i++) {
                        if (tr.intersects(screen.renderEntityList.get(i))) {
                            clear = false;
                        }
                    }
                    if (clear) {
                        screen.addToRenderList(tr);
                        items--;
                    }
                }
                break;
            case 1:
                if (items>0) {
                    tr = new TileStone(posX, posY-32, screen);
                    clear = true;
                    for (int i = 0; i<screen.renderTileList.size(); i++) {
                        if (tr.intersects(screen.renderTileList.get(i))) {
                            clear = false;
                        }
                    }
                    for (int i = 0; i<screen.renderEntityList.size(); i++) {
                        if (tr.intersects(screen.renderEntityList.get(i))) {
                            clear = false;
                        }
                    }
                    if (clear) {
                        screen.addToRenderList(tr);
                        items--;
                    }
                }
                break;
            case 2:
                if (items>0) {
                    tr = new TileStone(posX-32, posY, screen);
                    clear = true;
                    for (int i = 0; i<screen.renderTileList.size(); i++) {
                        if (tr.intersects(screen.renderTileList.get(i))) {
                            clear = false;
                        }
                    }
                    for (int i = 0; i<screen.renderEntityList.size(); i++) {
                        if (tr.intersects(screen.renderEntityList.get(i))) {
                            clear = false;
                        }
                    }
                    if (clear) {
                        screen.addToRenderList(tr);
                        items--;
                    }
                }
                break;
            case 3:
                if (items>0) {
                    tr = new TileStone(posX, posY+height, screen);
                    clear = true;
                    for (int i = 0; i<screen.renderTileList.size(); i++) {
                        if (tr.intersects(screen.renderTileList.get(i))) {
                            clear = false;
                        }
                    }
                    for (int i = 0; i<screen.renderEntityList.size(); i++) {
                        if (tr.intersects(screen.renderEntityList.get(i))) {
                            clear = false;
                        }
                    }
                    if (clear) {
                        screen.addToRenderList(tr);
                        items--;
                    }
                }
                break;
        }
    }
    
    public void update(long delta) {
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
        if (healthTexture == null) {
            healthTexture = ResourceLoader.loadImage("HealthBar", ".PNG");
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
        
        healthTexture.bind();
        glBegin(GL_QUADS);
        glTexCoord2f((float)health/maxHealth, 0.0f);  // Upper-Left
        glVertex2i(posX, posY-4);

        glTexCoord2f((float)health/maxHealth-Renderer.getTextureFloat(1, healthTexture.getImageHeight()), 0.0f);  // Upper-Right
        glVertex2i((int)(posX+width/((float)maxHealth/health)), posY-4);

        glTexCoord2f((float)health/maxHealth-Renderer.getTextureFloat(1, healthTexture.getImageHeight()), Renderer.getTextureFloat(1, healthTexture.getImageHeight()));  // Lower-Right
        glVertex2i((int)(posX+width/((float)maxHealth/health)), posY-1);

        glTexCoord2f((float)health/maxHealth, Renderer.getTextureFloat(1, healthTexture.getImageHeight()));  // Lower-Left
        glVertex2i(posX, posY-1);
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
