/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.Random;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenBoot extends Screen {

    private Random gen = new Random();
    
    public ScreenBoot(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.MAINMENU;
        startup();
    }
    
    public void startup() {
        addToRenderList(new GUIImage(Natora.screenWidth/2-256, Natora.screenHeight/2-74, 512, 147, 0, 0, 512, 147, this, "NatoraLogo", ".PNG"));
    }
    
    public void actionPerformed(int id) {
    }
    
    @Override
    public void mouseUpdate() {
        super.mouseUpdate();
        Renderer.removeFromRenderList(this);
        nt.currentScreen = new ScreenMenu(nt);
    }
    
}
