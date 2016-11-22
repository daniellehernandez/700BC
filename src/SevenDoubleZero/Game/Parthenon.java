package SevenDoubleZero.Game;

import SevenDoubleZero.Characters.Apollo;
import SevenDoubleZero.Characters.Athena;
import SevenDoubleZero.Characters.Melee;
import SevenDoubleZero.Characters.RPGCharacter;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.swing.*;

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
        g.drawString("Your HP: " + player.getHealth(), 100, 10);
        g.drawString("Enemy HP: " + ai.getHealth(), 250, 10);
        JFrame frame = new JFrame("Game Over");

        if (player.atk) {
            if (player.charATK.getCurrentFrame().equals(player.charATK.getImage(3))) {
                player.attacked = true;
            }
            else if (player.charATK.getCurrentFrame().equals(player.charATK.getImage(4)) && player.attacked) {
                ai.takeDamage(player.getDamage());
                player.attacked = false;
            }
            if (!ai.isAlive()) {
                JOptionPane.showMessageDialog(frame, "Congratulations! You just have defeated AI " + ai.name, "You won", JOptionPane.OK_OPTION);
                gc.exit();
            }
            if (player.onceJumped) {
                player.jump(player.charATK);
            } else {
                player.charATK.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.atk = false;
        } else if (player.move) {
            if (player.onceJumped) {
                player.jump();
            }
            else {
                player.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.move = false;
        } else if (player.crouch) {
            player.y = 250;
            player.charCRO.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            player.crouch = false;
        } else if (player.onceJumped) {
            player.jump();
        } else {
            g.drawImage(player.staticImage.getFlippedCopy(player.direction, false), player.x, player.y);
        }

        AI(g, gc, frame);
    }

    private void AI(Graphics g, GameContainer gc, JFrame frame) throws SlickException {
        if (ai.atk) { // ATTACK
            editDirection();
            if (ai.realX > player.realX) {
                ai.charATK.getCurrentFrame().getFlippedCopy(true, false).draw(ai.x, ai.y);
            } else {
                ai.charATK.getCurrentFrame().getFlippedCopy(false, false).draw(ai.x, ai.y);
            }
            if (ai.charATK.getCurrentFrame().equals(ai.charATK.getImage(3))) {
                ai.attacked = true;
            }
            else if (ai.charATK.getCurrentFrame().equals(ai.charATK.getImage(4)) && ai.attacked) {
                player.takeDamage(ai.getDamage());
                ai.attacked = false;
            }
            if (!player.isAlive()) {
                JOptionPane.showMessageDialog(frame, "You were killed by AI " + ai.name + ".", "You lost", JOptionPane.OK_OPTION);
                gc.exit();
            }
            ai.atk = false;
        } else if (ai.move) { // WALK TOWARDS PLAYER
            editDirection();
            if (ai.realX > player.realX) {
                if (ai instanceof Melee) {
                    ai.direction = true;
                    ai.x--;
                    ai.realX--;
                } else {
                    ai.direction = false;
                    ai.x++;
                    ai.realX++;
                }
            } else if (ai.realX < player.realX) {
                if (ai instanceof Melee) {
                    ai.direction = false;
                    ai.x++;
                    ai.realX++;
                } else {
                    ai.direction = true;
                    ai.x--;
                    ai.realX--;
                }
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
                ai.x -= ai.getPan();
                ai.firstTimeOnLeft = false;
            }
        } else if (ai.realX < player.realX) { // ai <- pla
            ai.direction = false;
            ai.firstTimeOnLeft = true;
            if (ai.firstTimeOnRight) {
                ai.x += ai.getPan();
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
                player.x -= player.getPan();
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
                player.x += player.getPan();
                player.firstTimeOnRight = false;
            } else {
                player.x++;
                player.realX++;
            }
        }
        if (ai instanceof Melee) {
            ai.atk = ai.isNear(player, 40);
            ai.move = !(ai.isNear(player, 150));
        }
        else {
            ai.atk = !(ai.isNear(player, 60));
            ai.move = ai.isNear(player, 60);
        }
    }


    public int getID() {
        return 3;
    }
}