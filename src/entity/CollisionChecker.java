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
public class CollisionChecker
{

    private GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum, tileNum2;

        switch (entity.direction)
        {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.manager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.manager.tiles[tileNum].collision == true || gp.manager.tiles[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.manager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.manager.tiles[tileNum].collision == true || gp.manager.tiles[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.manager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.manager.tiles[tileNum].collision == true || gp.manager.tiles[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum = gp.manager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.manager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.manager.tiles[tileNum].collision == true || gp.manager.tiles[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player)
    {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++)
        {
            if (gp.obj[i] != null)
            {
                //Get Entity's solid Area Position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object solid Area Position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction)
                {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(gp.obj[i].solidArea))
                {
                    if (gp.obj[i].collision == true)
                    {
                        entity.collisionOn = true;
                    }
                    if (player == true)
                    {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] tarjet)
    {
        int index = 999;

        for (int i = 0; i < gp.npc.length; i++)
        {
            if (tarjet[i] != null)
            {
                //Get Entity's solid Area Position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object solid Area Position
                tarjet[i].solidArea.x = tarjet[i].worldX + tarjet[i].solidArea.x;
                tarjet[i].solidArea.y = tarjet[i].worldY + tarjet[i].solidArea.y;

                switch (entity.direction)
                {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(tarjet[i].solidArea))
                {
                    if (tarjet[i] != entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                tarjet[i].solidArea.x = tarjet[i].solidAreaDefaultX;
                tarjet[i].solidArea.y = tarjet[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer = false;
        //Get Entity's solid Area Position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Get the object solid Area Position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction)
        {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea))
        {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }

}
