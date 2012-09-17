/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.Random;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenOptions extends Screen {

    private Random gen = new Random();
    
    public ScreenOptions(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.BRANCHMENU;
        startup();
    }
    
    public void startup() {
        addToRenderList(new GUIText(Natora.screenWidth/2, 30, this, "Options", 36, true));
        addToRenderList(new GUIButton(Natora.screenWidth/2-150, 100, 300, 40, 0, this, "Toggle Fullscreen", 24));
        addToRenderList(new GUIButton(Natora.screenWidth/2-100, Natora.screenHeight-60, 200, 40, 1, this, "Back", 24));
        for (int count = 0; count<=gen.nextInt(30); count++) {
            int dim = gen.nextInt(16)+16;
            addToRenderList(new EntityMenuBug(gen.nextInt(nt.screenWidth-32),gen.nextInt(nt.screenHeight-32), dim, dim, this, 0, 0, Natora.screenWidth, Natora.screenHeight));
        }
    }
    
    /* glViewport(0, 0, Display.getWidth(), Display.getHeight());
            screenHeight=Display.getHeight();
            screenWidth=Display.getWidth();
     */
    
    public void actionPerformed(int id) {
        if (id==0) {
            if (!nt.fullscreen) {
                try {
                    nt.setDisplayMode(1280, 720, true); // 1280, 720  |  2560, 1440
                    glViewport(0, 0, 1280, 720);
                    nt.screenHeight=Display.getHeight();
                    nt.screenWidth=Display.getWidth();
                    nt.fullscreen=true;
                } catch (Exception ex) {
                    System.out.println("[Natora] Could not exit fullscreen");
                }
            } else {
                try {
                    nt.setDisplayMode(1280, 720, false);
                    glViewport(0, 0, 1280, 720);
                    nt.screenHeight=Display.getHeight();
                    nt.screenWidth=Display.getWidth();
                    nt.fullscreen=false;
                } catch (Exception ex) {
                    System.out.println("[Natora] Could not enter fullscreen");
                }
            }
            ViewPort.updateView();
        }
        if (id==1) {
            Renderer.removeFromRenderList(nt.currentScreen);
            nt.currentScreen = new ScreenMenu(nt);
        }
    }
    
}
