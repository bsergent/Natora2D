/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import java.util.ArrayList;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Renderer {
    
    private static ArrayList<RenderableObject> renderGameList;
    private static ArrayList<RenderableObject> renderMenuList;
    private static ArrayList<RenderableObject> renderIntroList;
    public ResourceLoader rl = new ResourceLoader();
    private Natora nt;

    public Renderer(Natora nt) {
        
        this.nt = nt;
        renderGameList = new ArrayList<RenderableObject>();
        renderMenuList = new ArrayList<RenderableObject>();
        renderIntroList = new ArrayList<RenderableObject>();
        System.out.println("[Renderer] Initialized");
        
    }
    
    public static void addToRenderList(RenderableObject ro, EnumGameState gs) {
        switch(gs) {
        case INTRO:
            renderIntroList.add(ro);
            break;
        case MENU:
            renderMenuList.add(ro);
            break;
        case INGAME:
            renderGameList.add(ro);
            break;
        }
    }
    
    public static void removeFromRenderList(RenderableObject ro, EnumGameState gs) {
        switch(gs) {
        case INTRO:
            renderIntroList.remove(ro.id);
            break;
        case MENU:
            renderMenuList.remove(ro.id);
            break;
        case INGAME:
            renderGameList.remove(ro.id);
            break;
        }
    }
    
    public static int getNextRenderId(EnumGameState gs) {
        switch(gs) {
        case INTRO:
            return renderIntroList.size();
        case MENU:
            return renderMenuList.size();
        case INGAME:
            return renderGameList.size();
        }
        return 0;
    }
    
    public static RenderableObject getObjectAt(int x, int y, EnumGameState gs) {
        switch(gs) {
        case INTRO:
            for (int i = 0; i<renderIntroList.size(); i++) {
                RenderableObject ro = renderIntroList.get(i);
                if (x>ro.posX&&x<ro.posX+ro.width && y>ro.posY&&y<ro.posY+ro.height) {
                    return ro;
                }
            }
        case MENU:
            for (int i = 0; i<renderMenuList.size(); i++) {
                RenderableObject ro = renderMenuList.get(i);
                if (x>ro.posX&&x<ro.posX+ro.width && y>ro.posY&&y<ro.posY+ro.height) {
                    return ro;
                }
            }
        case INGAME:
            for (int i = 0; i<renderGameList.size(); i++) {
                RenderableObject ro = renderGameList.get(i);
                if (x>ro.posX&&x<ro.posX+ro.width && y>ro.posY&&y<ro.posY+ro.height) {
                    return ro;
                }
            }
        }
        return null;
    }
    
    private void renderIntroObjects() {
        for (int i = 0; i<renderIntroList.size(); i++) {
            renderIntroList.get(i).draw();
        }
    }
    
    private void renderMenuObjects() {
        for (int i = 0; i<renderMenuList.size(); i++) {
            renderMenuList.get(i).draw();
        }
    }
    
    private void renderGameObjects() {
        for (int i = 0; i<renderGameList.size(); i++) {
            renderGameList.get(i).draw();
        }
    }
    
    public void render() {
        glMatrixMode(GL_PROJECTION);
        glEnable(GL_TEXTURE_2D);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, Natora.screenWidth, Natora.screenHeight, 0, 1, -1);

        glClear(GL_COLOR_BUFFER_BIT);

        switch(nt.gs) {
            case INTRO: renderIntroObjects();
                break;
            case MENU: renderMenuObjects();
                break;
            case INGAME:  renderGameObjects();
        }
        System.out.println("[Renderer] Rendering "+renderGameList.size()+" entities");

        Display.update();
        Display.sync(60);
    }
    
    public void move() {
        switch(nt.gs) {
        case INTRO:
            for (int i = 0; i<renderIntroList.size(); i++) {
                if (renderIntroList.get(i) instanceof Entity) {
                    Entity ent = (Entity) renderIntroList.get(i);
                    ent.move();
                }
            }
            break;

        case MENU:
            for (int i = 0; i<renderMenuList.size(); i++) {
                if (renderMenuList.get(i) instanceof Entity) {
                    Entity ent = (Entity) renderMenuList.get(i);
                    ent.move();
                }
            }
            break;

        case INGAME:
            for (int i = 0; i<renderGameList.size(); i++) {
                if (renderGameList.get(i) instanceof Entity) {
                    Entity ent = (Entity) renderGameList.get(i);
                    ent.move();
                }
            }
            break;
        }
    }
    
    public void mouseUpdate() {
        switch(nt.gs) {
        case INTRO:
            for (int i = 0; i<renderIntroList.size(); i++) {
                if (renderIntroList.get(i) instanceof GUI) {
                    GUI gui = (GUI) renderIntroList.get(i);
                    gui.checkMouse();
                }
            }
            break;

        case MENU:
            for (int i = 0; i<renderMenuList.size(); i++) {
                if (renderMenuList.get(i) instanceof GUI) {
                    GUI gui = (GUI) renderMenuList.get(i);
                    gui.checkMouse();
                }
            }
            break;

        case INGAME:
            for (int i = 0; i<renderGameList.size(); i++) {
                if (renderGameList.get(i) instanceof GUI) {
                    GUI gui = (GUI) renderGameList.get(i);
                    gui.checkMouse();
                }
            }
            break;
        }
    }
    
}
