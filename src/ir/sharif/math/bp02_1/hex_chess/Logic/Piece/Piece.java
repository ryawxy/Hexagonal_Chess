package ir.sharif.math.bp02_1.hex_chess.Logic.Piece;

import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.StringColor;

import java.awt.*;

public abstract class Piece {
public Color color;
public String symbol;
    public Piece(Color color, String symbol) {
        this.color=color;
        this.symbol=symbol;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public abstract boolean isValidMove(Move move);

}

