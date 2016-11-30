package SevenDoubleZero.Game;

import SevenDoubleZero.Characters.RPGCharacter;

public class Hero {
    private static Hero hero= null;
    RPGCharacter player;

    private Hero(){

    }

    public static Hero getInstance() {
        if (hero == null) {
            hero = new Hero();
        }
        return hero;
    }

    public void setHero(RPGCharacter player) {
        this.player = player;
    }

    public RPGCharacter getPlayer() {
        return player;
    }
}
