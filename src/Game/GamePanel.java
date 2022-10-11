package Game;

import entity.CollisionChecker;
import entity.Entity;
import entity.EventHandler;
import entity.Player;
import tile.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

/**
 *
 * @author avile
 */
public class GamePanel extends JPanel implements Runnable
{

    //GAME SETTINGS
    public int timesPerSecond = 75;
    public Thread gameThread;
    public final int originalTileSize = 32;
    private final int scale = 4;
    public int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 11;
    public final int maxScreenRow = 6;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

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

    //Player Settings
    int x = 100, y = 100, speed = 4;

    //GAME STATE
    public int gameState;
    public final int TITLE_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int DIALOGUE_STATE = 3;
    public final int CHARACTER_STATE = 4;

    //DEBUG VARIABLES
    long startTime = 0, lastTime = 0;

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
        aSetter.setObject();
        aSetter.setNPC();
        gameState = TITLE_STATE;
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / timesPerSecond;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if (delta >= 1)
            {
                update();

                repaint();
                delta--;
                drawCount++;
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

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if (gameState == TITLE_STATE)
        {
            ui.draw(g2);
        } else
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
            
            //UI
            ui.draw(g2);
            g2.dispose();
        }

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
