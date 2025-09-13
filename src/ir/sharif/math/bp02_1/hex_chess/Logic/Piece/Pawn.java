package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;

import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;


import java.awt.*;


public class Pawn extends Piece {


    public Pawn(Color color, String symbol) {
        super(color, "Pawn");
    }

    public boolean isValidMove(Move move) {
        Cell startSpot = move.getStartSpot();
        Cell endSpot = move.getEndSpot();
        int rankDis = endSpot.getRank() - startSpot.getRank();
        int fileDis = endSpot.getFile() - startSpot.getFile();
        boolean validMove = false;
        if (Board.getBoard().regularValidMove(move)) {
            for (int i = 2; i < 12; i++) {
                if (startSpot.getRank() == 7 && startSpot.getFile() == i && startSpot.getFile() != 6) {
                    if (Board.getBoard().getPiece(startSpot).getColor().equals(Color.BLACK)) {
                        if (fileDis == 0 && rankDis == -2) {
                            if (Board.getBoard().isCellEmpty(endSpot) && Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - 1, startSpot.getFile()))) {
                                validMove = true;
                            }
                        }
                    }
                }
            }
            for (int i = 1; i <= 6; i++) {
                if (startSpot.getRank() == i && startSpot.getFile() == i + 1) {
                    if (Board.getBoard().getPiece(startSpot).getColor().equals(Color.WHITE)) {
                        if (fileDis == 0 && rankDis == 2) {
                            if (Board.getBoard().isCellEmpty(endSpot) && Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + 1, startSpot.getFile()))) {
                                validMove = true;
                            }
                        }
                    }
                }
            }
            for (int i = 6; i < 11; i++) {
                if (startSpot.getRank() == 11 - i && startSpot.getFile() == i) {
                    if (Board.getBoard().getPiece(startSpot).getColor().equals(Color.WHITE)) {
                        if (fileDis == 0 && rankDis == 2) {
                            if (Board.getBoard().isCellEmpty(endSpot) && Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + 1, startSpot.getFile()))) {
                                validMove = true;
                            }
                        }
                    }
                }
            }
            if (rankDis == 1 && fileDis == 0 && Board.getBoard().isCellEmpty(endSpot) && Board.getBoard().getPiece(startSpot).getColor().equals(Color.WHITE)) {
                validMove = true;
            }
            if (rankDis == -1 && fileDis == 0 && Board.getBoard().isCellEmpty(endSpot) && Board.getBoard().getPiece(startSpot).getColor().equals(Color.BLACK)) {
                validMove = true;
            }
            if (!Board.getBoard().isCellEmpty(move.getEndSpot())) {
                if (Board.getBoard().getPiece(move.getStartSpot()).getColor().equals(Color.WHITE)) {
                    if (move.getStartSpot().getFile() < 6 && move.getEndSpot().getFile() <= 6) {
                        if (rankDis == 0 && fileDis == -1 || rankDis==1 && fileDis==1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.BLACK)) {
                                validMove = true;
                            }
                        }
                    }
                    if (move.getStartSpot().getFile() == 6 && move.getEndSpot().getFile() < 6) {
                        if (rankDis == 0 && fileDis == -1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.BLACK)) {
                                validMove = true;
                            }
                        }
                    }
                    if (move.getStartSpot().getFile() > 6 && move.getEndSpot().getFile() >= 6) {
                        if (rankDis == 1 && fileDis == -1 || rankDis == 0 && fileDis == 1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.BLACK)) {
                                validMove = true;
                            }
                        }
                    }
                    if (move.getStartSpot().getFile() == 6 && move.getEndSpot().getFile() > 6) {
                        if (rankDis == 0 && fileDis == 1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.BLACK)) {
                                validMove = true;
                            }
                        }
                    }
                }
                if (Board.getBoard().getPiece(move.getStartSpot()).getColor().equals(Color.BLACK)) {
                    if (move.getStartSpot().getFile() < 6 && move.getEndSpot().getFile() <= 6) {
                        if (rankDis == 0 && fileDis == 1 || rankDis == -1 && fileDis == -1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.WHITE)) {
                                validMove = true;
                            }
                        }
                    }
                    if (move.getStartSpot().getFile() > 6 && move.getEndSpot().getFile() >= 6) {
                        if (rankDis == -1 && fileDis == 1 || rankDis == 0 && fileDis == -1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.WHITE)) {
                                validMove = true;
                            }
                        }
                    }
                    if (move.getStartSpot().getFile() == 6 && move.getEndSpot().getFile() < 6) {
                        if (rankDis == -1 && fileDis == 1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.WHITE)) {
                                validMove = true;
                            }
                        }
                    }
                    if (move.getStartSpot().getFile() == 6 && move.getEndSpot().getFile() > 6) {
                        if (rankDis == -1 && fileDis == 1) {
                            if (Board.getBoard().getPiece(move.getEndSpot()).getColor().equals(Color.WHITE)) {
                                validMove = true;
                            }
                        }
                    }
                }
            }

        }
        return validMove;
    }
    }


