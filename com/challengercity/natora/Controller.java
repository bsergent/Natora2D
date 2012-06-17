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
                if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                    System.out.println("[ControlListener] Found Key - Up");
                    nt.thePlayer.setPosY(nt.thePlayer.getPosY()-1);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                    System.out.println("[ControlListener] Found Key - Down");
                    nt.thePlayer.setPosY(nt.thePlayer.getPosY()+1);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                    System.out.println("[ControlListener] Found Key - Left");
                    nt.thePlayer.setPosX(nt.thePlayer.getPosX()-1);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                    System.out.println("[ControlListener] Found Key - Right");
                    nt.thePlayer.setPosX(nt.thePlayer.getPosX()+1);
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                    System.out.println("[ControlListener] Found Key - C");
                    Random gen = new Random();
                    new Player(gen.nextInt(nt.screenWidth),gen.nextInt(nt.screenHeight),20,20,0,0,0,0,"Random Player");
                }
                try {
                    Thread.sleep(5);
                } catch (Exception ex) {

                }
            }
        }
    }
}
