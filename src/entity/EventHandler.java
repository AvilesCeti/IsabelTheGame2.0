/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import Game.GamePanel;

/**
 *
 * @author avile
 */
public class EventHandler
{

    private GamePanel gp;
    public EventRect[][] eventRect;
    private int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp)
    {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        for (int i = 0; i < gp.maxWorldRow; i++)
        {
            for (int j = 0; j < gp.maxWorldCol; j++)
            {

                eventRect[i][j] = new EventRect();
                eventRect[i][j].x = (gp.tileSize / 10) * 4;
                eventRect[i][j].y = (gp.tileSize / 10) * 4;
                eventRect[i][j].width = (gp.tileSize / 10) * 2;
                eventRect[i][j].height = (gp.tileSize / 10) * 2;
                eventRect[i][j].eventRectDefaultX = eventRect[i][j].x;
                eventRect[i][j].eventRectDefaultY = eventRect[i][j].y;
            }
        }
    }

    public void checkEvent()
    {
        //CHECK IF THE PLAYER IS MORE THAN 1 TILE AWAY 
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize)
        {
            canTouchEvent = true;
        }

        if (canTouchEvent)
        {
//            if (hit(72, 81, "any"))
//            {
//                exampleEvent(72, 81, gp.DIALOGUE_STATE);
//            }
//            if (hit(67, 84, "any"))
//            {
//                exampleEnterEvent(67, 84, gp.DIALOGUE_STATE);
//            }
//            if (hit(62, 81, "any"))
//            {
//                exampleOnceEvent(62, 81, gp.DIALOGUE_STATE);
//            }
        }

    }

    public boolean hit(int col, int row, String reqDirection)
    {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false)
        {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
            {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;

            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    private void exampleEvent(int col, int row, int gameState)
    {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Quieres ir a la casa?";

        eventRect[col][row].eventDone = true;
    }

    private void exampleOnceEvent(int col, int row, int gameState)
    {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Quieres ir a la casa?";
        canTouchEvent = false;
    }

    private void exampleEnterEvent(int col, int row, int gameState)
    {
        if (gp.keyHandler.enterPressed == true)
        {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "This is another example xd";

        }
    }

}
