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
    public Player thePlayer;
    public Controller control;
    public Renderer renderer;
    public String version;
    public static int screenWidth;
    public static int screenHeight;

    public Natora(String username) {
        super();
        gs = EnumGameState.INTRO;
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
        renderer = new Renderer(this); // Prepare for rendering

        // INTRO
        new GUIImage(screenWidth/2-195, screenHeight/2-66, 390, 112, 0, 0, 0, 0, EnumGameState.INTRO,"NatoraHeader", ".PNG");
        
        // MENU
        new GUIImage(screenWidth/2-195, 20, 390, 112, 0, 0, 0, 0, EnumGameState.MENU,"NatoraHeader", ".PNG");
        new GUIButton(screenWidth/2-80, screenHeight/2-15, 160, 30, 0, 0, 160, 30, EnumGameState.MENU, "GUIStartGame", ".PNG");
        
        // INGAME
        thePlayer = new Player(30,30,32,32,0,0,16,16,EnumGameState.INGAME,username);
        
        while(!Display.isCloseRequested()) { // Game Loop
            renderer.move();
            renderer.render();
            renderer.mouseUpdate();
        }
        
        Display.destroy();
        System.exit(0);
    }
}
