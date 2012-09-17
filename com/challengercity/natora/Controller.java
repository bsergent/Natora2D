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
    private int cooldown = 0;
    
    public Controller(Natora nt) {
        this.nt = nt;
        try {
            Mouse.create();
            Keyboard.create();
        } catch (org.lwjgl.LWJGLException ex) {
            System.out.println("Keyboard could not be initialized.");
        }
        System.out.println("[ControlListener] Initialized");
    }
    
    public void checkInput() {
            switch(nt.gs) {
            case MAINMENU:
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    System.exit(0);
                }
                break;
            // Split
            case BRANCHMENU:
                if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
                    Renderer.removeFromRenderList(nt.currentScreen);
                    nt.currentScreen = new ScreenMenu(nt); 
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    System.exit(0);
                }
                break;
            // Split
            case LOGIN:
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    System.exit(0);
                }
                break;
            // Split
            case INGAME:
            if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
                nt.thePlayer.setVelY(-0.35f);
                nt.thePlayer.setVelX(0);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
                nt.thePlayer.setVelY(0.35f);
                nt.thePlayer.setVelX(0);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
                nt.thePlayer.setVelX(-0.35f);
                nt.thePlayer.setVelY(0);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
                nt.thePlayer.setVelX(0.35f);
                nt.thePlayer.setVelY(0);
            } else {
                nt.thePlayer.setVelX(0);
                nt.thePlayer.setVelY(0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
                Random gen = new Random();
                if (cooldown<=0) {
                    nt.currentScreen.addToRenderList(new EntityMonster(ViewPort.getViewX(gen.nextInt(nt.screenWidth-32)),ViewPort.getViewY(gen.nextInt(nt.screenHeight-32)),32,32,nt.currentScreen));
                    cooldown = 15;
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                if (cooldown<=0) {
                    nt.thePlayer.performAction1();
                    cooldown = 10;
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_X) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                if (cooldown<=0) {
                    nt.thePlayer.performAction2();
                    cooldown = 10;
                }
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
                if (cooldown<=0) {
                    nt.thePlayer.toggleHover();
                    cooldown = 10;
                }
            }
            if (cooldown <= 0) {
                nt.thePlayer.performingAction = false;
            }
            
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                // Pause Game
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
                Renderer.removeFromRenderList(nt.currentScreen);
                nt.currentScreen = new ScreenMenu(nt); 
            }
            
            break;
        }
        cooldown--;
    }
}
