/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import entity.NPC_Doc;



/**
 *
 * @author avile
 */
public class AssetSetter
{

    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;

    }

    public void setObject()
    {
//        gp.obj[0] = new OBJ_Coin(gp);
//        gp.obj[0].worldX = gp.tileSize*26;
//        gp.obj[0].worldY = gp.tileSize*15;

    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_Doc(gp);
        gp.npc[0].worldX = gp.tileSize * 75;
        gp.npc[0].worldY = gp.tileSize * 82;
        
    }

}
