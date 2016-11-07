package net.bc;



import javafx.scene.layout.Background;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;


/**
 * Created by Hernandez, Amora, Acal, Serato
 */
public class Level {
    public int width = 100, height = 100;
    public Background[][] bg = new Background[width][height];
    public final String Dpath = "res/world/level_"; //when shifting to another place
    public String path = Dpath;

    public TiledMap map = null;

    public Level(int id){
        path = Dpath+Integer.toString(id) + ".tmx";
        System.out.println(path);

        try{
            new TiledMap(path,false);
        }catch(SlickException e){
            System.out.println("Error Loading Map");
        }


        for(int x = 0; x < bg.length; x++ ){
            for(int y = 0; y < bg[0].length; y++) {
               Rectangle recs = new Rectangle(x * tile.size, y * tile.size, tile.size, tile.size;
                bg[x][y] = new Background(recs);
            }
        }
    }
}
