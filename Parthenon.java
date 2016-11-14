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
    int y = 300;

    public Parthenon(int state){

    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        apollo = new SpriteSheet("res/apollospritesheet.png",350,250);
        apolloAnimate = new Animation(apollo, 300);
        apolloAnimate.getFrame();
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image bg = new Image("res/Parthenon.png");
        g.drawImage(bg,0,0);
        apolloAnimate.draw(0,0);

    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        apolloAnimate.update(delta);
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_UP)){y -= 1;}
        if(input.isKeyDown(Input.KEY_DOWN)){y += 1;}
        if(input.isKeyDown(Input.KEY_LEFT)){x -= 1;}
        if(input.isKeyDown(Input.KEY_RIGHT)){x += 1;}
    }


    public int getID() {
        return 3;
    }
}
