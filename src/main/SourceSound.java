package main;

import Persistencia.GameSettings;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author avile
 */
public class SourceSound
{

    private boolean toLoop, type;
    private MediaPlayer mp;
    private URL url;

    public SourceSound(URL url, boolean toLoop, boolean type)
    {
        this.mp = new MediaPlayer(new Media(url.toString()));
        this.toLoop = toLoop;
        this.type = type;
        this.url = url;
        if (toLoop)
        {
            mp.setCycleCount(MediaPlayer.INDEFINITE);
        }
        if (type)
        {
            mp.volumeProperty().bindBidirectional(GameSettings.musicVolume);
        } else
        {
            mp.volumeProperty().bind(GameSettings.soundEVolume);
        }
    }

    public URL getUrl()
    {
        return url;
    }

    public boolean getType()
    {
        return type;
    }

    public boolean isToLoop()
    {
        return toLoop;
    }

    public void setToLoop(boolean toLoop)
    {
        this.toLoop = toLoop;
    }

    public MediaPlayer getMediaPlayer()
    {
        return mp;
    }

}
