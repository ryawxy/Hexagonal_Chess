package ir.sharif.math.bp02_1.hex_chess.graphics.listeners;

import java.io.File;

public class DummyEventListener implements EventListener {
    @Override
    public void onClick(int row, char col) {

    }

    @Override
    public EventListener onLoad(File file) {

        return null;
    }

    @Override
    public EventListener onSave(File file) {

        return null;
    }

    @Override
    public void onNewGame() {

    }
}
