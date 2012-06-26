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
    
    private byte dir = 0;
    private byte ani = 0;
    protected TrueTypeFont font;
    private int aniDelay = 10;
    public boolean performingAction = false;
    private Texture texture2;
    private int maxHealth = 100;
    public int health = 100;
    private Texture healthTexture;
    public int wealth = 0;
    public int items = 10;
    private String username;
    private ScreenGame screen;

    public EntityPlayer(int x, int y, int width, int height, Screen sc, String username) {
        super(x, y, width, height, 0, 0, 32, 32, sc);
        this.username = username;
        this.screen = (ScreenGame) sc;
    }
    
    public String getUsername() {
        return username;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(byte dir) {
        this.dir = dir;
    }

    public int getAni() {
        return ani;
    }

    public void setAni(byte ani) {
        this.ani = ani;
    }
    
    public Rectangle getHitbox(){
        hitbox.setBounds(posX+4, posY+4, width-8, height-8);
        return hitbox;
    }
    
    public void performAction1() { // Attack/Mine
        performingAction = true;
        switch (dir) {
            case 0:
                screen.world.breakTile(posX+16+8, posY+8, width-16, height-16);
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.intersectsOffset(ro, 16, 0)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.health=em.health-5;
                    }
                }
                break;
            case 1:
                screen.world.breakTile(posX+8, posY-16+8, width-16, height-16);
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.intersectsOffset(ro, 0, -16)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.health=em.health-5;
                    }
                }
                break;
            case 2:
                screen.world.breakTile(posX-16+8, posY+8, width-16, height-16);
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.intersectsOffset(ro, -16, 0)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.health=em.health-5;
                    }
                }
                break;
            case 3:
                screen.world.breakTile(posX+8, posY+16+8, width-16, height-16);
                for (int i = 0; i<screen.renderEntityList.size(); i++) {
                    RenderableObject ro = screen.renderEntityList.get(i);
                    if (this.intersectsOffset(ro, 0, 16)&&ro instanceof EntityMonster) {
                        EntityMonster em = (EntityMonster) ro;
                        em.health=em.health-5;
                    }
                }
                break;
        }
    }
    
    public void performAction2() { // Place/Use
        boolean clear = true;
        switch (dir) {
            case 0:
                if (items>0) {
                    screen.world.placeTile(posX+48, posY+16);
                }
                break;
            case 1:
                if (items>0) {
                    screen.world.placeTile(posX+16, posY-32);
                }
                break;
            case 2:
                if (items>0) {
                    screen.world.placeTile(posX-32, posY+16);
                }
                break;
            case 3:
                if (items>0) {
                    screen.world.placeTile(posX+16, posY+48);
                }
                break;
        }
    }
    
    public void update(long delta) {
        if (posX+velX*delta>0&&posX+velX*delta+width<Natora.screenWidth && posY+velY*delta>0&&posY+velY*delta+height<Natora.screenHeight&&!performingAction) {
            boolean clear = screen.world.intersectsOffset(this, (int)(velX*delta), (int)(velY*delta));
            if (clear) {
                posX = posX + (int)(velX*delta);
                posY = posY + (int)(velY*delta);
                screen.world.navigateWorld(-velX, -velY, delta);
            }
        }
        if (velX==0&&velY==0) {
            ani=0;
        }
        
        if (health<=0) {
            this.delete();
            if (screen.nt.thePlayer.equals(this)) {
                Renderer.removeFromRenderList(screen.nt.currentScreen);
                screen.nt.currentScreen = new ScreenMenu(screen.nt);
            }
        }
        /* Check direction */
        if (velY>0) {
            dir=3;
        } else if (velY<0) {
            dir=1;
        }
        if (velX>0) {
            dir=0;
        } else if (velX<0){
            dir=2;
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
