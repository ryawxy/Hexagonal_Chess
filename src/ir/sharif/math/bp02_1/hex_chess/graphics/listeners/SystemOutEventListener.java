package ir.sharif.math.bp02_1.hex_chess.graphics.listeners;

import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Cell;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Move;
import ir.sharif.math.bp02_1.hex_chess.Logic.Piece.*;
import ir.sharif.math.bp02_1.hex_chess.Logic.AudioPlayer;
import ir.sharif.math.bp02_1.hex_chess.graphics.models.StringColor;
import ir.sharif.math.bp02_1.hex_chess.util.PieceName;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SystemOutEventListener implements EventListener {
    private Piece currentPiece = null;
    private final int[] rankFile = {0, 0};
   private final  ArrayList<StringColor> c =new ArrayList<>();
    private final static ArrayList<StringColor> d = new ArrayList<>();
    char [] whiteTime = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'};
    char [] blackTime = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V'};

   public ArrayList<StringColor> getArray(){
       return d;
   }

    @Override
    public void onClick(int row, char col) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        int file = Board.getBoard().CharToInt(col);

        if (currentPiece == null) {
            if (!Board.getBoard().isCellEmpty(new Cell(row, file))) {
                for (int i = 1; i < 12; i++) {
                    for (int j = 1; j < 12; j++) {
                        char file2 = Board.getBoard().intToChar(j);
                        if (Board.getBoard().getPiece(new Cell(row, file)).isValidMove(new Move(new Cell(row, file), new Cell(i, j)))) {
                            if (!Board.getBoard().isCellEmpty(new Cell(i, j))) {
                                Piece endPiece = Board.getBoard().getPiece(new Cell(i, j));
                                String PieceName = Board.getBoard().StringToPieceName(endPiece);
                                Color color = Board.getBoard().getPiece(new Cell(i, j)).getColor();
                                if (!(Board.getBoard().getPiece(new Cell(i, j)) instanceof King)) {
                                    Board.getBoard().getApplication().setCellProperties(i, file2, PieceName, Color.PINK, color);
                                }
                            }else {
                                    Board.getBoard().getApplication().setCellProperties(i, file2, null, Color.PINK, null);
                                }

                        }
                    }
                }
                currentPiece = Board.getBoard().getPiece(new Cell(row, file));
                rankFile[0] = row;
                rankFile[1] = file;
            }
        } else {
            if (!Board.getBoard().isCellEmpty(new Cell(row, file))) {
                if (Board.getBoard().getPiece(new Cell(row, file)) == currentPiece) {
                    for (int i = 1; i < 12; i++) {
                        for (int j = 1; j < 12; j++) {
                            char file2 = Board.getBoard().intToChar(j);
                            if (!Board.getBoard().isOutOfRange(i, j))
                                if (Board.getBoard().getPiece(new Cell(row, file)).isValidMove(new Move(new Cell(row, file), new Cell(i, j)))) {
                                    if (!Board.getBoard().isCellEmpty(new Cell(i, j))) {
                                        Piece endPiece = Board.getBoard().getPiece(new Cell(i, j));
                                        String pieceName = Board.getBoard().StringToPieceName(endPiece);
                                        Color color = Board.getBoard().getPiece(new Cell(i, j)).getColor();
                                        if(!(Board.getBoard().getPiece(new Cell(i,j)) instanceof King)) {

                                            Board.getBoard().getApplication().setCellProperties(i, file2, pieceName, null, color);
                                        }
                                    } else {
                                        Board.getBoard().getApplication().setCellProperties(i, file2, null, null, null);
                                    }
                                }
                        }
                    }

                    currentPiece = null;
                }

            }
        }
        if (currentPiece != null)
            if (currentPiece.isValidMove(new Move(new Cell(rankFile[0], rankFile[1]), new Cell(row, file)))) {

                Piece piece = Board.getBoard().getPiece(new Cell(rankFile[0], rankFile[1]));
                String newPiece = Board.getBoard().StringToPieceName(piece);
                Color color = Board.getBoard().getPiece(new Cell(rankFile[0], rankFile[1])).getColor();
                for (int i = 1; i < 12; i++) {
                    for (int j = 1; j < 12; j++) {
                        if (!Board.getBoard().isOutOfRange(i, j))
                            if (piece.isValidMove(new Move(new Cell(rankFile[0], rankFile[1]), new Cell(i, j)))) {

                                if (!(i == row && j == file)) {

                                    char file3 = Board.getBoard().intToChar(j);
                                    if (Board.getBoard().isCellEmpty(new Cell(i, j))) {

                                        Board.getBoard().getApplication().setCellProperties(i, file3, null, null, null);

                                    } else {
                                        Piece piece1 = Board.getBoard().getPiece(new Cell(i, j));
                                        String pieceName = Board.getBoard().StringToPieceName(piece1);
                                        Color color1 = Board.getBoard().getPiece(new Cell(i, j)).getColor();
                                        Board.getBoard().getApplication().setCellProperties(i, file3, pieceName, null, color1);
                                    }
                                }
                            }
                    }
                }
                boolean turn=false;
                if(Board.getBoard().getTime().getCurrentPlayer()==0 && Board.getBoard().getPiece(new Cell(rankFile[0],rankFile[1])).getColor().equals(Color.WHITE)){
                    turn =true;
                }
                if(Board.getBoard().getTime().getCurrentPlayer()==1 && Board.getBoard().getPiece(new Cell(rankFile[0],rankFile[1])).getColor().equals(Color.BLACK)){
                    turn =true;
                }
                if (Board.getBoard().turnManager(rankFile[0], rankFile[1])) {
                    boolean valid=true;
                    if(!Board.getBoard().isCellEmpty(new Cell(row,file))){
                        if(Board.getBoard().getPiece(new Cell(row,file)) instanceof King){
                            valid = false;
                        }
                    }
                    if(valid && turn) {
                        Board.getBoard().movePiece(new Move(new Cell(rankFile[0], rankFile[1]), new Cell(row, file)));
                        Board.getBoard().getApplication().setCellProperties(row, col, newPiece, null, color);
                        if(Board.getBoard().getPiece(new Cell(row,file))!=null)
                        if(Board.getBoard().getPiece(new Cell(row,file)).getColor().equals(Color.WHITE)){
                            Board.getBoard().getTime().setWhiteTimeLeft(0);
                            Board.getBoard().getTime().setBlackTimeLeft(20000);
                            Board.getBoard().setTurn(1);
                        }
                        if(Board.getBoard().getPiece(new Cell(row,file))!=null)
                        if(Board.getBoard().getPiece(new Cell(row,file)).getColor().equals(Color.BLACK)){
                            Board.getBoard().getTime().setWhiteTimeLeft(20000);
                            Board.getBoard().getTime().setBlackTimeLeft(0);
                            Board.getBoard().setTurn(0);
                        }
                        AudioPlayer player = new AudioPlayer();

                        player.play("D:\\chess\\history\\promote.wav");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.pause();
                    }
                    File file3=new File("D:\\chess\\history\\save.txt");
                    try {
                        onSave(file3);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                currentPiece = null;
            }
            }

    @Override
    public EventListener onLoad(File file) {
file = new File("D:\\chess\\history\\save.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String s = sc.next();
                for(int i=0;i<=5;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i+1,1));
                        Board.getBoard().getApplication().setCellProperties(i,'a', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i,1));
                        Board.getBoard().getApplication().setCellProperties(i+1,'a',null,null,null);
                    }
                }
                for(int i=6;i<=11;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-5,11));
                        Board.getBoard().getApplication().setCellProperties(i-5,'l',null,null,null);
                    }

                }
                for(int i=12;i<=18;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-11,2));
                        Board.getBoard().getApplication().setCellProperties(i-11,'b',null,null,null);
                    }

                }
                for(int i=19;i<=25;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-18,10));
                        Board.getBoard().getApplication().setCellProperties(i-18,'k',null,null,null);
                    }

                }
                for(int i=26;i<=33;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-25,3));
                        Board.getBoard().getApplication().setCellProperties(i-25,'c',null,null,null);
                    }

                }
                for(int i=34;i<=41;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-33,9));
                        Board.getBoard().getApplication().setCellProperties(i-33,'i',null,null,null);
                    }

                }
                for(int i=42;i<=50;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-41,4));
                        Board.getBoard().getApplication().setCellProperties(i-41,'d',null,null,null);
                    }

                }
                for(int i=51;i<=59;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-50,8));
                        Board.getBoard().getApplication().setCellProperties(i-50,'h',null,null,null);
                    }
                }
                for(int i=60;i<=69;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-59,5));
                        Board.getBoard().getApplication().setCellProperties(i-59,'e',null,null,null);
                    }
                }
                for(int i=70;i<=79;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-69,7));
                        Board.getBoard().getApplication().setCellProperties(i-69,'g',null,null,null);
                    }
                }
                for(int i=80;i<=90;i++){
                    if(s.charAt(i)=='a'){
                        Board.getBoard().setPiece(new Rook(Color.BLACK,"Rook"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.BLACK_ROCK,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='b'){
                        Board.getBoard().setPiece(new Rook(Color.WHITE,"Rook"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.WHITE_ROCK,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='c'){
                        Board.getBoard().setPiece(new Pawn(Color.BLACK,"Pawn"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.BLACK_PAWN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='d'){
                        Board.getBoard().setPiece(new Pawn(Color.WHITE,"Pawn"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.WHITE_PAWN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='e'){
                        Board.getBoard().setPiece(new Bishop(Color.BLACK,"Bishop"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.BLACK_BISHOP,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='f'){
                        Board.getBoard().setPiece(new Bishop(Color.WHITE,"Bishop"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.WHITE_BISHOP,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='g'){
                        Board.getBoard().setPiece(new King(Color.BLACK,"King"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.BLACK_KING,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='h'){
                        Board.getBoard().setPiece(new King(Color.WHITE,"King"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.WHITE_KING,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='i'){
                        Board.getBoard().setPiece(new Queen(Color.BLACK,"Queen"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.BLACK_QUEEN,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='j'){
                        Board.getBoard().setPiece(new Queen(Color.WHITE,"Queen"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.WHITE_QUEEN,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='k'){
                        Board.getBoard().setPiece(new Knight(Color.BLACK,"Knight"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.BLACK_KNIGHT,null,Color.BLACK);
                    }
                    if(s.charAt(i)=='l'){
                        Board.getBoard().setPiece(new Knight(Color.WHITE,"Knight"),new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f', PieceName.WHITE_KNIGHT,null,Color.WHITE);
                    }
                    if(s.charAt(i)=='t'){
                        Board.getBoard().setPiece(null,new Cell(i-79,6));
                        Board.getBoard().getApplication().setCellProperties(i-79,'f',null,null,null);
                    }

                }
                if(s.charAt(91)=='A'||s.charAt(91)=='B'||s.charAt(91)=='C'||s.charAt(91)=='D'||s.charAt(91)=='E'||s.charAt(91)=='F'||s.charAt(91)=='G'||s.charAt(91)=='H'||s.charAt(91)=='I'
                        ||s.charAt(91)=='J'||s.charAt(91)=='K'||s.charAt(91)=='L'||s.charAt(91)=='M'||s.charAt(91)=='N'||s.charAt(91)=='O'||s.charAt(91)=='P'||s.charAt(91)=='Q'||s.charAt(91)=='R'||s.charAt(91)=='S'||s.charAt(91)=='T' || s.charAt(91)=='U'){
                    Board.getBoard().setTurn(1);
                    Board.getBoard().getApplication().setMessage("Black's Turn");
                    Board.getBoard().getTime().setBlackTimeLeft(CharToInt(s.charAt(91))*1000);
                    Board.getBoard().getTime().setWhiteTimeLeft(0);
                    Board.getBoard().getTime().setCurrentPlayer(1);

                }else{
                    Board.getBoard().setTurn(0);
                    Board.getBoard().getApplication().setMessage("White's Turn");
                    Board.getBoard().getTime().setBlackTimeLeft(0);
                    Board.getBoard().getTime().setWhiteTimeLeft(CharToInt2(s.charAt(91))*1000);
                    Board.getBoard().getTime().setCurrentPlayer(0);
                }

                if(s.length()>92){

                    for(int i=1;i<s.length()-91;i++){

                        if(s.charAt(i+91)=='a'){
                            c.add(new StringColor(PieceName.BLACK_ROCK,Color.BLACK));
                            Board.getBoard().getCapturedPieces().add(new Rook(Color.BLACK,"Rook"));
                        }
                        if(s.charAt(i+91)=='b'){
                            c.add(new StringColor(PieceName.WHITE_ROCK,Color.WHITE));
                            Board.getBoard().getCapturedPieces().add(new Rook(Color.WHITE,"Rook"));
                        }
                        if(s.charAt(i+91)=='c'){
                            c.add(new StringColor(PieceName.BLACK_PAWN,Color.BLACK));
                            Board.getBoard().getCapturedPieces().add(new Pawn(Color.BLACK,"Pawn"));
                        }
                        if(s.charAt(i+91)=='d'){
                            c.add(new StringColor(PieceName.WHITE_PAWN,Color.WHITE));
                            Board.getBoard().getCapturedPieces().add(new Pawn(Color.BLACK,"Pawn"));
                        }
                        if(s.charAt(i+91)=='e'){
                            c.add(new StringColor(PieceName.BLACK_BISHOP,Color.BLACK));
                            Board.getBoard().getCapturedPieces().add(new Bishop(Color.BLACK,"Bishop"));
                        }
                        if(s.charAt(i+91)=='f'){
                            c.add(new StringColor(PieceName.WHITE_BISHOP,Color.WHITE));
                            Board.getBoard().getCapturedPieces().add(new Bishop(Color.WHITE,"Bishop"));
                        }
                        if(s.charAt(i+91)=='g'){
                            c.add(new StringColor(PieceName.BLACK_KING,Color.BLACK));
                            Board.getBoard().getCapturedPieces().add(new King(Color.BLACK,"King"));
                        }
                        if(s.charAt(i+91)=='h'){
                            c.add(new StringColor(PieceName.WHITE_KING,Color.WHITE));
                            Board.getBoard().getCapturedPieces().add(new King(Color.WHITE,"King"));
                        }
                        if(s.charAt(i+91)=='i'){
                            c.add(new StringColor(PieceName.BLACK_QUEEN,Color.BLACK));
                            Board.getBoard().getCapturedPieces().add(new Queen(Color.BLACK,"Queen"));
                        }
                        if(s.charAt(i+91)=='j'){
                            c.add(new StringColor(PieceName.WHITE_QUEEN,Color.WHITE));
                            Board.getBoard().getCapturedPieces().add(new Queen(Color.WHITE,"Queen"));
                        }
                        if(s.charAt(i+91)=='k'){
                            c.add(new StringColor(PieceName.BLACK_KNIGHT,Color.BLACK));
                            Board.getBoard().getCapturedPieces().add(new Knight(Color.BLACK,"Knight"));
                        }
                        if(s.charAt(i+91)=='l'){
                            c.add(new StringColor(PieceName.WHITE_KNIGHT,Color.WHITE));
                            Board.getBoard().getCapturedPieces().add(new Knight(Color.WHITE,"Knight"));
                        }
                    }
                    Board.getBoard().getApplication().setRemovedPieces(Board.getBoard().toArray(c));
                }
                for(int i=0;i<Board.getBoard().getCapturedPieces().size();i++){
                    d.add(c.get(i));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public EventListener onSave(File file) throws IOException {

 file = new File("D:\\chess\\history\\save.txt");
        FileWriter wr=new FileWriter(file);
        StringBuilder str = new StringBuilder();
        for(int i=1;i<=6;i++){
            if(!Board.getBoard().isOutOfRange(i,1)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,1))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,1));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=6;i++){
            if(!Board.getBoard().isOutOfRange(i,11)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,11))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,11));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=7;i++){
            if(!Board.getBoard().isOutOfRange(i,2)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,2))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,2));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=7;i++){
            if(!Board.getBoard().isOutOfRange(i,10)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,10))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,10));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=8;i++){
            if(!Board.getBoard().isOutOfRange(i,3)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,3))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,3));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=8;i++){
            if(!Board.getBoard().isOutOfRange(i,9)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,9))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,9));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=9;i++){
            if(!Board.getBoard().isOutOfRange(i,4)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,4))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,4));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=9;i++){
            if(!Board.getBoard().isOutOfRange(i,8)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,8))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,8));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=10;i++){
            if(!Board.getBoard().isOutOfRange(i,5)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,5))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,5));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=10;i++){
            if(!Board.getBoard().isOutOfRange(i,7)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,7))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,7));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }
        for(int i=1;i<=11;i++){
            if(!Board.getBoard().isOutOfRange(i,6)){
                if(!Board.getBoard().isCellEmpty(new Cell(i,6))){
                    Piece piece = Board.getBoard().getPiece(new Cell(i,6));
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.BLACK)){
                        str.append('c');
                    }
                    if(piece.getSymbol().equals("Pawn") && piece.getColor().equals(Color.WHITE)){
                        str.append('d');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.BLACK)){
                        str.append('e');
                    }
                    if(piece.getSymbol().equals("Bishop") && piece.getColor().equals(Color.WHITE)){
                        str.append('f');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.BLACK)){
                        str.append('a');
                    }
                    if(piece.getSymbol().equals("Rook") && piece.getColor().equals(Color.WHITE)){
                        str.append('b');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.BLACK)){
                        str.append('i');
                    }
                    if(piece.getSymbol().equals("Queen") && piece.getColor().equals(Color.WHITE)){
                        str.append('j');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.BLACK)){
                        str.append('g');
                    }
                    if(piece.getSymbol().equals("King") && piece.getColor().equals(Color.WHITE)){
                        str.append('h');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.BLACK)){
                        str.append('k');
                    }
                    if(piece.getSymbol().equals("Knight") && piece.getColor().equals(Color.WHITE)){
                        str.append('l');
                    }
                }else{
                    str.append("t");
                }
            }
        }

        if(Board.getBoard().getTurn()%2==0){
                str.append(whiteTime[Board.getBoard().getTime().getWhiteTimeLeft()/1000]);


        }else{
            str.append(blackTime[Board.getBoard().getTime().getBlackTimeLeft()/1000]);
        }
        if(!Board.getBoard().getCapturedPieces().isEmpty()) {
            for (int i = 0; i < Board.getBoard().getCapturedPieces().size(); i++) {
                String Piece = Board.getBoard().StringToPieceName(Board.getBoard().getCapturedPieces().get(i));
                Color color = Board.getBoard().getCapturedPieces().get(i).getColor();
            }
            int a = Board.getBoard().getCapturedPieces().size();
            for (int i = 0; i < a; i++) {
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Pawn") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.BLACK)) {
                    str.append('c');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Pawn") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.WHITE)) {
                    str.append('d');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Bishop") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.BLACK)) {
                    str.append('e');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Bishop") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.WHITE)) {
                    str.append('f');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Rook") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.BLACK)) {
                    str.append('a');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Rook") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.WHITE)) {
                    str.append('b');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Queen") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.BLACK)) {
                    str.append('i');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Queen") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.WHITE)) {
                    str.append('j');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("King") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.BLACK)) {
                    str.append('g');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("King") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.WHITE)) {
                    str.append('h');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Knight") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.BLACK)) {
                    str.append('k');
                }
                if (Board.getBoard().getCapturedPieces().get(i).getSymbol().equals("Knight") && Board.getBoard().getCapturedPieces().get(i).getColor().equals(Color.WHITE)) {
                    str.append('l');
                }
            }
        }


wr.write(String.valueOf(str));
wr.flush();
wr.close();
        return null;
    }

    @Override
    public void onNewGame() {
        for(int i=1;i<12;i++) {
            for (int j = 1; j < 12; j++) {
                if (!Board.getBoard().isOutOfRange(i, j)) {
                    Board.getBoard().getApplication().setCellProperties(i, Board.getBoard().intToChar(j), null, null, null);
                    Board.getBoard().setPiece(null, new Cell(i, j));
                }
            }
        }

        d.clear();
        Board.getBoard().getB().clear();

        c.clear();
        Board.getBoard().getCapturedPieces().clear();

        Board.getBoard().getApplication().setRemovedPieces(Board.getBoard().toArray(d));
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("D:\\chess\\history\\save.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.print("");
        writer.close();


        Board.getBoard().setTurn(0);
        Board.getBoard().getApplication().setMessage("White's Turn");
         Board.getBoard().getTime().setWhiteTimeLeft(20000);
         Board.getBoard().getTime().setBlackTimeLeft(0);
         Board.getBoard().getTime().setCurrentPlayer(0);

        for (int i = 2; i < 11; i++) {
            Board.getBoard().getApplication().setCellProperties(7, Board.getBoard().intToChar(i), PieceName.BLACK_PAWN, null, Color.BLACK);
            Board.getBoard().setPiece(new Pawn(Color.BLACK, PieceName.BLACK_PAWN), new Cell(7, i));
        }
        //white-Left-Pawn
        for (int i = 1; i < 6; i++) {
            Board.getBoard().getApplication().setCellProperties(i, Board.getBoard().intToChar(i + 1), PieceName.WHITE_PAWN, null, Color.WHITE);
            Board.getBoard().setPiece(new Pawn(Color.WHITE, PieceName.WHITE_PAWN), new Cell(i, i + 1));
        }
        //white-Right-Pawn
        for (int i = 1; i < 5; i++) {
            Board.getBoard().getApplication().setCellProperties(i, Board.getBoard().intToChar(11 - i), PieceName.WHITE_PAWN, null, Color.WHITE);
            Board.getBoard().setPiece(new Pawn(Color.WHITE, PieceName.WHITE_PAWN), new Cell(i, 11 - i));
        }
        //black-Bishop
        for (int i = 9; i < 12; i++) {
            Board.getBoard().getApplication().setCellProperties(i, Board.getBoard().intToChar(6), PieceName.BLACK_BISHOP, null, Color.BLACK);
            Board.getBoard().setPiece(new Bishop(Color.BLACK, PieceName.BLACK_BISHOP), new Cell(i, 6));
        }
        //white-Bishop
        for (int i = 1; i < 4; i++) {
            Board.getBoard().getApplication().setCellProperties(i, Board.getBoard().intToChar(6), PieceName.WHITE_BISHOP, null, Color.WHITE);
            Board.getBoard().setPiece(new Bishop(Color.WHITE, PieceName.WHITE_BISHOP), new Cell(i, 6));
        }
        //black-Rook
        Board.getBoard().setPiece(new Rook(Color.BLACK, PieceName.BLACK_ROCK), new Cell(8, 3));
        Board.getBoard().getApplication().setCellProperties(8, Board.getBoard().intToChar(3), PieceName.BLACK_ROCK, null, Color.BLACK);
        Board.getBoard().setPiece(new Rook(Color.BLACK, PieceName.BLACK_ROCK), new Cell(8, 9));
        Board.getBoard().getApplication().setCellProperties(8, Board.getBoard().intToChar(9), PieceName.BLACK_ROCK, null, Color.BLACK);
        //white-Rook
        Board.getBoard().setPiece(new Rook(Color.WHITE, PieceName.WHITE_ROCK), new Cell(1, 3));
        Board.getBoard().getApplication().setCellProperties(1, Board.getBoard().intToChar(3), PieceName.WHITE_ROCK, null, Color.WHITE);
        Board.getBoard().setPiece(new Rook(Color.WHITE, PieceName.WHITE_ROCK), new Cell(1, 9));
        Board.getBoard().getApplication().setCellProperties(1, Board.getBoard().intToChar(9), PieceName.WHITE_ROCK, null, Color.WHITE);
        //black-Knight
        Board.getBoard().setPiece(new Knight(Color.BLACK, PieceName.BLACK_KNIGHT), new Cell(9, 4));
        Board.getBoard().getApplication().setCellProperties(9, Board.getBoard().intToChar(4), PieceName.BLACK_KNIGHT, null, Color.BLACK);
        Board.getBoard().setPiece(new Knight(Color.BLACK, PieceName.BLACK_KNIGHT), new Cell(9, 8));
        Board.getBoard().getApplication().setCellProperties(9, Board.getBoard().intToChar(8), PieceName.BLACK_KNIGHT, null, Color.BLACK);
        //white-Knight
        Board.getBoard().setPiece(new Knight(Color.WHITE, PieceName.WHITE_KNIGHT), new Cell(1, 4));
        Board.getBoard().getApplication().setCellProperties(1, Board.getBoard().intToChar(4), PieceName.WHITE_KNIGHT, null, Color.WHITE);
        Board.getBoard().setPiece(new Knight(Color.WHITE, PieceName.WHITE_KNIGHT), new Cell(1, 8));
        Board.getBoard().getApplication().setCellProperties(1, Board.getBoard().intToChar(8), PieceName.WHITE_KNIGHT, null, Color.WHITE);
        //black-Queen
        Board.getBoard().setPiece(new Queen(Color.BLACK, PieceName.BLACK_QUEEN), new Cell(10, 5));
        Board.getBoard().getApplication().setCellProperties(10, Board.getBoard().intToChar(5), PieceName.BLACK_QUEEN, null, Color.BLACK);
        //white-Queen
        Board.getBoard().setPiece(new Queen(Color.WHITE, PieceName.WHITE_QUEEN), new Cell(1, 5));
        Board.getBoard().getApplication().setCellProperties(1, Board.getBoard().intToChar(5), PieceName.WHITE_QUEEN, null, Color.WHITE);
        //black-King
        Board.getBoard().setPiece(new King(Color.BLACK, PieceName.BLACK_KING), new Cell(10, 7));
        Board.getBoard().getApplication().setCellProperties(10, Board.getBoard().intToChar(7), PieceName.BLACK_KING, null, Color.BLACK);
        //white-King
        Board.getBoard().setPiece(new King(Color.WHITE, PieceName.WHITE_KING), new Cell(1, 7));
        Board.getBoard().getApplication().setCellProperties(1, Board.getBoard().intToChar(7), PieceName.WHITE_KING, null, Color.WHITE);


        d.clear();
        c.clear();

    }
    public int CharToInt(char file) {
        char[] File = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V'};
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
    public int CharToInt2(char file) {
        char[] File = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'};
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

}
