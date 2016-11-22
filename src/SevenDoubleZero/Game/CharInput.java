package SevenDoubleZero.Game;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class CharInput extends BasicGameState{
    private boolean chosen = false;
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame cbg, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (chosen) {
            sbg.enterState(3);
        }
    }

    public int getID() {
        return 2;
    }
}
