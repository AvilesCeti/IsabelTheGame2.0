/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author avile
 */
public class Sound
{

    MediaPlayer mediaPlayer;
    URL soundUrl[] = new URL[30];

    public Sound()
    {
        try
        {
            soundUrl[0] = getClass().getResource("/music/konosuba.wav");
            soundUrl[1] = getClass().getResource("/sounds/snd_select.wav");
            soundUrl[2] = getClass().getResource("/sounds/mus_f_laugh.wav");
            soundUrl[3] = getClass().getResource("/music/toradora.wav");
            soundUrl[4] = getClass().getResource("/music/nothing.mp3");
            soundUrl[5] = getClass().getResource("/music/song.mp3");
            mediaPlayer = new MediaPlayer(new Media(soundUrl[0].toString()));
        } catch (Exception e)
        {
            System.err.println("Alguna cancion no se pudo cargar");
        }

    }

    public void setFile(int i, double volume)
    {
        try
        {
            final Media media = new Media(soundUrl[i].toString());
            mediaPlayer = new MediaPlayer(media);
            setVolume(volume);
        } catch (Exception e)
        {
        }
    }

    public void setVolume(double i)
    {
        double fraccion = i / 100;
        double volumen = fraccion * fraccion;
        mediaPlayer.setVolume(volumen);
    }

    public void play()
    {
        mediaPlayer.play();
    }

    public void loop()
    {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void stop()
    {
        mediaPlayer.stop();
    }
}
