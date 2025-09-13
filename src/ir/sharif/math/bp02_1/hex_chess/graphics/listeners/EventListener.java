package ir.sharif.math.bp02_1.hex_chess.graphics.listeners;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public interface EventListener {
    void onClick(int row, char col) throws UnsupportedAudioFileException, LineUnavailableException, IOException;

    EventListener onLoad(File file);

    EventListener onSave(File file) throws IOException;

    void onNewGame();
}
