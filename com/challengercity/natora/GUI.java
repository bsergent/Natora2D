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
    protected EnumGameState gs;
    
    public GUI(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, EnumGameState gs) {
        
        this.posX=x;
        this.posY=y;
        this.picX=picX;
        this.picY=picY;
        this.picWidth=picWidth;
        this.picHeight=picHeight;
        this.width=width;
        this.height=height;
        this.gs=gs;
        this.id = Renderer.getNextRenderId(gs);
        Renderer.addToRenderList(this, gs);
        
    }
    
    public abstract void delete();
    public abstract void draw();
    public abstract void checkMouse();
    
}
