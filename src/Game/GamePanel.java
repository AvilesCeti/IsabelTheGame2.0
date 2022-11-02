package Game;

import Persistencia.GameSettings;
import entity.CollisionChecker;
import entity.Entity;
import entity.EventHandler;
import entity.Player;
import tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import main.IsabelTheGame;

/**
 *
 * @author avile
 */
public class GamePanel extends JPanel implements Runnable
{

    //GAME SETTINGS
    public int timesPerSecond = 60;
    public Thread gameThread;
    public final int originalTileSize = 32;
    private final int scale = 4;
    public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 13;
    public final int maxScreenRow = 7;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    //FULL SCREEN SETTINGS
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    private BufferedImage tempScreen;
    private Graphics2D g2;

    //WORLD SETTINGS
    public int maxWorldCol;
    public int maxWorldRow;

    //EVENTS
    public EventHandler eHandler;
    
    //SOUND
    Sound music = new Sound();
    Sound se = new Sound();

    //UI
    public UI ui = new UI(this);

    //KEY EVENTS
    public GameKeyHandler keyHandler;

    //OBJECTS AND ENTITYES
    public Entity[] npc = new Entity[10];
    public Entity obj[] = new Entity[10];
    public AssetSetter aSetter = new AssetSetter(this);
    public ArrayList<Entity> eList = new ArrayList<>();

    //PLAYER & COLLISIONS
    public Player player;
    public CollisionChecker cChecker = new CollisionChecker(this);

    //TILES & BACKGROUND
    public TileManager manager;

    //GAME STATE
    public int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int DIALOGUE_STATE = 3;
    public final int CHARACTER_STATE = 4;
    public final int OPTIONS_STATE = 5;

    //DEBUG VARIABLES
    long FPS = 0;

    public GamePanel()
    {
        keyHandler = new GameKeyHandler(this);             //Inicialize the KeyEvents
        player = new Player(this, keyHandler);
        manager = new TileManager(this);
        eHandler = new EventHandler(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);       //Make the panel to create Graphics things on second thread

        this.addKeyListener(keyHandler);
        this.setFocusable(true);        //Make the panel Focusable to be render in a first Instance    

    }

    public void setupGame()
    {
        music.setFile(1);
        music.play();
        aSetter.setObject();
        aSetter.setNPC();
        gameState = PLAY_STATE;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(GameSettings.isFullScreen){
            setFullScreen();
        }
    }

    public void setFullScreen()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported())
        {
            gd.setFullScreenWindow(IsabelTheGame.frame);
            screenWidth2 = IsabelTheGame.frame.getWidth();
            screenHeight2 = IsabelTheGame.frame.getHeight();
        } else
        {
            System.err.print("FullScreen is not supported.");
        }
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / timesPerSecond;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1)
            {
                update();

                render();

                delta--;
                drawCount++;
            }
            if (timer >= 1000000000)
            {
                FPS = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update()
    {
        if (gameState == PLAY_STATE)
        {
            //NPC
            for (int i = 0; i < npc.length; i++)
            {
                if (npc[i] != null)
                {
                    npc[i].update();
                }
            }

            //PLAYER
            player.update();
        }
        if (gameState == PAUSE_STATE)
        {
            // nothing
        }
    }

    public void render()
    {
        //TILE
        manager.draw(g2);

        //ADD ENTITYES TO THE LIST
        eList.add(player);
        for (int i = 0; i < npc.length; i++)
        {
            if (npc[i] != null)
            {
                eList.add(npc[i]);
            }
        }
        for (int i = 0; i < obj.length; i++)
        {
            if (obj[i] != null)
            {
                eList.add(obj[i]);
            }
        }

        Collections.sort(eList, new Comparator<Entity>()
        {
            @Override
            public int compare(Entity o1, Entity o2)
            {
                int result = Integer.compare(o1.worldY, o2.worldY);
                return result;
            }
        });

        //DRAW ENTITIES
        for (int i = 0; i < eList.size(); i++)
        {
            eList.get(i).draw(g2);
        }

        //EMPTY ENTITY LIST
        eList.clear();

        if (keyHandler.isDebug)
        {
            g2.setFont(new Font("Courier New", Font.PLAIN, 32));
            g2.setColor(Color.YELLOW);
            g2.drawString("FPS: "+FPS, 50, 50);
        }

        //UI
        ui.draw(g2);
        drawToScreen();
    }

    public void drawToScreen()
    {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

}
