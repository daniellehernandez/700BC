package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Athena extends RPGCharacter {
    private static final int HEALTH = 375;
    private static final int MANNA = 300;
    private static final int ORDINARY_DAMAGE = 45;
    private static final int SPECIAL_DAMAGE = 65;
    private static final int PAN = 100;
    private static final int REACH = 35;

    public Athena() throws SlickException {
        super(new Image("res/Characters/Athena/AthenaAttack1.png"),250,
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, "Athena");
    }

    public Athena(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Athena/AthenaAttack1.png"),250,
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Athena/Athena.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, x, direction, "Athena");
    }
}
