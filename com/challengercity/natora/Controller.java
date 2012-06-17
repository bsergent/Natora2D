/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import java.util.Random;
/**
 *
 * @author Ben Sergent V
 */
public class Controller {

    protected Natora nt;
    
    public Controller(Natora nt) {
        this.nt = nt;
        try {
            Mouse.create();
            Keyboard.create();
        } catch (org.lwjgl.LWJGLException ex) {
            System.out.println("Keyboard could not be initialized.");
        }
        Thread inputThread = new Thread(new InputThread(), "inputThread");
        inputThread.start();
    }
    
    class InputThread implements Runnable {
	Thread runner;
	public void run() {
            System.out.println("[ControlListener] Initialized");
            while(true) {
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    System.out.println("[ControlListener] Found Key - Escape");
                    System.exit(0);
                }
                switch(nt.gs) {
                    case INTRO:
                    if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
                        System.out.println("[ControlListener] Found Key - M");
                        nt.gs = EnumGameState.MENU;
                    }
                    break;
                    // Split
                    case MENU:
                    if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
                        System.out.println("[ControlListener] Found Key - G");
                        nt.gs = EnumGameState.INGAME;
                    }
                    break;
                    // Split
                    case INGAME:
                    if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                        System.out.println("[ControlListener] Found Key - Up");
                        nt.thePlayer.setVelY(-3);
                        nt.thePlayer.setVelX(0);
                        nt.thePlayer.setDir(1);
                    } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                        System.out.println("[ControlListener] Found Key - Down");
                        nt.thePlayer.setVelY(3);
                        nt.thePlayer.setVelX(0);
                        nt.thePlayer.setDir(3);
                    } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                        System.out.println("[ControlListener] Found Key - Left");
                        nt.thePlayer.setVelX(-3);
                        nt.thePlayer.setVelY(0);
                        nt.thePlayer.setDir(2);
                    } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                        System.out.println("[ControlListener] Found Key - Right");
                        nt.thePlayer.setVelX(3);
                        nt.thePlayer.setVelY(0);
                        nt.thePlayer.setDir(0);
                    } else {
                        nt.thePlayer.setVelX(0);
                        nt.thePlayer.setVelY(0);
                        nt.thePlayer.setAni(0);
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                        System.out.println("[ControlListener] Found Key - C");
                        Random gen = new Random();
                        Player p = new Player(gen.nextInt(nt.screenWidth),gen.nextInt(nt.screenHeight),32,32,0,0,16,16,EnumGameState.INGAME,"Random Player");
                    }
                    try {
                        Thread.sleep(5);
                    } catch (Exception ex) {

                    }
                    break;
                }
            }
        }
    }
}
