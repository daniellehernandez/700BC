package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Athena extends RPGCharacter implements Melee{
    private static final int HEALTH = 350;
    private static final int MANNA = 300;
    private static final int ORDINARY_DAMAGE = 20;
    private static final int SPECIAL_DAMAGE = 50;
    public static final int PAN = 90;

    public Athena() throws SlickException {
        super(new Image("res/Characters/Athena/AthenaAttack1.png"),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, "Athena");
    }

    public Athena(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Athena/AthenaAttack1.png"),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, x, direction, "Athena");
    }
}
