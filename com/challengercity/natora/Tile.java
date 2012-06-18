/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V
 */
public class Tile extends RenderableObject {
    
    private float posX, posY, width, height, velX, velY;
    private int picX1, picY1, picX2, picY2;
    protected EnumGameState gs;
    private String name;
    private int hardness;
    
    public Tile(float x, float y, int picX1, int picY1, int picX2, int picY2, EnumGameState gs, String name, int hardness) {
        
        this.posX=x;
        this.posY=y;
        this.picX1=picX1;
        this.picY1=picY1;
        this.picX2=picX2;
        this.picY2=picY1;
        this.name=name;
        this.hardness=hardness;
        this.gs = gs;
        this.id = screen.getNextRenderId();
        screen.addToRenderList(this);
          
    }
    
    public float getX() {
        return posX;
    }
    
    public float getY() {
        return posY;
    }
    
    public void breakTile() {
        // Drop item(s)
        delete();
    }
    
    public int getHardness() {
        return hardness;
    }

    public String getName() {
        return name;
    }
    
    public void delete() {
        screen.removeFromRenderList(this);
    }
    
    public void draw() {
        glRectd(posX,posY,posX+width,posY+height);
    }
    
    // Tile Declaration
    //public static final Tile stone;
    
    // Tile Creation
    static
    {
        //stone = new Tile();
    }
}
