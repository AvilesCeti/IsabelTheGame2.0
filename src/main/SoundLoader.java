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
        h.addSound("flower", new SourceSound(getClass().getResource("/music/flower.mp3"), true, true));
        h.addSound("kono", new SourceSound(getClass().getResource("/music/konosuba.mp3"), true, true));
        h.addSound("nothing", new SourceSound(getClass().getResource("/music/nothing.mp3"), true, true));
        h.addSound("song", new SourceSound(getClass().getResource("/music/song.mp3"), true, true));
        h.addSound("toradora", new SourceSound(getClass().getResource("/music/toradora.mp3"), true, true));
        h.addSound("intro", new SourceSound(getClass().getResource("/music/intro.mp3"), true, true));
        h.addSound("asgore", new SourceSound(getClass().getResource("/talkM/snd_txtasg.wav"), false, false));
        h.addSound("papa", new SourceSound(getClass().getResource("/talkM/snd_txtpap.wav"), false, false));
        h.addSound("sans", new SourceSound(getClass().getResource("/talkM/snd_txtsans.wav"), false, false));
        h.addSound("toriel", new SourceSound(getClass().getResource("/talkM/snd_txttor.wav"), false, false));

    }

    public void exists(boolean type, URL url)
    {

    }

}
