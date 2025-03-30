package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class AudioHandler {

    public static void playSound(String location) {
        try {
            File musicPath = new File(location);

            if(musicPath.exists()) {
                AudioInputStream input = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                clip.start();

            }
            else {
                System.out.println("Can't find file");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
