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
        cloudAnimate = new Animation(new SpriteSheet("res/Maps/clouuds.png.png",700,500), 300000);
	}


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Image bg = new Image("res/Maps/Map.png");
        Image cloudConstant = new Image("res/Maps/clouds1.png");

        if(start) {
            g.drawImage(cloudConstant, 0, 0);
        }else if(animate){
            cloudAnimate.draw(0,0);
            animate = false;
        }else{
            g.drawImage(bg,0,0);
        }
	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		Input input = gc.getInput();


            if(input.isKeyPressed(Input.KEY_ENTER) && start){
                cloudAnimate.update(delta);
                animate = true;
                start = false;
            }
			if(input.isKeyPressed(Input.KEY_SPACE)){
				sbg.enterState(3);
			}
	}


	public int getID() {
		return 1;
	}
}
