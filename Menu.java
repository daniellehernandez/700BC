import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState{
	
	
	public Menu(int state){
		
	}


	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}


	//WHERE YOU PUT THE GRAPHICS THAT WILL SHOW ON THE SCREEN
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Image bg = new Image("res/bg.png");
		g.drawImage(bg,0,0);

	}


	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		Input input = gc.getInput();
		int xMouse = Mouse.getX();
		int yMouse = Mouse.getY();

		if((xMouse > 488 && xMouse < 633)&& (yMouse > 136 && yMouse < 187 )){ //
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
			}
		}
	}


	public int getID() {
		return 0;
	}
}
