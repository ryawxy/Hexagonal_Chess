package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;

import java.awt.*;

import java.awt.*;

public class King extends Piece {

    public King(Color color, String symbol) {
        super(color, "King");
    }
    public boolean isValidMove(Move move) {
        //Orthogonal move
        // vertical move
        Cell startSpot = move.getStartSpot();
        Cell endSpot = move.getEndSpot();
        int rankDis = endSpot.getRank() - startSpot.getRank();
        int fileDis = endSpot.getFile() - startSpot.getFile();
        boolean valid=false;

        if (Board.getBoard().regularValidMove(move)) {
            if(move.getStartSpot().getFile()<11)
                if (rankDis == 0 && fileDis == 1) {
                    valid=true;
                }
            if(move.getStartSpot().getFile()>1)
                if (rankDis == 0 && fileDis == -1) {
                    valid=true;
                }

                if(fileDis==0 && Math.abs(rankDis)==1){
                    valid=true;
                }


                if (startSpot.getFile() < 6) {
                    if (Math.abs(fileDis) == 1 && fileDis == rankDis) {
                        valid=true;
                    }
                }


                if (startSpot.getFile() != 6) {
                    if (rankDis == 0 && Math.abs(fileDis) == 1) {
                        valid=true;
                    }

                }

                if (startSpot.getFile() > 6) {
                    if (fileDis == -1 * rankDis && Math.abs(fileDis) == 1) {
                        valid=true;
                    }

                }
                if (startSpot.getFile() == 6) {
                    if (rankDis == 0 && Math.abs(fileDis) == 1) {
                        valid=true;
                    }
                    if (rankDis == -1 && Math.abs(fileDis) == 1) {
                        valid=true;
                    }

                }
                if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                    if (fileDis == 2 * rankDis && Math.abs(rankDis) == 1) {
                            valid=true;
                        }
                    }
                if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                    if (fileDis == -2 * rankDis && Math.abs(rankDis) == 1) {
                            valid=true;
                        }
                    }
                if (startSpot.getFile() < 6 && endSpot.getFile() > 6) {
                        if (fileDis == 2 && rankDis == 0) {
                            valid=true;
                        }
                    }
                if (startSpot.getFile() > 6 && endSpot.getFile() < 6) {
                        if (fileDis == -2 && rankDis == 0) {
                            valid=true;
                        }
                    }
                if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                    if(rankDis == 2 * fileDis && Math.abs(fileDis) == 1 || rankDis == -1 * fileDis && Math.abs(rankDis) == 1){
                        valid=true;
                    }
                }

                if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                        if(rankDis == -2 * fileDis && Math.abs(fileDis) == 1 || rankDis == fileDis && Math.abs(rankDis) == 1){
                            valid=true;
                        }
                    }
        }
        return valid;
    }
}


