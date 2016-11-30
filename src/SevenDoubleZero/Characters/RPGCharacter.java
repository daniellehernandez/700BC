package SevenDoubleZero.Characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class RPGCharacter {
    public String name;
    private int health;
    private int manna;
    private int damage;
    private int spDamage;
    private int pan;
    public int reach;

    public Image staticImage;
    public Image charHurt;
    public Animation charAnimate;
    public Animation charATK;
    public Animation charCRO;
    public Animation charJUM;

    public int x;
    public int y;
    public int realX;
    public int hurtX;
    private boolean descending = false;
    public boolean up = true;
    public boolean down = false;
    public boolean corner = false;
    public boolean onceJumped = false;
    public boolean move = false;
    public boolean atk = false;
    public boolean crouch = false;
    public boolean direction = false; // FALSE indicates RIGHT
    public boolean firstTimeOnLeft = false;
    public boolean firstTimeOnRight = true;
    public boolean attacked = false;
    public boolean hurt = false;

    RPGCharacter(Image staticImage, int y, Animation charAnimate, Animation charATK, Animation charCRO, Animation charJUM, int health, int manna, int damage, int spDamage, int pan, int reach, String name) throws SlickException {
        this(staticImage, y, charAnimate, charATK, charCRO, charJUM, health, manna, damage, spDamage, pan, reach, 10, false, name);
    }

    RPGCharacter(Image staticImage, int y, Animation charAnimate, Animation charATK, Animation charCRO, Animation charJUM, int health, int manna, int damage, int spDamage, int pan, int reach, int x, boolean direction, String name) throws SlickException {
        this.staticImage = staticImage;
        this.charHurt = staticImage;
        this.y = y;
        this.charAnimate = charAnimate;
        this.charATK = charATK;
        this.charCRO = charCRO;
        this.charJUM = charJUM;
        this.health = health;
        this.manna = manna;
        this.damage = damage;
        this.spDamage = spDamage;
        this.pan = pan;
        this.reach = reach;
        this.x = x;
        this.realX = x;
        this.direction = direction;
        this.name = name;
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

    public boolean cornered(RPGCharacter opponent, int x){
        return opponent.attacked && (x <= -75 || x >= 425);
    }

    public void attack(RPGCharacter opponent, Animation newLevel, GameContainer gc) {
        if (charATK.getCurrentFrame().equals(charATK.getImage(1)) && ((direction && realX > opponent.realX) || (!direction && realX < opponent.realX))) {
            attacked = true;
        }
        else if (charATK.getCurrentFrame().equals(charATK.getImage(2)) && attacked) {
            opponent.takeDamage(getDamage());
            attacked = false;
            opponent.hurt = true;
            if (opponent.realX < realX) {
                opponent.hurtX = opponent.realX - 70;
            } else {
                opponent.hurtX = opponent.realX + 70;
            }
        }/*
        if (!opponent.isAlive()) {
            newLevel.draw(45, 45);
        }*/
        if (onceJumped) {
            jump(charATK);
        } else {
            charATK.getCurrentFrame().getFlippedCopy(direction, false).draw(x, y);
        }
        atk = false;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpDamage() {
        return spDamage;
    }

    public void setSpDamage(int spDamage) {
        this.spDamage = spDamage;
    }

    public int getPan() {
        return pan;
    }

    public int getManna() {
        return manna;
    }

    public void setManna(int manna) {
        this.manna = manna;
    }

    public void takeDamage(int damage){
        if (getHealth() - damage < 0) {
            setHealth(0);
        } else {
            setHealth(getHealth() - damage);
        }
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }
}
