package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;

import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;


import java.awt.*;

public class Bishop extends Piece {


    public Bishop(Color color, String symbol) {
        super(color, "Bishop");
    }

    @Override
    public boolean isValidMove(Move move) {
        Cell startSpot = move.getStartSpot();
        Cell endSpot = move.getEndSpot();
        //horizontal move
        boolean isValid = true;
        boolean valid = false;
        if (Board.getBoard().regularValidMove(move)) {
            if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDirection = (rankDis > 0) ? 1 : -1;
                int fileDirection = (fileDis > 0) ? 1 : -1;
                if (rankDis * fileDis > 0 && fileDis == rankDis * 2) {
                    valid = true;
                    for (int i = 1; i < Math.abs(rankDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDirection, startSpot.getFile() + 2 * i * fileDirection)))
                            isValid = false;

                    }

                }
            }
            if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDirection = (rankDis > 0) ? 1 : -1;
                int fileDirection = (fileDis > 0) ? 1 : -1;
                if (rankDis * fileDis < 0 && fileDis == rankDis * -2) {
                    valid = true;
                    for (int i = 1; i < Math.abs(rankDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDirection, startSpot.getFile() + 2 * i * fileDirection)))
                            isValid = false;

                    }
                }
            }
            if ((6 - startSpot.getFile()) % 2 == 0) {
                if (startSpot.getFile() < 6 && endSpot.getFile() > 6) {

                    int startFileDis = 6 - startSpot.getFile();
                    // Cell middleSpot = cell[startSpot.getRank() + startFileDis / 2][6];
                    int startRankDis = startFileDis / 2;

                    for (int i = 1; i < startRankDis; i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i, startSpot.getFile() + 2 * i)))
                            isValid = false;

                    }

                    int endFileDis = endSpot.getFile() - 6;
                    int endRankDis = endSpot.getRank() - startSpot.getRank() - startFileDis / 2;
                    if (endFileDis == endRankDis * -2) {
                        valid = true;
                        for (int i = 1; i < Math.abs(endRankDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + startFileDis / 2 - i, 6 + 2 * i)))
                                isValid = false;

                        }
                    }
                }
                if (startSpot.getFile() > 6 && endSpot.getFile() < 6) {
                    int startFileDis = 6 - startSpot.getFile();
                    //  Cell middleSpot = cell[startSpot.getRank() - startFileDis / 2][6];
                    int startRankDis = startSpot.getRank() - startFileDis / 2;
                    if (startFileDis == -2 * startRankDis) {

                        for (int i = 1; i < Math.abs(startRankDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i, startSpot.getFile() - 2 * i)))
                                isValid = false;

                        }
                    }
                    int endFileDis = endSpot.getFile() - 6;
                    int endRankDis = endSpot.getRank() - startSpot.getRank() + startFileDis / 2;
                    if (endFileDis == endRankDis * 2) {
                        valid = true;
                        for (int i = 1; i < Math.abs(endRankDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - startFileDis / 2 - i, 6 - 2 * i)))
                                isValid = false;

                        }
                    }
                }
            }
            if ((6 - startSpot.getFile()) % 2 != 0) {
                if (startSpot.getFile() < 5 && endSpot.getFile() > 5) {
                    int startFileDis = 5 - startSpot.getFile();
                    int endFileDis = endSpot.getFile() - 7;
                    if (endFileDis == -2 * (endSpot.getRank() - startSpot.getRank() - startFileDis / 2)) {
                        valid = true;
                        if (endSpot.getFile() > 7) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + startFileDis / 2, 7)))
                                isValid = false;
                            for (int i = 1; i < endFileDis / 2; i++) {
                                if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + startFileDis / 2 - i, 7 + 2 * i)))
                                    isValid = false;

                            }
                        }
                    }
                    if (startSpot.getFile() < 5) {
                        for (int i = 1; i < startFileDis / 2; i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i, startSpot.getFile() + 2 * i)))
                                isValid = false;

                        }
                    }

                }
                if (startSpot.getFile() == 5 && endSpot.getFile() == 7 && startSpot.getRank() == 1 && endSpot.getRank() == 1) {
                    isValid = true;
                    valid = true;
                }
                if (startSpot.getFile() > 5 && endSpot.getFile() < 5) {

                    int startFileDis = 7 - startSpot.getFile();
                    int endFileDis = endSpot.getFile() - 5;
                    if (endFileDis == 2 * (endSpot.getRank() - (startSpot.getRank() - startFileDis / 2))) {
                        valid = true;

                        for (int i = 1; i < Math.abs(endFileDis) / 2; i++) {
                            if (startSpot.getRank() - startFileDis / 2 - i > 0 && startSpot.getRank() - startFileDis / 2 - i < 12 && 5 - 2 * i > 0)
                                if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - startFileDis / 2 - i, 5 - 2 * i))) {
                                    isValid = false;
                                }
                        }
                    }
                    if (endSpot.getFile() == 7) {
                        valid = true;
                        isValid = true;
                    }
                    if (endSpot.getFile() > 7) {
                        for (int i = 1; i < Math.abs(startFileDis / 2); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i, startSpot.getFile() - 2 * i))) {
                                isValid = false;
                            }
                        }
                    }

                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 10 && endSpot.getFile() == 5 && endSpot.getRank() == 10) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 11 && startSpot.getRank() == 5 && endSpot.getFile() == 5 && endSpot.getRank() == 7) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 9)) && Board.getBoard().isCellEmpty(new Cell(7, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 9 && startSpot.getRank() == 6 && endSpot.getFile() == 5 && endSpot.getRank() == 7) {
                    if (Board.getBoard().isCellEmpty(new Cell(7, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 11 && startSpot.getRank() == 2 && endSpot.getFile() == 5 && endSpot.getRank() == 4) {
                    if (Board.getBoard().isCellEmpty(new Cell(7, 4)) && Board.getBoard().isCellEmpty(new Cell(9, 3))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 9 && startSpot.getRank() == 3 && endSpot.getFile() == 5 && endSpot.getRank() == 4) {
                    if (Board.getBoard().isCellEmpty(new Cell(4, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 4 && endSpot.getFile() == 5 && endSpot.getRank() == 4) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 1 && endSpot.getFile() == 5 && endSpot.getRank() == 1) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 9 && startSpot.getRank() == 8 && endSpot.getFile() == 5 && endSpot.getRank() == 9) {
                    if (Board.getBoard().isCellEmpty(new Cell(9, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 9 && endSpot.getFile() == 5 && endSpot.getRank() == 9) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 9 && endSpot.getFile() == 7 && endSpot.getRank() == 9) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 9 && endSpot.getFile() == 9 && endSpot.getRank() == 8) {
                    if (Board.getBoard().isCellEmpty(new Cell(9, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                //////
                if (startSpot.getFile() == 5 && startSpot.getRank() == 6 && endSpot.getFile() == 7 && endSpot.getRank() == 6) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 6 && endSpot.getFile() == 5 && endSpot.getRank() == 9) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 6 && endSpot.getFile() == 11 && endSpot.getRank() == 4) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 7)) && Board.getBoard().isCellEmpty(new Cell(5, 9))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 11 && startSpot.getRank() == 4 && endSpot.getFile() == 5 && endSpot.getRank() == 6) {
                    if (Board.getBoard().isCellEmpty(new Cell(5, 9)) && Board.getBoard().isCellEmpty(new Cell(6, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 9 && startSpot.getRank() == 5 && endSpot.getFile() == 5 && endSpot.getRank() == 6) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 6 && endSpot.getFile() == 5 && endSpot.getRank() == 6) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 6 && endSpot.getFile() == 9 && endSpot.getRank() == 5) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 3 && endSpot.getFile() == 7 && endSpot.getRank() == 3) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 3 && endSpot.getFile() == 9 && endSpot.getRank() == 2) {
                    if (Board.getBoard().isCellEmpty(new Cell(3, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 5 && startSpot.getRank() == 3 && endSpot.getFile() == 11 && endSpot.getRank() == 1) {
                    if (Board.getBoard().isCellEmpty(new Cell(3, 7)) && Board.getBoard().isCellEmpty(new Cell(2, 9))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 11 && startSpot.getRank() == 1 && endSpot.getFile() == 5 && endSpot.getRank() == 3) {
                    if (Board.getBoard().isCellEmpty(new Cell(3, 7)) && Board.getBoard().isCellEmpty(new Cell(2, 9))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 9 && startSpot.getRank() == 2 && endSpot.getFile() == 5 && endSpot.getRank() == 3) {
                    if (Board.getBoard().isCellEmpty(new Cell(3, 7))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 3 && endSpot.getFile() == 5 && endSpot.getRank() == 3) {
                    valid = true;
                    isValid = true;
                }
                if (startSpot.getFile() == 7 && startSpot.getRank() == 6 && endSpot.getFile() == 9 && endSpot.getRank() == 5) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 8))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 8 && startSpot.getRank() == 4 && endSpot.getFile() == 5 && endSpot.getRank() == 9) {
                    if (Board.getBoard().isCellEmpty(new Cell(6, 7)) && Board.getBoard().isCellEmpty(new Cell(8, 6))) {
                        valid = true;
                        isValid = true;
                    }
                }
                if (startSpot.getFile() == 9 && startSpot.getRank() == 2 && endSpot.getFile() == 5 && endSpot.getRank() == 9) {
                    if (Board.getBoard().isCellEmpty(new Cell(4, 8)) && Board.getBoard().isCellEmpty(new Cell(6, 7)) && Board.getBoard().isCellEmpty(new Cell(8, 6))) {
                        valid = true;
                        isValid = true;
                    }
                }
            }
            if (endSpot.getFile() <= 6 && startSpot.getFile() <= 6) {
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDirection = (rankDis > 0) ? 1 : -1;
                int fileDirection = (fileDis > 0) ? 1 : -1;
                if (rankDis == 2 * fileDis) {
                    valid = true;
                    for (int i = 1; i < Math.abs(fileDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + 2 * i * rankDirection, startSpot.getFile() + i * fileDirection)))
                            isValid = false;

                    }
                }
            }
            if (endSpot.getFile() >= 6 && startSpot.getFile() >= 6) {
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDirection = (rankDis > 0) ? 1 : -1;
                int fileDirection = (fileDis > 0) ? 1 : -1;
                if (rankDis == fileDis) {
                    valid = true;
                    for (int i = 1; i < Math.abs(rankDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDirection, startSpot.getFile() + i * fileDirection)))
                            isValid = false;

                    }
                }
            }
            if (endSpot.getFile() < 6 && startSpot.getFile() > 6) {
                int startFileDis = 6 - startSpot.getFile();
                int endFileDis = endSpot.getFile() - 6;
                int endRankDis = endSpot.getRank() - startSpot.getRank() - startFileDis;
                //  Cell middleSpot = cell[startSpot.getRank() + startFileDis][6];
                if (endFileDis * 2 == endRankDis) {
                    valid = true;
                    for (int i = 1; i < Math.abs(endFileDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + startFileDis - 2 * i, 6 - i)))
                            isValid = false;

                    }
                }

                for (int i = 1; i < Math.abs(startFileDis); i++) {
                    if (startSpot.getRank() - i > 0 && startSpot.getRank() - i < 12 && startSpot.getFile() - i > 0 && startSpot.getFile() - i < 12)
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - i, startSpot.getFile() - i)))
                            isValid = false;
                }
            }
            if (startSpot.getFile() < 6 && endSpot.getFile() > 6) {
                int endFileDis = endSpot.getFile() - 6;
                int startFileDis = 6 - startSpot.getFile();
                int endRankDis = endSpot.getRank() - startSpot.getRank() - 2 * startFileDis;
                if (endFileDis == endRankDis) {
                    valid = true;
                    for (int i = 1; i < endFileDis; i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + 2 * startFileDis + i, 6 + i)))
                            isValid = false;

                    }
                }

                // Cell middleSpot = cell[startSpot.getRank() + 2 * startFileDis][6];
                for (int i = 1; i < startFileDis; i++) {
                    if (startSpot.getRank() + 2 * i > 0 && startSpot.getFile() + i < 12 && startSpot.getRank() + 2 * i < 12 && startSpot.getFile() + i > 0)
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + 2 * i, startSpot.getFile() + i)))
                            isValid = false;

                }
            }
            if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int rankDirection = (rankDis > 0) ? 1 : -1;
                int fileDirection = (fileDis > 0) ? 1 : -1;
                if (fileDis * -2 == rankDis && Math.abs(fileDis) > 1) {
                    valid = true;
                    for (int i = 1; i < Math.abs(fileDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + 2 * i * rankDirection, startSpot.getFile() + i * fileDirection)))
                            isValid = false;

                    }
                }
                if (rankDis == -2 && fileDis == 1) {
                    valid = true;
                    isValid = true;
                }
            }
            if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int rankDirection = (rankDis > 0) ? 1 : -1;
                int fileDirection = (fileDis > 0) ? 1 : -1;
                if (fileDis == rankDis * -1) {
                    valid = true;
                    for (int i = 1; i < Math.abs(rankDis) + 1; i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDirection, startSpot.getFile() + i * fileDirection)))
                            isValid = false;

                    }
                }
            }
            if (startSpot.getFile() < 6 && endSpot.getFile() > 6) {
                int endFileDis = endSpot.getFile() - 6;
                int startFileDis = 6 - startSpot.getFile();
                int endRankDis = endSpot.getRank() - startSpot.getRank() + startFileDis;
                if (endFileDis * -2 == endRankDis) {
                    valid = true;
                    for (int i = 1; i < endFileDis; i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - startFileDis - 2 * i, 6 + i)))
                            isValid = false;

                    }
                }
                // Cell middleSpot = cell[startSpot.getRank() - startFileDis][6];
                for (int i = 1; i < Math.abs(startFileDis); i++) {
                    if (startSpot.getRank() - i > 0 && startSpot.getFile() + i < 12) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - i, startSpot.getFile() + i)))
                            isValid = false;

                    }
                }
            }
            if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                int fileDis = endSpot.getFile() - startSpot.getFile();
                int rankDis = endSpot.getRank() - startSpot.getRank();

                if (fileDis == rankDis * -1 && Math.abs(fileDis) == 1) {
                    isValid = true;
                    valid = true;
                }
            }
            if (endSpot.getFile() >= 6 && startSpot.getFile() >= 6) {
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int fileDis = endSpot.getFile() - startSpot.getFile();
                if (rankDis == fileDis && Math.abs(fileDis) == 1) {
                    isValid = true;
                    valid = true;
                }
            }
            if (endSpot.getFile() >= 6 && startSpot.getFile() >= 6) {
                int rankDis = endSpot.getRank() - startSpot.getRank();
                int fileDis = endSpot.getFile() - startSpot.getFile();
                if (fileDis * -2 == rankDis) {
                    if (Math.abs(rankDis) == 2) {
                        valid = true;
                        isValid = true;
                    }
                }
            }
            //////////////////////////////////////////////
            if(startSpot.getRank()==10 && startSpot.getFile()==5 && endSpot.getFile()==7 && endSpot.getRank()==10){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==7 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==7){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==7 && startSpot.getFile()==5 && endSpot.getFile()==7 && endSpot.getRank()==7){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==7 && startSpot.getFile()==5 && endSpot.getFile()==9 && endSpot.getRank()==6) {
                if (Board.getBoard().isCellEmpty(new Cell(7, 7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==7 && startSpot.getFile()==5 && endSpot.getFile()==11 && endSpot.getRank()==5) {
                if (Board.getBoard().isCellEmpty(new Cell(7, 7)) && Board.getBoard().isCellEmpty(new Cell(6,9))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==7 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==10) {
                if (Board.getBoard().isCellEmpty(new Cell(9, 6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==5 && startSpot.getFile()==8 && endSpot.getFile()==5 && endSpot.getRank()==10) {
                if (Board.getBoard().isCellEmpty(new Cell(7, 7)) && Board.getBoard().isCellEmpty(new Cell(9,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==5 && endSpot.getFile()==7 && endSpot.getRank()==4){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==5 && endSpot.getFile()==9 && endSpot.getRank()==3) {
                if (Board.getBoard().isCellEmpty(new Cell(4, 7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==5 && endSpot.getFile()==11 && endSpot.getRank()==2) {
                if (Board.getBoard().isCellEmpty(new Cell(4, 7)) && Board.getBoard().isCellEmpty(new Cell(3,9))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==7) {
                if (Board.getBoard().isCellEmpty(new Cell(6, 6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==7 && endSpot.getFile()==4 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(6, 6)) && Board.getBoard().isCellEmpty(new Cell(7,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==9 && endSpot.getFile()==5 && endSpot.getRank()==10) {
                if (Board.getBoard().isCellEmpty(new Cell(9, 6)) && Board.getBoard().isCellEmpty(new Cell(7,7)) && Board.getBoard().isCellEmpty(new Cell(5,8))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==8 && endSpot.getFile()==5 && endSpot.getRank()==7) {
                if (Board.getBoard().isCellEmpty(new Cell(6, 6)) && Board.getBoard().isCellEmpty(new Cell(4,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==8 && endSpot.getFile()==4 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(4, 7)) && Board.getBoard().isCellEmpty(new Cell(6,6)) && Board.getBoard().isCellEmpty(new Cell(7,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==10 && endSpot.getFile()==5 && endSpot.getRank()==10) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 9)) && Board.getBoard().isCellEmpty(new Cell(5,8)) && Board.getBoard().isCellEmpty(new Cell(7,7)) && Board.getBoard().isCellEmpty(new Cell(9,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==4) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==7 && endSpot.getFile()==4 && endSpot.getRank()==5) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 6)) && Board.getBoard().isCellEmpty(new Cell(4,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==7 && endSpot.getFile()==3 && endSpot.getRank()==6) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 6)) && Board.getBoard().isCellEmpty(new Cell(4,5)) && Board.getBoard().isCellEmpty(new Cell(5,4))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==7 && endSpot.getFile()==2 && endSpot.getRank()==7) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 6)) && Board.getBoard().isCellEmpty(new Cell(4,5)) && Board.getBoard().isCellEmpty(new Cell(5,4)) && Board.getBoard().isCellEmpty(new Cell(6,3))) {
                    isValid = true;
                    valid = true;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(startSpot.getRank()==6 && startSpot.getFile()==5 && endSpot.getFile()==5 && endSpot.getRank()==9){
                isValid=false;
                valid=false;
            }
            if(startSpot.getRank()==6 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==9) {
                if (Board.getBoard().isCellEmpty(new Cell(8, 6)) ) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==8 && endSpot.getFile()==5 && endSpot.getRank()==9) {
                if (Board.getBoard().isCellEmpty(new Cell(6, 7)) && Board.getBoard().isCellEmpty(new Cell(8,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==6) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==7 && endSpot.getFile()==4 && endSpot.getRank()==7) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 6)) && Board.getBoard().isCellEmpty(new Cell(6,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==7 && endSpot.getFile()==3 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 6)) && Board.getBoard().isCellEmpty(new Cell(6,5)) && Board.getBoard().isCellEmpty(new Cell(7,4))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==8 && endSpot.getFile()==5 && endSpot.getRank()==6) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 6)) && Board.getBoard().isCellEmpty(new Cell(3,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==8 && endSpot.getFile()==4 && endSpot.getRank()==7) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 7)) && Board.getBoard().isCellEmpty(new Cell(5,6)) && Board.getBoard().isCellEmpty(new Cell(6,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==8 && endSpot.getFile()==3 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 7)) && Board.getBoard().isCellEmpty(new Cell(5,6)) && Board.getBoard().isCellEmpty(new Cell(6,5)) && Board.getBoard().isCellEmpty(new Cell(7,4))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==7 && startSpot.getFile()==9 && endSpot.getFile()==5 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(8, 7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==6 && startSpot.getFile()==11 && endSpot.getFile()==5 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(7, 9)) && Board.getBoard().isCellEmpty(new Cell(8,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==8 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==8){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==8 && startSpot.getFile()==5 && endSpot.getFile()==7 && endSpot.getRank()==8){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==8 && startSpot.getFile()==5 && endSpot.getFile()==9 && endSpot.getRank()==7) {
                if ( Board.getBoard().isCellEmpty(new Cell(8,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==8 && startSpot.getFile()==5 && endSpot.getFile()==11 && endSpot.getRank()==6) {
                if (Board.getBoard().isCellEmpty(new Cell(7, 9)) && Board.getBoard().isCellEmpty(new Cell(8,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==5 && startSpot.getFile()==5 && endSpot.getFile()==7 && endSpot.getRank()==5){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==5 && startSpot.getFile()==5 && endSpot.getFile()==9 && endSpot.getRank()==4) {
                if ( Board.getBoard().isCellEmpty(new Cell(5,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==5 && startSpot.getFile()==5 && endSpot.getFile()==11 && endSpot.getRank()==3) {
                if (Board.getBoard().isCellEmpty(new Cell(4, 9)) && Board.getBoard().isCellEmpty(new Cell(5,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==5 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==5){
                isValid=true;
                valid=true;
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==9 && endSpot.getFile()==5 && endSpot.getRank()==5) {
                if ( Board.getBoard().isCellEmpty(new Cell(5,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==4 && startSpot.getFile()==9 && endSpot.getFile()==11 && endSpot.getRank()==3) {
                if (Board.getBoard().isCellEmpty(new Cell(4, 9)) && Board.getBoard().isCellEmpty(new Cell(5,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==11 && endSpot.getFile()==5 && endSpot.getRank()==5) {
                if (Board.getBoard().isCellEmpty(new Cell(4, 9)) && Board.getBoard().isCellEmpty(new Cell(5,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==8 && endSpot.getFile()==5 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 7)) && Board.getBoard().isCellEmpty(new Cell(7,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==3 && startSpot.getFile()==8 && endSpot.getFile()==4 && endSpot.getRank()==9) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 7)) && Board.getBoard().isCellEmpty(new Cell(7,6)) && Board.getBoard().isCellEmpty(new Cell(8,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==9 && endSpot.getFile()==5 && endSpot.getRank()==2) {
                if ( Board.getBoard().isCellEmpty(new Cell(2,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==9 && endSpot.getFile()==4 && endSpot.getRank()==9) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 8)) && Board.getBoard().isCellEmpty(new Cell(4,7)) && Board.getBoard().isCellEmpty(new Cell(7,6)) && Board.getBoard().isCellEmpty(new Cell(8,5))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==1 && startSpot.getFile()==9 && endSpot.getFile()==5 && endSpot.getRank()==8) {
                if (Board.getBoard().isCellEmpty(new Cell(3, 8)) && Board.getBoard().isCellEmpty(new Cell(4,7)) && Board.getBoard().isCellEmpty(new Cell(7,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==5 && endSpot.getFile()==9 && endSpot.getRank()==1) {
                if ( Board.getBoard().isCellEmpty(new Cell(2,7))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==5 && endSpot.getFile()==7 && endSpot.getRank()==2) {
                    isValid = true;
                    valid = true;
                }
            if(startSpot.getRank()==2 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==2) {
                isValid = true;
                valid = true;
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==7 && endSpot.getFile()==5 && endSpot.getRank()==5) {
                if ( Board.getBoard().isCellEmpty(new Cell(4,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==7 && endSpot.getFile()==4 && endSpot.getRank()==6) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 5)) && Board.getBoard().isCellEmpty(new Cell(4,6))) {
                    isValid = true;
                    valid = true;
                }
            }
            if(startSpot.getRank()==2 && startSpot.getFile()==7 && endSpot.getFile()==3 && endSpot.getRank()==7) {
                if (Board.getBoard().isCellEmpty(new Cell(5, 5)) && Board.getBoard().isCellEmpty(new Cell(4,6)) && Board.getBoard().isCellEmpty(new Cell(6,4))) {
                    isValid = true;
                    valid = true;
                }
            }



        } else {
            isValid = false;
        }
        return isValid && valid;
    }
}
