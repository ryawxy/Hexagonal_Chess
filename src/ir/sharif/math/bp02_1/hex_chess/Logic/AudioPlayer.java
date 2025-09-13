package ir.sharif.math.bp02_1.hex_chess.Logic;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioPlayer {
    private Clip clip;
    public void play(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void pause() {

        if (clip != null && clip.isRunning()) {

            clip.stop();

        }
    }
}