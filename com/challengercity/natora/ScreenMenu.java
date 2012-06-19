/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.lwjgl.input.Mouse;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenMenu extends Screen {

    public ScreenMenu(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.MENU;
        startup();
    }
    
    public void startup() {
        GUI b;
        b = new GUIButton(Natora.screenWidth/2-100, 200, 250, 40, 0, this, "SinglePlayer", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-100, 250, 250, 40, 1, this, "MultiPlayer" ,24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-100, 300, 250, 40, 2, this, "Options", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-100, 350, 250, 40, 3, this, "About", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-100, Natora.screenHeight-60, 250, 40, 4, this, "Exit", 24);
        addToRenderList(b);
        b = new GUIImage(Natora.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, this, "NatoraLogo", ".PNG");
        addToRenderList(b);
        //b = new GUIImageLogo(0, 0, Natora.screenWidth, Natora.screenHeight, 1, 1, 8, 8, this, "BlackDot", ".PNG");
        //addToRenderList(b);
        //b = new GUIImageLogo(Natora.screenWidth/2-100, Natora.screenHeight/2-100, 200, 200, 0, 148, 100, 100, this, "NatoraLogo", ".PNG");
        //addToRenderList(b);
    }
    
    public void actionPerformed(int id) {
        if (id==0) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenGame(nt);
        }
        if (id==1) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenGame(nt);
        }
        if (id==2) {
            try {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                for (int i=0;i<modes.length;i++) {
                    DisplayMode current = modes[i];
                    System.out.println(current.getWidth() + "x" + current.getHeight() + "x" +
                                        current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
                }
            } catch (Exception ex) {
                
            }
        }
        if (id==3) {
            System.out.println("------Temporary About Page------");
            System.out.println("-------------NATORA-------------");
            System.out.println("-------------v0.0.7-------------");
            System.out.println("-Created by ha1fBit/BenSergentV-");
            System.out.println("---------Copyright 2012---------");
        }
        if (id==4) {
            Display.destroy();
            System.exit(0);
        }
    }
    
    public class GUIImageLogo extends GUIImage {
        public GUIImageLogo (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen screen, String texName, String texExt) {
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
