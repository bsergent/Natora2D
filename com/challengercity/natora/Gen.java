/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.Random;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class Gen {

    private static Random rand = new Random();
    
    public static void genMain(World world) {
        for (int i = 0; i < world.tileCount; i++) {
            world.tiles[i] = new TileStone(world, new Rectangle(world.getXFromArrayIndex(i),world.getYFromArrayIndex(i), 32, 32), i);
        }
    }
    
    public static void genOres(World world) {
        
    }
    
    public static void genSpawn(World world) {
        int middle = world.tileCount/2+world.worldTilesWidth/2;
        for(int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                if (middle+x+(y*world.worldTilesWidth)>=0 && middle+x+(y*world.worldTilesWidth) <= world.tileCount) {
                    world.tiles[middle+x+(y*world.worldTilesWidth)] = new TileDirt(world, new Rectangle(world.getXFromArrayIndex(middle+x+(y*world.worldTilesWidth)), world.getYFromArrayIndex(middle+x+(y*world.worldTilesWidth)), 32, 32),middle+x+(y*world.worldTilesWidth));
                }
            }
        }
    }
    
    public static void genEdgeBorder(World world) {
        
    }
    
    public static void genTunnels(World world) {
        for (int i = 0; i < world.tileCount; i++) {
            if (rand.nextInt(100)<=50) {
                world.tiles[i] = new TileDirt(world, new Rectangle(world.getXFromArrayIndex(i),world.getYFromArrayIndex(i), 32, 32), i);
            }
        }
    }
    
}

/*
public void fill(int x, int y, byte color) {
    byte colorMask = getPixelColor(x,y);
    ArrayList<Coordinates> pixelsToFill = new ArrayList<Coordinates>();
    pixelsToFill.add(new Coordinates(x,y));
    changePixel(x,y,color,1,false);

    for (int p = 0; p < pixelsToFill.size(); p++) {
        int xp = pixelsToFill.get(p).x;
        int yp = pixelsToFill.get(p).y;
        if (xp < 128*5 && yp < 128*5 && xp > 0 && yp > 0) {
            try {
                if (xp+5 <= 128*5) {
                    if (getPixelColor(xp+5,yp) == colorMask) {
                        pixelsToFill.add(new Coordinates(xp+5,yp));
                        changePixel(xp+5, yp, color, 1, false);
                    }
                }
                if (xp-5 >= 0) {
                    if (getPixelColor(xp-5,yp) == colorMask) {
                        pixelsToFill.add(new Coordinates(xp-5,yp));
                        changePixel(xp-5, yp, color, 1, false);
                    }
                }
                if (yp+5 <= 128*5) {
                    if (getPixelColor(xp,yp+5) == colorMask) {
                        pixelsToFill.add(new Coordinates(xp,yp+5));
                        changePixel(xp, yp+5, color, 1, false);
                    }
                }
                if (yp-5 >= 0) {
                    if (getPixelColor(xp,yp-5) == colorMask) {
                        pixelsToFill.add(new Coordinates(xp,yp-5));
                        changePixel(xp, yp-5, color, 1, false);
                    }
                }
            } catch (Exception ex) {ex.printStackTrace();}
        }//End If
    }//End For
    pixelsToFill.clear();
    System.out.println("Finished Filling.");
    this.repaint();
}
*/