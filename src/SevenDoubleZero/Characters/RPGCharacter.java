package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

public abstract class RPGCharacter {
    public Animation charAnimate;
    public Animation charATK;
    public Animation charCRO;
    Animation charJUM;

    public int x = 10;
    public int y = 250;
    private boolean descending = false;
    public boolean onceJumped = false;
    public boolean move = false;
    public boolean atk = false;
    public boolean crouch = false;
    public boolean direction = false;
    public boolean firstTimeOnLeft = false;
    public boolean firstTimeOnRight = false;

    RPGCharacter(Animation charAnimate, Animation charATK, Animation charCRO, Animation charJUM) throws SlickException {
        this.charAnimate = charAnimate;
        this.charATK = charATK;
        this.charCRO = charCRO;
        this.charJUM = charJUM;
    }

    RPGCharacter(Animation charAnimate, Animation charATK, Animation charCRO, Animation charJUM, int x) throws SlickException {
        this.charAnimate = charAnimate;
        this.charATK = charATK;
        this.charCRO = charCRO;
        this.charJUM = charJUM;
        this.x = x;
    }

    public boolean isNear(RPGCharacter opponent, int distance) {
        return this.x >= opponent.x - distance && this.x <= opponent.x + distance;
    }

    public void jump() {
        jump(charJUM);
    }

    public void jump(Animation anim) {
        anim.getCurrentFrame().getFlippedCopy(direction, false).draw(x, y);
        if (!(descending)) {
            y -= 1;
            if (y == 150) {
                descending = true;
            }
        } else {
            if (y < 250) {
                y += 1;
            } else {
                onceJumped = false;
                descending = false;
            }
        }
    }
}
