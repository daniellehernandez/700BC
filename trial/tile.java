package net.bc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static javax.imageio.ImageIO.read;

/**
 * Created by Danielle98 on 11/7/2016.
 */
public class tile {
    public static int size = 32;
    public static int[] blank = {-1,-1};
    public static int[] bricks = {0,0};
    public static int[] newcoloredBricks = {1,0};

    //collision
    //items
    //character

    public static BufferedImage terrain,background,items,characters;
    public tile() {
        try{
            tile.background = read(new File("/res/bg.png"));
            tile.terrain = read(new File("/res/ter.png"));
            tile.characters = read(new File("/res/char.png"));
            tile.items = read(new File("/res/item.png"));
        } catch(Exception e){
            System.out.println("Error Loading Images");
        }
    }
}
