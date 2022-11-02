/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import Persistencia.GameSettings;
import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.InputStream;
import main.IsabelTheGame;

/**
 *
 * @author avile
 */
public class UI
{

    private Font dialogo;
    GamePanel gp;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinish = false;
    public Graphics2D g2;
    public String currentDialogue = "";
    public int subState = 0;

    public String[][] messages = new String[20][20];
    public int messageSet = 0;
    public int messageIndex = 0;

    public int commandNum = 0;

    public Entity npc;
    public boolean banderaM = true;

    public UI(GamePanel gp)
    {
        this.gp = gp;

        try
        {
            InputStream is = getClass().getResourceAsStream("/fonts/dialogo.ttf");
            dialogo = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        setMessages();
    }

    private void setMessages()
    {
        messages[0][0] = "¿Que?, enserio despues de tanto\ndesarrollo vas a jugarlo en facil??";
        messages[0][1] = "Anda ya!, escoge algo mas complicado\nprometo que no muerde jaja";

        messages[1][0] = "Hmmm...\nnormal eh?, not bad, pero...";
        messages[1][1] = "Se que puedes hacerlo en modo dificil!\nyo creo en ti! :D";

        messages[2][0] = "Asi se habla!, Dificil, como las mujeres\nluchonas y empoderadas juegan";
        messages[2][1] = "*INCREMENTANDO DAÑO DE TODOS LOS ENEMIGOS*\n*DISMINUYENDO LOOT*\n*AUMENTANDO PELIGROSAMENTE NIVEL DE SARCASMO*";
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void drawPauseScreen()
    {
        String text = "JUEGO PAUSADO";

        g2.setFont(g2.getFont().deriveFont(40F));

        int x = getXStringCenter(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXStringCenter(String cadena)
    {
        int lenght = (int) g2.getFontMetrics().getStringBounds(cadena, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght / 2;
        return x;
    }

    public int getXStringAlignRight(String cadena, int tailX)
    {
        int lenght = (int) g2.getFontMetrics().getStringBounds(cadena, g2).getWidth();
        int x = tailX - lenght;
        return x;
    }

    private void drawDialogueScreen()
    {
        if (npc.dialogueSet == 0 && npc.dialogueIndex == 4)
        {
            //WINDOW
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            int width = gp.screenWidth - (gp.tileSize * 4);
            int height = gp.tileSize * 3;
            drawSubWindow(x, y, width, height);

            g2.setFont(dialogo);
            g2.setFont(g2.getFont().deriveFont(40F));
            x += gp.tileSize / 2;
            y += gp.tileSize / 2;

            g2.drawString("Facil", x, y);
            if (commandNum == 0)
            {
                g2.drawString(">", x - 20, y);
            }
            x += 300;
            g2.drawString("Normal", x, y);
            if (commandNum == 1)
            {
                g2.drawString(">", x - 20, y);
            }
            x += 300;
            g2.drawString("Dificil", x, y);
            if (commandNum == 2)
            {
                g2.drawString(">", x - 20, y);
            }
            if (gp.keyHandler.enterPressed)
            {
                gp.keyHandler.enterPressed = false;
                switch (commandNum)
                {
                    case 0:
                        gp.gameState = 8;
                        messageSet = 0;
                        break;
                    case 1:
                        gp.gameState = 8;
                        messageSet = 1;
                        break;
                    case 2:
                        gp.gameState = 8;
                        messageSet = 2;
                        break;
                }
                gp.keyHandler.enterPressed = false;
            }
        } else
        {
            if (npc.dialogueSet == 2 && banderaM)
            {
                banderaM = false;
                gp.music.volumeScale = 1;
                gp.music.checkVolume();
            }
            //WINDOW
            int x = gp.tileSize * 2;
            int y = gp.tileSize;
            int width = gp.screenWidth - (gp.tileSize * 4);
            int height = gp.tileSize * 3;
            drawSubWindow(x, y, width, height);

            g2.setFont(dialogo);
            g2.setFont(g2.getFont().deriveFont(40F));
            x += gp.tileSize / 2;
            y += gp.tileSize / 2;

            if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null)
            {
                currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
                if (gp.keyHandler.enterPressed)
                {
                    if (gp.gameState == gp.DIALOGUE_STATE)
                    {
                        npc.dialogueIndex++;

                        gp.keyHandler.enterPressed = false;
                    }
                }
            } else
            {
                npc.dialogueIndex = 0;
                if (gp.gameState == gp.DIALOGUE_STATE)
                {
                    npc.dialogueSet++;
                    gp.gameState = gp.PLAY_STATE;
                }
            }

            for (String line : currentDialogue.split("\n"))
            {
                g2.drawString(line, x, y);
                y += gp.tileSize / 2;
            }
        }

    }

    private void showMessages()
    {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 3;
        drawSubWindow(x, y, width, height);

        g2.setFont(dialogo);
        g2.setFont(g2.getFont().deriveFont(40F));
        x += gp.tileSize / 2;
        y += gp.tileSize / 2;

        if (messages[messageSet][messageIndex] != null)
        {
            currentDialogue = messages[messageSet][messageIndex];
            if (gp.keyHandler.enterPressed)
            {
                if (gp.gameState == 8)
                {
                    messageIndex++;

                    gp.keyHandler.enterPressed = false;
                }
            }
        } else
        {
            gp.gameState = gp.DIALOGUE_STATE;
            if (messageSet == 2)
            {
                npc.dialogueSet++;
                npc.dialogueIndex = 0;
                gp.gameState = gp.PLAY_STATE;
            }
            messageIndex = 0;
        }

        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(line, x, y);
            y += gp.tileSize / 2;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height)
    {
        g2.setColor(new Color(0, 0, 0, 220));
        g2.fillRoundRect(x, y, width, height, 35, 35);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawCharacterScreen()
    {
        final int frameX = gp.tileSize / 2;
        final int frameY = gp.tileSize / 2;
        final int frameWidth = (int) (gp.tileSize * 3);
        final int frameHeight = (int) (gp.tileSize * 5);

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = (int) (frameX + gp.tileSize * .5);
        int textY = (int) (frameY + gp.tileSize * .5);
        final int lineHeight = 34;

        //NAMES
        g2.drawString("Nivel", textX, textY);
        textY += lineHeight;
        g2.drawString("Salud", textX, textY);
        textY += lineHeight;
        g2.drawString("Velocidad", textX, textY);
        textY += lineHeight;
        g2.drawString("Fuerza", textX, textY);
        textY += lineHeight;
        g2.drawString("Ataque", textX, textY);
        textY += lineHeight;
        g2.drawString("Defensa", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Monedas", textX, textY);
        textY += lineHeight + 100;
        g2.drawString("Arma:", textX, textY);
        textY += lineHeight + 50;
        g2.drawString("Escudo:", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 50;
        textY = (int) (frameY + gp.tileSize * .5);
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.speed);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight;
        value = String.valueOf(gp.player.coins);
        textX = getXStringAlignRight(value, tailX);
        g2.drawString(value, textX, textY);

        textY += lineHeight + 70;
        value = String.valueOf(gp.player.defense);
        textX = getXStringAlignRight(value, tailX);
        g2.drawImage(gp.player.currentWeapon.down1, textX - 30, textY, null);

        textY += lineHeight + 50;
        value = String.valueOf(gp.player.defense);
        textX = getXStringAlignRight(value, tailX);
        g2.drawImage(gp.player.currentShield.down1, textX - 30, textY, null);
    }

    private void drawOptionsScreen()
    {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(26F));

        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize / 2;
        int frameWidth = gp.tileSize * 5;
        int frameHeight = gp.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState)
        {
            case 0:
                optionsTop(frameX, frameY, 6);
                break;
            case 2:
                options_controls(frameX, frameY, 6);
                break;
        }
        gp.keyHandler.enterPressed = false;
    }

    public void optionsTop(int frameX, int frameY, int scaleY)
    {
        int textX, textY;
        String text = "Options";
        textX = getXStringCenter(text);
        textY = frameY + gp.tileSize / 2;

        g2.drawString(text, textX, textY);

        //FULL SCREEN
        text = "Pantalla Completa: ";
        textX = frameX + gp.tileSize / 2;
        textY += gp.tileSize / 1.5;
        g2.drawString(text, textX, textY);
        if (commandNum == 0)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
            if (gp.keyHandler.enterPressed == true)
            {
                GameSettings.isFullScreen = !GameSettings.isFullScreen;
            }
        }

        //SOUND MUSIC
        text = "Volumen Musica: ";
        textY += gp.tileSize / 1.5;
        g2.drawString(text, textX, textY);
        if (commandNum == 1)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
        }

        //SOUND EFFECTS
        text = "Volumen Efectos: ";
        textY += gp.tileSize / 1.5;
        g2.drawString(text, textX, textY);
        if (commandNum == 2)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
        }

        //CONTROLS
        text = "Controles";
        textY += gp.tileSize / 1.5;
        g2.drawString(text, textX, textY);
        if (commandNum == 3)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
            if (gp.keyHandler.enterPressed == true)
            {
                subState = 2;
                commandNum = 0;
            }
        }

        //EXIT
        text = "Salir";
        textY += gp.tileSize / 1.5;
        g2.drawString(text, textX, textY);
        if (commandNum == 4)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
            if (gp.keyHandler.enterPressed == true)
            {
                IsabelTheGame.salir();
            }
        }

        text = "Regresar";
        textY = (gp.tileSize * scaleY) - gp.tileSize / 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 5)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
            if (gp.keyHandler.enterPressed == true)
            {
                gp.gameState = gp.PLAY_STATE;
            }
        }

        //FULL SCREEN CHECK BOX
        textX = (int) (frameX + gp.tileSize * 3);
        textY = (int) (frameY + gp.tileSize * 1);

        if (!GameSettings.isFullScreen)
        {
            g2.drawRect(textX, textY, gp.tileSize / 3, gp.tileSize / 3);
        } else
        {
            g2.fillRect(textX, textY, gp.tileSize / 3, gp.tileSize / 3);
        }

        //MUSIC VOLUME
        textY += gp.tileSize / 1.5;
        g2.drawRect(textX, textY, (int) (gp.tileSize * 1.5), gp.tileSize / 3);
        int volumeWidth = (int) (((gp.tileSize * 1.5) / 5) * gp.music.volumeScale);
        g2.fillRect(textX, textY, volumeWidth, gp.tileSize / 3);

        //EFFECT VOLUME
        textY += gp.tileSize / 1.5;
        g2.drawRect(textX, textY, (int) (gp.tileSize * 1.5), gp.tileSize / 3);
        volumeWidth = (int) (((gp.tileSize * 1.5) / 5) * gp.se.volumeScale);
        g2.fillRect(textX, textY, volumeWidth, gp.tileSize / 3);
    }

    public void options_controls(int frameX, int frameY, int scaleY)
    {
        int textX, textY;

        String text = "Controles";
        textX = getXStringCenter(text);
        textY = frameY + gp.tileSize / 2;
        g2.drawString(text, textX, textY);

        text = "Arriba:";
        textX = frameX + gp.tileSize / 2;
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Izquierda:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Abajo:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Derecha:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Menu:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Estadisticas Jugador:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Accion:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);
        text = "Pausa:";
        textY += gp.tileSize / 2;
        g2.drawString(text, textX, textY);

        text = "Regresar";
        textY = (gp.tileSize * scaleY) - gp.tileSize / 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0)
        {
            g2.drawString(">", textX - gp.tileSize / 3, textY);
            if (gp.keyHandler.enterPressed == true)
            {
                subState = 0;
                commandNum = 3;
            }
        }

        textX = (int) (frameX + gp.tileSize * 3.5);
        textY = frameY + gp.tileSize;
        g2.drawString("W", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("A", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("S", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("D", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("CTRL", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("G", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Enter", textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("P", textX, textY);
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(dialogo);
        g2.setColor(Color.WHITE);

        //PLAY STATE
        if (gp.gameState == gp.PLAY_STATE)
        {
            // UI DISPLAYED ON GAME
        }
        //PAUSE STATE
        if (gp.gameState == gp.PAUSE_STATE)
        {
            //UI DISPLAYED ON PAUSE
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.DIALOGUE_STATE)
        {
            //UI DISPLAYED ON PAUSE
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if (gp.gameState == gp.CHARACTER_STATE)
        {
            drawCharacterScreen();
        }
        //OPTIONS STATE
        if (gp.gameState == gp.OPTIONS_STATE)
        {
            drawOptionsScreen();
        }
        if (gp.gameState == 8)
        {
            showMessages();
        }
    }
}
