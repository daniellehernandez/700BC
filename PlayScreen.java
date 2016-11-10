import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class PlayScreen extends BasicGameState{
	Image hermes;
	int x = 10;
	int y = 300;
	
	public PlayScreen(int state){
		
	}


	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		hermes = new Image("res/hermes.png");
		
	}


	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Image bg = new Image("res/Pics.png");
		g.drawImage(bg,0,0);
		g.drawImage(hermes,x,y);
		
	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)){y -= 1;}
		if(input.isKeyDown(Input.KEY_DOWN)){y += 1;}	
		if(input.isKeyDown(Input.KEY_LEFT)){x -= 1;}
		if(input.isKeyDown(Input.KEY_RIGHT)){x += 1;}	
	}


	public int getID() {
		return 1;
	}
}
