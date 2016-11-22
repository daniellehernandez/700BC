package SevenDoubleZero.Game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class PlayScreen extends BasicGameState{
    private boolean animate = false;
    private Animation cloudAnimate;
    private boolean start = true;

    PlayScreen(){

    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cloudAnimate = new Animation(new SpriteSheet("res/Maps/maps.png",700,500), 500);
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image bg = new Image("res/Maps/1.png");

        if(start) {
            g.drawImage(bg, 0, 0);
        }else if(animate){
            cloudAnimate.setLooping(false);
            cloudAnimate.draw(0, 0);
            g.setColor(Color.black);
            g.drawString("[Press Space]", 300, 450);
        }

    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        Input input = gc.getInput();
        cloudAnimate.update(delta);

        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && start && getID() == 1){
            animate = true;
            start = false;
        }

        if(input.isKeyPressed(Input.KEY_SPACE) && !start && getID() == 1 ){
            sbg.enterState(3);
        }


    }
	public int getID() {
		return 1;
	}
}
