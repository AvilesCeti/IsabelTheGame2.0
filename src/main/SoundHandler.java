package main;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author avile
 */
public class SoundHandler
{

    
    private static Map<String, SourceSound> sounds;
    private double factor, volume;
    public static String actualKey = "kono";

    public SoundHandler()
    {
        sounds = new HashMap<>();
    }

    public void addSound(String key, SourceSound value)
    {
        sounds.put(key, value);
    }

    public SourceSound getSound(String key)
    {
        return sounds.get(key);
    }

    public void stop(String key)
    {
        sounds.get(key).getMediaPlayer().stop();
    }

    public void pause(String key)
    {
        sounds.get(key).getMediaPlayer().pause();
    }

    public void play(String key)
    {
        sounds.get(key).getMediaPlayer().play();
    }


}
