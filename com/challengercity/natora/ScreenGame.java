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
        addToRenderList(nt.thePlayer = new EntityPlayer(Natora.screenWidth/2-16,Natora.screenHeight/2-16,32,32,this,nt.username));
        Natora.gs=EnumGameState.INGAME;

        addToRenderList(new TileRock(10, 100, this));
        addToRenderList(new TileRock(10, 132, this));
        addToRenderList(new TileRock(10, 164, this));
        addToRenderList(new TileRock(10, 196, this));
        addToRenderList(new TileRock(10, 228, this));
        
        addToRenderList(new TileDirt(42, 100, this));
        addToRenderList(new TileDirt(42, 132, this));
        addToRenderList(new TileDirt(42, 164, this));
        addToRenderList(new TileDirt(42, 196, this));
        addToRenderList(new TileDirt(42, 228, this));
        
        addToRenderList(new TileDirt(106, 100, this));
        addToRenderList(new TileDirt(106, 132, this));
        addToRenderList(new TileDirt(106, 164, this));
        addToRenderList(new TileDirt(138, 196, this));
        addToRenderList(new TileDirt(138, 228, this));
        
        addToRenderList(new ItemCoin(74, 100, this));
        addToRenderList(new ItemRock(74, 116, this));
        addToRenderList(new ItemDirt(74, 132, this));
        
        addToRenderList(new GUIText(10, 9, this, "Version: "+Natora.version, 16, false));
        addToRenderList(new GUITextFPS(10, 29, this, 16, false));
        addToRenderList(new GUITextEnt(10, 49, this, 16, false));
        addToRenderList(new GUITextTile(10, 69, this, 16, false));
        addToRenderList(new GUITextWealth(10, 89, this, 16, false));
        addToRenderList(new GUITextItems(10, 109, this, 16, false));
        
        addToRenderList(new GUIText(10, Natora.screenHeight-100, this, "'Arrow Keys' - Movement", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-80, this, "'C' - Create Passive NPC", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-60, this, "'Z' - Attack/Mine", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-40, this, "'X' - Place/Use", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-20, this, "'M' - Main Menu", 16, false));
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
                label = "Entities: "+Renderer.getEntityCount();
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
    
    public class GUITextTile extends GUIText {
        public GUITextTile (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public void draw() {
            if (visible) {
                label = "Tiles: "+Renderer.getTileCount();
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
    
    public class GUITextWealth extends GUIText {
        public GUITextWealth (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public void draw() {
            if (visible) {
                label = "Coins: "+nt.thePlayer.wealth;
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
    
    public class GUITextItems extends GUIText {
        public GUITextItems (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public void draw() {
            if (visible) {
                label = "Items: "+nt.thePlayer.items;
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
    
    public class GUIImageMoving extends GUIImage {
        public GUIImageMoving (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen screen, String texName, String texExt) {
            super(x, y, width, height, picX, picY, picWidth, picHeight, screen, texName, texExt);
        }
        public boolean done = false;
        public void draw() {
            if (!done) {
                width=width-4;
                posX=posX+2;
                height=height-4;
                posY=posY+2;
                if (width<=0||height<=0) {
                    done = true;
                }
            }
            super.draw();
        }
    }
}
