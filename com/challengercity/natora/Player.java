/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author Ben Sergent V
 */
public class Player extends Entity {
    
    protected int dir;
    private String username;

    public Player(int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, String username) {
        super(x, y, width, height, picX, picY, picWidth, picHeight);
        this.username = username;
        System.out.println("[Entity] Entity created - "+x+","+y+" - "+"Player - "+username);
    }
    
    public String getUsername() {
        return username;
    }
    
    public void draw() {
        ResourceLoader.loadImage("NatoraLogo", ".PNG").bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);  // Upper-Left
        glVertex2i(posX, posY);

        glTexCoord2f(1, 0);  // Upper-Right
        glVertex2i(posX+width, posY);

        glTexCoord2f(1, 1);  // Lower-Right
        glVertex2i(posX+width, posY+height);

        glTexCoord2f(0, 1);  // Lower-Left
        glVertex2i(posX, posY+height);
        glEnd();
    }
    
}
