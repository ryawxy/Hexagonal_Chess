package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;

import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;


import java.awt.*;

public class Knight extends Piece{

    public Knight(Color color, String symbol) {
        super(color, "Knight");
    }
    public boolean isValidMove(Move move){
        //vertical
        Cell startSpot = move.getStartSpot();
        Cell endSpot = move.getEndSpot();
        int rankDis = endSpot.getRank() - startSpot.getRank();
        int fileDis = endSpot.getFile() - startSpot.getFile();
        boolean isValid=false;
        if (Board.getBoard().regularValidMove(move)) {
            if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                if (rankDis == 3 && fileDis == 1 || rankDis == -3 && fileDis == -1 || rankDis == 2 && fileDis == -1
                        || rankDis == -2 && fileDis == 1) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                if (rankDis == 2 && fileDis == 1 || rankDis == -2 && fileDis == -1 || rankDis == 3 && fileDis == -1
                        || rankDis == -3 && fileDis == 1) {
                    isValid = true;
                }
            }
            //diagonal move
            if (startSpot.getFile() <= 6 && endSpot.getFile() < 6) {
                if (rankDis == -2 && fileDis == -3 || rankDis == -3 && fileDis == -2 || rankDis == 1 && fileDis == -2
                        || rankDis == -1 && fileDis == -3 || rankDis==3 && fileDis==2 || rankDis==-1 && fileDis==2 || rankDis==1 && fileDis==3
                || rankDis==2 && fileDis==3) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() >= 6 && endSpot.getFile() > 6) {
                if (rankDis == 1 && fileDis == 2 || rankDis == -1 && fileDis == 3 || rankDis == -2 && fileDis == 3
                        || rankDis == -3 && fileDis == 2 || rankDis==3 && fileDis==-2 || rankDis==-1 && fileDis==-2 || rankDis==1 && fileDis==-3
                || rankDis==2 && fileDis==-3) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() <= 6 && endSpot.getFile() == 6) {
                if (rankDis == 3 && fileDis == 2 || rankDis == -1 && fileDis == 2) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() >= 6 && endSpot.getFile() == 6) {
                if (rankDis == 3 && fileDis == -2 || rankDis == -1 && fileDis == -2) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() == 4 && endSpot.getFile() > 6) {
                if (rankDis == 1 && fileDis == 3 || rankDis == 0 && fileDis == 3) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() == 5 && endSpot.getFile() > 6) {
                if (rankDis == 2 && fileDis == 2 || rankDis == 0 && fileDis == 3 || rankDis == -1 && fileDis == 3 ||
                        rankDis == -2 && fileDis == 2) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() == 8 && endSpot.getFile() < 6) {
                if (rankDis == 1 && fileDis == -3 || rankDis == 0 && fileDis == -3) {
                    isValid = true;
                }
            }
            if (startSpot.getFile() == 7 && endSpot.getFile() < 6) {
                if (rankDis == 2 && fileDis == -2 || rankDis == 0 && fileDis == -3 || rankDis == -2 && fileDis == -2 ||
                        rankDis == -1 && fileDis == -3) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }
}
