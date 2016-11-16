import org.newdawn.slick.*;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Serato, Acal, Amora, Hernandez on 11/14/2016.
 */
public class Parthenon extends BasicGameState {
    private Apollo player;
    private Apollo ai;

    public Parthenon(int state){
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new Apollo();
        ai = new Apollo();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int frame = 0;
        Image bg = new Image("res/Parthenon.png");
        Image apolloConstant = new Image("res/ApolloWalk2.png");
        g.drawImage(bg, 0, 0);

        AI(gc, sbg, g);

        if (player.move) {
            if (player.onceJumped) {
                if (player.atk) {
                    player.jump(player.apolloATK);
                } else {
                    player.jump();
                }
            }
            else if (player.atk) {
                player.apolloATK.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            else {
                player.apolloAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.move = false;
        }
        else if (player.atk){
            if (player.onceJumped) {
                player.jump(player.apolloATK);
            } else {
                player.apolloATK.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.atk = false;
        }
        else if (player.crouch) {
            player.y = 250;
            player.apolloCRO.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            player.crouch = false;
        }
        else if (player.onceJumped) {
            player.jump();
        }
        else {
            g.drawImage(apolloConstant.getFlippedCopy(player.direction,false), player.x, player.y);
        }


    }

    // FIXME not functional yet
    public void AI(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image apolloAI = new Image("res/ApolloWalk2.png");
        if (!(ai.isNear(player, 200)) && !(ai.isNear(player, 10))) {
            System.out.println("RUNFOYALAYF");
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
            ai.apolloAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(ai.x, ai.y);
            ai.move = false;
        }
        else if (ai.isNear(player, 10)) {
            ai.apolloATK.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            System.out.println("HIT HIM");
            ai.atk = false;
        }
        else if (player.atk) {
            ai.apolloCRO.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.crouch = false;
        }
        else {
            System.out.println("tweet tweet");
            g.drawImage(apolloAI.getFlippedCopy(ai.direction, false), ai.x, ai.y);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        player.apolloAnimate.update(delta);
        player.apolloATK.update(delta);
        ai.apolloAnimate.update(delta);
        ai.apolloATK.update(delta);
        Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_A)){
            player.atk = true;
            ai.atk = true;
        }
        if(input.isKeyDown(Input.KEY_DOWN)) {
            player.crouch = true;
        }
        else if (input.isKeyDown(Input.KEY_UP)) {
            player.onceJumped = true;
        }
        if(input.isKeyDown(Input.KEY_LEFT) && player.x > -200) {
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
        if(input.isKeyDown(Input.KEY_RIGHT) && player.x < 550){
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

    private class Apollo {
        private SpriteSheet apollo;
        private SpriteSheet apolloAttack;
        private SpriteSheet apolloCrouch;
        private SpriteSheet apolloJump;
        private Animation apolloAnimate;
        private Animation apolloATK;
        private Animation apolloCRO;
        private Animation apolloJUM;

        private int x = 10;
        private int y = 250;
        private boolean descending = false;
        private boolean onceJumped = false;
        private boolean move = false;
        private boolean atk = false;
        private boolean crouch = false;
        private boolean direction = false;
        private boolean firstTimeOnLeft = false;
        private boolean firstTimeOnRight = false;

        public Apollo() throws SlickException {
            apollo = new SpriteSheet("res/Apollo.png",350,250);
            apolloAttack = new SpriteSheet("res/Apollo2.png",350,250);
            apolloCrouch = new SpriteSheet("res/apolloCrouch.png", 350, 250);
            apolloJump = new SpriteSheet("res/apolloJump.png", 350, 250);
            apolloAnimate = new Animation(apollo, 150);
            apolloATK = new Animation(apolloAttack, 150);
            apolloCRO = new Animation(apolloCrouch, 150);
            apolloJUM = new Animation(apolloJump, 150);
        }

        public boolean isNear(Apollo opponent, int distance){
            return this.x >= opponent.x - distance && this.x <= opponent.x + distance;
        }

        public void jump() {
            jump(apolloJUM);
        }

        public void jump(Animation anim) {
            anim.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            if (!(descending)) {
                y -= 1;
                if (y == 150) {
                    descending = true;
                }
            }
            else {
                if (y < 250) {
                    y += 1;
                }
                else {
                    onceJumped = false;
                    descending = false;
                }
            }
        }
    }
}
