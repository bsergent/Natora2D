/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;
import java.util.Random;
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
        Natora.gs=EnumGameState.MAINMENU;
        startup();
    }
    
    public void startup() {
        GUI b;
        b = new GUIButton(Natora.screenWidth/2-125, 200, 250, 40, 0, this, "SinglePlayer", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-125, 250, 250, 40, 1, this, "MultiPlayer" ,24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-125, 300, 250, 40, 2, this, "Options", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-125, 350, 250, 40, 5, this, "Change-Log", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-125, 400, 250, 40, 3, this, "Credits", 24);
        addToRenderList(b);
        b = new GUIButton(Natora.screenWidth/2-125, Natora.screenHeight-60, 250, 40, 4, this, "Exit", 24);
        addToRenderList(b);
        b = new GUIImage(Natora.screenWidth/2-256, 20, 512, 147, 0, 0, 512, 147, this, "NatoraLogo", ".PNG");
        addToRenderList(b);
        b = new GUIText(Natora.screenWidth/2-256+287, 20+108, this, "v"+Natora.version, 12, false);
        addToRenderList(b);
        b = new GUIText(Natora.screenWidth/2-256+200, 20+120, this, "Logged in as "+nt.username, 12, false);
        addToRenderList(b);
        Random gen = new Random();
        for (int count = 0; count<=gen.nextInt(30); count++) {
            int dim = gen.nextInt(16)+16;
            addToRenderList(new EntityMenuBug(gen.nextInt(nt.screenWidth-32),gen.nextInt(nt.screenHeight-32), dim, dim, this, 0, 0, Natora.screenWidth, Natora.screenHeight));
        }
        //b = new GUIImageMoving(0, 0, Natora.screenWidth, Natora.screenHeight, 1, 1, 8, 8, this, "BlackDot", ".PNG");
        //addToRenderList(b);
        //b = new GUIImageMoving(Natora.screenWidth/2-100, Natora.screenHeight/2-100, 200, 200, 0, 148, 100, 100, this, "NatoraLogo", ".PNG");
        //addToRenderList(b);
    }
    
    public void actionPerformed(int id) {
        if (id==0) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenLoadingWorld(nt); 
        }
        if (id==1) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenMultiPlayer(nt); 
        }
        if (id==2) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenOptions(nt); 
        }
        if (id==3) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenCredits(nt); 
        }
        if (id==4) {
            Display.destroy();
            System.exit(0);
        }
        if (id==5) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenChangeLog(nt); 
  
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
