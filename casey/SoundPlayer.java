package casey;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by casey on 4/15/15.
 */
public class SoundPlayer {

    //audio clip variables
    private static AudioClip kibbleChomp;
    private static AudioClip snakeMove;
    private static AudioClip gameOver;
    private static AudioClip winner;

    public SoundPlayer(){
        try{

            //need to add game sounds to GitHub ~~ STILL NEED WINNER SOUND ~~
            URL kibbleChompUrl = new URL("https://github.com/MuffinUprising/Snake/blob/master/casey/kibbleChomp.wav");
            URL snakeMoveUrl = new URL("https://github.com/MuffinUprising/Snake/blob/master/casey/snakeMove.wav");
            URL gameOverUrl = new URL("https://github.com/MuffinUprising/Snake/blob/master/casey/gameOver.wav");
            URL winnerUrl = new URL("https://github.com/MuffinUprising/Snake/blob/master/casey/winner.wav");

            kibbleChomp = Applet.newAudioClip(kibbleChompUrl);
            snakeMove = Applet.newAudioClip(snakeMoveUrl);
            gameOver = Applet.newAudioClip(gameOverUrl);
            winner = Applet.newAudioClip(winnerUrl);


        }catch(MalformedURLException murle){
            murle.printStackTrace();
        }
    }

    //kibble chomp method
    public static void playKibbleChomp(){
        kibbleChomp.play();
    }
    //snake move method
    public static void playSnakeMove(){
        snakeMove.play();
    }
    //game over method
    public static void playGameOver(){
        gameOver.play();
    }
    //winner method
    public static void playWinner(){
        winner.play();
    }

}
