package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class RPGCharacter {
    public String name;
    private float health;
    private int manna;

    public Image staticImage;
    public Animation charAnimate;
    public Animation charATK;
    public Animation charCRO;
    public Animation charJUM;

    public int x;
    public int y = 250;
    public int realX;
    private boolean descending = false;
    public boolean onceJumped = false;
    public boolean move = false;
    public boolean atk = false;
    public boolean crouch = false;
    public boolean direction = false; // FALSE indicates RIGHT
    public boolean firstTimeOnLeft = false;
    public boolean firstTimeOnRight = true;
    public boolean attacked = false;

    RPGCharacter(Image staticImage, Animation charAnimate, Animation charATK, Animation charCRO, Animation charJUM, int health, int manna, String name) throws SlickException {
        this(staticImage, charAnimate, charATK, charCRO, charJUM, health, manna, 10, false, name);
    }

    RPGCharacter(Image staticImage, Animation charAnimate, Animation charATK, Animation charCRO, Animation charJUM, int health, int manna, int x, boolean direction, String name) throws SlickException {
        this.staticImage = staticImage;
        this.charAnimate = charAnimate;
        this.charATK = charATK;
        this.charCRO = charCRO;
        this.charJUM = charJUM;
        this.health = health;
        this.manna = manna;
        this.x = x;
        this.realX = x;
        this.direction = direction;
        this.name = name;
    }

    public boolean isNear(RPGCharacter opponent, int distance, boolean shift) {
        if (shift) {
            return this.realX >= opponent.realX - distance && this.realX <= opponent.realX + distance;
        } else {
            boolean ret = this.realX >= opponent.realX - distance && this.realX <= opponent.realX + distance;
            if (ret) {
                if (this.realX >= opponent.realX - distance && this.realX <= opponent.realX) { // op -- this
                    this.direction = true;
                } else { // this -- op
                    this.direction = false;
                }
            }
            return ret;
        }
    }

    public boolean isNear(RPGCharacter opponent, int distance) {
        boolean ret = this.realX >= opponent.realX - distance && this.realX <= opponent.realX + distance;
        if (ret) {
            if (this.realX >= opponent.realX - distance && this.realX <= opponent.realX) { // op -- this
                this.direction = true;
            } else { // this -- op
                this.direction = false;
            }
        }
        return ret;
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



    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getManna() {
        return manna;
    }

    public void setManna(int manna) {
        this.manna = manna;
    }

    public void takeDamage(float damage){
        setHealth(getHealth() - damage);
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }
}
