/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import entity.NPC_Doc;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
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
        
        try
        {
            SourceDataLine sdl = AudioSystem.getSourceDataLine(new AudioFormat(48000, 16, 2, true, true ));
                    sdl.available();
                    } catch (LineUnavailableException ex)
        {
            Logger.getLogger(AssetSetter.class.getName()).log(Level.SEVERE, null, ex);
        }

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
