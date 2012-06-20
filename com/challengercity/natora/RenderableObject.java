/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import java.awt.Rectangle;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class RenderableObject {

    protected int posX, posY, width, height;
    public int id;
    protected Screen screen;
    protected boolean visible = true;
    protected Rectangle hitbox = new Rectangle();
    
    public abstract void draw();
    public abstract void delete();
    
    public void hide() {
        visible = false;
    }
    
    public void show() {
        visible = true;
    }
    
    public Rectangle getHitbox(){
        hitbox.setBounds(posX, posY, width, height);
        return hitbox;
    }
    
    public boolean intersects(RenderableObject ro) {
	return getHitbox().intersects(new Rectangle(ro.getHitbox()));
    }
    
    public boolean offsetIntersects(RenderableObject ro, int offsetX, int offsetY) {
        Rectangle offsetHitbox = new Rectangle(getHitbox().x+offsetX, getHitbox().y+offsetY, getHitbox().width, getHitbox().height);
	return offsetHitbox.intersects(new Rectangle(ro.getHitbox()));
    }
}
