/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.io.*;
/**
 *
 * @author Ben Sergent V
 */
public class ResourceLoader {

    protected Toolkit tk = Toolkit.getDefaultToolkit();
    
    public java.awt.Image loadImage(String imageName) {
        return tk.getImage(getClass().getResource("/com/challengercity/natora/resources/"+imageName));
    }
    
}
