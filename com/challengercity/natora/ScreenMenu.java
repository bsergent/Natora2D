/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.challengercity.natora;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
/**
 *
 * @author Ben Sergent V/ha1fBit
 */
public class ScreenMenu extends Screen {

    public ScreenMenu(Natora nt) {
        super(nt);
        Natora.gs=EnumGameState.MENU;
        startup();
    }
    
    public void startup() {
        GUI b;
        b = new GUIButton(Natora.screenWidth/2-100, 180, 200, 30, 0, 0, 160, 30, 0, this, "GUISinglePlayer", ".PNG");
        addToRenderList(b);
        //b.hide();
        b = new GUIButton(Natora.screenWidth/2-100, 220, 200, 30, 0, 0, 160, 30, 1, this, "GUIMultiPlayer", ".PNG");
        addToRenderList(b);
        //b.hide();
        b = new GUIButton(Natora.screenWidth/2-100, 260, 200, 30, 0, 0, 160, 30, 2, this, "GUIControls", ".PNG");
        addToRenderList(b);
        //b.hide();
        b = new GUIButton(Natora.screenWidth/2-100, 300, 200, 30, 0, 0, 160, 30, 3, this, "GUIAbout", ".PNG");
        addToRenderList(b);
        //b.hide();
        b = new GUIButton(Natora.screenWidth/2-100, 340, 200, 30, 0, 0, 160, 30, 4, this, "GUIExit", ".PNG");
        addToRenderList(b);
        //b.hide();
        b = new GUIImageLogo(Natora.screenWidth/2-195, Natora.screenHeight/2-66, 400, 112, 0, 0, 400, 112, this,"NatoraLogo", ".PNG");
        addToRenderList(b);
    }
    
    public void actionPerformed(int id) {
        if (id==0) {
            Renderer.removeFromRenderList(this);
            nt.currentScreen = new ScreenGame(nt);
        }
    }
    
    public class GUIImageLogo extends GUIImage {
        public GUIImageLogo (int x, int y, int width, int height, int picX, int picY, int picWidth, int picHeight, Screen screen, String texName, String texExt) {
            super(x, y, width, height, picX, picY, picWidth, picHeight, screen, texName, texExt);
        }
        public boolean done;
        public void draw() {
            super.draw();
            if (!done) {
                posY=posY-5;
                for (int i = 0; i<renderList.size(); i++) {
                    RenderableObject ro = renderList.get(i);
                    if (this.intersects(ro)) {
                        ro.hide();
                    }
                }
                if (posY<=20) {
                    done = true;
                }
            }
        }
    }
}
