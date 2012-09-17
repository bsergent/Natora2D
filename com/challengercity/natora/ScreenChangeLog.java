/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.Font;
import java.util.Random;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenChangeLog extends Screen {

    public int lastY;
    public int lastStopY;

    public ScreenChangeLog(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.BRANCHMENU;
        lastY = Natora.screenHeight + 50;
        lastStopY = Natora.screenHeight / 2 - 80;

        Random gen = new Random();
        for (int count = 0; count <= gen.nextInt(15); count++) {
            int dim = gen.nextInt(16) + 16;
            addToRenderList(new EntityMenuBug(gen.nextInt(nt.screenWidth/2-230), gen.nextInt(nt.screenHeight - 32), dim, dim, this, 0, 0, Natora.screenWidth/2-230, Natora.screenHeight));
        }
        for (int count = 0; count <= gen.nextInt(15); count++) {
            int dim = gen.nextInt(16) + 16;
            addToRenderList(new EntityMenuBug(nt.screenWidth-gen.nextInt(nt.screenWidth/2-230), gen.nextInt(nt.screenHeight - 32), dim, dim, this, Natora.screenWidth/2+230, 0, Natora.screenWidth, Natora.screenHeight));
        }

        addToRenderList(new GUITextCredits(this, "Natora©", 48, true, 0));
        addToRenderList(new GUITextCredits(this, "v" + Natora.version, 24, true, -25));

        addToRenderList(new GUITextCredits(this, "v0.1.4 Alpha - 6/30/12", 16, true, 10));
        //addToRenderList(new GUITextCredits(this, "+Mobs spawn automatically", 24, true, 0));
        //addToRenderList(new GUITextCredits(this, "*Player spawns at map center", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Optimized world generation", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Optimized tiles", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Optimized screen movement", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Only loads on-screen tiles", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.1.3 Alpha - 6/25/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Simple world generation", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Moving view", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Fullscreen option", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Optimized texture and font loading", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Global button cooldown", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Grid-Tiled world", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.1.2 Alpha - 6/20/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Change-log", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Monsters attack", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.1.1 Alpha - 6/20/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Working login", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Started Grid-Tiling the world", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Placing of tiles", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Health and health bars", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Custom hitboxes", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "-Debug system output", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.1.0 Alpha - 6/20/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Added action key 'Z', attacking and mining", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Tweaked collision detection", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Suffocation inside of tiles", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.0.9 Alpha - 6/19/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Delta movement", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Menu Sprites", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Items", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Tiles", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Collision Detection", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Coins drop from monsters", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Control listener in main loop for cooldowns", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Renders by layer (Tiles>Entities>Players>GUI)", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Tweaked monster AI", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "*Tweaked speeds", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.0.8 Alpha - 6/19/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Credits", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Username below player", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.0.7 Alpha - 6/18/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Text Rendering", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Edge Borders", 24, true, 0));
        
        addToRenderList(new GUITextCredits(this, "v0.0.1-6 Alpha - 6/14-17/12", 16, true, 10));
        addToRenderList(new GUITextCredits(this, "+Rendering", 24, true, 0));
        addToRenderList(new GUITextCredits(this, "+Very basic game engine mechanics", 24, true, 0));

        addToRenderList(new GUITextCreditsEnd(this, "Natora © 2012", 48, true, 300));
        addToRenderList(new GUITextCreditsEnd(this, "Yep, just like the credits.", 24, true, -15));
        addToRenderList(new GUITextCreditsEnd(this, "Press \"M\" to go back to the menu.", 24, true, 0));

    }

    public void actionPerformed(int id) {
    }
    
    public void reset() {
        for (GUI gui:renderGUIList) {
            if (gui instanceof GUITextCredits) {
                GUITextCredits newGui = (GUITextCredits) gui;
                newGui.posY = newGui.firstY;
            }
            if (gui instanceof GUITextCreditsEnd) {
                GUITextCreditsEnd newGui = (GUITextCreditsEnd) gui;
                newGui.posY = newGui.firstY;
            }
        }
    }

    public class GUITextCredits extends GUIText {

        public GUITextCredits(Screen screen, String text, int fontSize, boolean centered, int extraSpace) {
            super(Natora.screenWidth / 2, 0, screen, text, fontSize, centered);
            this.extraSpace = extraSpace;
        }
        public boolean done = false;
        public int extraSpace;
        public boolean hasSetLastY = false;
        public int firstY = 0;
        public TrueTypeFont font;

        public void draw() {
            if (visible) {
                if (!done) {
                    posY = posY - (int) (0.1f * Natora.lastDelta);
                    if (posY <= -20) {
                        done = true;
                    }
                }

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
                
                if (!hasSetLastY) {
                    posY = lastY + extraSpace;
                    firstY = posY;
                    lastY = posY + 3 + font.getHeight(label);
                    hasSetLastY = true;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX + width / 2 - (font.getWidth(label) / 2);
                    strPosY = posY + height / 2 - (font.getHeight(label) / 2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }

    public class GUITextCreditsEnd extends GUIText {

        public GUITextCreditsEnd(Screen screen, String text, int fontSize, boolean centered, int extraSpace) {
            super(Natora.screenWidth / 2, 0, screen, text, fontSize, centered);
            this.extraSpace = extraSpace;
        }
        public boolean done = false;
        public int extraSpace;
        public boolean hasSetLastY = false;
        public int firstY = 0;
        public TrueTypeFont font;

        public void draw() {
            if (visible) {
                if (!done && hasSetLastY) {
                    posY = posY - (int) (0.1f * Natora.lastDelta);
                    if (posY <= lastStopY) {
                        done = true;
                        lastStopY = posY + 3 + font.getHeight(label);
                    }
                }

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
                if (!hasSetLastY) {
                    posY = lastY + extraSpace;
                    firstY = posY;
                    lastY = posY + 3 + font.getHeight(label);
                    hasSetLastY = true;
                }
                int strPosX = posX;
                int strPosY = posY;
                if (centered) {
                    strPosX = posX + width / 2 - (font.getWidth(label) / 2);
                    strPosY = posY + height / 2 - (font.getHeight(label) / 2);
                }
                font.drawString(strPosX, strPosY, label);
            }
        }
    }
    
}
