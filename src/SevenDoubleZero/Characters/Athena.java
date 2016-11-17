package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Athena extends RPGCharacter {
    private static final int HEALTH = 350;
    private static final int MANNA = 300;

    public Athena() throws SlickException {
        super(new Image("res/Characters/Athena/AthenaAttack1.png"),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                HEALTH, MANNA);
    }

    public Athena(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Athena/AthenaAttack1.png"),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                HEALTH, MANNA, x, direction);
    }
}
