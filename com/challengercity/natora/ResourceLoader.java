/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challengercity.natora;

import org.newdawn.slick.opengl.*;
import java.io.*;
import org.lwjgl.opengl.Display;
/**
 *
 * @author Ben Sergent V
 */
public class ResourceLoader {
    
    public static Texture loadImage(String imageName, String fileExt) {
        Texture texture = null;
        try {
                texture = TextureLoader.getTexture(fileExt, new FileInputStream(new File("/Users/Ben/Documents/Natora/src/com/challengercity/natora/resources/"+imageName+fileExt)));
        } catch (Exception ex) {
                System.out.println("[ResourceLoader] Could not load texture - "+imageName+fileExt);
                ex.printStackTrace();
                Display.destroy();
                System.exit(1);
        }
        return texture;
    }
    
    public static Texture loadRemoteImage(String imageName, String fileExt) {
        Texture texture = null;
        try {
                texture = TextureLoader.getTexture(fileExt, new FileInputStream(new File("resources/"+imageName+fileExt)));
                // Replace PNG with your file extension
        } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("[ResourceLoader] Could not load texture - "+imageName);
        }
        return texture;
    }
    
}
