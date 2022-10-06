/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import Game.GamePanel;

/**
 *
 * @author avile
 */
public class OBJ_Coin extends Entity
{

    public OBJ_Coin(GamePanel gp)
    {
        super(gp);
        name = "Coin";
        down1 = loadImage("/objects/coin.png");
        
        solidArea.x = (gp.tileSize/5)*1;
        solidArea.y = (gp.tileSize/5)*1;
        solidArea.width = (gp.tileSize/5)*3;
        solidArea.height = (gp.tileSize/5)*3;
    }

}
