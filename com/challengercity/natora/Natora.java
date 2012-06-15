/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.*;
import javax.swing.ImageIcon;
/**
 *
 * @author Ben Sergent V
 */
public class Natora {

    protected String username;
    protected Player thePlayer;
    protected Controller control;
    protected EnumGameState gs;
    protected Screen screen;
    protected RenderScreen rdrScreen;

    public Natora(String username) {
        super();
        control = new Controller(this);
        gs = EnumGameState.menu;
        screen = new Screen();
    }
    
    public static void main(String[] args) {
        
        Natora nt = new Natora(args[0]);
        nt.run();
        
    }
    
    public void startup(String username) {
        
        Natora nt = new Natora(username);
        nt.run();
        
    }
    
    public void run() {
        rdrScreen = new RenderScreen(screen, ColorList.black, ColorList.neonGreen, new Font("New Courier", Font.PLAIN, 24));
        screen.setWidthAndHeight(2560, 1440);
        screen.setFullscreen(rdrScreen);
        
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        screen.restoreScreen();
    }
}
