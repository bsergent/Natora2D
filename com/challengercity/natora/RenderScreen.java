/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.awt.*;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class RenderScreen extends JFrame {

    protected Screen screen;
    protected ResourceLoader rl = new ResourceLoader();
    private Image logo = rl.loadImage("NatoraLogo.png");
    
    public RenderScreen(Screen screen, Color bg, Color fg, Font font) {
        super();
        this.screen = screen;
        setBackground(bg);
        setForeground(fg);
        setFont(font);
    }
    
    public void paint (Graphics g) { // Work on painting GUI Screens from other classes, also convert to OpenGL
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        
        g.drawImage(logo, screen.centerx-(logo.getWidth(this)/2), screen.centery-(logo.getHeight(this)/2), this);
        g.drawString("Natora", screen.centerx-41, screen.centery+logo.getHeight(this)/2+20);
    }
    
}
