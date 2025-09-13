package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;

import java.awt.*;



public class Rook extends Piece {

    public Rook(Color color, String symbol) {
        super(color, "Rook");
    }

    public boolean isValidMove(Move move) {
        Cell startSpot = move.getStartSpot();
        Cell endSpot = move.getEndSpot();
        int rankDis = endSpot.getRank() - startSpot.getRank();
        int fileDis = endSpot.getFile() - startSpot.getFile();
        int rankDir = (rankDis > 0) ? 1 : -1;
        int fileDir = (fileDis > 0)?1:-1;
        //diagonal-move
        boolean isValid = true;
        boolean valid=false;
        if (Board.getBoard().regularValidMove(move)) {
            if(startSpot.getFile()!=7) {
                if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                    if (rankDis == fileDis) {
                        valid = true;
                        for (int i = 1; i < Math.abs(rankDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDir, startSpot.getFile() + i * rankDir))) {
                                isValid = false;
                            }
                        }
                    }
                }


                if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                    if (rankDis == 0 && fileDis != 0) {
                        valid = true;
                        for (int i = 1; i < Math.abs(fileDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), startSpot.getFile() + i * fileDir))) {
                                isValid = false;
                            }
                        }
                    }
                }


                if (startSpot.getFile() < 6 && endSpot.getFile() > 6) {
                    int startFileDis = 6 - startSpot.getFile();
                    int endFileDis = endSpot.getFile() - 6;
                    //middleSpot=(fileDis+startRank,6)
                    if (endSpot.getRank() == startFileDis + move.getStartSpot().getRank()) {
                        valid = true;
                        for (int i = 1; i < endFileDis; i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + startFileDis, 6 + i))) {
                                isValid = false;
                            }

                        }
                    }
                    for (int i = 1; i < startFileDis; i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i, startSpot.getFile() + i))) {
                            isValid = false;
                        }
                    }
                    if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + startFileDis, 6))) {
                        isValid = false;
                    }
                    if(startSpot.getRank()+fileDis<12 && startSpot.getRank()+fileDis>0)
                    if (endSpot.getFile() == 7 && Board.getBoard().isCellEmpty(new Cell(startSpot.getRank()+fileDis,6))) {
                        isValid = true;

                    }
                }
                if (startSpot.getFile() > 6 && endSpot.getFile() < 6) {
                    int startFileDis = 6 - startSpot.getFile();
                    //middle-spot(startRank,6)
                    int endFileDis = endSpot.getFile() - 6;
                    if (endFileDis == endSpot.getRank() - startSpot.getRank()) {
                        valid = true;
                        for (int i = 1; i < Math.abs(endFileDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - i, 6 - i))) {
                                isValid = false;
                            }
                        }
                    }
                    if (startSpot.getFile() == 7) {
                        if (Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), 6))) {
                            isValid = true;
                            valid = true;
                        }
                    }
                    for (int i = 1; i < Math.abs(startFileDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), startSpot.getFile() - i))) {
                            isValid = false;
                        }
                    }
                    if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), 6))) {
                        isValid = false;
                    }
                }
                if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                    if (rankDis == 0 && fileDis != 0) {
                        valid = true;
                        for (int i = 1; i < Math.abs(fileDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), startSpot.getFile() + i * fileDir))) {
                                isValid = false;
                            }
                        }
                    }
                }
                if (startSpot.getFile() >= 6 && endSpot.getFile() >= 6) {
                    if (rankDis == -1 * fileDis) {
                        valid = true;
                        for (int i = 1; i < Math.abs(fileDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDir, startSpot.getFile() + i * fileDir))) {
                                isValid = false;
                            }
                        }
                    }
                }
                if (startSpot.getFile() < 6 && endSpot.getFile() > 6) {
                    if (!valid) {
                        //middle-Spot(startRank,6)
                        int endFileDis = endSpot.getFile() - 6;
                        if (endFileDis == -1 * (endSpot.getRank() - startSpot.getRank()))
                            valid = true;
                        for (int i = 1; i < Math.abs(endFileDis); i++) {
                            if (startSpot.getRank() - i > 0 && startSpot.getRank() - i < 12 && 6 + i < 12)
                                if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - i, 6 + i))) {
                                    isValid = false;
                                }
                        }
                        int startFileDis = 6 - startSpot.getFile();
                        for (int i = 1; i < startFileDis; i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), startSpot.getFile() + i))) {
                                isValid = false;
                            }
                        }
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(), 6))) {
                            isValid = false;
                        }
                    }
                }
                if (startSpot.getFile() > 6 && endSpot.getFile() < 6) {
                    if (!valid) {
                        int startFileDis = 6 - startSpot.getFile();
                        //middle-Spot(startRank-startFileDis,startFile+startFileDis)
                        int endFileDis = endSpot.getFile() - 6;
                        if (endSpot.getRank() == startSpot.getRank() - startFileDis)
                            valid = true;
                        for (int i = 1; i < Math.abs(endFileDis); i++) {
                            if (startSpot.getRank() - startFileDis - i > 0 && startSpot.getRank() - startFileDis - i < 12 && 6 - i > 0)
                                if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - startFileDis - i, 6 - i))) {
                                    isValid = false;
                                }
                        }
                        for (int i = 1; i < Math.abs(startFileDis); i++) {
                            if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i, startSpot.getFile() - i))) {
                                isValid = false;
                            }
                        }
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() - startFileDis, 6))) {
                            isValid = false;
                        }
                    }
                }
                if (startSpot.getFile() <= 6 && endSpot.getFile() <= 6) {
                    if (rankDis == 0 && fileDis != 0) {
                        if (Math.abs(fileDis) == 1) {
                            isValid = true;
                        }
                        valid = true;
                    }
                }
                //verical move
                if (rankDis != 0 && fileDis == 0) {
                    valid = true;
                    for (int i = 1; i < Math.abs(rankDis); i++) {
                        if (!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank() + i * rankDir, startSpot.getFile()))) {
                            isValid = false;
                        }
                    }
                }
                if (rankDis != 0 && fileDis == 0) {
                    if (Math.abs(rankDis) == 1) {
                        valid = true;
                        isValid = true;
                    }
                }
            }else{
                if(startSpot.getFile()==7 && endSpot.getFile()<7){
                    if(startSpot.getRank()==endSpot.getRank()-1){
                        valid=true;
                    }
                    int endFileDis = endSpot.getFile()-6;
                    for(int i=1;i<Math.abs(endFileDis);i++){
                        if(!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank()+1,startSpot.getFile()-1-i))){
                            isValid=false;

                        }
                    }
                }
                if(startSpot.getFile()==7 && endSpot.getFile()>7){
                    if(startSpot.getRank()==endSpot.getRank()){
                        valid=true;
                    }
                    for(int i=1;i<fileDis;i++){
                        if(!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank(),startSpot.getFile()+i))){
                            isValid=false;
                        }
                    }
                }
                if(startSpot.getFile()==7 && endSpot.getFile()<7){
                    if(endSpot.getFile()==startSpot.getFile()+fileDis && endSpot.getRank()==startSpot.getRank()+fileDis+1){
                        valid=true;
                    }
                    for(int i=1;i<Math.abs(fileDis-1);i++){
                        if(startSpot.getRank()-i>0 && startSpot.getRank()-i<12 && startSpot.getFile()-1-i>0 && startSpot.getFile()-1-i<12)
                        if(!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank()-i,startSpot.getFile()-1-i))){
                            isValid=false;
                        }
                    }
                }
                if(startSpot.getFile()==7 && endSpot.getFile()>7){
                    if(endSpot.getRank() == startSpot.getRank()-fileDis){
                        valid=true;
                    }
                    for(int i=1;i<fileDis;i++){
                        if(startSpot.getRank()-i>0 && startSpot.getRank()-i<12 && startSpot.getFile()+i>0 && startSpot.getFile()+i<12)
                        if(!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank()-i,startSpot.getFile()+i))){
                            isValid=false;
                        }
                    }
                }
                if(startSpot.getFile()==7 && fileDis==0 && rankDis!=0){
                    valid=true;
                    for(int i=1;i<Math.abs(rankDis);i++){
                        if(!Board.getBoard().isCellEmpty(new Cell(startSpot.getRank()+i*rankDir,startSpot.getFile()))){
                            isValid=false;
                        }
                    }
                }
            }



        }
        else{
            isValid=false;
        }
        return isValid && valid;
        }

    }









