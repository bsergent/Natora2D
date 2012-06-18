/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIButton extends GUI {

    private Texture texture;
    private int hovered = 0;
    public int actionId;
    
    public GUIButton (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, int actionId, Screen screen, String texName, String texExt) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, screen);
        this.texture = ResourceLoader.loadImage(texName, texExt);
        this.actionId = actionId;
    }
    
    public void delete() {
        
    }
    
    public void draw() {
        if (visible) {
            texture.bind();

            glBegin(GL_QUADS);
            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Left
            glVertex2i(posX, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+(hovered*picHeight), texture.getImageHeight()));  // Upper-Right
            glVertex2i(posX+width, posY);

            glTexCoord2f(Renderer.getTextureFloat(picX+picWidth, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Right
            glVertex2i(posX+width, posY+height);

            glTexCoord2f(Renderer.getTextureFloat(picX, texture.getImageWidth()), Renderer.getTextureFloat(picY+picHeight+(hovered*picHeight), texture.getImageHeight()));  // Lower-Left
            glVertex2i(posX, posY+height);
            glEnd();
        }
    }
    
    public void checkMouse() {
        if (Mouse.getX()>posX&&Mouse.getX()<(posX+width) && (Natora.screenHeight-Mouse.getY())>posY&&(Natora.screenHeight-Mouse.getY())<(posY+height)) {
            hovered = 1;
            if (Mouse.isButtonDown(0)) {
                screen.actionPerformed(id);
            }
        } else {
            hovered = 0;
        }
    }
    
}
