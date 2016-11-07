package net.bc;

import java.awt.*;

/**
 * Created by Danielle98 on 11/7/2016.
 */
public class background extends Rectangle {
    public int[] id = {-1,-1};
    public background(Rectangle rect, int[] id){
        setBounds(rect);
        this.id = id;
    }

    public void render(Graphics g){
        g.drawImage(tile.background, x - (int)core.offsetX, y - (int)core.offsetX,x + width - (int)core.offsetX ,y + height - (int)core.offsetY, id[0] * tile.size, id[1]*tile.size, id[0] *tile.size + tile.size, id[1] * tile.size + tile.size, null);
    }

}
