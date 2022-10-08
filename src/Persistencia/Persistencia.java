package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
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
            escritura.append("musicVolume:100\n");
            escritura.append("effectsVolume:100\n");
            escritura.append("isFullScreen:false\n");
            escritura.append("nothingChecked:false\n");
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

            String line = entrada.nextLine();
            GameSettings.musicVolume = Double.parseDouble(line.split(":")[1]);
            line = entrada.nextLine();
            GameSettings.soundEVolume = Double.parseDouble(line.split(":")[1]);
            line = entrada.nextLine();
            GameSettings.isFullScreen = Boolean.parseBoolean(line.split(":")[1]);
            line = entrada.nextLine();
            GameSettings.nothingChecked = Boolean.parseBoolean(line.split(":")[1]);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveData(){
        System.out.println("Saving Data");
        try
        {
            escritura = new FileWriter(file);
            escritura.write("");
            escritura.append("musicVolume:"+GameSettings.musicVolume+"\n");
            escritura.append("effectsVolume:"+GameSettings.soundEVolume+"\n");
            escritura.append("isFullScreen:"+GameSettings.isFullScreen+"\n");
            escritura.append("nothingChecked:"+GameSettings.nothingChecked+"\n");
            escritura.close();
        } catch (IOException ex)
        {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
