package casey;

import java.util.Timer;

import javax.swing.*;


public class SnakeGame {

	public final static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
	public final static int yPixelMaxDimension = 501;

	public static int xSquares ;
	public static int ySquares ;

	public final static int squareSize = 50;

	protected static Snake snake ;

	protected static Kibble kibble;

	protected static Score score;

	//boolean variables
	public static boolean soundOn = true;
	public static boolean warpWallsOn = false;
	public static boolean hardMode = false; 	// for implementation of Hard Mode
	// possible options include faster clock time,
	// exponential growth (?), maze walls


	static final int BEFORE_GAME = 1;
	static final int DURING_GAME = 2;
	static final int GAME_OVER = 3;
	static final int GAME_WON = 4;
	static final int PAUSE_MENU = 5;
	//The values are not important. The important thing is to use the constants
	//instead of the values so you are clear what you are setting. Easy to forget what number is Game over vs. game won
	//Using constant names instead makes it easier to keep it straight. Refer to these variables 
	//using statements such as SnakeGame.GAME_OVER 

	private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening. 
	//Other classes like Snake and DrawSnakeGamePanel will need to query this, and change it's value

	protected static long clockInterval = 500; //controls game speed
	//Every time the clock ticks, the snake moves
	//This is the time between clock ticks, in milliseconds
	//1000 milliseconds = 1  second.

	static JFrame snakeFrame;
	static JPanel optionsPanel;
	static OptionsGUI optionsGUI;
	static DrawSnakeGamePanel snakePanel;
	static SoundPlayer soundPlayer; //for implementation of sound

	//Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
	//http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
	//http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html


	private static void createAndShowGUI() {
		//Create and set up the window.
		snakeFrame = new JFrame();
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		snakeFrame.setSize(xPixelMaxDimension, yPixelMaxDimension);
		snakeFrame.setUndecorated(true); //hide title bar
		snakeFrame.setVisible(true);
		snakeFrame.setResizable(false);

		snakePanel = new DrawSnakeGamePanel(snake, kibble, score);
		snakePanel.setFocusable(true);
		snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents


		snakeFrame.add(snakePanel);
		snakePanel.addKeyListener(new GameControls(snake));

		soundPlayer = new SoundPlayer(); //sound player
		optionsGUI = new OptionsGUI();  //options gui
		optionsPanel = new JPanel();

		setGameStage(BEFORE_GAME);

	}

	private static void initializeGame() {
		//set up score, snake and first kibble
		xSquares = xPixelMaxDimension / squareSize;
		ySquares = yPixelMaxDimension / squareSize;

		snake = new Snake(xSquares, ySquares, squareSize);
		kibble = new Kibble(snake);
		score = new Score();
		setSoundsOn(true);

		gameStage = BEFORE_GAME;
	}

	protected static void newGame() {
		Timer timer = new Timer();
		GameClock clockTick = new GameClock(snake, kibble, score, snakePanel);
		timer.scheduleAtFixedRate(clockTick, 0 , clockInterval);

	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initializeGame();
				createAndShowGUI();
			}
		});
	}

	public static void setHardMode(boolean hardMode) {
		SnakeGame.hardMode = hardMode;
		SnakeGame.setGameSpeed(200);
		return;

	}

	public static int getGameStage() {
		return gameStage;
	}

	public static boolean gameEnded() {
		if (gameStage == GAME_OVER || gameStage == GAME_WON){
			return true;
		}
		return false;
	}

	public static void setGameStage(int gameStage) {
		SnakeGame.gameStage = gameStage;
	}

	public static void setSoundsOn(boolean soundsOn) { SnakeGame.soundOn = soundsOn; }

	public static void setWarpWallsOn(boolean warpWallsOn) {
		SnakeGame.warpWallsOn = warpWallsOn;
	}

	public static void setGameSpeed(int msPerTick) {
		clockInterval = msPerTick;
		return;
	}

	protected static void displayOptionsGUI(){
		optionsPanel = optionsGUI.rootPanel;
		gameStage = PAUSE_MENU;
		optionsPanel.setFocusable(true);
		optionsPanel.setOpaque(true);
		optionsPanel.requestFocusInWindow();

		snakeFrame.add(optionsPanel);
		snakeFrame.validate();
		snakeFrame.repaint();

	}
}
