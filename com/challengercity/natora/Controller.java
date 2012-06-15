/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import org.lwjgl.input.Keyboard;
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
            System.out.println("Started!");
            
            while(true) {
                while(Keyboard.next()) {
                    System.out.println("Found key!!!");
                    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                        System.out.println("ESCAPE!!!!!");
                    }
                }
            }
        }
    }
}
