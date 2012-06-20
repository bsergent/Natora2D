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
    public static String version;
    public static Screen currentScreen;
    public static int screenWidth;
    public static int screenHeight;
    private static long lastFrame;
    private static long lastFPS;
    private static int fps;
    public static int currentFPS;
    public static long lastDelta;

    public Natora(String username) {
        super();
        gs = EnumGameState.MAINMENU;
        this.username=username;
    }
    
    public static void main(String[] args) {
        Natora nt = new Natora(args[0]);
        nt.run();
    }
    
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        if (delta > 70) {
            delta = 70;
        }
        lastDelta = delta;
        return delta;
    }
    
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) { 
            currentFPS=fps;
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }
    
    public void run() {
        version = "0.1.1 Alpha";
        System.out.println("[Natora] Initialized - v"+version);
        screenWidth=1280;
        screenHeight=720;
        //screenWidth=1600;
        //screenHeight=1200;
        try {
            Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            //Display.setFullscreen(true);
            Display.setTitle("Natora - v"+version);
            Display.create();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        renderer = new Renderer(this);
        currentScreen = new ScreenMenu(this); // Create Menu Screen
        control = new Controller(this); // Listen for input
        
        lastFPS = getTime();
        getDelta();
        
        while(!Display.isCloseRequested()) { // Game Loop
            control.checkInput();
            currentScreen.updateMovement(getDelta());
            renderer.render();
            currentScreen.mouseUpdate();
            updateFPS();
        }
        
        Display.destroy();
        System.exit(0);
    }
}
