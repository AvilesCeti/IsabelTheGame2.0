


package object;

import entity.Entity;
import Game.GamePanel;

/**
 *
 * @author avile
 */
public class OBJ_Weapon extends Entity{

    public OBJ_Weapon(GamePanel gp)
    {
        super(gp);
        
        name = "Weapon";
        down1 = loadImage("/objects/coin.png", gp.tileSize/2, gp.tileSize/2);
        attackValue = 2;
    }
    
}
