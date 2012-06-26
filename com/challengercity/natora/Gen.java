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
    
    public static void genTunnels(World world) {
        for (int i = 0; i < world.tileCount; i++) {
            if (rand.nextInt(100)<=50) {
                world.tiles[i] = new TileDirt(world, new Rectangle(world.getXFromArrayIndex(i),world.getYFromArrayIndex(i), 32, 32), i);
            }
        }
    }
    
}
