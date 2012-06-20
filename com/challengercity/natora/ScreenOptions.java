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
public class ScreenOptions extends Screen {

    private Random gen = new Random();
    
    public ScreenOptions(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.BRANCHMENU;
        startup();
    }
    
    public void startup() {
        addToRenderList(new GUIText(Natora.screenWidth/2, Natora.screenHeight/2-25, this, "No options yet. Will be added later.", 24, true));
        addToRenderList(new GUIText(Natora.screenWidth/2, Natora.screenHeight/2, this, "I would like to finish the game engine first.", 24, true));
        addToRenderList(new GUIText(Natora.screenWidth/2, Natora.screenHeight/2+20, this, "Press 'M' to return to the menu.", 16, true));
        for (int count = 0; count<=gen.nextInt(30); count++) {
            int dim = gen.nextInt(16)+16;
            addToRenderList(new EntityMenuBug(gen.nextInt(nt.screenWidth-32),gen.nextInt(nt.screenHeight-32), dim, dim, this, 0, 0, Natora.screenWidth, Natora.screenHeight));
        }
    }
    
    public void actionPerformed(int id) {
        
    }
    
}
