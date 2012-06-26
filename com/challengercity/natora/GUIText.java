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

    protected static TrueTypeFont font48;
    protected static TrueTypeFont font36;
    protected static TrueTypeFont font24;
    protected static TrueTypeFont font16;
    protected static TrueTypeFont font12;
    protected String label;
    protected int fontSize;
    protected boolean centered;
    
    public GUIText (int x, int y, Screen screen, String text, int fontSize, boolean centered) {
        super(x, y, 0, 0, 1, 0, 128, 24, screen);
        this.label = text;
        this.centered = centered;
        this.fontSize = fontSize;
    }
    
    public void draw() {
        if (visible) {
            if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                loadFonts();
            }
            int strPosX = posX;
            int strPosY = posY;
            switch (fontSize) {
                case 48:
                    if (centered) {
                        strPosX = posX+width/2-(font48.getWidth(label)/2);
                        strPosY = posY+height/2-(font48.getHeight(label)/2);
                    }
                    font48.drawString(strPosX, strPosY, label);
                    break;
                case 36:
                    if (centered) {
                        strPosX = posX+width/2-(font36.getWidth(label)/2);
                        strPosY = posY+height/2-(font36.getHeight(label)/2);
                    }
                    font36.drawString(strPosX, strPosY, label);
                    break;
                case 24:
                    if (centered) {
                        strPosX = posX+width/2-(font24.getWidth(label)/2);
                        strPosY = posY+height/2-(font24.getHeight(label)/2);
                    }
                    font24.drawString(strPosX, strPosY, label);
                    break;
                case 16:
                    if (centered) {
                        strPosX = posX+width/2-(font16.getWidth(label)/2);
                        strPosY = posY+height/2-(font16.getHeight(label)/2);
                    }
                    font16.drawString(strPosX, strPosY, label);
                    break;
                case 12:
                    if (centered) {
                        strPosX = posX+width/2-(font12.getWidth(label)/2);
                        strPosY = posY+height/2-(font12.getHeight(label)/2);
                    }
                    font12.drawString(strPosX, strPosY, label);
                    break;
            }
        }
    }
    
    public void loadFonts() {
        font48 = new TrueTypeFont(new Font("Courier", Font.PLAIN, 46),true);
        font36 = new TrueTypeFont(new Font("Courier", Font.PLAIN, 36),true);
        font24 = new TrueTypeFont(new Font("Courier", Font.PLAIN, 24),true);
        font16 = new TrueTypeFont(new Font("Courier", Font.PLAIN, 16),true);
        font12 = new TrueTypeFont(new Font("Courier", Font.PLAIN, 12),true);
    }
    
    public void delete() {
        
    }
    
    public void checkMouse() {
    }
}
