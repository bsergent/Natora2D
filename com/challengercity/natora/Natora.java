/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
/**
 *
 * @author Ben Sergent V
 */
public class Natora {

    private EnumGameState gs;
    public String username;
    public Player thePlayer;
    public Controller control;
    public Renderer renderer;
    public String version;
    public int screenWidth;
    public int screenHeight;

    public Natora(String username) {
        super();
        gs = EnumGameState.menu;
        this.username=username;
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
        version = "0.0.5";
        System.out.println("[Natora] Initialized - v"+version);
        screenWidth=1280;
        screenHeight=720;
        try {
            Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            //Display.setFullscreen(true);
            Display.setTitle("Natora - "+version);
            Display.create();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        control = new Controller(this); // Listen for input
        renderer = new Renderer(this); // Prepare for rendering
        
        thePlayer = new Player(30,30,16,16,0,0,0,0,username);
        new Player(5,5,16,16,0,0,0,0,"xXWeboy60Xx"); // Create test object
        
        while(!Display.isCloseRequested()) { // Game Loop
            renderer.render(screenWidth, screenHeight);
        }
        
        Display.destroy();
        System.exit(0);
    }
}
