package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Sotto and Serato on November 16, 2016.
 */
public class Apollo extends RPGCharacter {
    public Apollo() throws SlickException {
        super(new Animation(new SpriteSheet("res/Characters/Apollo/Apollo.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/Apollo2.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloCrouch.png", 350, 250) , 250),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloJump.png", 350, 250) , 250));
    }

    public Apollo(int x) throws SlickException {
        super(new Animation(new SpriteSheet("res/Characters/Apollo/Apollo.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/Apollo2.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloCrouch.png", 350, 250) , 250),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloJump.png", 350, 250) , 250),
                x);
    }
}