package net.bc;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

/**
 * Created by Danielle98 on 11/7/2016.
 */
public class core extends Applet implements Runnable {

    private static final long serialVersionUID = 1l;
    private static JFrame window = new JFrame();
    public static final int res = 1;
    public static final double offsetX = 0, offsetY = 0;
    public static final double direction = 0;
    public boolean moving = false;
    public boolean run = false;
    private Image screen;
    public static Dimension screenSize = new Dimension(700,568);
    public static Dimension pixel =  new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight());
    public static Dimension size;
    public static String name = "Game";

    public core (){
    setPreferredSize(screenSize);
    }

    public static void main(String[] args) {
        core core1 = new core();
        window = new JFrame();
        window.add(core1);
        window.pack();

        size = new Dimension(window.getWidth(),window.getHeight());
        window.setTitle(name);
        window.setResizable(false);
        window.setLocation(0,0);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        core1.start();
    }

    public void start(){
        requestFocus();
        run = true;
        new Thread(this).start();

    }
    @Override
    public void stop(){
        run = false;
    }

    public void tick(){

    }

    public void render(){
        Graphics g = screen.getGraphics();
        g = this.getGraphics();
        g.drawImage(screen,0,0,screenSize.width,screenSize.height,0,0,pixel.width,pixel.height, null);
        g.dispose();
    }

    public void run() {
        screen = createVolatileImage(pixel.width,pixel.height);

        while(run){
            tick();
            render();
        }

        try{
            Thread.sleep(5);
        }catch(Exception e){
            System.out.println("Sleep thread error");
        }
    }

}
