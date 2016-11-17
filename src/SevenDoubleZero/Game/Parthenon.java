package SevenDoubleZero.Game;

import SevenDoubleZero.Characters.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class Parthenon extends BasicGameState {
    private RPGCharacter player;
    private RPGCharacter ai;

    Parthenon() {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Apollo();
        ai = new Apollo(350);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image bg = new Image("res/Maps/Parthenon.png");
        Image apolloConstant = new Image("res/Characters/Apollo/ApolloWalk2.png");
        g.drawImage(bg, 0, 0);

        AI(g);

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
            g.drawImage(apolloConstant.getFlippedCopy(player.direction, false), player.x, player.y);
        }


    }

    private void AI(Graphics g) throws SlickException {
        Image apolloAI = new Image("res/Characters/Apollo/ApolloWalk2.png");
        if (!(ai.isNear(player, 150)) && !(ai.isNear(player, 10))) {
            if (player.x > ai.x) { // go Right
                ai.direction = false;
                ai.move = true;
                ai.firstTimeOnLeft = true;
                if (ai.firstTimeOnRight) {
                    ai.x += 70;
                    ai.firstTimeOnRight = false;
                } else {
                    ai.x += 1;
                }
            } else { // go Left
                ai.direction = true;
                ai.move = true;
                ai.firstTimeOnRight = true;
                if (ai.firstTimeOnLeft) {
                    ai.x -= 70;
                    ai.firstTimeOnLeft = false;
                } else {
                    ai.x -= 1;
                }
            }
            ai.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(ai.x, ai.y);
            ai.move = false;
        } else if (ai.isNear(player, 10)) {
            // FIXME sloppy conversion below
            if (player.x > ai.x) { // go Right
                ai.direction = false;
                ai.move = true;
                ai.firstTimeOnLeft = true;
                if (ai.firstTimeOnRight) {
                    ai.x += 70;
                    ai.firstTimeOnRight = false;
                }
            }
            else { // go Left
                ai.direction = true;
                ai.move = true;
                ai.firstTimeOnRight = true;
                if (ai.firstTimeOnLeft) {
                    ai.x -= 70;
                    ai.firstTimeOnLeft = false;
                }
            }
            ai.charATK.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.atk = false;
        } else if (player.atk) {
            ai.charCRO.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.crouch = false;
        } else {
            g.drawImage(apolloAI.getFlippedCopy(ai.direction, false), ai.x, ai.y);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        player.charAnimate.update(delta);
        player.charATK.update(delta);
        ai.charAnimate.update(delta);
        ai.charATK.update(delta);
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
            }
            player.direction = true;
            player.move = true;
            player.firstTimeOnRight = true;
            if (player.firstTimeOnLeft) {
                player.x -= 70;
                player.firstTimeOnLeft = false;
            } else {
                player.x -= 1;
            }
        }
        if (input.isKeyDown(Input.KEY_RIGHT) && player.x < 550) {
            if (player.x < -100) {
                player.x = -100;
            }
            player.direction = false;
            player.move = true;
            player.firstTimeOnLeft = true;
            if (player.firstTimeOnRight) {
                player.x += 70;
                player.firstTimeOnRight = false;
            } else {
                player.x += 1;
            }
        }

        ai.atk = ai.isNear(player, 10);
    }


    public int getID() {
        return 3;
    }
}