/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import java.io.File;
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
    public static boolean fullscreen = false;
    public static int screenWidth;
    public static int screenHeight;
    private static long lastFrame;
    private static long lastFPS;
    private static int fps;
    public static int currentFPS;
    public static long lastDelta;
    public static int guiCooldown = 0;
    private static File mainDir;

    public Natora(String username, String dir) {
        super();
        this.mainDir=new File(dir);
        this.username=username;
        run();
    }
    
    public static void main(String[] args) {
        Natora nt = new Natora(args[0], args[1]);
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
    
    public void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width) && 
            (Display.getDisplayMode().getHeight() == height) && 
            (Display.isFullscreen() == fullscreen)) {
                return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i=0;i<modes.length;i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against the 
                        // original display mode then it's probably best to go for this one
                        // since it's most likely compatible with the monitor
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                            (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                                targetDisplayMode = current;
                                break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width,height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
        }
    }
    
    public void run() {
        version = "0.1.4 Alpha";
        System.out.println("[Natora] Initialized - v"+version);
        screenWidth=1280;
        screenHeight=720;
        try {
            setDisplayMode(screenWidth, screenHeight, fullscreen);
            Display.setTitle("Natora - v"+version);
            Display.create();
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
            screenHeight=Display.getHeight();
            screenWidth=Display.getWidth();
            ViewPort.updateView();
        } catch(Exception ex) {
            System.out.println("[Natora] Could not setup display.");
            ex.printStackTrace();
            System.exit(1);
        }
        
        renderer = new Renderer(this);
        currentScreen = new ScreenBoot(this);
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
