package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;

import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;


import java.awt.*;

public class Queen extends Piece {
    Rook rook=new Rook(Color.WHITE,"Rook");
    Bishop bishop=new Bishop(Color.WHITE,"Bishop");
    public Queen(Color color, String symbol) {
        super(color, "Queen");
    }

    public boolean isValidMove(Move move) {
        if (Board.getBoard().regularValidMove(move)) {
            return (rook.isValidMove(move) || bishop.isValidMove(move));
        }
        return false;
    }
}
