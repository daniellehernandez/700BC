package SevenDoubleZero.Levels;

import SevenDoubleZero.Characters.Apollo;
import SevenDoubleZero.Characters.Athena;
import SevenDoubleZero.Characters.RPGCharacter;
import SevenDoubleZero.Game.Heart;
import SevenDoubleZero.Game.Hero;
import SevenDoubleZero.Game.PowerUp;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Parthenon extends BasicGameState {
    private RPGCharacter player;
    private RPGCharacter ai;
    private Animation bg;
    private Animation nextLevel;
    private Animation loseLevel;
    private static final int AI_CHASE = 150;
    private Hero hero;
    private Music bgMusic2;
    private Sound walk;
    private Sound attack;
    private Sound hurt;
    private PowerUp heart;
    private int heartExistence;

    public Parthenon() {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        hero = Hero.getInstance();
        player = hero.getPlayer();
        ai = new Athena(350, true);
        bg = new Animation(new SpriteSheet("res/Maps/Parthenon.png", 700, 500), 1500);
        nextLevel = new Animation(new SpriteSheet("res/Maps/NewLevel.png", 450, 360), 150);
        loseLevel = new Animation(new SpriteSheet("res/Maps/losescreen.png", 450, 360), 700);
        bgMusic2 = new Music("res/Sounds/gamebg.wav");
        attack = new Sound("res/Sounds/attack.wav");
        walk = new Sound("res/Sounds/walkk.wav");
        hurt = new Sound("res/Sounds/hurt.wav");
        gc.setShowFPS(false);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0, 0);

        g.drawString("Your HP: " + player.getHealth(), 100, 10);
        g.drawString("Enemy HP: " + ai.getHealth(), 250, 10);
        if (ai.getHealth() <= 0) {
            nextLevel.draw(gc.getWidth()/5, gc.getHeight()/5);
            player.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            ai.charAnimate.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            nextLevel.setLooping(false);
            if (nextLevel.isStopped()) {
                g.drawString("[PRESS SPACE TO CONTINUE]", 300, 450);
                if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
                    if (!(player instanceof Apollo)) {
                        sbg.enterState(6);
                    } else {
                        sbg.enterState(7);
                    }
                }
            }
        } else if (player.getHealth() <= 0) {
            loseLevel.draw(gc.getWidth()/5, gc.getHeight()/10);
            player.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            ai.charAnimate.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            loseLevel.setLooping(false);
        }else {
            if (heart == null) {
                int randomNum = (int) (Math.random() * (3000 + (heartExistence * 500)));
                System.out.println("Random number is " + randomNum);
                if (randomNum == 1) {
                    heart = new Heart();
                    heart.setY(0);
                    heart.setX((int) (Math.random() * (gc.getWidth() - heart.getImage().getWidth())));
                    heartExistence++;
                }
            } else {
                heart.getImage().draw(heart.getX(), heart.getY());
                if (System.nanoTime() % 100 == 0) {
                    System.out.println("I gotchu, now at " + (heart.getY() + 1));
                    heart.setY(heart.getY() + 20);
                }

                if (heart.getY() >= gc.getHeight()) {
                    heart = null;
                } else if ((heart.getX() >= player.realX && heart.getX() <= player.realX + player.staticImage.getWidth()) && heart.getY() == player.y) {
                    player.setHealth(player.getHealth() + 50);
                    heart = null;
                }
            }
            if (player.hurt) {
                if (player.realX < ai.realX && player.realX > player.hurtX) {
                    player.x--;
                    player.realX--;
                    g.drawImage(player.charHurt.getFlippedCopy(player.direction, false), player.x, player.y);
                } else if (player.realX > ai.realX && player.realX <= player.hurtX) {
                    player.x++;
                    player.realX++;
                    g.drawImage(player.charHurt.getFlippedCopy(player.direction, false), player.x, player.y);
                } else {
                    player.hurt = false;
                }
            } else if (player.atk) {
                player.attack(ai, nextLevel, gc);
            } else if (player.move) {
                if (player.onceJumped) {
                    player.jump();
                } else {
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

            AI(g);
        }
    }

    private void AI(Graphics g) throws SlickException {
        if (ai.x >= 450) {
            ai.x = 450;
            ai.realX = 450;
        } else if (ai.x <= -100) {
            ai.x = -100;
            ai.realX = -100;
        }
        if (ai.hurt) {
            if (player.realX < ai.realX && !ai.isNear(player, ai.reach - 10)) {
                ai.realX -= player.reach/3;
                ai.x -= player.reach/3;
                System.out.println("LEFt");
                ai.charAnimate.getCurrentFrame().getFlippedCopy(true, false).draw(ai.x, ai.y);
            } else {
                ai.realX += player.reach/3;
                ai.x += player.reach/3;
                System.out.println("RIGHT");
                ai.charAnimate.getCurrentFrame().getFlippedCopy(false, false).draw(ai.x, ai.y);
            }

            System.out.println("now hurting");
            if (!ai.isNear(player, player.reach) || !player.attacked) {
                ai.hurt = false;
            }
            if (ai.cornered(player, ai.realX) || ai.corner) {
                ai.corner = true;
                if (ai.realX > 400) {
                    while (ai.realX > 400) {
                        ai.x--;
                        ai.realX--;
                        ai.charAnimate.getCurrentFrame().getFlippedCopy(true, false).draw(ai.x, ai.y);
                    }
                    ai.corner = false;
                } else if (ai.realX < 0){
                    while (ai.realX < 0) {
                        ai.x++;
                        ai.realX++;
                        ai.charAnimate.getCurrentFrame().getFlippedCopy(false, false).draw(ai.x, ai.y);
                    }
                    ai.corner = false;
                } else {
                    ai.hurt = false;
                }
            } else {
                if (ai.realX < player.realX && ai.realX > ai.hurtX) {
                    ai.x--;
                    ai.realX--;
                    ai.charAnimate.getCurrentFrame().getFlippedCopy(!player.direction, false).draw(ai.x, ai.y);
                } else if (ai.realX > player.realX && ai.realX <= ai.hurtX) {
                    ai.x++;
                    ai.realX++;
                    ai.charAnimate.getCurrentFrame().getFlippedCopy(!player.direction, false).draw(ai.x, ai.y);
                } else {
                    ai.hurt = false;
                }
            }
        } else if (ai.atk) { // ATTACK
            System.out.println("now attacking");
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
                player.hurt = true;
                if (player.realX < ai.realX) {
                    player.hurtX = player.realX - 70;
                } else {
                    player.hurtX = player.realX + 70;
                }
            }
            //ai.atk = false;
        } else if (player.atk || ai.move) { // WALK TOWARDS PLAYER
            System.out.println("now moving");
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
        player = hero.getPlayer();
        player.charAnimate.update(delta);
        player.charATK.update(delta);
        ai.charAnimate.update(delta);
        ai.charATK.update(delta);
        bg.update(delta);
        nextLevel.update(delta);

        Input input = gc.getInput();

        if (!player.isAlive()) {
            if (input.isKeyDown(Input.KEY_Y)) {
                sbg.enterState(0);
            } else if (input.isKeyDown(Input.KEY_N)){
                gc.exit();
            }
        }

        if (input.isKeyDown(Input.KEY_A)) {
            if(!attack.playing()) {
                attack.play();
            }
            player.atk = true;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            player.crouch = true;
        } else if (input.isKeyDown(Input.KEY_UP)) {
            player.onceJumped = true;
        }
        if (input.isKeyDown(Input.KEY_LEFT) && player.x > -200) {
            if(!walk.playing()) {
                walk.play();
            }
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
            if(!walk.playing()) {
                walk.play();
            }
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
        ai.atk = ai.isNear(player, ai.reach);
        if (ai.atk && !hurt.playing()) {
            hurt.play();
        }
        ai.move = !(ai.isNear(player, AI_CHASE));
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        bgMusic2.loop();
    }

    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        bgMusic2.stop();
    }

    public int getID() {
        return 5;
    }
}