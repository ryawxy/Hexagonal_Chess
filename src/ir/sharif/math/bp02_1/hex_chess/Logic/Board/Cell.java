package ir.sharif.math.bp02_1.hex_chess.Logic.Board;


import ir.sharif.math.bp02_1.hex_chess.Logic.Piece.Piece;

public class Cell {
    int rank;
    int file;
   // Piece piece;

    public Cell(int rank, int file){
        this.file= file;
        this.rank=rank;
       // piece=null;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public boolean isCellEmpty(Cell [][] cell){;
        return cell[rank][file] == null;
    }
    }

