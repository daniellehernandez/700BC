package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Apollo extends RPGCharacter {
    private static final int HEALTH = 350;
    private static final int MANNA = 450;
    private static final int ORDINARY_DAMAGE = 35;
    private static final int SPECIAL_DAMAGE = 50;
    private static final int PAN = 70;
    private static final int REACH = 45;

    public Apollo() throws SlickException {
        super(new Image("res/Characters/Apollo/ApolloWalk2.png"),
                250,
                new Animation(new SpriteSheet("res/Characters/Apollo/Apollo.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/Apollo2.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloCrouch.png", 350, 250) , 250),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloJump.png", 350, 250) , 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, "Apollo");
    }

    public Apollo(int x, boolean direction) throws SlickException {
        super(new Image("res/Characters/Apollo/ApolloWalk2.png"),250,
                new Animation(new SpriteSheet("res/Characters/Apollo/Apollo.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/Apollo2.png", 350, 250), 150),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloCrouch.png", 350, 250) , 250),
                new Animation(new SpriteSheet("res/Characters/Apollo/apolloJump.png", 350, 250) , 250),
                HEALTH, MANNA, ORDINARY_DAMAGE, SPECIAL_DAMAGE, PAN, REACH, x, direction, "Apollo");
    }

}