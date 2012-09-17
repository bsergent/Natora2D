/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Rectangle;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
import java.util.Random;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class EntityMonster extends Entity {
    
    private int dir = 0;
    private byte ani = 0;
    private int aniDelay = 15;
    private int movementChangeDelay = 0;
    private int attackDelay = 0;
    private int maxHealth = 20;
    public int health = 20;
    private static Texture healthTexture;
    protected static Texture staticTexture;
    private Random randGen = new Random();
    private ScreenGame screen;
    

    public EntityMonster(int x, int y, int width, int height, Screen sc) {
        super(x, y, width, height, 0, 0, 16, 16, sc);
        this.screen = (ScreenGame) sc;
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

    public void setAni(byte ani) {
        this.ani = ani;
    }
    
    public Rectangle getHitbox(){
        hitbox.setBounds(posX+4, posY+4, width-8, height-8);
        return hitbox;
    }
    
    public void update(long delta) { // Fix from getting stuck at borders, check future bounds before going
        EntityPlayer playerToAttack = null;
        if (movementChangeDelay<=0) {
            
            for (int i = 0; i<screen.renderEntityList.size(); i++) {
                if (screen.renderEntityList.get(i).intersectsField(posX-128, posY-128, width+256, height+256) && screen.renderEntityList.get(i) instanceof EntityPlayer) {
                    playerToAttack = (EntityPlayer) screen.renderEntityList.get(i);
                }
            }
            
            if (playerToAttack != null && attackDelay<=0) { // A Player is Near
                
                if (playerToAttack.intersectsField(posX-8, posY-8, width+16, height+16)) { // Adjacent to Player
                    playerToAttack.health = playerToAttack.health-5;
                    attackDelay = 60;
                }
                
            } else {
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
                }
            }
            movementChangeDelay=60;
        }
        boolean clear = true;
        clear = this.screen.world.intersectsOffset(this, (int)(velX*delta), (int)(velY*delta));
        if (clear) {
            posX = posX + (int)(velX*delta);
            posY = posY + (int)(velY*delta);
            if (playerToAttack != null) {
                if (playerToAttack.intersectsField(posX-8,posY-160, 16, 320)) {
                    if (playerToAttack.posY>this.posY) {
                        velY = 0.3f;
                        velX = 0.0f;
                    } else if (playerToAttack.posY<this.posY) {
                        velY = -0.3f;
                        velX = 0.0f;
                    }
                } else {
                    if (playerToAttack.posX>this.posX) {
                        velX = 0.3f;
                        velY = 0.0f;
                    } else if (playerToAttack.posX<this.posX) {
                        velX = -0.3f;
                        velY = 0.0f;
                    }
                }
                if (playerToAttack.intersectsField(posX-160,posY-8, 320, 16)) {
                    if (playerToAttack.posX>this.posX) {
                        velX = 0.3f;
                        velY = 0.0f;
                    } else if (playerToAttack.posX<this.posX) {
                        velX = -0.3f;
                        velY = 0.0f;
                    }
                } else {
                    if (playerToAttack.posY>this.posY) {
                        velY = 0.3f;
                        velX = 0.0f;
                    } else if (playerToAttack.posY<this.posY) {
                        velY = -0.3f;
                        velX = 0.0f;
                    }
                }
                movementChangeDelay=1;
            }
        }
        movementChangeDelay--;
        attackDelay--;
        
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
        
        
        /* Check if still */
        if (velX==0&&velY==0) {
            ani=0;
        }

        /* Check if suffocating */
        if (!this.screen.world.intersects(this)) {
            delete();
        }
        
        /* Check if dead */
        if (health<=0) {
            this.kill();
        }  
    }
    
    public void draw() {
        onScreen = ViewPort.checkOnScreen(this);
        if (onScreen) {
            if (staticTexture == null) {
                staticTexture = ResourceLoader.loadImage("EntityMonster", ".PNG");
            }
            if (healthTexture == null) {
                healthTexture = ResourceLoader.loadImage("HealthBar", ".PNG");
            }

            staticTexture.bind();
            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth), staticTexture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight), staticTexture.getImageHeight()));  // Upper-Left
            glVertex2i(ViewPort.getViewX(posX), ViewPort.getViewY(posY));

            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth)+picWidth, staticTexture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight), staticTexture.getImageHeight()));  // Upper-Right
            glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY));

            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth)+picWidth, staticTexture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight)+picHeight, staticTexture.getImageHeight()));  // Lower-Right
            glVertex2i(ViewPort.getViewX(posX)+width, ViewPort.getViewY(posY)+height);

            glTexCoord2f(Renderer.getTextureFloat(picX+(ani*picWidth), staticTexture.getImageWidth()), Renderer.getTextureFloat(picY+(dir*picHeight)+picHeight, staticTexture.getImageHeight()));  // Lower-Left
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
    
}
