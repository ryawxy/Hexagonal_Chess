package ir.sharif.math.bp02_1.hex_chess.Logic;
import ir.sharif.math.bp02_1.hex_chess.Logic.Board.Board;
import java.util.Timer;
import java.util.TimerTask;
public class Time {
    private Timer timer;
    private int currentPlayer;
    private int whiteTimeLeft;
    private int blackTimeLeft;
    public Time() {
        this.timer = new Timer();
        this.currentPlayer = 0;
        this.whiteTimeLeft = 20000;
        this.blackTimeLeft = 20000;
        startTimer();
    }
    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentPlayer == 0) {
                    whiteTimeLeft -= 1000;
                    System.out.println("White time left: " + whiteTimeLeft / 1000 + " seconds");
                    if (whiteTimeLeft <= 0) {
                        switchPlayer();
                    }
                    if(whiteTimeLeft<=5000 && whiteTimeLeft>=0){
                        AudioPlayer player = new AudioPlayer();
                        player.play("D:\\chess\\history\\tenseconds.wav");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.pause();
                    }
                } else {
                    blackTimeLeft -= 1000;
                    System.out.println("Black time left: " + blackTimeLeft / 1000 + " seconds");
                    if (blackTimeLeft <= 0) {
                        switchPlayer();
                    }
                    if(blackTimeLeft<=5000 && blackTimeLeft>=0){
                        AudioPlayer player = new AudioPlayer();
                        player.play("D:\\chess\\history\\tenseconds.wav");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.pause();
                    }
                }

            }
            }, 1000, 1000);
    }
    private void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
        if (currentPlayer == 0) {
            whiteTimeLeft = 20000;
            Board.getBoard().getApplication().setMessage("White's Turn");
        } else {
            blackTimeLeft = 20000;
            Board.getBoard().getApplication().setMessage("Black's Turn");
        }
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setWhiteTimeLeft(int whiteTimeLeft) {
        this.whiteTimeLeft = whiteTimeLeft;
    }
    public void setBlackTimeLeft(int blackTimeLeft) {
        this.blackTimeLeft = blackTimeLeft;
    }
    public int getWhiteTimeLeft() {
        return whiteTimeLeft;
    }
    public int getBlackTimeLeft() {
        return blackTimeLeft;
    }
}

