/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
import java.util.Random;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class EntityMonster extends Entity {
    
    private int dir = 0;
    private int ani = 0;
    private int aniDelay = 15;
    private int movementChangeDelay = 0;
    private int maxHealth = 20;
    public int health = 20;
    private Texture healthTexture;
    private Random randGen = new Random();
    

    public EntityMonster(int x, int y, int width, int height, Screen sc) {
        super(x, y, width, height, 0, 0, 16, 16, sc);
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
    
    public void update(long delta) { // Fix from getting stuck at borders, check future bounds before going
        if (movementChangeDelay<=0) {
            if (randGen.nextBoolean()) {
                if (randGen.nextInt(3)==0) {
                    velX = randGen.nextFloat()-0.5f;
                    if (velX>=0.3f) {
                        velX = 0.3f;
                    }
                    if (velX<=-0.3f) {
                        velX = -0.3f;
                    }
                } else {
                    velX = 0;
                    dir=randGen.nextInt(3);
                }
                velY = 0;
                if (velX>0) {
                    dir=0;
                } else if (velX<0){
                    dir=2;
                }
            } else {
                if (randGen.nextInt(3)==0) {
                    velY = randGen.nextFloat()-0.5f;
                    if (velY>=0.3f) {
                        velY=0.3f;
                    }
                    if (velY <=-0.3f) {
                        velY=-0.3f;
                    }
                } else {
                    velY = 0;
                    dir=randGen.nextInt(3);
                }
                velX = 0;
                if (velY>0) {
                    dir=3;
                } else if (velY<0) {
                    dir=1;
                }
            }
            movementChangeDelay=60;
        }
        if (posX+velX*delta>0&&posX+velX*delta+width<Natora.screenWidth && posY+velY*delta>0&&posY+velY*delta+height<Natora.screenHeight) { // Check screen boundries
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
        movementChangeDelay--;
        if (velX==0&&velY==0) {
            ani=0;
        }

        for (int i = 0; i<screen.renderTileList.size(); i++) {
            if (this.intersects((RenderableObject) screen.renderTileList.get(i))) {
                delete();
            }
        }
          
        if (health<=0) {
            this.kill();
        }  
    }
    
    public void draw() {
        if (texture == null) {
            texture = ResourceLoader.loadImage("EntityMonster", ".PNG");
        }
        if (healthTexture == null) {
            healthTexture = ResourceLoader.loadImage("HealthBar", ".PNG");
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
