package entity;

import Game.GamePanel;
import tile.UtilityTool;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author avile
 */
public abstract class Entity
{

    //CLASS PROPERTIES
    public GamePanel gp;
    public int worldX, worldY;
    public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2, up3, left3, right3, down3;
    public String direction = "down";

    //COUNTERS AND BOOLEANS
    public boolean isIdle = true;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public int actionCounter = 0;
    public boolean collision = false;
    public BufferedImage image;
    //DIALOGUES
    public String[][] dialogues;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;

    //COLLISION THINGS
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    //ENTITY PROPERTIES
    public String name;
    public int type;
    public int speed;
    public int maxLife;
    public int life;
    public int level;
    public int strength;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coins;
    public Entity currentWeapon;
    public Entity currentShield;

    //ITEM PROPERTIES
    public int attackValue;
    public int defenseValue;

    public Entity(GamePanel gp)
    {
        this.dialogues = new String[20][20];
        this.gp = gp;
    }

    public BufferedImage loadImage(String path)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return image;
    }

    public BufferedImage loadImage(String path, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return image;
    }

    public void facePlayer()
    {
        switch (gp.player.direction)
        {
            case "up":
                direction = "down";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;
            case "down":
                direction = "up";
                break;
        }
    }

    public void speak()
    {
        
    }

    public void startDialogue(Entity entity, int setNum)
    {
        gp.gameState = gp.DIALOGUE_STATE;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    public void setAction()
    {
    }

    public void update()
    {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer)
        {
            //MAYBE A MONSTER THAT TOUCH THE PLAYER (?)
        }

        //IF COLISSION IS FALSE, PLAYER CAN MOVE
        if (collisionOn == false)
        {
            switch (direction)
            {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 16)
        {
            if (spriteNumber == 1)
            {
                spriteNumber = 2;
            } else
            {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            switch (direction)
            {
                case "up":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            image = up1;
                        } else
                        {
                            image = up2;
                        }
                    } else
                    {
                        image = up3;
                    }
                    break;
                case "down":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            image = down1;
                        } else
                        {
                            image = down2;
                        }
                    } else
                    {
                        image = down3;
                    }
                    break;
                case "left":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            image = left1;
                        } else
                        {
                            image = left2;
                        }
                    } else
                    {
                        image = left3;
                    }
                    break;
                case "right":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            image = right1;
                        } else
                        {
                            image = right2;
                        }
                    } else
                    {
                        image = right3;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, null);
            if (gp.keyHandler.isDebug)
            {
                g2.setColor(Color.RED);
                g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }

        }
    }
}
