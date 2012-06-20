/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import java.util.ArrayList;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Renderer {
    
    private static ArrayList<Screen> renderList;
    public ResourceLoader rl = new ResourceLoader();
    private Natora nt;
    private Texture backgroundTexture;

    public Renderer(Natora nt) {
        
        this.nt = nt;
        renderList = new ArrayList<Screen>();
        System.out.println("[Renderer] Initialized");
        
    }
    
    public static void addToRenderList(Screen sc) {
        renderList.add(sc);
    }
    
    public static void removeFromRenderList(Screen sc) {
        renderList.remove(sc.tempScreenId);
    }
    
    public static int getEntityCount() {
        int count = 0;
        for (int i = 0; i<Screen.renderEntityList.size(); i++) {
            if (Screen.renderEntityList.get(i) instanceof Entity) {
                count++;
            }
        }
        return count;
    }
    
    public static int getTileCount() {
        int count = 0;
        for (int i = 0; i<Screen.renderTileList.size(); i++) {
            if (Screen.renderTileList.get(i) instanceof Tile) {
                count++;
            }
        }
        return count;
    }
    
    public void render() {
        glMatrixMode(GL_PROJECTION);
        glEnable(GL_TEXTURE_2D);
        Display.setVSyncEnabled(true);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, Natora.screenWidth, Natora.screenHeight, 0, 1, -1);

        glClear(GL_COLOR_BUFFER_BIT);
        glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        if (Natora.gs==EnumGameState.INGAME) {
            if (backgroundTexture == null) {
                backgroundTexture = ResourceLoader.loadImage("Background", ".PNG");
            }
            backgroundTexture.bind();

            glBegin(GL_QUADS);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexCoord2f(0,0);  // Upper-Left
            glVertex2i(0, 0);

            glTexCoord2f(32,0);  // Upper-Right
            glVertex2i(Natora.screenWidth, 0);

            glTexCoord2f(32,32);  // Lower-Right
            glVertex2i(Natora.screenWidth, Natora.screenHeight);

            glTexCoord2f(0,32);  // Lower-Left
            glVertex2i(0, Natora.screenHeight);
            glEnd();
        }
        
        for (int i = 0; i<renderList.size(); i++) {
            renderList.get(i).render();
        }

        Display.update();
        Display.sync(60);
    }
    
    public static float getTextureFloat(int num, int max) {
        return ((float)num)/max;
    }
    
}
