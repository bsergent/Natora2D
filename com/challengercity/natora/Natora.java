/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
/**
 *
 * @author Ben Sergent V
 */
public class Natora {

    protected String username;
    protected Player thePlayer;
    protected Controller control;
    protected EnumGameState gs;

    public Natora(String username) {
        super();
        gs = EnumGameState.menu;
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
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Natora");
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        control = new Controller(this);
        
        while(!Display.isCloseRequested()) { // Game Loop
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
        System.exit(0);
    }
}
