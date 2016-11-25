package Game;

import org.newdawn.slick.*;
import org.newdawn.slick.Game;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class PlayScreen extends BasicGameState{
    private boolean animate = false;
    private Animation cloudAnimate;
    private boolean start = true;
    private Music bgMusic1;

    PlayScreen(){

    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cloudAnimate = new Animation(new SpriteSheet("res/Maps/maps.png",700,500), 500);
        bgMusic1 = new Music("res/Sounds/mapbg.wav");
        if(!bgMusic1.playing()) {
            bgMusic1.play(1f, 1f);
        }
        //bgMusic1.play();

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image bg = new Image("res/Maps/1.png");
        Image cloudConstant = new Image("res/Maps/7.png");

            cloudAnimate.setLooping(false);
            cloudAnimate.draw(0, 0);
            g.setColor(Color.black);
            g.drawString("[Press Space]", 300, 450);


    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        Input input = gc.getInput();
        cloudAnimate.update(delta);

        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && start && getID() == 1){
            animate = true;
            start = false;
        }

        if(input.isKeyPressed(Input.KEY_SPACE) && !start && getID() == 1 ){
            sbg.enterState(3); //REDIRECTED AUTOMATICALLY IN PARTHENON FOR D MEANTIME
        }
    }
	public int getID() {
		return 1;
	}

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        bgMusic1.loop();
    }

    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
            bgMusic1.stop();
    }
}
