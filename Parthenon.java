import org.newdawn.slick.*;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Danielle98 on 11/14/2016.
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
        g.drawImage(bg,0,0);
        if (player.move) {
            if (player.jump) {
                player.apolloJUM.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
                player.jump();
            } else {
                player.apolloAnimate.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            }
            player.move = false;
        }
        else if (player.atk){
            player.apolloATK.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            if (player.jump) {
                player.jump();
            }
            player.atk = false;
        }
        else if (player.crouch) {
            player.jump = false;
            player.apolloCRO.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            player.crouch = false;
        }
        else if (player.jump){
            player.apolloJUM.getCurrentFrame().getFlippedCopy(player.direction, false).draw(player.x, player.y);
            player.jump();
        }
        else {
            player.y = 250;
            player.descending = false;
            g.drawImage(apolloConstant.getFlippedCopy(player.direction,false), player.x, player.y);
        }
        AI(gc, sbg, g);
    }

    // FIXME not functional yet
    public void AI(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image apolloAI = new Image("res/ApolloWalk2.png");
        if (!(isNear())) {
            ai.apolloAnimate.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.move = false;
        } else if (ai.atk) {
            ai.apolloATK.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.atk = false;
        } else if (ai.crouch) {
            ai.apolloCRO.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.crouch = false;
        } else if (ai.jump) {
            ai.apolloJUM.getCurrentFrame().getFlippedCopy(ai.direction, false).draw(ai.x, ai.y);
            ai.jump = false;
        } else {
            g.drawImage(apolloAI.getFlippedCopy(ai.direction, false), ai.x, ai.y);
        }
    }

    private boolean isNear() {
        // TODO stub only
        return false;
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        player.apolloAnimate.update(delta);
        player.apolloATK.update(delta);
        Input input = gc.getInput();
        //if(input.isKeyDown(Input.KEY_UP)){y -= 1;}
        //if(input.isKeyDown(Input.KEY_DOWN)){y += 1;}
        if(input.isKeyDown(Input.KEY_A)){
            player.atk = true;
        }
        if(input.isKeyDown(Input.KEY_DOWN)) {
            player.crouch = true;
        }
        else if (input.isKeyDown(Input.KEY_UP)) {
            player.jump = true;
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
        private boolean move = false;
        private boolean atk = false;
        private boolean crouch = false;
        private boolean jump = false;
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

        public void jump() {
            if (!(descending)) {
                y -= 1;
                if (y == 150) {
                    descending = true;
                }
            }
            else {
                if (y < 250) {
                    y += 1;
                } else {
                    descending = false;
                }
            }
            jump = false;
        }
    }
}
