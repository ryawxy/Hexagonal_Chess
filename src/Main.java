import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Piece.Piece;
import ir.sharif.math.bp02_1.hex_chess.graphics.listeners.SystemOutEventListener;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = Board.getBoard();
        board.getApplication().registerEventListener(new SystemOutEventListener());
        File file2=new File("D:\\chess\\history\\save.txt");
        if(file2.exists()) {
            board.getApplication().registerEventListener(new SystemOutEventListener().onLoad(file2));
            board.getApplication().registerEventListener(new SystemOutEventListener().onSave(file2));
        }
}
}
