/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import org.lwjgl.input.Keyboard;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public enum EnumControls {

    moveUp(Keyboard.KEY_UP),
    moveDown(Keyboard.KEY_DOWN),
    moveLeft(Keyboard.KEY_LEFT),
    moveRight(Keyboard.KEY_RIGHT),
    performAction1(0),
    performAction2(0);
    
    private int key;
    
    private EnumControls(int key) {
        this.key = key;
    }
    
    public static void setKey() {
        
    }
    
    public static int getKey() {
        return moveUp.key;
    }
    
}
