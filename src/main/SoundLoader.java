package main;

import java.net.URL;

/**
 *
 * @author avile
 */
public final class SoundLoader
{

    public SoundLoader()
    {
        SoundHandler h = IsabelTheGame.soundHandler;
        h.addSound("flower", new SourceSound(true, getClass().getResource("/music/flower.ogg"), "flower", "flower.ogg", true, 0, 0, 0, true));
        h.addSound("kono", new SourceSound(true, getClass().getResource("/music/konosuba.ogg"), "kono", "konosuba.ogg", true, 0, 0, 0, true));
        h.addSound("nothing", new SourceSound(true, getClass().getResource("/music/nothing.ogg"), "nothing", "nothing.ogg", true, 0, 0, 0, true));
        h.addSound("song", new SourceSound(true, getClass().getResource("/music/song.ogg"), "song", "song.ogg", true, 0, 0, 0, true));
        h.addSound("toradora", new SourceSound(true, getClass().getResource("/music/toradora.ogg"), "toradora", "toradora.ogg", true, 0, 0, 0, true));
        h.addSound("asgore", new SourceSound(false, getClass().getResource("/talkM/snd_txtasg.wav"), "asgore", "snd_txtasg.wav", false, 0, 0, 0, false));
        h.addSound("papa", new SourceSound(false, getClass().getResource("/talkM/snd_txtpap.wav"), "papa", "snd_txtpap.wav", false, 0, 0, 0, false));
        h.addSound("sans", new SourceSound(false, getClass().getResource("/talkM/snd_txtsans.wav"), "sans", "snd_txtsans.wav", false, 0, 0, 0, false));
        h.addSound("toriel", new SourceSound(false, getClass().getResource("/talkM/snd_txttor.wav"), "toriel", "snd_txttor.wav", false, 0, 0, 0, false));

    }

    public void exists(boolean type, URL url)
    {

    }

}
