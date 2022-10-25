package entity;

import Game.GamePanel;
import java.util.Random;

/**
 *
 * @author avile
 */
public class NPC_Doc extends Entity
{

    public static boolean isStarting = true;

    public NPC_Doc(GamePanel gp)
    {
        super(gp);

        direction = "down";
        speed = 1;
        if (gp.player.isFalling == true)
        {
            speed = 0;
        }

        int factor = gp.tileSize / 6;

        solidArea.x = factor * 2;
        solidArea.y = factor * 3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = factor * 2;
        solidArea.height = factor * 2;

        getNPCImages();
        setFirstDialogues();
        isIdle = false;
    }

    public void setFirstDialogues()
    {
        vaciarDialogos();
        dialogues[0] = "Vaya... ¿Estas bien?";
        dialogues[1] = "Tu debes ser Isabel ¿No?";
        dialogues[2] = "Vaya golpe! jajaja";
        dialogues[3] = "Bueno!, me mandaron a darte indicaciones para la cita de hoy";
        dialogues[4] = "Tendrias que ir a tu casa primero";
        dialogues[5] = "Come algo, descansa un poco, la cita es mañana por la tarde";

//        dialogues[1][0] = "Ya fuiste a tu casa?";
//        dialogues[1][1] = "¿No?";
//        dialogues[1][2] = "No te preocupes, puedes pasear por aqui todo lo que gustes";
//        dialogues[1][3] = "El dia no pasara hasta que tomes la siesta";
//        dialogues[1][4] = "Hasta luego!";
    }

    public void setSecondDialogues()
    {

    }

    public void vaciarDialogos()
    {
        for (int i = 0; i < dialogues.length; i++)
        {
            if (dialogues[i] != null)
            {
                dialogues[i] = null;
            }
        }
    }

    @Override
    public void speak()
    {
        super.speak();
    }

    private void getNPCImages()
    {
        up1 = loadImage("/npc_doc/up1.png");
        up2 = loadImage("/npc_doc/up2.png");
        left1 = loadImage("/npc_doc/left1.png");
        left2 = loadImage("/npc_doc/left2.png");
        right1 = loadImage("/npc_doc/right1.png");
        right2 = loadImage("/npc_doc/right2.png");
        down1 = loadImage("/npc_doc/down1.png");
        down2 = loadImage("/npc_doc/down2.png");
        up3 = loadImage("/npc_doc/up3.png");
        right3 = loadImage("/npc_doc/right3.png");
        left3 = loadImage("/npc_doc/left3.png");
        down3 = loadImage("/npc_doc/down3.png");
    }

    @Override
    public void setAction()
    {
        if (isStarting == true)
        {
            actionCounter++;
            if (actionCounter > 120)
            {
                Random random = new Random();

                int i = random.nextInt(100) + 1;      //pick up a aleatory number

                if (i <= 25)
                {
                    direction = "up";
                }
                if (i >= 25 && i <= 50)
                {
                    direction = "down";
                }
                if (i >= 50 && i <= 75)
                {
                    direction = "left";
                }
                if (i >= 75 && i <= 100)
                {
                    direction = "right";
                }
                actionCounter = 0;
            }
        } else
        {
            direction = "left";
            speed = 5;
        }
    }

}
