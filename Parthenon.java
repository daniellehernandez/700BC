import org.newdawn.slick.*;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Danielle98 on 11/14/2016.
 */
public class Parthenon extends BasicGameState {
    private SpriteSheet apollo;
    private Animation apolloAnimate;
    int x = 10;
    int y = 250;
    boolean move = false;
    boolean direction = false;
    boolean firstTimeOnLeft = false;
    boolean firstTimeOnRight = false;

    public Parthenon(int state){

    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        apollo = new SpriteSheet("res/Apollo.png",350,250);
        apolloAnimate = new Animation(apollo, 150);

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int frame = 0;
        Image bg = new Image("res/Parthenon.png");
        Image apolloConstant = new Image("res/ApolloWalk2.png");
        g.drawImage(bg,0,0);
        if(move == true) {
            apolloAnimate.getCurrentFrame().getFlippedCopy(direction, false).draw(x, y);
            move = false;
        } else {
            g.drawImage(apolloConstant.getFlippedCopy(direction,false),x,y);
        }
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        apolloAnimate.update(delta);
        Input input = gc.getInput();
        //if(input.isKeyDown(Input.KEY_UP)){y -= 1;}
        //if(input.isKeyDown(Input.KEY_DOWN)){y += 1;}
        if(input.isKeyDown(Input.KEY_LEFT) && x > -200) {
            if (x > 500) {
                x = 500;
            }
            direction = true;
            move = true;
            firstTimeOnRight = true;
            if (firstTimeOnLeft) {
                x -= 70;
                firstTimeOnLeft = false;
            } else {
                x -= 1;
            }
        }
        if(input.isKeyDown(Input.KEY_RIGHT) && x < 550){
            if (x < -100) {
                x = -100;
            }
            direction = false;
            move = true;
            firstTimeOnLeft = true;
            if (firstTimeOnRight) {
                x += 70;
                firstTimeOnRight = false;
            } else {
                x += 1;
            }
        }
    }


    public int getID() {
        return 3;
    }
}
