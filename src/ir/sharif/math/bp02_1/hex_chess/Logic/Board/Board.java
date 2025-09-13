package ir.sharif.math.bp02_1.hex_chess.Logic.Board;

import ir.sharif.math.bp02_1.hex_chess.Logic.Piece.Bishop;
import ir.sharif.math.bp02_1.hex_chess.Logic.Piece.*;
import ir.sharif.math.bp02_1.hex_chess.Logic.AudioPlayer;
import ir.sharif.math.bp02_1.hex_chess.Logic.Time;
import ir.sharif.math.bp02_1.hex_chess.graphics.Application;
import ir.sharif.math.bp02_1.hex_chess.graphics.listeners.SystemOutEventListener;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.StringColor;
import ir.sharif.math.bp02_1.hex_chess.util.PieceName;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    private final int rank;
    private final int file;
    private final List<Piece> capturedPieces;
    private final Piece[][] pieceHolder;
    private final static Board board;
    private final Time time = new Time();

    static {
        try {
            board = new Board();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    final Application application = new Application();
    private int turn;
    final SystemOutEventListener eventListener = new SystemOutEventListener();


    private final ArrayList<StringColor> b;

    public Board() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.rank = 12;
        this.file = 12;
        capturedPieces = new ArrayList<>();
        pieceHolder = new Piece[12][12];
        setInitialBoard();
        b = new ArrayList<>();


    }

    public Time getTime() {
        return time;
    }

    public ArrayList<StringColor> getB() {
        return b;
    }

    public List<Piece> getCapturedPieces() {
        return (List<Piece>) capturedPieces;
    }

    public Application getApplication() {
        return application;
    }

    public boolean isCellEmpty(Cell cell) {

        return pieceHolder[cell.getRank()][cell.getFile()] == null;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isOutOfRange(int i, int j) {
        boolean range = false;
        if (i == 7 && j == 1 || i == 7 && j == 11 || i == 8 && j == 1 || i == 8 && j == 2 || i == 8 && j == 11 || i == 8 && j == 10
                || i == 9 && j == 1 || i == 9 && j == 2 || i == 9 && j == 3 || i == 9 && j == 11 || i == 9 && j == 10 ||
                i == 9 && j == 9 || i == 10 && j == 1 || i == 10 && j == 2 || i == 10 && j == 3 || i == 10 && j == 4 ||
                i == 10 && j == 11 || i == 10 && j == 10 || i == 10 && j == 9 || i == 10 && j == 8 || i == 11 && j == 1
                || i == 11 && j == 2 || i == 11 && j == 3 || i == 11 && j == 4 || i == 11 && j == 5 || i == 11 && j == 11 ||
                i == 11 && j == 10 || i == 11 && j == 9 || i == 11 && j == 8 || i == 11 && j == 7) {

            range = true;
        }
        if (i < 0 || i > 11 || j < 0 || j > 11) {

            range = true;
        }
        return range;
    }

    public boolean regularValidMove(Move move) {
        boolean valid = true;
        if (!Board.getBoard().isCellEmpty(move.getStartSpot()) && !Board.getBoard().isCellEmpty(move.getEndSpot())) {
            if (Board.getBoard().getPiece(move.getStartSpot()).getColor().equals(Board.getBoard().getPiece(move.getEndSpot()).getColor())) {
                valid = false;
            }
        }

        return !isCellEmpty(move.getStartSpot()) && !isOutOfRange(move.getStartSpot().getRank(), move.getStartSpot().file)
                && !isOutOfRange(move.getEndSpot().getRank(), move.getEndSpot().file) && valid;
    }

    public void setPiece(Piece piece, Cell cell) {
        if (isOutOfRange(cell.getRank(), cell.getFile())) {
            throw new IndexOutOfBoundsException();
        } else {
            pieceHolder[cell.getRank()][cell.getFile()] = piece;
        }
    }

    public Piece getPiece(Cell cell) {
        if (isOutOfRange(cell.getRank(), cell.getFile())) {
            throw new IndexOutOfBoundsException();
        }
        return pieceHolder[cell.getRank()][cell.getFile()];
    }

    public char intToChar(int file) {
        char[] File = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l'};
        return File[file - 1];
    }

    public int CharToInt(char file) {
        char[] File = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l'};
        int len = File.length;
        int i = 0;
        while (i < len) {
            if (File[i] == file) {
                return i + 1;
            } else {
                i++;
            }
        }
        return 0;
    }
    public String StringToPieceName(Piece piece) {
        if (piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)) {
            return PieceName.BLACK_PAWN;
        }
        if (piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)) {
            return PieceName.WHITE_PAWN;
        }
        if (piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)) {
            return PieceName.BLACK_ROCK;
        }
        if (piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)) {
            return PieceName.WHITE_ROCK;
        }
        if (piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)) {
            return PieceName.BLACK_KNIGHT;
        }
        if (piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)) {
            return PieceName.WHITE_KNIGHT;
        }
        if (piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)) {
            return PieceName.BLACK_KING;
        }
        if (piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)) {
            return PieceName.WHITE_KING;
        }
        if (piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)) {
            return PieceName.BLACK_QUEEN;
        }
        if (piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)) {
            return PieceName.WHITE_QUEEN;
        }
        if (piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)) {
            return PieceName.BLACK_BISHOP;
        }
        if (piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)) {
            return PieceName.WHITE_BISHOP;
        }
        return null;
    }

    public void setInitialBoard() {
        //black-Pawn
        for (int i = 2; i < 11; i++) {
            application.setCellProperties(7, intToChar(i), PieceName.BLACK_PAWN, null, Color.BLACK);
            setPiece(new Pawn(Color.BLACK, PieceName.BLACK_PAWN), new Cell(7, i));
        }
        //white-Left-Pawn
        for (int i = 1; i < 6; i++) {
            application.setCellProperties(i, intToChar(i + 1), PieceName.WHITE_PAWN, null, Color.WHITE);
            setPiece(new Pawn(Color.WHITE, PieceName.WHITE_PAWN), new Cell(i, i + 1));
        }
        //white-Right-Pawn
        for (int i = 1; i < 5; i++) {
            application.setCellProperties(i, intToChar(11 - i), PieceName.WHITE_PAWN, null, Color.WHITE);
            setPiece(new Pawn(Color.WHITE, PieceName.WHITE_PAWN), new Cell(i, 11 - i));
        }
        //black-Bishop
        for (int i = 9; i < 12; i++) {
            application.setCellProperties(i, intToChar(6), PieceName.BLACK_BISHOP, null, Color.BLACK);
            setPiece(new Bishop(Color.BLACK, PieceName.BLACK_BISHOP), new Cell(i, 6));
        }
        //white-Bishop
        for (int i = 1; i < 4; i++) {
            application.setCellProperties(i, intToChar(6), PieceName.WHITE_BISHOP, null, Color.WHITE);
            setPiece(new Bishop(Color.WHITE, PieceName.WHITE_BISHOP), new Cell(i, 6));
        }
        //black-Rook
        setPiece(new Rook(Color.BLACK, PieceName.BLACK_ROCK), new Cell(8, 3));
        application.setCellProperties(8, intToChar(3), PieceName.BLACK_ROCK, null, Color.BLACK);
        setPiece(new Rook(Color.BLACK, PieceName.BLACK_ROCK), new Cell(8, 9));
        application.setCellProperties(8, intToChar(9), PieceName.BLACK_ROCK, null, Color.BLACK);
        //white-Rook
        setPiece(new Rook(Color.WHITE, PieceName.WHITE_ROCK), new Cell(1, 3));
        application.setCellProperties(1, intToChar(3), PieceName.WHITE_ROCK, null, Color.WHITE);
        setPiece(new Rook(Color.WHITE, PieceName.WHITE_ROCK), new Cell(1, 9));
        application.setCellProperties(1, intToChar(9), PieceName.WHITE_ROCK, null, Color.WHITE);
        //black-Knight
        setPiece(new Knight(Color.BLACK, PieceName.BLACK_KNIGHT), new Cell(9, 4));
        application.setCellProperties(9, intToChar(4), PieceName.BLACK_KNIGHT, null, Color.BLACK);
        setPiece(new Knight(Color.BLACK, PieceName.BLACK_KNIGHT), new Cell(9, 8));
        application.setCellProperties(9, intToChar(8), PieceName.BLACK_KNIGHT, null, Color.BLACK);
        //white-Knight
        setPiece(new Knight(Color.WHITE, PieceName.WHITE_KNIGHT), new Cell(1, 4));
        application.setCellProperties(1, intToChar(4), PieceName.WHITE_KNIGHT, null, Color.WHITE);
        setPiece(new Knight(Color.WHITE, PieceName.WHITE_KNIGHT), new Cell(1, 8));
        application.setCellProperties(1, intToChar(8), PieceName.WHITE_KNIGHT, null, Color.WHITE);
        //black-Queen
        setPiece(new Queen(Color.BLACK, PieceName.BLACK_QUEEN), new Cell(10, 5));
        application.setCellProperties(10, intToChar(5), PieceName.BLACK_QUEEN, null, Color.BLACK);
        //white-Queen
        setPiece(new Queen(Color.WHITE, PieceName.WHITE_QUEEN), new Cell(1, 5));
        application.setCellProperties(1, intToChar(5), PieceName.WHITE_QUEEN, null, Color.WHITE);
        //black-King
        setPiece(new King(Color.BLACK, PieceName.BLACK_KING), new Cell(10, 7));
        application.setCellProperties(10, intToChar(7), PieceName.BLACK_KING, null, Color.BLACK);
        //white-King
        setPiece(new King(Color.WHITE, PieceName.WHITE_KING), new Cell(1, 7));
        application.setCellProperties(1, intToChar(7), PieceName.WHITE_KING, null, Color.WHITE);

        application.setMessage("White's Turn");
    }

    public static Board getBoard() {
        return board;
    }


    public void movePiece(Move move) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        boolean valid = false;
        boolean valid1 = false;
        boolean valid2 = true;
        if (!getBoard().isCellEmpty(move.getEndSpot())) {
            if (getBoard().getPiece(move.getEndSpot()) instanceof King) {
                valid2 = false;
            }
        }


        if (getBoard().getPiece(move.getStartSpot()) != null)
            if (getBoard().canMove(move.getStartSpot()) && getBoard().regularValidMove(move) && valid2 && gotOutOfCheck(move,getPiece(move.getStartSpot()).getColor())) {
                int rank = move.getEndSpot().getRank();
                int file1 = move.getEndSpot().getFile();
                Color color = Board.getBoard().getPiece(move.getStartSpot()).getColor();
                if (getBoard().getPiece(move.getStartSpot()).getSymbol().equals("Pawn")) {
                    Pawn pawn = new Pawn(color, "Pawn");
                    Board.getBoard().setPiece(pawn, move.getStartSpot());
                    if (pawn.isValidMove(move)) {
                        if (color.equals(Color.BLACK)) {
                            if (rank == 1) {
                                valid = true;
                            }
                        }
                        if (color.equals(Color.WHITE)) {
                            if (rank == 6 && file1 == 1 || rank == 7 && file1 == 2 || rank == 8 && file1 == 3 || rank == 9 && file1 == 4
                                    || rank == 10 && file1 == 5 || rank == 11 && file1 == 6 || rank == 6 && file1 == 11 || rank == 7 && file1 == 10
                                    || rank == 8 && file1 == 9 || rank == 9 && file1 == 8 || rank == 10 && file1 == 7) {
                                valid1 = true;
                            }
                        }
                    }
                }

                if (valid1) {
                    char file2 = intToChar(move.getEndSpot().getFile());
                    char file3 = intToChar(move.getStartSpot().getFile());
                    String newPiece = application.showPromotionPopup();

                    if (newPiece.equals("Bishop")) {
                        setPiece(new Bishop(Color.WHITE, "Bishop"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.WHITE_BISHOP, null, Color.WHITE);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);
                    }
                    if (newPiece.equals("Knight")) {
                        setPiece(new Knight(Color.WHITE, "Knight"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.WHITE_KNIGHT, null, Color.WHITE);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);

                    }
                    if (newPiece.equals("Rook")) {
                        setPiece(new Rook(Color.WHITE, "Rook"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.WHITE_ROCK, null, Color.WHITE);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);

                    }
                    if (newPiece.equals("Queen")) {
                        setPiece(new Queen(Color.WHITE, "Queen"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.WHITE_QUEEN, null, Color.WHITE);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);

                    }
                }
                if (valid) {
                    char file2 = intToChar(move.getEndSpot().getFile());
                    char file3 = intToChar(move.getStartSpot().getFile());
                    String newPiece = application.showPromotionPopup();

                    if (newPiece.equals("Bishop")) {
                        setPiece(new Bishop(Color.BLACK, "Bishop"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.BLACK_BISHOP, null, Color.BLACK);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);
                    }
                    if (newPiece.equals("Knight")) {
                        setPiece(new Knight(Color.BLACK, "Knight"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.BLACK_KNIGHT, null, Color.BLACK);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);

                    }
                    if (newPiece.equals("Rook")) {
                        setPiece(new Rook(Color.BLACK, "Rook"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.BLACK_ROCK, null, Color.BLACK);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);

                    }
                    if (newPiece.equals("Queen")) {
                        setPiece(new Queen(Color.BLACK, "Queen"), move.getEndSpot());
                        application.setCellProperties(move.getEndSpot().getRank(), file2, null, null, null);
                        application.setCellProperties(move.getEndSpot().getRank(), file2, PieceName.BLACK_QUEEN, null, Color.BLACK);
                        application.setCellProperties(move.getStartSpot().getRank(), file3, null, null, null);

                    }
                }

            }

        if (getPiece(move.getStartSpot()) != null && !valid && !valid1 && valid2) {
            Piece startPiece = getPiece(move.getStartSpot());
            Piece endPiece = getPiece(move.getEndSpot());
            int startRank = move.getStartSpot().rank;
            char startFile = intToChar(move.getStartSpot().getFile());
            if (startPiece.isValidMove(move)) {
                colorKingSpot();

                if (!isCellEmpty(move.getEndSpot())) {
                    if (!startPiece.getColor().equals(endPiece.getColor())) {
                        capturePiece(move);
                    }
                }

                application.setCellProperties(startRank, startFile, null, null, null);
                setPiece(getPiece(move.getStartSpot()), move.getEndSpot());
                pieceHolder[move.getStartSpot().getRank()][move.getStartSpot().getFile()] = null;

                if (getBoard().isCheckmated(Color.BLACK)) {
                    application.showMessagePopup("White won");
                    eventListener.onNewGame();
                }
                if (getBoard().isCheckmated(Color.WHITE)) {
                    application.showMessagePopup("Black won");
                    eventListener.onNewGame();
                }
                if (getBoard().isStalemated(Color.BLACK)) {
                    application.showMessagePopup("Draw");
                    eventListener.onNewGame();
                }
                if (getBoard().isStalemated(Color.WHITE)) {
                    application.showMessagePopup("Draw");
                    eventListener.onNewGame();
                }
            }

        }
    }


    public void capturePiece(Move move) {
        Piece peicee = getPiece(move.getStartSpot());
        Piece piece = getPiece(move.getEndSpot());

        if (!isCellEmpty(move.getEndSpot()) && !peicee.getColor().equals(piece.getColor())) {
            capturedPieces.add(pieceHolder[move.getEndSpot().getRank()][move.getEndSpot().getFile()]);
            String Piece = StringToPieceName(pieceHolder[move.getEndSpot().getRank()][move.getEndSpot().getFile()]);
            Color color = piece.getColor();
            b.add(new StringColor(Piece, color));
            b.addAll(eventListener.getArray());
            application.setRemovedPieces(toArray(b));
            eventListener.getArray().clear();

        }
    }
    public boolean turnManager(int rank, int file) {
        boolean turn2 = false;
        if (!getBoard().isCellEmpty(new Cell(rank, file))) {
            if (turn % 2 == 0 && getBoard().getPiece(new Cell(rank, file)).getColor().equals(Color.WHITE) ) {
                application.setMessage("Black's Turn");
                turn2 = true;
                turn++;

            }
            if (turn % 2 == 1 && getBoard().getPiece(new Cell(rank, file)).getColor().equals(Color.BLACK)) {
                application.setMessage("White's Turn");
                turn2 = true;
                turn++;
            }
        }
        return turn2;
    }
    public boolean isSpotThreatened(Cell startSpot) {
        if (startSpot.getRank() > 0 && startSpot.getRank() < 12 && startSpot.getFile() > 0 && startSpot.getFile() < 12) {
            if (Board.getBoard().isCellEmpty(startSpot)) {
                return false;
            }
            for (int i = 1; i < 12; i++) {
                for (int j = 1; j < 12; j++) {
                    Cell endSpot = new Cell(i, j);
                    if (!getBoard().isOutOfRange(i, j)) {
                        Color pieceColor = Board.getBoard().getPiece(startSpot).getColor();
                        if (!isCellEmpty(new Cell(i, j))) {
                            Piece enemyPiece = Board.getBoard().getPiece(endSpot);
                            if (!Board.getBoard().isCellEmpty(endSpot) && !enemyPiece.getColor().equals(pieceColor) &&
                                    Board.getBoard().getPiece(endSpot).isValidMove(new Move(endSpot, startSpot))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isChecked(Color color) {
        Cell spot = getKingSpot(color);
        return isSpotThreatened(spot);
    }

    public void colorKingSpot() {
        if (isChecked(Color.BLACK)) {
            Cell spot = Board.getBoard().getKingSpot(Color.BLACK);
            application.setCellProperties(spot.getRank(), intToChar(spot.getFile()), PieceName.BLACK_KING, Color.RED, Color.BLACK);
        }
        if (isChecked(Color.WHITE)) {
            Cell spot = Board.getBoard().getKingSpot(Color.WHITE);
            application.setCellProperties(spot.getRank(), intToChar(spot.getFile()), PieceName.WHITE_KING, Color.RED, Color.BLACK);
        }
    }

    public boolean canMove(Cell startSpot) {
        boolean valid2 = false;
        Piece piece = Board.getBoard().getPiece(startSpot);
        Color color = piece.getColor();
        boolean valid = !isChecked(color);
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 12; j++) {
                Cell endSpot = new Cell(i, j);
                if (!isOutOfRange(i, j)) {
                    if (piece instanceof King) {
                        if (piece.isValidMove(new Move(startSpot, endSpot)) && !isSpotThreatened(endSpot)) {
                            valid2 = true;
                        }
                    } else {
                        if (piece.isValidMove(new Move(startSpot, endSpot))) {
                            valid2 = true;
                        }
                    }

                }
            }
        }
        return valid2 && !isChecked(color);
    }
    public boolean gotOutOfCheck(Move move, Color color) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Cell kingSpot = getKingSpot(color);
        boolean valid3=true;
        boolean valid2 = true;
        if (!getBoard().isCellEmpty(move.getEndSpot())) {
            if (getBoard().getPiece(move.getEndSpot()) instanceof King) {
                valid2 = false;
            }
        }
        if (isChecked(color) && getPiece(move.getStartSpot()).getColor().equals(color)) {
            Cell endSpot = move.getEndSpot();
            Cell startSpot = move.getStartSpot();

            if (regularValidMove(move) && valid2 && !getPiece(move.getStartSpot()).getSymbol().equals("Pawn")) {
                getBoard().movePiece(move);
                if(isChecked(color)){
                    getBoard().movePiece(new Move(move.getEndSpot(),move.getStartSpot()));
                    valid3=false;
                }else{
                    getBoard().movePiece(new Move(endSpot,startSpot));
                    valid3 = true;
                }
            }

        }
        return valid3;
    }

    public boolean isCheckmated(Color color) {
        Cell spot = getKingSpot(color);
        if (!isSpotThreatened(spot)) {
            return false;
        } else {
            if (spot.getRank() > 0 && spot.getFile() > 0 && spot.getRank() < 12 && spot.getFile() < 12) {
                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        if (!(i == 0 && j == 0) && spot.getRank() + i > 0 && spot.getRank() + i < 12 && spot.getFile() + j > 0 && spot.getFile() + j < 12) {
                            Cell endSpot = new Cell(spot.getRank() + i, spot.getFile() + j);
                            if (Board.getBoard().getPiece(spot).isValidMove(new Move(spot, endSpot))) {
                                if (isSpotThreatened(new Cell(i, j))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;

    }

    public Cell getKingSpot(Color color) {
        for (int i = 1; i < 12; i++) {
            for (int j = 1; j < 12; j++) {
                if (!getBoard().isOutOfRange(i, j)) {
                    Piece piece = Board.getBoard().getPiece(new Cell(i, j));
                    if (piece instanceof King && piece.getColor().equals(color)) {
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }

    public boolean isStalemated(Color color) {
        Cell KingSpot = getKingSpot(color);
        boolean isChecked = isSpotThreatened(KingSpot);
        if (!isChecked) {
            for (int i = 1; i < 12; i++) {
                for (int j = 1; j < 12; j++) {
                    Cell spot = new Cell(i, j);
                    if (!Board.board.isOutOfRange(i, j)) {
                        Piece piece = Board.getBoard().getPiece(spot);
                        if (!Board.getBoard().isCellEmpty(spot)) {
                            if (piece.getColor().equals(color) && canMove(spot)) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public StringColor[] toArray(ArrayList<StringColor> arrayList) {
        StringColor[] array = new StringColor[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }
    public void ticktack(){
        if(turn%2==0){
            if(getBoard().getTime().getWhiteTimeLeft()<5000){

            }
        }
    }
}
