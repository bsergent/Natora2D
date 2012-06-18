/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class RenderableObject {

    protected int posX, posY, width, height;
    public int id;
    protected Screen screen;
    protected boolean visible = true;
    public abstract void draw();
    public abstract void delete();
    
    public void hide() {
        visible = false;
    }
    
    public void show() {
        visible = true;
    }
    
    public boolean intersects(RenderableObject ro) {
        if (ro.posX>posX&&ro.posX<(posX+width) && ro.posY>posY&&ro.posY<(posY+height)) {
            System.out.println("[Collision] Collided!");
            return true;
        } else {
            System.out.println("[Collision] Nope");
            return false;
        }
    }
    
}
