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
    
    public GUIImage (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, EnumGameState gs, String texName, String texExt) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, gs);
        this.texture = ResourceLoader.loadImage(texName, texExt);
    }
    
    public void delete() {
        
    }
    
    public void draw() {
        texture.bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);  // Upper-Left
        glVertex2i(posX, posY);

        glTexCoord2f(1, 0);  // Upper-Right
        glVertex2i(posX+width, posY);

        glTexCoord2f(1, 1);  // Lower-Right
        glVertex2i(posX+width, posY+height);

        glTexCoord2f(0, 1);  // Lower-Left
        glVertex2i(posX, posY+height);
        glEnd();
    }
    
    public void checkMouse() {
    }
    
}
