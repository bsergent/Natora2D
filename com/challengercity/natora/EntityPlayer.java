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
    private boolean hover = false;

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
        switch (dir) {
            case 0:
                if (items>0) {
                    screen.world.placeTile(posX+56, posY+16);
                }
                break;
            case 1:
                if (items>0) {
                    screen.world.placeTile(posX+16, posY-28);
                }
                break;
            case 2:
                if (items>0) {
                    screen.world.placeTile(posX-28, posY+16);
                }
                break;
            case 3:
                if (items>0) {
                    screen.world.placeTile(posX+16, posY+56);
                }
                break;
        }
    }
    
    public void toggleHover() {
        if (hover) {
            hover = false;
        } else {
            hover = true;
        }
    }
    
    public void update(long delta) {
        if (performingAction) {
            if (velY>0) {
                velY=0.15f;
            } else if (velY<0) {
                velY=-0.15f;
            }
            if (velX>0) {
                velX=0.15f;
            } else if (velX<0){
                velX=-0.15f; 
            }
        }
        boolean clear = screen.world.intersectsOffset(this, (int)(velX*delta), (int)(velY*delta));
        if (clear) {
            posX = posX + (int)(velX*delta);
            posY = posY + (int)(velY*delta);
            //screen.world.navigateWorld(-velX, -velY, delta);
            ViewPort.centerView(posX+(width/2), posY+(height/2));
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
        
        /* Check hovered tile */
        if (hover) {
            switch (dir) {
                case 0:
                    if (items>0) {
                        screen.world.hoverTile(posX+56, posY+16);
                    }
                    break;
                case 1:
                    if (items>0) {
                        screen.world.hoverTile(posX+16, posY-28);
                    }
                    break;
                case 2:
                    if (items>0) {
                        screen.world.hoverTile(posX-28, posY+16);
                    }
                    break;
                case 3:
                    if (items>0) {
                        screen.world.hoverTile(posX+16, posY+56);
                    }
                    break;
            }
        }
    }
    
    public void draw() {
        onScreen = ViewPort.checkOnScreen(this);
        if (onScreen) {
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
            glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY));

            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth)+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight), texture.getImageHeight()));  // Upper-Right
            glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY));

            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth)+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight)+picHeight, texture.getImageHeight()));  // Lower-Right
            glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)+height);

            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth), texture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight)+picHeight, texture.getImageHeight()));  // Lower-Left
            glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)+height);
            glEnd();

            healthTexture.bind();
            glBegin(GL_QUADS);
            glTexCoord2f((float)health/maxHealth, 0.0f);  // Upper-Left
            glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)-4);

            glTexCoord2f((float)health/maxHealth-Renderer.getTextureFloat(1, healthTexture.getImageHeight()), 0.0f);  // Upper-Right
            glVertex2i((int)(ViewPort.getViewX(posX)+width/((float)maxHealth/health)), ViewPort.getViewY(posY)-4);

            glTexCoord2f((float)health/maxHealth-Renderer.getTextureFloat(1, healthTexture.getImageHeight()), Renderer.getTextureFloat(1, healthTexture.getImageHeight()));  // Lower-Right
            glVertex2i((int)(ViewPort.getViewX(posX)+width/((float)maxHealth/health)), ViewPort.getViewY(posY)-1);

            glTexCoord2f((float)health/maxHealth, Renderer.getTextureFloat(1, healthTexture.getImageHeight()));  // Lower-Left
            glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)-1);
            glEnd();

            /* Action Animation */
            if (performingAction) {
                texture2.bind();
                glBegin(GL_QUADS);
                switch (dir) {
                case 0:
                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Left
                    glVertex2i(ViewPort.getViewX(posX)+16, ViewPort.getViewY(posY));

                    glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Right
                    glVertex2i(ViewPort.getViewX(posX)+width+16, ViewPort.getViewY(posY));

                    glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Right
                    glVertex2i(ViewPort.getViewX(posX)+width+16, ViewPort.getViewY(posY)+height);

                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Left
                    glVertex2i(ViewPort.getViewX(posX)+16, ViewPort.getViewY(posY)+height);
                    break;
                case 1:
                    glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Left
                    glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)-20);

                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(0, texture2.getImageHeight()));  // Upper-Right
                    glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)-20);

                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Right
                    glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)+height-20);

                    glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Lower-Left
                    glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)+height-20);
                    break;
                case 2:
                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Left
                    glVertex2i(ViewPort.getViewX(posX)-16, ViewPort.getViewY(posY));

                    glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Right
                    glVertex2i(ViewPort.getViewX(posX)+width-16, ViewPort.getViewY(posY));

                    glTexCoord2f(Renderer.getTextureFloat(64, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Right
                    glVertex2i(ViewPort.getViewX(posX)+width-16, ViewPort.getViewY(posY)+height);

                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Left
                    glVertex2i(ViewPort.getViewX(posX)-16, ViewPort.getViewY(posY)+height);
                    break;
                case 3:
                    glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Left
                    glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)+20);

                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(32, texture2.getImageHeight()));  // Upper-Right
                    glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)+20);

                    glTexCoord2f(Renderer.getTextureFloat(32, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Right
                    glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)+height+20);

                    glTexCoord2f(Renderer.getTextureFloat(0, texture2.getImageWidth()), Renderer.getTextureFloat(64, texture2.getImageHeight()));  // Lower-Left
                    glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY)+height+20);
                    break;
                }
                glEnd();
            }

            /* Name String */
            int strPosX = ViewPort.getViewX(posX)+width/2-(font.getWidth(username)/2);
            int strPosY = ViewPort.getViewY(posY)+height+2;
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
    
}
