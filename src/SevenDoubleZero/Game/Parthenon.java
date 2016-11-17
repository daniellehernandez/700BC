package SevenDoubleZero.Game;

import SevenDoubleZero.Characters.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class Parthenon extends BasicGameState {
    private RPGCharacter player;
    private RPGCharacter ai;
    private Animation bg;

    Parthenon() {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Apollo();
        ai = new Athena(350, true);
        bg = new Animation(new SpriteSheet("res/Maps/Parthenons.png", 700, 500), 1500);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0, 0);

        if (player.move) {
            if (player.onceJumped) {
                if (player.atk) {
                    player.jump(player.charATK);
                } else {
                    player.jump();
                }
            } else if (player.atk) {
                player.charATK.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            } else {
                player.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.move = false;
        } else if (player.atk) {
            if (player.onceJumped) {
                player.jump(player.charATK);
            } else {
                player.charATK.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.atk = false;
        } else if (player.crouch) {
            player.y = 250;
            player.charCRO.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            player.crouch = false;
        } else if (player.onceJumped) {
            player.jump();
        } else {
            g.drawImage(player.staticImage.getFlippedCopy(player.direction, false), player.x, player.y);
        }

        AI(g);
    }

    private void AI(Graphics g) throws SlickException {
        if (ai.atk) { // ATTACK
            editDirection();
            if (ai.realX > player.realX) {
                ai.charATK.getCurrentFrame().getFlippedCopy(true, false).draw(ai.x, ai.y);
            } else {
                ai.charATK.getCurrentFrame().getFlippedCopy(false, false).draw(ai.x, ai.y);
            }
            ai.atk = false;
        } else if (ai.move) { // WALK TOWARDS PLAYER
            editDirection();
            if (ai.realX > player.realX) {
                ai.direction = true;
                ai.x--;
                ai.realX--;
            } else if (ai.realX < player.realX) {
                ai.direction = false;
                ai.x++;
                ai.realX++;
            }
            if (ai.realX > player.realX) {
                ai.charAnimate.getCurrentFrame().getFlippedCopy(true, false).draw(ai.x, ai.y);
            } else {
                ai.charAnimate.getCurrentFrame().getFlippedCopy(false, false).draw(ai.x, ai.y);
            }
        } else {
            if (ai.realX < player.realX) {
                g.drawImage(ai.staticImage.getFlippedCopy(false, false), ai.x, ai.y);
            } else {
                g.drawImage(ai.staticImage.getFlippedCopy(true, false), ai.x, ai.y);
            }
        }
    }

    private void editDirection() {
        if (ai.realX > player.realX) { // pla - ai
            ai.direction = true;
            ai.firstTimeOnRight = true;
            if (ai.firstTimeOnLeft) {
                ai.x -= 70;
                ai.firstTimeOnLeft = false;
            }
        } else if (ai.realX < player.realX) { // ai <- pla
            ai.direction = false;
            ai.firstTimeOnLeft = true;
            if (ai.firstTimeOnRight) {
                ai.x += 70;
                ai.firstTimeOnRight = false;
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        player.charAnimate.update(delta);
        player.charATK.update(delta);
        ai.charAnimate.update(delta);
        ai.charATK.update(delta);
        bg.update(delta);

        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            player.atk = true;
            ai.atk = true;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            player.crouch = true;
        } else if (input.isKeyDown(Input.KEY_UP)) {
            player.onceJumped = true;
        }
        if (input.isKeyDown(Input.KEY_LEFT) && player.x > -200) {
            if (player.x > 500) {
                player.x = 500;
                player.realX = 500;
            }
            player.direction = true;
            player.move = true;
            player.firstTimeOnRight = true;
            if (player.firstTimeOnLeft) {
                player.x -= 70;
                player.firstTimeOnLeft = false;
            } else {
                player.x--;
                player.realX--;
            }
        }
        if (input.isKeyDown(Input.KEY_RIGHT) && player.x < 550) {
            if (player.x < -100) {
                player.x = -100;
                player.realX = -100;
            }
            player.direction = false;
            player.move = true;
            player.firstTimeOnLeft = true;
            if (player.firstTimeOnRight) {
                player.x += 70;
                player.firstTimeOnRight = false;
            } else {
                player.x++;
                player.realX++;
            }
        }

        ai.atk = ai.isNear(player, 40);
        ai.move = !(ai.isNear(player, 150));
    }


    public int getID() {
        return 3;
    }
}