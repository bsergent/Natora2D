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
    
    private static ArrayList<RenderableObject> renderList;
    public ResourceLoader rl = new ResourceLoader();
    private Natora nt;

    public Renderer(Natora nt) {
        
        this.nt = nt;
        renderList = new ArrayList<RenderableObject>();
        System.out.println("[Renderer] Initialized");
        
    }
    
    public static void addToRenderList(RenderableObject ro) {
        renderList.add(ro);
    }
    
    public static void removeFromRenderList(RenderableObject ro) {
        renderList.remove(ro.id);
    }
    
    public static int getNextRenderId() {
        return renderList.size();
    }
    
    private void renderObjects() {
        for (int i = 0; i<renderList.size(); i++) {
            renderList.get(i).draw();
        }
    }
    
    public void render(int width, int height) {
        glMatrixMode(GL_PROJECTION);
        glEnable(GL_TEXTURE_2D);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, width, height, 0, 1, -1);

        glClear(GL_COLOR_BUFFER_BIT);

        renderObjects();

        Display.update();
        Display.sync(60);
    }
    
}
