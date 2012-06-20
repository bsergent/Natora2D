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
public class EntityMenuBug extends Entity {
    
    private int dir = 0;
    private int ani = 0;
    private int xBounds1, yBounds1, xBounds2, yBounds2;
    private int aniDelay = 15;
    private int movementChangeDelay;
    private Random randGen = new Random();
    

    public EntityMenuBug(int x, int y, int width, int height, Screen sc, int xBounds1, int yBounds1, int xBounds2, int yBounds2) {
        super(x, y, width, height, 0, 0, 32, 32, sc);
        movementChangeDelay = randGen.nextInt(60);
        this.xBounds1=xBounds1;
        this.xBounds2=xBounds2;
        this.yBounds1=yBounds1;
        this.yBounds2=yBounds2;
        System.out.println("[Entity] Entity created - "+x+","+y+" - "+"MenuBug");
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
    
    public void updateMovement(long delta) { // Fix from getting stuck at borders, check future bounds before going
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
            movementChangeDelay=randGen.nextInt(60);
        }
        if (posX+velX*delta>xBounds1&&posX+velX*delta+width<xBounds2 && posY+velY*delta>yBounds1&&posY+velY*delta+height<yBounds2) { // Check screen boundries
            posX = posX + (int)(velX*delta);
            posY = posY + (int)(velY*delta);
//            for (int i = 0; i<screen.renderList.size(); i++) {
//                RenderableObject ro = screen.renderList.get(i);
//                if (this.intersects(ro)) {
//                    posX -= velX;
//                    posY -= velY;
//                }
//            }
        }
        movementChangeDelay--;
        if (velX==0&&velY==0) {
            ani=0;
        }
    }
    
    public void draw() {
        if (texture == null) {
            texture = ResourceLoader.loadImage("MenuBug", ".PNG");
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
