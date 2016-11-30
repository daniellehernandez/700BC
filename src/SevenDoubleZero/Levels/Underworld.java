package SevenDoubleZero.Levels;

import SevenDoubleZero.Characters.Artemis;
import SevenDoubleZero.Characters.Flying;
import SevenDoubleZero.Characters.Hades;
import SevenDoubleZero.Characters.RPGCharacter;
import SevenDoubleZero.Game.Hero;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Underworld extends BasicGameState {
    private Hero hero;
    private RPGCharacter player;
    private RPGCharacter ai;
    private Animation bg;
    private Animation nextLevel;
    private Animation loseLevel;
    private static final int AI_CHASE = 250;
    private Music bgMusic2;
    private Sound walk;
    private Sound attack;
    private Sound hurt;
    private Image heart;
    private int randomNum;

    public Underworld() throws SlickException {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        hero = Hero.getInstance();
        player = hero.getPlayer();
        ai = new Hades(350, true);
        bg = new Animation(new SpriteSheet("res/Maps/Underworld.png", 700, 500), 1500);
        nextLevel = new Animation(new SpriteSheet("res/Maps/NewLevel.png", 450, 360), 150);
        loseLevel = new Animation(new SpriteSheet("res/Maps/losescreen.png", 450, 360), 700);
        bgMusic2 = new Music("res/Sounds/gamebg.wav");
        attack = new Sound("res/Sounds/attack.wav");
        walk = new Sound("res/Sounds/walkk.wav");
        hurt = new Sound("res/Sounds/hurt.wav");
        gc.setShowFPS(false);
        randomNum = (int) (Math.random() * 10);
        heart = new Image("res/Maps/heart.png").getScaledCopy(10, 10);

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        bg.draw(0, 0);

        g.drawString("Your HP: " + player.getHealth(), 100, 10);
        g.drawString("Enemy HP: " + ai.getHealth(), 250, 10);



        if (ai.getHealth() <= 0) {
            nextLevel.draw(gc.getWidth()/5, gc.getHeight()/10);
            player.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            ai.charAnimate.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            nextLevel.setLooping(false);
            if (nextLevel.isStopped()) {
                if (!(player instanceof Artemis)) {
                    sbg.enterState(5);
                } else {
                    sbg.enterState(6);
                }
            }
        } else if (player.getHealth() <= 0) {
            loseLevel.draw(gc.getWidth()/5, gc.getHeight()/10);
            player.charAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            ai.charAnimate.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            loseLevel.setLooping(false);
        } else {
            if (true) {
                float heartY = 0;
                float heartX =(float) (Math.random() * gc.getWidth());

                heart.draw(heartX, heartY);
                while (true) {
                    try {
                        //sending the actual Thread of execution to sleep X milliseconds
                        Thread.sleep(1000);
                        System.out.println("SHIT");
                    } catch (Exception e) {
                        //heartY++;
                    }

                    if (heartY >= gc.getHeight()) {
                        break;
                    }
                    if ((heartX >= player.realX && heartX <= player.realX + player.staticImage.getWidth()) && heartY == player.y) {
                        player.setHealth(player.getHealth() + 50);
                        break;
                    }

                }

            }
            if (player.hurt) {
                if (player.realX < ai.realX && player.realX > player.hurtX) {
                    player.x--;
                    player.realX--;
                    g.drawImage(player.staticImage.getFlippedCopy(player.direction, false), player.x, player.y);
                } else if (player.realX > ai.realX && player.realX <= player.hurtX) {
                    player.x++;
                    player.realX++;
                    g.drawImage(player.staticImage.getFlippedCopy(player.direction, false), player.x, player.y);
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

            AI(g, gc);
        }
    }

    private void AI(Graphics g, GameContainer gc) throws SlickException {
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
            if (ai instanceof Flying) {
                ai.y = 150;
            }
            editDirection();
            if (ai.realX > player.realX) {
                ai.charATK.getCurrentFrame().getFlippedCopy(true, false).draw(ai.x, ai.y);
            } else {
                ai.charATK.getCurrentFrame().getFlippedCopy(false, false).draw(ai.x, ai.y);
            }
            if (ai.charATK.getCurrentFrame().equals(ai.charATK.getImage(1))) {
                ai.attacked = true;
            }
            else if (ai.charATK.getCurrentFrame().equals(ai.charATK.getImage(2)) && ai.attacked) {
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
            if (ai instanceof Flying) {
                if (ai.up) {
                    ai.y--;
                    if (ai.y <= 125) {
                        ai.down = true;
                        ai.up = false;
                    }
                } else {
                    ai.y++;
                    if (ai.y >= 200) {
                        ai.down = false;
                        ai.up = true;
                    }
                }
            }
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
                g.drawImage(ai.charAnimate.getImage(0).getFlippedCopy(false, false), ai.x, ai.y);
            } else {
                g.drawImage(ai.charAnimate.getImage(0).getFlippedCopy(true, false), ai.x, ai.y);
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

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        bgMusic2.loop();
    }

    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        bgMusic2.stop();
    }

    public int getID() {
        return 3;
    }
}