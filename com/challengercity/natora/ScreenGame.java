/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenGame extends Screen {

    public ScreenGame(Natora nt) {
        super(nt);
        addToRenderList(nt.thePlayer = new EntityPlayer(Natora.screenWidth/2-16,Natora.screenHeight/2-16,32,32,0,0,32,32,this,nt.username));
        Natora.gs=EnumGameState.INGAME;
        
        addToRenderList(new GUIText(10, 9, this, "Version: "+Natora.version, 16, false));
        addToRenderList(new GUITextFPS(10, 29, this, 16, false));
        addToRenderList(new GUITextEnt(10, 49, this, 16, false));
    }
    
    public void actionPerformed(int id) {
        
    }
    
    public class GUITextFPS extends GUIText {
        public GUITextFPS (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public void draw() {
            if (visible) {
                label = "FPS: "+Natora.currentFPS;
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
    }
    
    public class GUITextEnt extends GUIText {
        public GUITextEnt (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public void draw() {
            if (visible) {
                label = "Entities: "+Renderer.getObjectCount();
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
    }
}
