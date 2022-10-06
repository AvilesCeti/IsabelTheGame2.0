


package object;

import Game.GamePanel;
import entity.Entity;

/**
 *
 * @author avile
 */
public class OBJ_WhiteHouse extends Entity{

    public OBJ_WhiteHouse(GamePanel gp)
    {
        super(gp);
        name = "White House";
        down3 = loadImage("/objects/whiteHouse.png", gp.tileSize*2, gp.tileSize*4);
        solidArea.y = (int) (gp.tileSize*.8);
        solidArea.width = gp.tileSize*2;
        solidArea.height = (int) (gp.tileSize*2);
        solidAreaDefaultY = solidArea.y;
        collision = true;
        isIdle = true;
    }
    
}
