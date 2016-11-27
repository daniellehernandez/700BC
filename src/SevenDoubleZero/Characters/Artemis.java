package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Artemis extends RPGCharacter {

    private static final int HEALTH = 300;
    private static final int MANNA = 300;
    private static final int ORDINARY_DAMAGE = 35;
    private static final int SPECIAL_DAMAGE = 65;
    private static final int PAN = 100;
    private static final int REACH = 55;

    public Artemis() throws SlickException {
        super(new Image("res/Characters/Artemis/artemiswalk1.png"),250,
                new Animation(new SpriteSheet("res/Characters/Artemis/Artemis.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Artemis/Artemis2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Artemis/ArtemisCrouch.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Artemis/ArtemisJump.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, "Artemis");
    }

    public Artemis(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Artemis/artemiswalk1.png"),250,
                new Animation(new SpriteSheet("res/Characters/Artemis/Artemis.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Artemis/Artemis2.png", 350, 250), 100),
                new Animation(new SpriteSheet("res/Characters/Artemis/ArtemisCrouch.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Artemis/ArtemisJump.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, x, direction, "Artemis");
    }
}
