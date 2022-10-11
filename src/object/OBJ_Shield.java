


package object;

import Game.GamePanel;
import entity.Entity;

/**
 *
 * @author avile
 */
public class OBJ_Shield extends Entity
{

    public OBJ_Shield(GamePanel gp)
    {
        super(gp);
        
        name = "Shield";
        down1 = loadImage("/objects/chest1.png", gp.tileSize/2, gp.tileSize/2);
        defenseValue = 2;
    }
    
}
