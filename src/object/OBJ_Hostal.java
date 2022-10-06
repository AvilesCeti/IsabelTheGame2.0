


package object;

import Game.GamePanel;
import entity.Entity;

/**
 *
 * @author avile
 */
public class OBJ_Hostal extends Entity{

    public OBJ_Hostal(GamePanel gp)
    {
        super(gp);
        name = "Hostal";
        down3 = loadImage("/objects/hostal.png", gp.tileSize*3, gp.tileSize*4);
        solidArea.y = (int) (gp.tileSize*.8);
        solidArea.width = gp.tileSize*3;
        solidArea.height = (int) (gp.tileSize*2);
        solidAreaDefaultY = solidArea.y;
        collision = true;
        isIdle = true;
    }
    
}
