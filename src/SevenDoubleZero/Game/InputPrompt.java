package SevenDoubleZero.Game;


import SevenDoubleZero.Characters.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class InputPrompt extends BasicGameState{
    private Image bg;
    private Hero hero;
    private String intro = "[PRESS ENTER AFTER CHOOSING A CHARACTER]";
    private StateBasedGame sbg;
    private int choice = 0;

    public InputPrompt() {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        hero = Hero.getInstance();
        bg = new Image("res/Maps/ChoiceNo.png");
        this.sbg = sbg;
        gc.setShowFPS(false);
        choice = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame cbg, Graphics g) throws SlickException {
        bg.draw(0, 0);
        g.setColor(Color.black);
        g.drawString(intro, 175, 460);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        /*if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            hero.setHero(new Apollo());
            sbg.enterState(3);
        } else if (gc.getInput().isKeyPressed(Input.KEY_2)) {
            hero.setHero(new Artemis());
            sbg.enterState(3);
        } else if (gc.getInput().isKeyPressed(Input.KEY_3)) {
            hero.setHero(new Athena());
            sbg.enterState(3);
        } else if (gc.getInput().isKeyPressed(Input.KEY_4)){
            hero.setHero(new Hades());
            sbg.enterState(4);
        } else if (gc.getInput().isKeyPressed(Input.KEY_5)) {
            hero.setHero(new Hermes());
            sbg.enterState(3);
        }*/

        if (gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
            System.out.println("You chose " + choice);
            switch (choice) {
                case 1:
                    hero.setHero(new Apollo());
                    System.out.println("set to Apollo");
                    sbg.enterState(1);
                    break;
                case 2:
                    hero.setHero(new Artemis());
                    System.out.println("set to Artemis");
                    sbg.enterState(1);
                    break;
                case 3:
                    hero.setHero(new Athena());
                    System.out.println("set to Athena");
                    sbg.enterState(1);
                    break;
                case 4:
                    hero.setHero(new Hades());
                    System.out.println("set to Hades");
                    sbg.enterState(1);
                    break;
                case 5:
                    hero.setHero(new Hermes());
                    System.out.println("set to Hermes");
                    sbg.enterState(1);
                    break;
                default:
                    // do nothing
            }
        }
    }

    public void mouseClicked(int button, int x, int y, int clickCount){
        try {
            if (x >= 109 && x <= 245 && y >= 118 && y <= 255) {
                bg = new Image("res/Maps/Choice.png");
                System.out.println("You chose Apollo");
                choice = 1;
            } else if (x >= 280 && x <= 419 && y >= 118 && y <= 255) {
                System.out.println("You chose Artemis");
                bg = new Image("res/Maps/Choice1.png");
                choice = 2;
            } else if (x >= 450 && x <= 587 && y >= 118 && y <= 255) {
                System.out.println("You chose Athena");
                bg = new Image("res/Maps/Choice2.png");
                choice = 3;
            } else if (x >= 190 && x <= 328 && y >= 280 && y <= 420) {
                System.out.println("You chose Hades");
                bg = new Image("res/Maps/Choice3.png");
                choice = 4;
            } else if (x >= 367 && x <= 505 && y >= 280 && y <= 420) {
                System.out.println("You chose Hermes");
                bg = new Image("res/Maps/Choice4.png");
                choice = 5;
            } else {
                bg = new Image("res/Maps/ChoiceNo.png");
                choice = 0;
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return 2;
    }
}
