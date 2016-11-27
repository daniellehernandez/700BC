package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Serato, JV on November 25, 2016.
 */
public class Hermes extends RPGCharacter implements Flying{
    private static final int HEALTH = 400;
    private static final int MANNA = 300;
    private static final int ORDINARY_DAMAGE = 35;
    private static final int SPECIAL_DAMAGE = 65;
    private static final int PAN = 100;
    private static final int REACH = 35;

    public Hermes() throws SlickException {
        super(new Image("res/Characters/Hermes/hermeswalk1.png"),175,
                new Animation(new SpriteSheet("res/Characters/Hermes/hermes.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Hermes/Hermes2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Hermes/hermes.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Hermes/hermes.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, "Hermes");
    }

    public Hermes(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Hermes/hermeswalk1.png"),175,
                new Animation(new SpriteSheet("res/Characters/Hermes/hermes.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Hermes/Hermes2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Hermes/hermes.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Hermes/hermes.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, x, direction, "Hermes");
    }
}
