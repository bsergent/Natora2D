/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class Item extends Entity {

    private int countdown = 300000; // 5 minutes
    
    public Item(int x, int y, int picX, int picY, String name, Screen sc) {
        super(x, y, 16, 16, picX, picY, 16, 16, sc);
    }
    
    public void draw() {
        if (texture == null) {
            texture = ResourceLoader.loadImage("Items", ".PNG");
        }
        texture.bind();
        
        glBegin(GL_QUADS);
        glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Left
        glVertex2i(posX, posY);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY, texture.getImageHeight()));  // Upper-Right
        glVertex2i(posX+width, posY);

        glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Right
        glVertex2i(posX+width, posY+height);

        glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight, texture.getImageHeight()));  // Lower-Left
        glVertex2i(posX, posY+height);
        glEnd();
        
        if (countdown <= 0) {
            delete();
        } else {
            countdown = countdown-(int)Natora.lastDelta;
        }
    }
    
    public void update(long delta) {
        for (int i = 0; i<screen.renderEntityList.size(); i++) {
            if (this.offsetIntersects((RenderableObject) screen.renderEntityList.get(i), (int)(velX*delta), (int)(velY*delta))) {
                if (screen.renderEntityList.get(i) instanceof EntityPlayer) {
                    EntityPlayer ep = (EntityPlayer) screen.renderEntityList.get(i);
                    ep.items++;
                    this.delete();
                }
            }
        }
    }
    
}
