package tile;

import Game.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author avile
 */
public class TileManager
{

    private String path, collision;
    private UtilityTool uTool = new UtilityTool();
    private final int TILE_CUANTITY = 63;
    private GamePanel panel;
    public Tile[] tiles;
    public int[][] mapTileNum;
    private InputStream is;
    private BufferedReader br;

    public TileManager()
    {
        tiles = new Tile[TILE_CUANTITY];
        loadImages("/maps/tileData.txt");
    }

    public TileManager(GamePanel panel)
    {
        this.panel = panel;

        tiles = new Tile[TILE_CUANTITY];

        is = getClass().getResourceAsStream("/maps/world1.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try
        {
            String line = br.readLine();
            String[] maxTile = line.split(" ");
            panel.maxWorldCol = maxTile.length;
            panel.maxWorldRow = maxTile.length;

            mapTileNum = new int[panel.maxWorldCol][panel.maxWorldRow];
            br.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        loadImages("/maps/tileData.txt");
        loadMap("/maps/world1.txt");
    }

    public void loadMap(String url)
    {
        try
        {
            is = getClass().getResourceAsStream(url);
            br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < panel.maxWorldCol && row < panel.maxWorldRow)
            {
                String line = br.readLine();
                while (col < panel.maxWorldCol)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == panel.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e)
        {
        }
    }

    public void loadImages(String url)
    {
        try
        {
            is = getClass().getResourceAsStream(url);
            br = new BufferedReader(new InputStreamReader(is));

            int col = 0;

            while (col < TILE_CUANTITY)
            {
                path = br.readLine();
                collision = br.readLine();

                setupImage(col, path, Boolean.valueOf(collision));

                col++;
            }
            br.close();
        } catch (Exception e)
        {
        }
    }

    public void setupImage(int index, String path, boolean collision)
    {
        try
        {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + path));
            tiles[index].image = uTool.scaleImage(tiles[index].image, panel.tileSize, panel.tileSize);
            tiles[index].collision = collision;
        } catch (Exception e)
        {
        }
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow)
        {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * panel.tileSize;
            int worldY = worldRow * panel.tileSize;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if (       worldX  + panel.tileSize> panel.player.worldX - panel.player.screenX
                    && worldX - panel.tileSize < panel.player.worldX + panel.player.screenX
                    && worldY + panel.tileSize > panel.player.worldY - panel.player.screenY
                    && worldY - panel.tileSize< panel.player.worldY + panel.player.screenY)
            {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, null);
                if (panel.keyHandler.isDebug)
                {
                    g2.setColor(Color.RED);
                    g2.drawRect(screenX, screenY, panel.tileSize, panel.tileSize);
                }

            }
            worldCol++;
            if (worldCol == panel.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
