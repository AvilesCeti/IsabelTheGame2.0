package Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author avile
 */
public class Persistencia
{

    private static File file;
    private static Scanner entrada;
    private static FileWriter escritura;

    public Persistencia()
    {
        try
        {
            file = new File("persistence.txt");

            if (!file.exists())
            {
                file.createNewFile();
                defaultSettings();
            } else
            {
                loadData();
            }
        } catch (Exception e)
        {
        }
    }

    private void defaultSettings()
    {
        System.out.println("DefaultSettings writing...");
        try
        {
            escritura = new FileWriter(file);
            escritura.write("");
            escritura.append("musicVolume:1\n");
            escritura.append("effectsVolume:1\n");
            escritura.append("isFullScreen:false\n");
            escritura.append("nothingChecked:false\n");
            escritura.append("hasBeenWatched:false\n");
            escritura.append("hasFirstFalled:false\n");
            escritura.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadData()
    {
        try
        {
            entrada = new Scanner(file);

            String line = getLine();
            GameSettings.musicVolume.set(Double.parseDouble(line.split(":")[1]));
            line = getLine();
            GameSettings.soundEVolume.set(Double.parseDouble(line.split(":")[1]));
            line = getLine();
            GameSettings.isFullScreen = Boolean.parseBoolean(line.split(":")[1]);
            line = getLine();
            GameSettings.nothingChecked = Boolean.parseBoolean(line.split(":")[1]);
            line = getLine();
            GameSettings.hasBeenWatched = Boolean.parseBoolean(line.split(":")[1]);
            line = getLine();
            GameSettings.hasFirstFalled = Boolean.parseBoolean(line.split(":")[1]);
            entrada.close();
        } catch (Exception ex)
        {
            System.err.println("No se pudieron escanear todos los elementos");
            defaultSettings();
            loadData();
        }
    }

    public static void saveData()
    {
        System.out.println("Saving Data");
        try
        {
            escritura = new FileWriter(file);
            escritura.write("");
            escritura.append("musicVolume:" + GameSettings.musicVolume.getValue() + "\n");
            escritura.append("effectsVolume:" + GameSettings.soundEVolume.getValue() + "\n");
            escritura.append("isFullScreen:" + GameSettings.isFullScreen + "\n");
            escritura.append("nothingChecked:" + GameSettings.nothingChecked + "\n");
            escritura.append("hasBeenWatched:" + GameSettings.hasBeenWatched + "\n");
            escritura.append("hasFirstFalled:" + GameSettings.hasFirstFalled + "\n");
            escritura.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getLine()
    {
        String in = entrada.nextLine();
        if (!in.equals(""))
        {
            return in;
        } else
        {
            return null;
        }
    }

}
