/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author avile
 */
public class Sound
{

    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound()
    {

        soundUrl[0] = getClass().getResource("/music/flower.wav");
        soundUrl[1] = getClass().getResource("/music/konosuba.wav");
        soundUrl[2] = getClass().getResource("/music/nothing.wav");
        soundUrl[3] = getClass().getResource("/music/song.wav");
        soundUrl[4] = getClass().getResource("/music/toradora.wav");
        soundUrl[5] = getClass().getResource("/music/intro.wav");
        soundUrl[6] = getClass().getResource("/music/noise.wav");
        soundUrl[7] = getClass().getResource("/talkM/snd_txtasg.wav");
        soundUrl[8] = getClass().getResource("/talkM/snd_txtpap.wav");
        soundUrl[9] = getClass().getResource("/talkM/snd_txtsans.wav");
        soundUrl[10] = getClass().getResource("/talkM/snd_txttor.wav");

    }

    public void setFile(int i)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            System.out.println(clip);
            clip.open(ais);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void play()
    {
        System.out.println("Hi");
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop()
    {
        clip.stop();
    }
}
