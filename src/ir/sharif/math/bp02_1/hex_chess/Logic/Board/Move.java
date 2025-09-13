package ir.sharif.math.bp02_1.hex_chess.Logic.Board;
public class Move {
     Cell startSpot;
    Cell endSpot;
    public Move(Cell startSpot,Cell endSpot){
        this.startSpot=startSpot;
        this.endSpot=endSpot;
    }

    public Cell getStartSpot() {
        return startSpot;
    }

    public Cell getEndSpot() {
        return endSpot;
    }
}
