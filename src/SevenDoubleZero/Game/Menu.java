package SevenDoubleZero.Game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class Menu extends BasicGameState{
	private Animation bg;
	private Music bgMusic;
	private Sound thunder;

	Menu(){
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Animation(new SpriteSheet("res/Maps/mm.png",700,500), 400);
		bgMusic = new Music("res/Sounds/main_menu_background_music.ogg");
		thunder = new Sound("res/Sounds/thunderogg.ogg");
	}

	//WHERE YOU PUT THE GRAPHICS THAT WILL SHOW ON THE SCREEN
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg.draw(0, 0);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		bg.update(delta);

		Input input = gc.getInput();
		int xMouse = Mouse.getX();
		int yMouse = Mouse.getY();


		if((xMouse > 488 && xMouse < 633)&& (yMouse > 136 && yMouse < 187 )){ //
			if(input.isMouseButtonDown(0) && getID() == 0 ){
				sbg.enterState(1);
			}
		}
	}

	public void enter(GameContainer gc, StateBasedGame sbg){
		try{
			super.enter(gc, sbg);
		}catch (SlickException e){
			e.printStackTrace();
		}
		bgMusic.loop();
		thunder.loop();
	}

	public void leave(GameContainer gc, StateBasedGame sbg){
		try{
			super.enter(gc, sbg);
		}catch (SlickException e){
			e.printStackTrace();
		}
		bgMusic.stop();
		thunder.stop();
	}

	public int getID() {
		return 0;
	}
}
