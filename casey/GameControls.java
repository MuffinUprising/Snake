package casey;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener{
	
	Snake snake;

	
	GameControls(Snake s){
		this.snake = s;
	}
	
	public void keyPressed(KeyEvent ev) {
		//keyPressed events are for catching events like function keys, enter, arrow keys
		//We want to listen for arrow keys to move snake
		//Has to id if user pressed arrow key, and if so, send info to Snake object

		//is game running? No? tell the game to draw grid, start, etc.
		
		//Get the component which generated this event
		//Hopefully, a DrawSnakeGamePanel object.
		//It would be a good idea to catch a ClassCastException here. 
		
		DrawSnakeGamePanel panel = (DrawSnakeGamePanel)ev.getComponent();

		if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME){
			//Start the game
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			SnakeGame.newGame();
			panel.repaint();
			return;
		}
		
		if (SnakeGame.getGameStage() == SnakeGame.GAME_OVER){
			snake.reset();
			Score.resetScore();
			panel.repaint();
			
			//Need to start the timer and start the game again
			SnakeGame.newGame();
			SnakeGame.setGameStage(SnakeGame.DURING_GAME);
			panel.repaint();
			return;
		}

		if (SnakeGame.getGameStage() == SnakeGame.PAUSE_MENU){
			SnakeGame.displayOptionsGUI();
			panel.repaint();
			return;
		}

		
		if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
			SoundPlayer.playSnakeMove();
			snake.snakeDown();
		}
		if (ev.getKeyCode() == KeyEvent.VK_UP) {
			SoundPlayer.playSnakeMove();
			snake.snakeUp();
		}
		if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
			SoundPlayer.playSnakeMove();
			snake.snakeLeft();
		}
		if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
			SoundPlayer.playSnakeMove();
			snake.snakeRight();
		}
		if (ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
			SnakeGame.displayOptionsGUI();
		}

	}


	@Override
	public void keyReleased(KeyEvent ev) {
		//We don't care about keyReleased events, but are required to implement this method anyway.		
	}


	@Override
	public void keyTyped(KeyEvent ev){
		//keyTyped events are for user typing letters on the keyboard, anything that makes a character display on the screen
		char keyPressed = ev.getKeyChar();
		char q = 'q';
		char m = 'm';
		char w = 'w';

		if( keyPressed == q){
			System.exit(0);    //quit if user presses the q key.
		}
		if( keyPressed == w){
			SnakeGame.setWarpWallsOn(true);
		}


	}

}
