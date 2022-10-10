/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.IsabelTheGame;

/**
 *
 * @author avile
 */
public class GameKeyHandler implements KeyListener
{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean isDebug = false, enterPressed;
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

        //PLAY STATE
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
