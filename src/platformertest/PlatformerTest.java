/*
 // To change this license header, choose License Headers in Project Properties.
 //To change this template file, choose Tools | Templates
 // and open the template in the editor. yes.
 package platformertest;

 import java.awt.Canvas;
 import java.awt.Color;
 import java.awt.Graphics;
 import javax.swing.JPanel;
 //import javax.swing.JPanel;

 /**
 *
 // @author Felix
 
 public class PlatformerTest extends JPanel{

 /**
 //param args the command line arguments
     
 public static void main(String[] args) {
 PlatformerTest pft = new PlatformerTest();
 Graphics g = null;
 JPanel panel = new JPanel();
 Canvas canvas = new Canvas();
 panel.setSize(400,400);
 canvas.setSize(400, 400);
 pft.paintComponent(g);
 pft.setSize(400, 400);
        
 }
    
 public int gamex(int x){
 return x*40;
 }
 public int gamey(int y){
 return y*40;
 }
 public void drawsquare(int x, int y){
        
        
 }
    
 public void paintComponent(Graphics g)
 {
 super.paintComponent(g);
 g.setColor(Color.red);
 g.drawRect(10, 10, 20, 20);
        
 }

    
 }
 */
package platformertest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.sound.midi.*;

@SuppressWarnings("serial")
public class PlatformerTest extends JPanel implements KeyListener {

    public int frameheight = 400;
    int x = 0;
    int y = 0;
    static int guyx = 0;
    static int guyy = 0;
    // public static int[] brickx = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 7, 13, 6, 7, 8};
    public static ArrayList<Integer> brickx = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 7, 13, 6, 7, 8));// = {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 3, 6, 1, 4, 4, 4};    static int tempcounter = 0;
    public static ArrayList<Integer> bricky = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 3, 6, 1, 4, 4, 4));// = {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 3, 6, 1, 4, 4, 4};    static int tempcounter = 0;
    public static boolean leftpressed = false;
    public static boolean rightpressed = false;
    public static boolean uppressed = false;
    public static boolean downpressed = false;
    public static int guyvelocity = 0;
    public static boolean gravityflipper = false;//used to only do gravity half the times
    public static boolean onground = true;
    // public static boolean hasdubjumped = false;//used  to see if you have used your double jump or still have it
    public static boolean willdubjump = false;
    public static boolean hasjumped = false;
    public static boolean hasdubjumped = false;
    public static byte gravitycounter = 0;//used to only do gravity even less
    public static byte gravity = 2;
    public static int jumpheight = 10;
    public static boolean restart = false;
    public static int guyxvelocity = 0;
    public static String fallsoundmp3 = "fallsound.mp3";
    public static boolean rightofblock = false;
    public static boolean leftofblock = false;
    public static boolean inblock = false;
    public static boolean underblock = false;
    public static boolean downflipper = false;
    public static boolean touchingsideblock = false;
    public static boolean donesliding = false;
    public static boolean bouncing = false;
    public static boolean bounce = false;
    public static int fpstimefinder = 0;
    public static int framedelay = 60;
    public static int framesthissecond = 0;
    public static int gamedelay = 10;
    public static int desiredfps = 60;
    public static boolean delayflipper = false;
    public static int lvleditx = 1;
    public static int lvledity = 1;
    public static int menuselect = 0;
    public static int menuscreen = 0;
    public static int gamecyclesthissecond = 0;
    public static int gamespeed = 0;
    public static int gamespeedcyclestart = 0;
    public static int menuselectmax = 1;
    public static int menuselectmin = 0;
    public static int desiredgamespeed = 60;
    public static boolean leveledit = false;
    public static boolean hasmoved = false;
    public static boolean deleteblock = false;
    public static boolean willbeinblock = false;

    public static boolean stayinmenu = true;//  makes the paint know if it should generate the menu

    public static String menumessage1 = "play";
    boolean running = false;
    int fps = 60;

    //   BufferedImage testtile = ImageIO.read(new File(ClassLoader.getSystemResourceAsStream("platformertest/testtile.png")));
    public PlatformerTest() {
        super();
        this.addKeyListener(this);
        BufferedImage imge = null;
    }

    private void moveBall() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.fillOval(x, y, 30, 30);

        if (stayinmenu) {
            drawMenu(g2d);

        } else if (leveledit) {
            if (brickx.size() == bricky.size()) {
                for (int i = 0; i < brickx.size(); i++) {
                    generateGraphics(i, g2d);
                }
                g2d.setColor(Color.red);
                g2d.drawRect(tilex(lvleditx) + guyx, abs(tiley(lvledity) - this.getHeight()), 40, 40);
                System.out.println(lvleditx + ", " + lvledity);
            } else {
                g2d.drawString("Something went terribly wrong(X and Y mismatch)", 30, 30);
            }

        } else {

            if (brickx.size() == bricky.size()) {

                g2d.setColor(Color.black);
                onground = false;//assume not on ground unless proven otherwise later
                rightofblock = false;//same deal
                leftofblock = false;
                underblock = false;
                willbeinblock = false;
                for (int i = 0; i < brickx.size(); i++) {
                    generateGraphics(i, g2d);
                }
                inblock = willbeinblock;

                framesthissecond++;
                if ((int) System.currentTimeMillis() > fpstimefinder + 1000) {
                    fps = framesthissecond;
                    framesthissecond = 0;
                    fpstimefinder = (int) System.currentTimeMillis();
                    if (delayflipper) {
                        if (fps > desiredfps + 10) {
                            framedelay++;

                        } else if (fps < desiredfps - 10) {
                            if (framedelay > 0) {
                                framedelay--;
                            }
                        }
                        delayflipper = false;
                    } else {
                        delayflipper = true;
                    }

                    System.out.println(framedelay + " framedelay");
                }
                g2d.setColor(Color.green);
                g2d.fillRect(this.getWidth() / 2, guyy, 10, 20);//make the guy in the middle of the screen
                g2d.setColor(Color.black);
                g2d.drawString(fps + "fps", 30, 30);

            } else {
                g2d.drawString("Something went terribly wrong(X and Y mismatch)", 30, 30);
            }

        }
    }

    private void drawMenu(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(this.getWidth() / 2 - 200, this.getHeight() / 2 - 20, 80, 40);//0
        g2d.fillRect(this.getWidth() / 2 + 100, this.getHeight() / 2 - 20, 80, 40);//1
        g2d.setColor(Color.decode("0x37B7E9"));
        g2d.drawString(menumessage1, this.getWidth() / 2 - 175, this.getHeight() / 2 + 5);
        g2d.drawString("edit", this.getWidth() / 2 + 125, this.getHeight() / 2 + 5);

        if (menuselect == 0) {
            g2d.setColor(Color.decode("0xA0B54D"));
            g2d.drawRect(this.getWidth() / 2 - 200, this.getHeight() / 2 - 20, 80, 40);
            g2d.setColor(Color.decode("0xAA66CC"));
            g2d.drawString(menumessage1, this.getWidth() / 2 - 175, this.getHeight() / 2 + 5);
        }
        if (menuselect == 1) {
            g2d.setColor(Color.decode("0xA0B54D"));
            g2d.drawRect(this.getWidth() / 2 + 100, this.getHeight() / 2 - 20, 80, 40);
            g2d.setColor(Color.decode("0xAA66CC"));
            g2d.drawString("edit", this.getWidth() / 2 + 125, this.getHeight() / 2 + 5);

        }

        writeStrings(g2d);
    }

    private void writeStrings(Graphics2D g2d) {

        //    g2d.drawImage(brickimg, null, this);
    }

    private void generateGraphics(int brickIndex, Graphics2D g2d) {
        if (tilex(brickx.get(brickIndex)) + guyx <= this.getX() - 40 || tilex(brickx.get(brickIndex)) + guyx >= this.getWidth())//exit if its not in the screen...
        {
            return;
        }

        g2d.fillRect((tilex(brickx.get(brickIndex))) + guyx, abs(tiley(bricky.get(brickIndex)) - this.getHeight()), 40, 40); //draw a box in accordance to the array value

        calculateCollision(brickIndex);

        //g2d.drawRect(abs(tiley(bricky.get(brickIndex))) - this.getHeight(), abs(tiley(bricky.get(brickIndex)) - this.getHeight()) + 40, brickIndex, jumpheight);

        //  g2d.fillRect(this.getWidth()/2,guyy,10,20);//draw guy in middle of screen
        // g2d.fillRect((tilex(brickx.get(i))) + guyx, (tiley(bricky.get(i))), 40, 40);
        //g2d.draw
    }

    private void calculateCollision(int brickIndex) {
        if ((tilex(brickx.get(brickIndex))) + guyx < this.getWidth() / 2 + 10 && (tilex(brickx.get(brickIndex))) + guyx > this.getWidth() / 2 - 40) {//if that block's in the same x pos as the guy...
            // onground = true;
            if (guyy < abs(tiley(bricky.get(brickIndex)) - this.getHeight()) && (guyy > abs(tiley(bricky.get(brickIndex)) - this.getHeight()) - 21)) {//and he's on it...
                onground = true;
                if (guyvelocity < 0) {                                                                //declare he's on the ground, set his velocity to 0

                    bounce = true;
                }

                guyy = abs(tiley(bricky.get(brickIndex)) - this.getHeight()) - 20;//snap to block
            }

        }

        if ((tilex(brickx.get(brickIndex))) + guyx < (this.getWidth() / 2) + 11 && (tilex(brickx.get(brickIndex) + 1)) + guyx > (this.getWidth() / 2) - 1) {
            if (abs(tiley(bricky.get(brickIndex)) - this.getHeight()) < guyy && abs(tiley(bricky.get(brickIndex) - 1) - this.getHeight()) > guyy) {
                if ((tilex(brickx.get(brickIndex) + 1)) + guyx > (this.getWidth() / 2) + 11) {
                    System.out.println("ayy");
                    leftofblock = true;
                }
                if ((tilex(brickx.get(brickIndex))) + guyx < (this.getWidth() / 2) - 11) {
                    System.out.println("lmao");
                    rightofblock = true;
                }
            }

        }
        if (guyNearBrickSide(brickIndex)) {

            if (abs(tiley(bricky.get(brickIndex)) - this.getHeight()) < guyy && abs(tiley(bricky.get(brickIndex) - 1) - this.getHeight()) > guyy + 4) {

                if ((tilex(brickx.get(brickIndex) + 1)) + guyx > (this.getWidth() / 2) + 10) {
                    //
                    willbeinblock = true;
                    System.out.println("l");
                    leftofblock = true;
                    //while((tilex(brickx.get(brickIndex) + 1)) + guyx > (this.getWidth() / 2) + 10){
                    guyx += 2;

                    // }
                    //System.out.println((tilex(brickx.get(brickIndex) + 1)));
                }/*else if ((tilex(brickx.get(brickIndex) + 1)) + guyx > (this.getWidth() / 2) + 11){
                 leftofblock = true; 
                 }*/ else {
                    leftofblock = false;
                }

                if ((tilex(brickx.get(brickIndex))) + guyx < (this.getWidth() / 2) - 10) {
                    //                                   System.out.println("rr");
                    willbeinblock = true;
                    guyx -= 2;
                    rightofblock = true;

                    /*} else if ((tilex(brickx.get(brickIndex))) + guyx < (this.getWidth() / 2) - 11){
                     rightofblock = true;
                     */                } else {
                    rightofblock = false;
                    

                }

            }

        }
        if (guyWithinBrickX(brickIndex)) {//if that block's in the same x pos as the guy...
            // onground = true;

            // System.out.println("wom1");
            if (guyy < abs(tiley(bricky.get(brickIndex)) - this.getHeight() - 41) && (guyy > abs(tiley(bricky.get(brickIndex)) - this.getHeight()))) {//and he's under it...
                // onground = true;
                //if(guyvelocity < 0){  
                //declare he's on the c, set his velocity to 0
                if (!inblock) {
                    
                    guyvelocity = abs(guyvelocity / 2) * -1;
                    underblock = true;
                }
                // }
                //                               System.out.println("owmyhead");
                //guyy =  abs(tiley(bricky.get(i))-this.getHeight())-20;//snap to block
            }

        }

    }

    private boolean guyNearBrickSide(int brickIndex) {
        return (tilex(brickx.get(brickIndex))) + guyx < (this.getWidth() / 2) + 10 && (tilex(brickx.get(brickIndex) + 1)) + guyx > (this.getWidth() / 2) - 0/* abs(tiley(bricky.get(i))-this.getHeight())*/;
    }

    private boolean guyWithinBrickX(int i) {
        return (tilex(brickx.get(i))) + guyx > this.getWidth() / 2 - 40 && (tilex(brickx.get(i))) + guyx < this.getWidth() / 2 + 10;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();

        PlatformerTest game = new PlatformerTest();
        frame.addKeyListener(game);
        frame.add(game);
        frame.setSize(600, 430);
        // frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setResizable(true);
        Thread.sleep(100);
        importImages();
        
        //Media fallsound = new Media(fallsoundmp3);
        //MediaPlayer mp = new MediaPlayer(fallsound);
        System.out.println(System.currentTimeMillis());
        fpstimefinder = (int) System.currentTimeMillis();
        boolean running = true;

        Thread physicsThread = new Thread() {
            @Override
            public void run() {
                Dimension oldsize = null;
                while (running) {
                    /* */
                    game.moveBall();

                    gamecyclesthissecond++;
                    if ((int) System.currentTimeMillis() > gamespeedcyclestart + 1000) {
                        gamespeed = gamecyclesthissecond;
                        gamecyclesthissecond = 0;
                        gamespeedcyclestart = (int) System.currentTimeMillis();
                        //if (delayflipper) {
                        if (gamespeed > desiredgamespeed + 10) {
                            gamedelay++;

                        } else if (gamespeed < desiredgamespeed - 10) {
                            gamedelay--;
                        }
                        //   delayflipper = false;
                        // } else {
                        //    delayflipper = true;
                        // }

                        System.out.println(gamedelay);
                    }
                    try {
                        Thread.sleep(gamedelay);
                    } catch (InterruptedException ex) {
                        //probably closing, nothing i can do

                    }
                    KeyEvent e = null;
                    if (gravitycounter > gravity && guyvelocity > -30 && !onground) {
                        guyvelocity--;
                        gravitycounter = 0;
                    } else {   //makes gravity real, only runs every (gravitycounter)st operation

                        gravitycounter++;
                    }

                    /* tempcounter++;
                        
                     if(tempcounter > 10){
                     guyx++;
                     }*/
                    if (onground && !inblock) {//ground refresh jumps 
                        willdubjump = false;
                        hasjumped = false;
                        hasdubjumped = false;
                        downflipper = false;

                    }
                    if (bounce) {
                        bounce = false;
                        bouncing = true;
                        guyvelocity = abs(guyvelocity / 8);

                        willdubjump = false;
                        hasjumped = false;
                        hasdubjumped = false;
                        downflipper = false;

                    }

                    if (leftpressed) {
                        if (leveledit) {
                            if (tilex(lvleditx) + guyx < 0) {//move level when in level edit, repeated below if not holding key
                                guyx += 5;
                            }

                            if (!hasmoved) {
                                lvleditx--;
                                hasmoved = true;
                            }
                        } else {
                            if (!rightofblock) {
                                if (guyxvelocity < 18) {
                                    if (guyxvelocity > 6) {
                                        donesliding = false;
                                    }
                                    guyxvelocity += 2;

                                }
                            }
                        }

                    }
                    /*if(rightofblock){
                     System.out.println("rob");
                     }else{System.out.println("nrob");}*/

                    if (rightpressed) {
                        if (leveledit) {
                            if (tilex(lvleditx) + guyx + tilex(2) > frame.getWidth()) {
                                guyx -= 5;
                            }
                            if (!hasmoved) {
                                lvleditx++;
                                hasmoved = true;

                            }
                        } else {
                            if (!leftofblock) {
                                if (guyxvelocity > -18) {
                                    if (guyxvelocity < -6) {
                                        donesliding = false;
                                    }
                                    guyxvelocity -= 2;
                                }
                            }
                        }
                    }
                    if(!(frame.getSize().equals(oldsize)) && !leveledit){
                        restart=true;
                        oldsize=frame.getSize();
                        
                    }
                    if (uppressed) {//when up is pressed, if you're on the ground you execute a normal jump and set the variable.
                        if (leveledit) {
                            if (!hasmoved) {
                                lvledity++;
                                hasmoved = true;
                            }

                        } else {
                            if ((onground || bouncing) && !inblock) {
                                guyvelocity = 8;
                                hasjumped = true;
                                onground = false;
                                bouncing = false;
                                System.out.println("sings");

                            } else if (rightofblock && rightpressed && !underblock) {

                                guyvelocity = 8;
                                System.out.println("wj");
                                guyxvelocity = -7;
                                //willdubjump = true;
                                hasdubjumped = false;
                                onground = false;
                                rightofblock = false;
                                hasjumped=true;

                            } else if (leftofblock && leftpressed && !underblock) {//wall jumps

                                guyvelocity = 8;
                                System.out.println("wj");
                                guyxvelocity = 7;

                                hasdubjumped = false;
                                hasjumped = true;
                                onground = false;
                                leftofblock = false;

                            } else {
                                if (willdubjump && !hasdubjumped) {
                                    
                                    guyvelocity = 5;

                                    hasdubjumped = true;
                                    hasjumped = true;
                                    System.out.println("dubs");
                                }
                            }
                        }
                    }
                    if (uppressed == false) {
                        if (hasjumped = true) {
                            willdubjump = true;
                        }
                    }//determine if you will double jump
                    if (downpressed) {
                        if (leveledit) {
                            if (!hasmoved) {
                                lvledity--;
                                hasmoved = true;
                            }
                        } else {

                            if (!downflipper && guyvelocity < 2) {
                                guyvelocity -= 5;
                                downflipper = true;
                            }
                        }
                    }

                    // System.out.println(onground);
                    if (restart) {
                        restart = false;
                        guyy = abs(tiley(3) - frame.getHeight());
                        guyx = (frame.getWidth() / 2) - 300;
                        guyvelocity = 3;
                        willdubjump = true;
                        hasdubjumped = false;
                        guyxvelocity = 0;

                    }//for restarting by pressing r

                    if (!onground || ((guyvelocity > 0) && onground)) { //only jump if in air or velocity is positive on ground
                        guyy = guyy - guyvelocity;
                    }//makes you move by guyvelocity amount

                    if (guyy > frame.getHeight())//if you're below the screen
                    {

                        restart = true;
                    }
                    guyx += guyxvelocity / 6;

                    if (guyxvelocity < 0) {
                        guyxvelocity++;

                    }

                    if (stayinmenu) {
                        if (!frame.isResizable()) {
                            frame.setResizable(true);
                        }

                    }
                    if (guyxvelocity > 0) {
                        guyxvelocity--;

                    }
                    if (!donesliding) {
                        if (rightofblock || leftofblock) {
                            guyxvelocity = 0;
                            donesliding = true;
                        }
                    }
                    /*if (leveledit && !uppressed && !downpressed && !leftpressed & !rightpressed) {
                     hasmoved = false;
                     if (tilex(lvleditx) + guyx + tilex(2) > frame.getWidth()) {
                     guyx -= 5;
                     }
                     if (tilex(lvleditx) + guyx < 0) {
                     guyx += 5;
                     }
                     }*/
                    if (leveledit) {
                        if (!frame.isResizable()) {
                            frame.setResizable(true);
                        }
                        if (!uppressed && !downpressed && !leftpressed & !rightpressed) {
                            hasmoved = false;
                            if (tilex(lvleditx) + guyx + tilex(2) > frame.getWidth()) {
                                guyx -= 5;
                            }
                            if (tilex(lvleditx) + guyx < 0) {
                                guyx += 5;
                            }
                        }
                        /*if(deleteblock){
                         deleteblock = false;
                         for(int i = 0; i<brickx.size();i++){
                         if(brickx.get(i) == lvleditx){
                         if(bricky.get(i)== lvledity)
                         brickx.remove(i);
                         bricky.remove(i);
                         System.out.println(brickx.size());
                         System.out.println(bricky.size());
                         }
                         }
                        
                         }*/

                    }

                }
            }
        };

        physicsThread.start();
        while (running) {
            game.repaint();
            Thread.sleep(framedelay);

        }
    }

    private static void importImages() {
        BufferedImage img = null;
    }

    public static int tilex(int x) {
        return x * 40;
    }

    public static int tiley(int y) {
        return y * 40;
    }
    /* public static char keyTyped(KeyEvent e) {
     return e.getKeyChar();
     }*/

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        //System.out.println(e.getKeyChar());
        if (key == 'a') {
            // guyx += -1;
            leftpressed = true;

        }
        if (key == 'd') {
            // guyx++;
            rightpressed = true;
        }
        if (key == 's') {
            // guyy += -1;
            downpressed = true;
        }
        if (key == 'w') {
            // guyy += 1;
            uppressed = true;
        }
        if (key == 'l') {
            // guyy += 1;
            uppressed = true;
        }
        if (key == ' ') {
            // guyy += 1;
            if (leveledit) {
                brickx.add(lvleditx);
                bricky.add(lvledity);
            }
        }
        if (key == '0') {
            if (leveledit) {
                boolean placeblock = true;
                for (int i = 0; i < brickx.size(); i++) {
                    if ((brickx.get(i) == lvleditx)) {
                        if ((bricky.get(i) == lvledity)) {

                            placeblock = false;
                            break;
                        }

                    }

                }
                if (placeblock) {
                    brickx.add(lvleditx);
                    bricky.add(lvledity);
                }

            }
        }
        if (key == '.') {
            //   deleteblock = true;
            //   deleteblock = false;
            for (int i = 0; i < brickx.size(); i++) {
                if (brickx.get(i) == lvleditx) {
                    if (bricky.get(i) == lvledity) {
                        brickx.remove(i);
                        bricky.remove(i);
                        System.out.println("");
                        System.out.println(brickx.size());
                        System.out.println(bricky.size());
                        System.out.println("");
                        i++;
                    }

                }
            }
            /*Iterator<Integer> i = brickx.iterator();
             while(i.hasNext()){
             i.next();
                
             if (brickx.get(i) == lvleditx) {
             if (bricky.get(i) == lvledity) {
             i.remove();
             }
             }
             }*/

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();

        if (key == 'r') {
            restart = true;
        }
        if (key == 27) {
            if (stayinmenu) {
                System.exit(0);
            }
            leveledit = false;
            stayinmenu = true;
            System.out.println("esc");
            guyx = 0;

        }
        if (stayinmenu) {
            if (key == 'd') {
                if (menuselect < menuselectmax) {
                    menuselect++;
                }
            }
            if (key == 'a') {
                if (menuselect > menuselectmin) {
                    menuselect--;
                }
            }
            if (key == ' ') {
                if (menuselect == 0) {//play clicked
                    stayinmenu = false;
                    framedelay = 10;

                }
                if (menuselect == 1) {//level edit clicked    
                    leveledit = true;
                    stayinmenu = false;
                }
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        System.out.println(e.getKeyChar());
        if (key == 'a') {
            // guyx += -1;
            leftpressed = false;
        }
        if (key == 'd') {
            // guyx++;
            rightpressed = false;
        }
        if (key == 's') {
            //  guyy += -1;
            downpressed = false;
        }
        if (key == 'w') {
            //  guyy += 1;
            uppressed = false;
        }
    }

    public void showMainMenu() {

    }
}
