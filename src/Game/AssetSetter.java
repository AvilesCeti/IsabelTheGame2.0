/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import entity.NPC_Doc;
import object.OBJ_Hostal;
import object.OBJ_WhiteHouse;

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
        gp.obj[0] = new OBJ_WhiteHouse(gp);
        gp.obj[0].worldX = gp.tileSize*76;
        gp.obj[0].worldY = (int) (gp.tileSize*89);
        
        gp.obj[1] = new OBJ_Hostal(gp);
        gp.obj[1].worldX = gp.tileSize*72;
        gp.obj[1].worldY = (int) (gp.tileSize*89);

    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_Doc(gp);
        gp.npc[0].worldX = gp.tileSize * 70;
        gp.npc[0].worldY = gp.tileSize * 82;
        
    }

}
