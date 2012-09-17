/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.TrueTypeFont;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenGame extends Screen {

    public World world;
    
    public ScreenGame(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.INGAME;
        
        world = new World(this);
        world.generate();
        world.spawnPlayer();
        
        addToRenderList(new ItemCoin(74, 100, this));
        addToRenderList(new ItemStone(74, 116, this));
        addToRenderList(new ItemDirt(74, 132, this));
        
        addToRenderList(new GUIText(10, 9, this, "Version: "+Natora.version, 16, false));
        addToRenderList(new GUITextFPS(10, 29, this, 16, false));
        addToRenderList(new GUITextPlayerX(10, 49, this, 16, false));
        addToRenderList(new GUITextPlayerY(10, 69, this, 16, false));
        addToRenderList(new GUITextEnt(10, 89, this, 16, false));
        addToRenderList(new GUITextTile(10, 109, this, 16, false));
        addToRenderList(new GUITextWealth(10, 129, this, 16, false));
        addToRenderList(new GUITextItems(10, 149, this, 16, false));
        
        addToRenderList(new GUIText(10, Natora.screenHeight-120, this, "'Arrow Keys' - Movement", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-100, this, "'C' - Create Monster", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-80, this, "'Z' - Attack/Mine", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-60, this, "'X' - Place/Use", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-40, this, "'CTRL' - Highlight", 16, false));
        addToRenderList(new GUIText(10, Natora.screenHeight-20, this, "'M' - Main Menu", 16, false));
    }
    
    public void actionPerformed(int id) {
        
    }
    
    public void render() {
        renderTempPlayerList.clear();
        world.draw();
        for (int i = 0; i<renderEntityList.size(); i++) {
            if (renderEntityList.get(i) instanceof EntityPlayer) {
                renderTempPlayerList.add((EntityPlayer) renderEntityList.get(i));
            } else {
                glColor4f(1.0f,1.0f,1.0f,1.0f);
                renderEntityList.get(i).draw();
            }
        }
        for (int i = 0; i<renderTempPlayerList.size(); i++) {
            glColor4f(1.0f,1.0f,1.0f,1.0f);
            renderTempPlayerList.get(i).draw();
        }
        for (int i = 0; i<renderGUIList.size(); i++) {
            glColor4f(1.0f,1.0f,1.0f,1.0f);
            renderGUIList.get(i).draw();
        }
        renderTempPlayerList.clear();
    }
    
    public class GUITextFPS extends GUIText {
        public GUITextFPS (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "FPS: "+Natora.currentFPS;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "Entities: "+Renderer.getEntityCount();
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "Tiles: "+world.tileCount;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "Coins: "+nt.thePlayer.wealth;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "Items: "+nt.thePlayer.items;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
    
    public class GUITextPlayerX extends GUIText {
        public GUITextPlayerX (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "TileX: "+(nt.thePlayer.posX/32+1);
                //label = "X: "+nt.thePlayer.posX;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
    
    public class GUITextPlayerY extends GUIText {
        public GUITextPlayerY (int x, int y, Screen screen, int fontSize, boolean centered) {
            super(x, y, screen, "Blank", fontSize, centered);
        }
        
        public TrueTypeFont font;
        
        public void draw() {
            if (visible) {
                label = "TileY: "+(nt.thePlayer.posY/32+1);
                //label = "Y: "+nt.thePlayer.posY;
                if (font48 == null || font36 == null || font24 == null || font16 == null || font12 == null) {
                    loadFonts();
                }
                switch (fontSize) {
                    case 48:
                        font = font48;
                        break;
                    case 36:
                        font = font36;
                        break;
                    case 24:
                        font = font24;
                        break;
                    case 16:
                        font = font16;
                        break;
                    case 12:
                        font = font12;
                        break;
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
