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
    
    public GUIButton (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, EnumGameState gs, String texName, String texExt) {
        super(x, y, width, height, picX, picY, picWidth, picHeight, gs);
        this.texture = ResourceLoader.loadImage(texName, texExt);
    }
    
    public void delete() {
        
    }
    
    public void draw() {
        texture.bind();

        glBegin(GL_QUADS);
        glTexCoord2f(picX, picY+(hovered*picHeight));  // Upper-Left
        glVertex2i(posX, posY);

        glTexCoord2f(picX+picWidth, picY+(hovered*picHeight));  // Upper-Right
        glVertex2i(posX+width, posY);

        glTexCoord2f(picX+picWidth, picY+picHeight+(hovered*picHeight));  // Lower-Right
        glVertex2i(posX+width, posY+height);

        glTexCoord2f(picX, picY+picHeight+(hovered*picHeight));  // Lower-Left
        glVertex2i(posX, posY+height);
        glEnd();
    }
    
    public void checkMouse() {
        if (Mouse.getX()>posX&&Mouse.getX()<(posX+width) && (Natora.screenHeight-Mouse.getY())>posY&&(Natora.screenHeight-Mouse.getY())<(posY+height)) {
            System.out.println("[GUIButton] Hovered!");
            hovered = 1;
            if (Mouse.isButtonDown(0)) {
                System.out.println("[GUIButton] Clicked!");
                Natora.gs=EnumGameState.INGAME;
            }
        } else {
            hovered = 0;
        }
    }
    
}
