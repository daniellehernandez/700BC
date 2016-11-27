package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Hades extends RPGCharacter {

    private static final int HEALTH = 350;
    private static final int MANNA = 300;
    private static final int ORDINARY_DAMAGE = 25;
    private static final int SPECIAL_DAMAGE = 65;
    private static final int PAN = 40;
    private static final int REACH = 50;

    public Hades() throws SlickException {
        super(new Image("res/Characters/Hades/hadesmove1.png"),190,
                new Animation(new SpriteSheet("res/Characters/Hades/hadesmove1.png", 450, 360), 150),
                new Animation(new SpriteSheet("res/Characters/Hades/Hades2.png", 450, 360), 300),
                new Animation(new SpriteSheet("res/Characters/Hades/hadesmove1.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Hades/hadesmove1.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, "Hades");
    }

    public Hades(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Hades/hadesmove1.png"),190,
                new Animation(new SpriteSheet("res/Characters/Hades/hadesmove1.png", 450, 360), 150),
                new Animation(new SpriteSheet("res/Characters/Hades/Hades2.png", 450, 360), 300),
                new Animation(new SpriteSheet("res/Characters/Hades/hadesmove1.png", 350, 250), 250),
                new Animation(new SpriteSheet("res/Characters/Hades/hadesmove1.png", 350, 250), 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, x, direction, "Hades");
    }
}
