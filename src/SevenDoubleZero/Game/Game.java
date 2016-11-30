package SevenDoubleZero.Game;

import SevenDoubleZero.Levels.*;
import org.lwjgl.LWJGLException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	private static final String Name = "700 BC";

	private static final int Menu = 0;
	private static final int PlayScreen = 1;
	private static final int InputPrompt = 2;
	private static final int Underworld = 3;
	private static final int Medusa = 4;
	private static final int Parthenon = 5;
	private static final int Archadia = 6;
	private static final int MountOlympus = 7;
	private static final int Characters = 8;

	private Game(String Name) throws SlickException, LWJGLException {
		super(Name);
		this.addState(new Menu());
		this.addState(new PlayScreen());
		this.addState(new CharacterScreen());
		this.addState(new InputPrompt());
		this.addState(new Underworld());
		this.addState(new Medusa());
		this.addState(new Parthenon());
		this.addState(new Archadia());
		this.addState(new MountOlympus());
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(Menu).init(gc,this);
		this.getState(PlayScreen).init(gc,this);
		this.getState(Characters).init(gc, this);
		this.getState(InputPrompt).init(gc, this);
		this.getState(Underworld).init(gc, this);
		this.getState(Medusa).init(gc, this);
		this.getState(Parthenon).init(gc,this);
		this.getState(Archadia).init(gc, this);
		this.getState(MountOlympus).init(gc, this);
		this.enterState(Menu);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;

		try {
			appgc = new AppGameContainer(new Game(Name));
			appgc.setDisplayMode(700, 500, false);
			appgc.start();
		} catch (SlickException | LWJGLException e) {
			e.printStackTrace();
		}
	}
}
