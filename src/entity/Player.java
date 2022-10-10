package entity;

import Game.GameKeyHandler;
import Game.GamePanel;
import Persistencia.GameSettings;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Shield;
import object.OBJ_Weapon;

/**
 *
 * @author avile
 */
public final class Player extends Entity
{

    private BufferedImage fall1, fall2, fall3;
    private BufferedImage defaultImage;
    private final GameKeyHandler gkh;

    public int screenX;
    public int screenY;
    public int lastNPC = 999;
    public boolean isFalling = true;        //SI SE HARA LA ANIMACION DE AVANZAR
    public int fallCounter = 0, fallNumber = 0;
    public double animationY = 0;

    public Player(GamePanel gp, GameKeyHandler gkh)
    {
        super(gp);
        this.defaultImage = null;
        this.gkh = gkh;
        screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
        screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

        int factor = gp.tileSize / 6;

        solidArea.x = factor * 2;
        solidArea.y = factor * 3;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = factor * 2;
        solidArea.height = factor * 2;

        animationY = -gp.tileSize;

        setDefaultValues();
        getPlayerImages();
        
        isFalling = !GameSettings.hasFirstFalled;
    }

    private void getPlayerImages()
    {
        up1 = loadImage("/player/up1.png");
        up2 = loadImage("/player/up2.png");
        left1 = loadImage("/player/left1.png");
        left2 = loadImage("/player/left2.png");
        right1 = loadImage("/player/right1.png");
        right2 = loadImage("/player/right2.png");
        down1 = loadImage("/player/down1.png");
        down2 = loadImage("/player/down2.png");
        up3 = loadImage("/player/up3.png");
        right3 = loadImage("/player/right3.png");
        left3 = loadImage("/player/left3.png");
        down3 = loadImage("/player/down3.png");
        fall1 = loadImage("/player/fall1.png");
        fall2 = loadImage("/player/fall2.png");
        fall3 = loadImage("/player/fall3.png");
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize * 67;
        worldY = gp.tileSize * 82;
        speed = (gp.timesPerSecond / 15);
        level = 1;
        maxLife = 50;
        life = maxLife;
        strength = 10;
        exp = 0;
        nextLevelExp = 100;
        coins = 0;
        currentWeapon = new OBJ_Weapon(gp);
        currentShield = new OBJ_Shield(gp);
        
        attack = getAttack();
        defense = getDefense();
    }

    public void fallAnimation()
    {
        fallNumber++;
        if (fallNumber < gp.timesPerSecond)
        {
            fallCounter = 1;

            animationY = animationY + ((screenY / gp.timesPerSecond) + (gp.tileSize / gp.timesPerSecond));
        } else if (fallNumber < (gp.timesPerSecond * 1.5))
        {
            animationY = screenY + (gp.tileSize * .1);
            fallCounter = 2;
        } else if (fallNumber < (gp.timesPerSecond * 2))
        {
            fallCounter = 3;
        } else if (fallNumber < (gp.timesPerSecond * 2.5))
        {
            fallCounter = 2;
        } else if (fallNumber < (gp.timesPerSecond * 3))
        {
            fallCounter = 3;
        } else if (fallNumber < (gp.timesPerSecond * 6.7))
        {
            fallCounter = 4;

            if (fallNumber > (gp.timesPerSecond * 4.5))
            {
                direction = "left";
                fallCounter = 5;
            }
            animationY = screenY;
            NPC_Doc.isStarting = false;
        } else
        {
            gp.gameState = gp.DIALOGUE_STATE;
            this.lastNPC = 0;
            gp.npc[0].speak();
            NPC_Doc.isStarting = true;
            isFalling = false;
            GameSettings.hasFirstFalled = true;
        }
    }

    @Override
    public void update()
    {
        if (!isFalling)
        {
            if (gkh.upPressed || gkh.leftPressed || gkh.rightPressed || gkh.downPressed || gkh.enterPressed)
            {
                isIdle = false;
                if (gkh.upPressed == true)
                {
                    direction = "up";

                }
                if (gkh.leftPressed == true)
                {
                    direction = "left";

                }
                if (gkh.downPressed == true)
                {

                    direction = "down";
                }
                if (gkh.rightPressed == true)
                {

                    direction = "right";
                }

                //CHECK TILE COLISSION
                collisionOn = false;
                gp.cChecker.checkTile(this);

                //CHECK OBJECT COLLISION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                //CHECK NPC COLLISION
                lastNPC = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(lastNPC);

                //CHECK EVENT
                gp.eHandler.checkEvent();

                //IF COLISSION IS FALSE, PLAYER CAN MOVE
                if (collisionOn == false && gkh.enterPressed == false)
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

                //RESET ENTER
                gp.keyHandler.enterPressed = false;

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
            } else
            {
                isIdle = true;
            }
        } else
        {
            fallAnimation();
        }
    }

    public void pickUpObject(int index)
    {
        if (index != 999)
        {

        }
    }

    @Override
    public void draw(Graphics2D g2)
    {
        if (!isFalling)
        {
            switch (direction)
            {
                case "up":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            defaultImage = up1;
                        } else
                        {
                            defaultImage = up2;
                        }
                    } else
                    {
                        defaultImage = up3;
                    }
                    break;
                case "down":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            defaultImage = down1;
                        } else
                        {
                            defaultImage = down2;
                        }
                    } else
                    {
                        defaultImage = down3;
                    }
                    break;
                case "left":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            defaultImage = left1;
                        } else
                        {
                            defaultImage = left2;
                        }
                    } else
                    {
                        defaultImage = left3;
                    }
                    break;
                case "right":
                    if (!isIdle)
                    {
                        if (spriteNumber == 1)
                        {
                            defaultImage = right1;
                        } else
                        {
                            defaultImage = right2;
                        }
                    } else
                    {
                        defaultImage = right3;
                    }
                    break;
            }

            g2.drawImage(defaultImage, screenX, screenY, null);
            if (gkh.isDebug)
            {
                g2.setColor(Color.RED);
                g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

            }
        } else
        {
            if (fallCounter == 1)
            {
                defaultImage = fall1;
            } else if (fallCounter == 2)
            {
                defaultImage = fall2;
            } else if (fallCounter == 3)
            {
                defaultImage = fall3;
            } else if (fallCounter == 4)
            {
                defaultImage = down3;
            } else if (fallCounter == 5)
            {
                defaultImage = left3;
            }

            g2.drawImage(defaultImage, screenX, (int) animationY, null);
        }
    }

    private void interactNPC(int npcIndex)
    {
        if (npcIndex != 999)
        {
            if (gp.keyHandler.enterPressed)
            {
                gp.gameState = gp.DIALOGUE_STATE;
                gp.npc[npcIndex].speak();
            }
        }
    }

    private int getAttack()
    {
        return strength * currentWeapon.attackValue;
    }

    private int getDefense()
    {
        return strength * currentShield.defenseValue;
    }
}
