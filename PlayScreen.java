import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class PlayScreen extends BasicGameState{
	Image hermes;
	int x = 10;
	int y = 300;
	
	public PlayScreen(int state){
		
	}


	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Image bg = new Image("res/Map.png");
		g.drawImage(bg,0,0);
		
	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		Input input = gc.getInput();


			if(input.isKeyPressed(Input.KEY_SPACE)){
				sbg.enterState(3);
			}
	}


	public int getID() {
		return 1;
	}
}
