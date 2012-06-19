/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIImage extends GUI {

    Texture texture;
    
    public GUIImage (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen screen, String texName, String texExt) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, screen);
        this.texName=texName;
        this.texExt=texExt;
    }
    
    public void delete() {
        
    }
    
    public void draw() {
        if (visible) {
            if (texture == null) {
            texture = ResourceLoader.loadImage(texName, texExt);
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
        }
    }
    
    public void checkMouse() {
    }
    
}
