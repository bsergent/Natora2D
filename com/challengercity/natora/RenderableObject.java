/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class RenderableObject {

    protected int posX, posY, width, height;
    public int id;
    public abstract void draw();
    public abstract void delete();
    
}
