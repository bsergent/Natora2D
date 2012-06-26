/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.Random;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenLoadingWorld extends Screen {

    private Random gen = new Random();
    
    public ScreenLoadingWorld(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.BRANCHMENU;
        startup();
    }
    
    public void startup() {
        addToRenderList(new GUIText(Natora.screenWidth/2, Natora.screenHeight/2, this, "Loading world, please wait...", 24, true));
    }
    
    public void actionPerformed(int id) {
    }

    @Override
    public void mouseUpdate() {
        super.mouseUpdate();
        Renderer.removeFromRenderList(this);
        nt.currentScreen = new ScreenGame(nt);
    }
    
}
