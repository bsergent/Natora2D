/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.*;
import javax.swing.JFrame;
/**
 *
 * @author Ben Sergent V
 */
public class Screen {
    
    private GraphicsDevice vc;
    private DisplayMode dm;
    protected int width;
    protected int height;
    protected int centerx;
    protected int centery;
    
    public Screen () {
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = env.getDefaultScreenDevice();
        dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        width = dm.getWidth();
        height = dm.getHeight();
        centerx = width/2;
        centery = height/2;
        
    }
    
    public void setFullscreen(JFrame window) {
        
        window.setUndecorated(true);
        window.setResizable(false);
        vc.setFullScreenWindow(window);
        
        if (dm != null && vc.isDisplayChangeSupported()) {
            try {
                vc.setDisplayMode(dm);
            } catch (Exception ex) {
                
            }
        }
        
    }
    
    public void setWidthAndHeight(int w, int h) {
        
        dm = new DisplayMode(w, h, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
        width = dm.getWidth();
        height = dm.getHeight();
        centerx = width/2;
        centery = height/2;
        
    }
    
    public Window getFullScreenWindow() {
        return vc.getFullScreenWindow();
    }
    
    public void restoreScreen() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }
    
}
