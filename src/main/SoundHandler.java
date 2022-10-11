package main;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avile
 */
public class SoundHandler
{

    private static Map<String, SourceSound> sounds;

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
