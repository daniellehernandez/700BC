package SevenDoubleZero.Game;

import SevenDoubleZero.Game.CharInputs.*;
import SevenDoubleZero.Levels.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	private static final String Name = "700 BC";

	private static final int Menu = 0;
	private static final int PlayScreen = 1;
	private static final int BeforeUnderworld = 2;
	private static final int Underworld = 3;
	private static final int BeforeMedusa = 4;
	private static final int Medusa = 5;
	private static final int BeforeParthenon = 6;
	private static final int Parthenon = 7;
	private static final int BeforeArchaedia = 8;
	private static final int Archadia = 9;
	private static final int BeforeMountOlympus = 10;
	private static final int MountOlympus = 11;

	private Game(String Name){
		super(Name);
		this.addState(new Menu());
		this.addState(new PlayScreen());
		this.addState(new BeforeUnderworld());
		this.addState(new Underworld());
		this.addState(new BeforeMedusa());
		this.addState(new Medusa());
		this.addState(new BeforeParthenon());
		this.addState(new Parthenon());
		this.addState(new BeforeArchadia());
		this.addState(new Archadia());
		this.addState(new BeforeMountOlympus());
		this.addState(new MountOlympus());
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(Menu).init(gc,this);
		this.getState(PlayScreen).init(gc,this);
		this.getState(BeforeUnderworld).init(gc, this);
		this.getState(Underworld).init(gc, this);
		this.getState(BeforeMedusa).init(gc, this);
		this.getState(Medusa).init(gc, this);
		this.getState(BeforeParthenon).init(gc, this);
		this.getState(Parthenon).init(gc,this);
		this.getState(BeforeArchaedia).init(gc, this);
		this.getState(Archadia).init(gc, this);
		this.getState(BeforeMountOlympus).init(gc, this);
		this.getState(MountOlympus).init(gc, this);
		this.enterState(Menu);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;

		try {
			appgc = new AppGameContainer(new Game(Name));
			appgc.setDisplayMode(700, 500, false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
