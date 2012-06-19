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
    private Random randGen = new Random();
    

    public EntityMonster(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen sc) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, sc);
        System.out.println("[Entity] Entity created - "+x+","+y+" - "+"Monster");
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
    
    public void move() {
        if (movementChangeDelay<=0) {
            if (randGen.nextBoolean()) {
                velX = randGen.nextInt(3)-1;
                velY = 0;
                if (velX>0) {
                    dir=0;
                } else if (velX<0){
                    dir=2;
                }
            } else {
                velY = randGen.nextInt(3)-1;
                velX = 0;
                if (velY>0) {
                    dir=3;
                } else if (velY<0) {
                    dir=1;
                }
            }
            movementChangeDelay=60;
        }
        if (posX+velX>0&&posX+velX+width<Natora.screenWidth && posY+velY>0&&posY+velY+height<Natora.screenHeight) { // Check screen boundries
            posX += velX;
            posY += velY;
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
            texture = ResourceLoader.loadImage("EntityMonster", ".PNG");
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
