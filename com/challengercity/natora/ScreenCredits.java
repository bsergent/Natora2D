/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenCredits extends Screen {

    public int lastY;
    public int lastStopY;
    
    public ScreenCredits(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.BRANCHMENU;
        lastY = Natora.screenHeight+50;
        lastStopY = Natora.screenHeight/2-80;
        
        addToRenderList(new GUITextCredits(this, "Natora©", 48, true, 0));
        addToRenderList(new GUITextCredits(this, "v"+Natora.version, 24, true, -25));
        
        addToRenderList(new GUITextCredits(this, "Created by:", 16, true,10));
        addToRenderList(new GUITextCredits(this, "Ben Sergent V", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "Game Design and Programming:", 16, true,10));
        addToRenderList(new GUITextCredits(this, "Ben Sergent V", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "Hopefully Tristan Spakes", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "Graphics:", 16, true, 20));
        addToRenderList(new GUITextCredits(this, "Ben Sergent V", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "Hopefully Mack Pierce", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "Music & Sound", 16, true, 20));
        addToRenderList(new GUITextCredits(this, "Hopefully Mack Pierce", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "Game Testers:", 16, true, 20));
        addToRenderList(new GUITextCredits(this, "Alex Solod", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "Many Others", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "Technologies:", 16, true, 20));
        addToRenderList(new GUITextCredits(this, "Java by Oracle", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "LWJGL by L.W.J.G.L. Project", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "Special Thanks:", 16, true, 20));
        addToRenderList(new GUITextCredits(this, "Frank & Melissa Sergent", 24, true ,0));
        addToRenderList(new GUITextCredits(this, "For encouraging me all the way.", 18, true, 0));
        
        addToRenderList(new GUITextCreditsEnd(this, "Natora © 2012", 48, true, 300));
        addToRenderList(new GUITextCreditsEnd(this, "Press \"M\" to go back to the menu.", 24, true, -15));
        
    }
    
    public void actionPerformed(int id) {
        
    }
    
    public class GUITextCredits extends GUIText {
        public GUITextCredits (Screen screen, String text, int fontSize, boolean centered, int extraSpace) {
            super(Natora.screenWidth/2, 0, screen, text, fontSize, centered);
            this.extraSpace = extraSpace;
        }
        
        public boolean done = false;
        public int extraSpace;
        public boolean hasSetLastY = false;
        
        public void draw() {
        if (visible) {
            if (!done) {
                posY = posY - (int)(Natora.lastDelta);
                if (posY<=-20) {
                    done = true;
                }
            }
            
            if (font == null) {
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, fontSize),true);
            }
            if (!hasSetLastY) {
                posY = lastY+extraSpace;
                lastY = posY+3+font.getHeight(label);
                hasSetLastY = true;
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
    }
    
    public class GUITextCreditsEnd extends GUIText {
        public GUITextCreditsEnd (Screen screen, String text, int fontSize, boolean centered, int extraSpace) {
            super(Natora.screenWidth/2, 0, screen, text, fontSize, centered);
            this.extraSpace = extraSpace;
        }
        
        public boolean done = false;
        public int extraSpace;
        public boolean hasSetLastY = false;
        
        public void draw() {
        if (visible) {
            if (!done&&hasSetLastY) {
                posY = posY - (int)(Natora.lastDelta);
                if (posY<=lastStopY) {
                    done = true;
                    lastStopY = posY+3+font.getHeight(label);
                }
            }
            
            if (font == null) {
                font = new TrueTypeFont(new Font("Courier", Font.PLAIN, fontSize),true);
            }
            if (!hasSetLastY) {
                posY = lastY+extraSpace;
                lastY = posY+3+font.getHeight(label);
                hasSetLastY = true;
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
    }
    
}
