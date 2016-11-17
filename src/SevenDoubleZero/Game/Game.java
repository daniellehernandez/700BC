package SevenDoubleZero.Game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Game extends StateBasedGame{

	private static final String Name = "700 BC";
	private static final int Menu = 0;
	private static final int PlayScreen = 1;
	private static final int Parthenon = 3;

	private Game(String Name){
		super(Name);
		this.addState(new Menu());
		this.addState(new PlayScreen());
		this.addState(new Parthenon());
	}


	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(Menu).init(gc,this);
		this.getState(PlayScreen).init(gc,this);
		this.getState(Parthenon).init(gc,this);
		this.enterState(Menu);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;

		try{
			appgc = new AppGameContainer(new Game(Name));
			appgc.setDisplayMode(700,500,false);
			appgc.start();
		}catch(SlickException e){ 
			e.printStackTrace(); 
		}


	}

}
