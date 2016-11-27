package SevenDoubleZero.Game.CharInputs;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class BeforeMountOlympus extends BasicGameState{
    private Image bg;
    private String intro = "[DOUBLE-CLICK THE CHARACTER TO SELECT]";
    private StateBasedGame sbg;

    public BeforeMountOlympus() {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bg = new Image("res/Maps/ChoiceNo.png");
        this.sbg = sbg;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame cbg, Graphics g) throws SlickException {
        bg.draw(0, 0);
        g.setColor(Color.black);
        g.drawString(intro, 175, 460);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void mouseClicked(int button, int x, int y, int clickCount){
        try {
            if (x >= 109 && x <= 245 && y >= 118 && y <= 255) {
                bg = new Image("res/Maps/Choice.png");
            } else if (x >= 280 && x <= 419 && y >= 118 && y <= 255) {
                bg = new Image("res/Maps/Choice1.png");
            } else if (x >= 450 && x <= 587 && y >= 118 && y <= 255) {
                bg = new Image("res/Maps/Choice2.png");
            } else if (x >= 190 && x <= 328 && y >= 280 && y <= 420) {
                bg = new Image("res/Maps/Choice3.png");
            } else if (x >= 367 && x <= 505 && y >= 280 && y <= 420) {
                bg = new Image("res/Maps/Choice4.png");
            } else {
                bg = new Image("res/Maps/ChoiceNo.png");
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
        if (clickCount == 2) { // double-clicked
            if (character(x, y)) {
                sbg.enterState(11);
            }
        }
    }

    private boolean character(int x, int y) {
        return (x >= 109 && x <= 245 && y >= 118 && y <= 255) ||
                (x >= 280 && x <= 419 && y >= 118 && y <= 255) ||
                (x >= 450 && x <= 587 && y >= 118 && y <= 255) ||
                (x >= 190 && x <= 328 && y >= 280 && y <= 420) ||
                (x >= 367 && x <= 505 && y >= 280 && y <= 420);
    }

    public int getID() {
        return 10;
    }
}
