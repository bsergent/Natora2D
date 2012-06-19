/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class GUIText extends GUI {

    protected TrueTypeFont font;
    protected String label;
    protected int fontSize = 16;
    protected boolean centered;
    
    public GUIText (int x, int y, Screen screen, String text, int fontSize, boolean centered) {
        super(x, y, 0, 0, 1, 0, 128, 24, screen);
        this.label = text;
        this.fontSize = fontSize;
        this.centered = centered;
    }
    
    public void draw() {
        if (visible) {
            if (font == null) {
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, fontSize),true);
            }
            int strPosX = posX;
            int strPosY = posY;
            if (centered) {
                strPosX = posX+width/2-(font.getWidth(label)/2);
                strPosY = posY+height/2-(font.getHeight(label)/2);
            }
            font.drawString(strPosX, strPosY, label);
        }
    }
    
    public void delete() {
        
    }
    
    public void checkMouse() {
    }
}
