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
public abstract class Screen {

    protected static ArrayList<RenderableObject> renderList;
    protected Natora nt;
    protected int id;
    
    public abstract void actionPerformed(int id);

    public Screen(Natora nt) {
        
        this.nt = nt;
        renderList = new ArrayList<RenderableObject>();
        Renderer.addToRenderList(this);
        System.out.println("[Screen] Initialized");
        
    }
    
    public RenderableObject getObjectAt(int x, int y) {
        for (int i = 0; i<renderList.size(); i++) {
            RenderableObject ro = renderList.get(i);
            if (x>ro.posX&&x<ro.posX+ro.width && y>ro.posY&&y<ro.posY+ro.height) {
                return ro;
            }
        }
        return null;
    }
    
    public int getNextRenderId() {
        return renderList.size();
    }
    
    public void addToRenderList(RenderableObject ro) {
        renderList.add(ro);
    }
    
    public void removeFromRenderList(RenderableObject ro) {
        renderList.remove(ro.id);
    }
    
    public void render() {
        for (int i = 0; i<renderList.size(); i++) {
            renderList.get(i).draw();
        }
    }
    
    public void updateMovement() {
        for (int i = 0; i<renderList.size(); i++) {
            if (renderList.get(i) instanceof Entity) {
                Entity ent = (Entity) renderList.get(i);
                ent.move();
            }
        }
    }
    
    public void mouseUpdate() {
        for (int i = 0; i<renderList.size(); i++) {
            if (renderList.get(i) instanceof GUI) {
                GUI gui = (GUI) renderList.get(i);
                gui.checkMouse();
            }
        }
    }
    
}
