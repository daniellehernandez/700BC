import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Game extends StateBasedGame{

	public static final String Name = "Naked Hermes";
	public static final int Menu = 0;
	public static final int PlayScreen = 1;
	public static final int Parthenon = 3;

	public Game (String Name){
		super(Name);
		this.addState(new Menu(Menu));
		this.addState(new PlayScreen(PlayScreen));
		this.addState(new Parthenon(Parthenon));
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
