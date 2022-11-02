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
        setDialogues();
        isIdle = false;
    }

    public final void setDialogues()
    {
        dialogues[0][0] = "Vaya... ¿Estas bien?";
        dialogues[0][1] = "Tu debes ser Isabel ¿No?";
        dialogues[0][2] = "Vaya golpe! jajaja";
        dialogues[0][3] = "Bueno!, me mandaron a preguntarte por la \ndificultad.\nasi que... ¿Cual sera?";

        dialogues[1][0] = "¿Que? ¿Quieres cambiar la dificultad?";
        dialogues[1][1] = "La verdad es que es imposible una vez\nselecionada...";
        dialogues[1][2] = "Bueno... ";
        dialogues[1][3] = "la verdad es que solo se programo \nuna dificultad";
        dialogues[1][4] = "Pero que este sea un secreto entre tu y yo \n¿Si?";
        dialogues[1][5] = "Por cierto!\nYa probaste pulsando \"G\" o \"T\" o maybe\n\"CTRL\"?";
        dialogues[1][6] = "Hasta luego!";
        
        dialogues[2][0] = "Bien...";
        dialogues[2][1] = "Tengo que confesar algo...";
        dialogues[2][2] = "La verdad es que, el desarrollo de\neste juego fue mas tedioso de lo\nque pense en un principio...";
        dialogues[2][3] = "Y, quiero pedir una disculpa, ya que no hay\nmas que esto que vez.";
        dialogues[2][4] = "Si presionas \"T\", veras los FPS del juego";
        dialogues[2][5] = "En un caso optimo, estos apareceran\nen 60 clavados";
        dialogues[2][6] = "Y me fue muy dificil programar esto\nsiendo que a mi me va a 36 y con\nbajones";
        dialogues[2][7] = "Para un juego asi...\ncon estos graficos...\npues algo va mal..";
        dialogues[2][8] = "Quiere decir que algo\nestoy haciendo mal y\nla verdad...";
        dialogues[2][9] = "No puedo seguir programando\neste juego de esta\nmanera.";
        dialogues[2][10] = "Habra un juego, si\ndespues\nIsabelTheGame2.0 llegara";
        dialogues[2][11] = "Tomemos esto como un\n\"IsabelTheGame 1.5\"";
        dialogues[2][12] = "Prometo que mejorare y hare el juego que tengo\nen mente";
        dialogues[2][13] = "Esto me duele, mucho\nEn mi ego, En lo que yo\nconsideraba mi fuerte\nLa Programacion, en lo que crei que era bueno,\npues... es un golpe bajo";
        dialogues[2][14] = "Aprendi mucho, eso si\nPero todavia me falta mucho mas\nQue llegara en la siguiente version";
        dialogues[2][15] = "Tengo que aprender un nuevo\nlenguaje, Java ya se\nme quedo corto para los\njuegos que quiero";
        dialogues[2][16] = "En fin...\nlo siento, y por favor.\nEspera por la siguiente version";
        dialogues[2][17] = "Elegiste la opcion\nDIFICIL\nY decir que esto es todo cuanto menos es\nbastante DIFICIL de aceptar. jejeje";
    }



    @Override
    public void speak()
    {
        facePlayer();
        startDialogue(this, dialogueSet);
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

                int i = random.nextInt(100) + 1;     
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
