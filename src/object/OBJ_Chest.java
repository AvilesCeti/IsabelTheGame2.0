/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import Game.GamePanel;
import entity.Entity;

/**
 *
 * @author avile
 */
public class OBJ_Chest extends Entity
{

    public OBJ_Chest(GamePanel gp)
    {
        super(gp);
        name = "Chest";
        down1 = loadImage("/objects/chest1.png");
        collision = true;
    }

}
