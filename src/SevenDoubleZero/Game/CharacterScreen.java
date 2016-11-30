package SevenDoubleZero.Game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class CharacterScreen extends BasicGameState{
    private Image athena;
    private Image apollo;
    private Image hades;
    private Image hermes;
    private Image artemis;
    private Animation athenaAni;
    private Animation apolloAni;
    private Animation artemisAni;
    private Animation hadesAni;
    private Animation hermesAni;
    private boolean start = true;
    private Music bgMusic1;
    int ct = 1000;

    CharacterScreen(){

    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        athena = new Image("res/Maps/CharScreen/athena.png");
        apollo = new Image("res/Maps/CharScreen/apollo.png");
        artemis = new Image("res/Maps/CharScreen/artemis.png");
        hermes = new Image("res/Maps/CharScreen/hermes.png");
        hades = new Image("res/Maps/CharScreen/hades.png");
        athenaAni = new Animation(new SpriteSheet("res/Characters/Athena/Athena2.png", 350, 250), 500);
        apolloAni = new Animation(new SpriteSheet("res/Characters/Apollo/Apollo2.png", 350, 250), 500);
        artemisAni = new Animation(new SpriteSheet("res/Characters/Artemis/Artemis2.png", 350, 250), 500);
        hadesAni = new Animation(new SpriteSheet("res/Characters/Hades/Hades2.png", 450, 360), 500);
        hermesAni = new Animation(new SpriteSheet("res/Characters/Hermes/Hermes2.png", 350, 250), 500);
        bgMusic1 = new Music("res/Sounds/mapbg.wav");
        if(!bgMusic1.playing()) {
            bgMusic1.play(1f, 1f);
        }

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        athena.draw(0,0);

        if (ct%5 == 0){
            athena.draw(0,0);
            athenaAni.draw(50,125);
        }

        if(ct%5 == 1){
            apollo.draw(0,0);
            apolloAni.draw(50, 125);
        }

        if(ct%5 == 2){
            artemis.draw(0,0);
            artemisAni.draw(50, 125);
        }

        if(ct%5 == 3){
            hermes.draw(0,0);
            hermesAni.draw(50, 125);
        }

        if(ct%5 == 4){
            hades.draw(0,0);
            hadesAni.draw(-30, 110);
        }
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        athenaAni.update(delta);
        Input input = gc.getInput();
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (input.getMouseX() >= gc.getWidth()/2 || input.isKeyPressed(Input.KEY_RIGHT)) {
                ct++;
            } else if (input.getMouseX() < gc.getWidth()/2 || input.isKeyPressed(Input.KEY_LEFT)){
                ct--;
            }
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            ct--;
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            ct++;
        } else if(input.isKeyDown(Input.KEY_ESCAPE)) {
            sbg.enterState(1);
        }
    }
    public int getID() {
        return 8;
    }

    public void enter(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        bgMusic1.loop();
    }

    public void leave(GameContainer gc, StateBasedGame sbg){
        try{
            super.enter(gc, sbg);
        }catch (SlickException e){
            e.printStackTrace();
        }
        bgMusic1.stop();
    }
}
