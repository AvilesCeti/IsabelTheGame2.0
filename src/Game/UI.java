/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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

    public int commandNum = 0;
    public int storyCounter = 0;

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
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
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
    }

    public void drawPauseScreen()
    {
        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(32F));

        int lenght = getXStringCenter(text);
        int x = lenght;
        int y = gp.screenHeight / 2;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x - 2, y - 2);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
    }

    public int getXStringCenter(String cadena)
    {
        int lenght = (int) g2.getFontMetrics().getStringBounds(cadena, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght / 2;
        return x;
    }

    public int getComponentCenter(int width)
    {
        int x = gp.screenWidth / 2 - width / 2;
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

        for (String line : currentDialogue.split("\n"))
        {
            g2.drawString(currentDialogue, x, y);
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

}
