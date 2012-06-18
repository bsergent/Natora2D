/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenGame extends Screen {

    public ScreenGame(Natora nt) {
        super(nt);
        addToRenderList(nt.thePlayer = new EntityPlayer(Natora.screenWidth/2-16,Natora.screenHeight/2-16,32,32,0,0,16,16,this,nt.username));
        Natora.gs=EnumGameState.INGAME;
    }
    
    public void actionPerformed(int id) {
        
    }
    
}
