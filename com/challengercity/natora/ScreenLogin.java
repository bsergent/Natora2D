/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenLogin extends Screen {

    public ScreenLogin(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.LOGIN;
        startup();
    }
    
    public void startup() {
        
    }
    
    public void actionPerformed(int id) {
        
    }
}
