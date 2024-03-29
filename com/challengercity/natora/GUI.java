/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class GUI extends RenderableObject {

    protected int posX, posY, width, height, picX, picY, picWidth, picHeight;
    protected Screen screen;
    protected String texName;
    protected String texExt;
    
    public GUI(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen screen) {
        
        this.posX=x;
        this.posY=y;
        this.picX=picX-1;
        this.picY=picY;
        this.picWidth=picWidth;
        this.picHeight=picHeight;
        this.width=width;
        this.height=height;
        this.screen=screen;
        
    }
    
    public abstract void delete();
    public abstract void draw();
    public abstract void checkMouse();
    
}
