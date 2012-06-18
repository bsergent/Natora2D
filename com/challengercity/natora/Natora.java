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

    public static EnumGameState gs;
    public String username;
    public EntityPlayer thePlayer;
    public Controller control;
    public Renderer renderer;
    public String version;
    public Screen currentScreen;
    public static int screenWidth;
    public static int screenHeight;
    private static long lastFrame;

    public Natora(String username) {
        super();
        gs = EnumGameState.MENU;
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
        version = "0.0.6";
        System.out.println("[Natora] Initialized - v"+version);
        screenWidth=1280;
        screenHeight=720;
        try {
            Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            //Display.setFullscreen(true);
            Display.setTitle("Natora - v"+version);
            Display.create();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        control = new Controller(this); // Listen for input
        renderer = new Renderer(this);
        currentScreen = new ScreenMenu(this); // Create Menu Screen
        
        currentScreen.updateMovement();
        renderer.render();
        currentScreen.mouseUpdate();
        
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        while(!Display.isCloseRequested()) { // Game Loop
            currentScreen.updateMovement();
            renderer.render();
            currentScreen.mouseUpdate();
        }
        
        Display.destroy();
        System.exit(0);
    }
}
