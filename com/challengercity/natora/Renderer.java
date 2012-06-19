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
    
    private static ArrayList<Screen> renderList;
    public ResourceLoader rl = new ResourceLoader();
    private Natora nt;

    public Renderer(Natora nt) {
        
        this.nt = nt;
        renderList = new ArrayList<Screen>();
        System.out.println("[Renderer] Initialized");
        
    }
    
    public static void addToRenderList(Screen sc) {
        renderList.add(sc);
    }
    
    public static void removeFromRenderList(Screen sc) {
        renderList.remove(sc.id);
    }
    
    public static int getObjectCount() {
        int count = 0;
        for (int i = 0; i<Screen.renderList.size(); i++) {
            if (Screen.renderList.get(i) instanceof Entity) {
                count++;
            }
        }
        return count;
    }
    
    public void render() {
        glMatrixMode(GL_PROJECTION);
        glEnable(GL_TEXTURE_2D);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, Natora.screenWidth, Natora.screenHeight, 0, 1, -1);

        glClear(GL_COLOR_BUFFER_BIT);
        glEnable (GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

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
