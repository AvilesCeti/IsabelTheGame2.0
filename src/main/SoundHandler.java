package main;

import Persistencia.GameSettings;
import java.util.HashMap;
import java.util.Map;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOgg;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryJavaSound;

/**
 *
 * @author avile
 */
public class SoundHandler
{

    private static SoundSystem mySoundSystem = null;
    private static Map<String, SourceSound> sounds;
    private double factor, volume;
    public static String actualKey = "kono";

    public SoundHandler()
    {
        if (mySoundSystem == null)
        {
            sounds = new HashMap<>();
            try
            {
                SoundSystemConfig.addLibrary(LibraryJavaSound.class);
                SoundSystemConfig.setCodec("wav", CodecWav.class);
                SoundSystemConfig.setCodec("ogg", CodecJOgg.class);
            } catch (SoundSystemException e)
            {
                System.err.println("error linking with the plug-ins");
            }

            mySoundSystem = new SoundSystem();
        }
    }

    public boolean createEffect(SourceSound data)
    {
        try
        {
            int aModel = SoundSystemConfig.ATTENUATION_NONE;
            float rFactor = 0;
            float x, y, z;
            x = data.getPoint3D()[0];
            y = data.getPoint3D()[1];
            z = data.getPoint3D()[2];
            mySoundSystem.newSource(data.isPriority(), data.getSourceName(), data.getUrl(),
                    data.getIdentifier(), data.isToLoop(), x, y, z, aModel, rFactor);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public boolean createMusic(SourceSound data)
    {
        try
        {
            int aModel = SoundSystemConfig.ATTENUATION_NONE;
            float rFactor = SoundSystemConfig.getDefaultRolloff();
            float x, y, z;
            x = data.getPoint3D()[0];
            y = data.getPoint3D()[1];
            z = data.getPoint3D()[2];
            mySoundSystem.newStreamingSource(data.isPriority(), data.getSourceName(),
                    data.getUrl(), data.getIdentifier(), data.isToLoop(), x,
                    y, z, aModel, rFactor);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public void close()
    {
        mySoundSystem.cleanup();
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
        SourceSound ss = getSound(key);
        String sourceName = ss.getSourceName();
        mySoundSystem.stop(sourceName);
    }

    public void pause(String key)
    {
        SourceSound ss = getSound(key);
        String sourceName = ss.getSourceName();
        mySoundSystem.pause(sourceName);
    }

    public void play(String key)
    {
        SourceSound ss = getSound(key);
        if (ss.getType())
        {
            createMusic(ss);
            setVolume(key, GameSettings.musicVolume);
            actualKey = key;
        } else
        {
            setVolume(key, GameSettings.soundEVolume);
            createEffect(ss);
        }
        String sourceName = ss.getSourceName();
        mySoundSystem.play(sourceName);
    }

    public void setVolume(String sourceName, double value)
    {
        factor = (value / 100);
        volume = factor * factor;
        System.out.println(sourceName);
        mySoundSystem.setVolume(sourceName, (float) volume);
    }

    public int getVolume(String sourceName)
    {
        return (int) (mySoundSystem.getVolume(sourceName) * 100);
    }

    public void sleep(long milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } catch (Exception e)
        {
        }
    }

}
