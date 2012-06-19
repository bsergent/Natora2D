/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.*;
import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIButton extends GUI {

    private Texture texture;
    private TrueTypeFont font;
    private String label;
    private int fontSize = 16;
    private int hovered = 0;
    public int actionId;
    public int cooldown = 0;
    
    public GUIButton (int x, int y, int width, int height, int actionId, Screen screen, String text, int fontSize) {
        super(x, y, width, height, 1, 0, 128, 24, screen);
        this.actionId = actionId;
        this.label = text;
        this.fontSize = fontSize;
    }
    
    public void delete() {
        
    }
    
    public void draw() {
        if (visible) {
            if (texture == null) {
                texture = ResourceLoader.loadImage("GUIButton", ".PNG");
            }
            if (font == null) {
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, fontSize),true);
            }
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
            
            Color color = new Color(30,105,23);
            if (hovered==1) {
                color = new Color(60,209,46);
            }
            int strPosX = posX+width/2-(font.getWidth(label)/2);
            int strPosY = posY+height/2-(font.getHeight(label)/2);
            font.drawString(strPosX, strPosY, label);
        }
    }
    
    public void checkMouse() {
        if (Mouse.getX()>posX&&Mouse.getX()<(posX+width) && (Natora.screenHeight-Mouse.getY())>posY&&(Natora.screenHeight-Mouse.getY())<(posY+height) && cooldown<=0) {
            hovered = 1;
            if (Mouse.isButtonDown(0)) {
                screen.actionPerformed(actionId);
                cooldown=30;
            }
        } else {
            hovered = 0;
        }
        cooldown--;
    }
    
}
