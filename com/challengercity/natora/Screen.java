/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public abstract class Screen {

    public ArrayList<Entity> renderEntityList;
    public ArrayList<EntityPlayer> renderTempPlayerList;
    public ArrayList<GUI> renderGUIList;
    protected Natora nt;
    protected int tempScreenId;
    protected int idCount;
    
    public abstract void actionPerformed(int actionId);

    public Screen(Natora nt) {
        
        this.nt = nt;
        renderEntityList = new ArrayList<Entity>();
        renderTempPlayerList = new ArrayList<EntityPlayer>();
        renderGUIList = new ArrayList<GUI>();
        Renderer.addToRenderList(this);
        
    }
    
    public RenderableObject getObjectAt(int x, int y) {
        for (int i = 0; i<renderEntityList.size(); i++) {
            RenderableObject ro = renderEntityList.get(i);
            if (x>ro.posX&&x<ro.posX+ro.width && y>ro.posY&&y<ro.posY+ro.height) {
                return ro;
            }
        }
        return null;
    }
    
    public int getRenderId() {
            
        int id = idCount;
        idCount++;
        return id;
        
    }
    
    public ArrayList getRenderList(String listName) {
        if (listName.equals("Entities")) {
            return renderEntityList;
        }
        if (listName.equals("Players")) {
            for (int i = 0; i<renderEntityList.size(); i++) {
                if (renderEntityList.get(i) instanceof EntityPlayer) {
                    renderTempPlayerList.add((EntityPlayer) renderEntityList.get(i));
                }
            }
            return renderTempPlayerList;
        }
        if (listName.equals("GUI")) {
            return renderGUIList;
        }
        return renderEntityList;
    }
    
    public void addToRenderList(RenderableObject ro) {
        if (ro instanceof Entity) {
            renderEntityList.add((Entity) ro);
        } else if (ro instanceof GUI) {
            renderGUIList.add((GUI) ro);
        } else {
            System.out.println("[Screen] Could not add entity to render list");
        }
    }
    
    public void removeFromRenderList(RenderableObject ro) {
        for (int i = 0; i<renderEntityList.size(); i++) {
            if (renderEntityList.get(i).id==ro.id) {
                renderEntityList.remove(i);
            }
        }
        for (int i = 0; i<renderGUIList.size(); i++) {
            if (renderGUIList.get(i).id==ro.id) {
                renderGUIList.remove(i);
            }
        }
    }
    
    public void render() {
        renderTempPlayerList.clear();
        for (int i = 0; i<renderEntityList.size(); i++) {
            if (renderEntityList.get(i) instanceof EntityPlayer) {
                renderTempPlayerList.add((EntityPlayer) renderEntityList.get(i));
            } else {
                glColor4f(1.0f,1.0f,1.0f,1.0f);
                renderEntityList.get(i).draw();
            }
        }
        for (int i = 0; i<renderTempPlayerList.size(); i++) {
            glColor4f(1.0f,1.0f,1.0f,1.0f);
            renderTempPlayerList.get(i).draw();
        }
        for (int i = 0; i<renderGUIList.size(); i++) {
            glColor4f(1.0f,1.0f,1.0f,1.0f);
            renderGUIList.get(i).draw();
        }
        renderTempPlayerList.clear();
    }
    
    public void updateMovement(long delta) {
        for (int i = 0; i<renderEntityList.size(); i++) {
            if (renderEntityList.get(i) instanceof Entity) {
                Entity ent = (Entity) renderEntityList.get(i);
                ent.update(delta);
            }
        }
    }
    
    public void mouseUpdate() {
        for (int i = 0; i<renderGUIList.size(); i++) {
            renderGUIList.get(i).checkMouse();
        }
    }
    
}
