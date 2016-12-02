package SevenDoubleZero.Game;

import SevenDoubleZero.Characters.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class PlayScreen extends BasicGameState{
    //private boolean animate = false;
    private Animation cloudAnimate;
    private boolean start = true;
    private Music bgMusic1;
    private Hero hero;
    private RPGCharacter player;
    private Image check;

    PlayScreen(){
    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        hero = Hero.getInstance();

        check = new Image("res/Maps/check.png").getScaledCopy(25, 25);
        cloudAnimate = new Animation(new SpriteSheet("res/Maps/maps.png",700,500), 500);
        bgMusic1 = new Music("res/Sounds/mapbg.wav");
        if(!bgMusic1.playing()) {
            bgMusic1.play(1f, 1f);
        }
        gc.setShowFPS(false);
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        player = hero.getPlayer();
        //System.out.println("HEY your player is " + player.toString());
        //Image bg = new Image("res/Maps/1.png");

        /*if(start) {
            g.drawImage(bg, 0, 0);
        }else if(animate){*/
        cloudAnimate.setLooping(false);
        cloudAnimate.draw(0, 0);
        g.setColor(Color.black);
        g.drawString("[Press Space]", 300, 450);
        if (cloudAnimate.isStopped()) {
            if (!(player instanceof Hades)) {
                if (player instanceof Apollo) {
                    check.draw(155, 110);
                } else if (player instanceof Athena) {
                    check.draw(512, 79);
                } else if (player instanceof Hermes) {
                    check.draw(356, 130);
                } else {
                    check.draw(493, 380);
                }
            } else {
                check.draw(574, 250);
            }
        }
        //}
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
        Input input = gc.getInput();
        cloudAnimate.update(delta);

        if(input.isKeyPressed(Input.KEY_SPACE)){
            if (!(player instanceof Hades)) {
                System.out.println("Becos it is " + player.toString()+ ",");
                sbg.enterState(3);
            } else {
                System.out.println("It is Hades!");
                sbg.enterState(4);
            }
        }
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

	public int getID() {
		return 1;
	}
}
