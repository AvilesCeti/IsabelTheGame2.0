/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author avile
 */
public class GameKeyHandler implements KeyListener
{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean isDebug = false, enterPressed = false;
    public GamePanel gp;

    public GameKeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (gp.gameState == gp.PLAY_STATE)
        {
            playState(code);
        } //PAUSE STATE
        else if (gp.gameState == gp.PAUSE_STATE)
        {
            pauseState(code);
        } //DIALOGUE STATE
        else if (gp.gameState == gp.DIALOGUE_STATE)
        {
            dialogueState(code);
        } //CHARACTER STATE 
        else if (gp.gameState == gp.CHARACTER_STATE)
        {
            characterState(code);
        } else if (gp.gameState == gp.OPTIONS_STATE)
        {
            optionsState(code);
        }
    }

    public void playState(int code)
    {
        if (code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P)
        {
            gp.gameState = gp.PAUSE_STATE;
        }
        if (code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_G)
        {
            gp.gameState = gp.CHARACTER_STATE;
        }

        if (code == KeyEvent.VK_CONTROL)
        {
            gp.gameState = gp.OPTIONS_STATE;
        }

        //DEBUG SYSTEM
        if (code == KeyEvent.VK_T)
        {
            isDebug = !isDebug;
        }
    }

    public void pauseState(int code)
    {
        if (code == KeyEvent.VK_P)
        {
            gp.gameState = gp.PLAY_STATE;
        }
    }

    public void dialogueState(int code)
    {
        if (code == KeyEvent.VK_ENTER)
        {
            gp.gameState = gp.PLAY_STATE;
        }
    }

    public void characterState(int code)
    {
        if (code == KeyEvent.VK_G)
        {
            gp.gameState = gp.PLAY_STATE;
        }
    }

    private void optionsState(int code)
    {
        if (code == KeyEvent.VK_CONTROL)
        {
            gp.gameState = gp.PLAY_STATE;
            gp.ui.commandNum = 0;
            gp.ui.subState = 0;
        }
        if (code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
        int maxInterval = 0;
        switch (gp.ui.subState)
        {
            case 0:
                maxInterval = 5;
                break;
            case 1:
                maxInterval = 0;
                break;
            case 2:
                maxInterval = 0;
                break;

        }
        if (code == KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0)
            {
                gp.ui.commandNum = maxInterval;
            }
        }
        if (code == KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > maxInterval)
            {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A)
        {
            if (gp.ui.subState == 0)
            {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0)
                {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0)
                {
                    gp.se.volumeScale--;
                }
            }
        }
        if (code == KeyEvent.VK_D)
        {
            if (gp.ui.subState == 0)
            {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5)
                {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5)
                {
                    gp.se.volumeScale++;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }

}
