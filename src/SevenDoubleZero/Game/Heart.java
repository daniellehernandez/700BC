package SevenDoubleZero.Game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Heart extends PowerUp{

    public Heart() throws SlickException{
        super(new Image("res/Maps/heart.png").getScaledCopy(100, 100));
    }
}
