package casey;

import java.awt.*;
import java.util.LinkedList;
import java.util.Enumeration;

import javax.swing.*;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint
	
	private Snake snake;
	private Kibble kibble;
	private Score score;

	Font abadi = new Font("Abadi MT Condensed Extra Bold.ttf", Font.BOLD, 24);

	
	DrawSnakeGamePanel(Snake s, Kibble k, Score sc){
		this.snake = s;
		this.kibble = k;
		this.score = sc;
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         * 5. pause game
         */

        gameStage = SnakeGame.getGameStage();
        
        switch (gameStage) {
			case 1: {
				displayInstructions(g);
				break;
			}
			case 2: {
				displayGame(g);
				break;
			}
			case 3: {
				displayGameOver(g);
				break;
			}
			case 4: {
				displayGameWon(g);
				break;
			}
			case 5: {
				displayOptionsGUI();
				break;
			}
		}
    }

	private void displayGameWon(Graphics g) {
		// Could never get game to finish when won
		// snake never eats last kibble
		g.clearRect(0,0,501,501);
		g.fillRect(0, 0, 501, 501);
		g.drawString("YOU WON SNAKE!!!", 150, 150);
		
	}
	private void displayGameOver(Graphics g) {

		//display game grid for background
		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;

		//color for bg and grid
		g.setColor(Color.BLACK);
		g.fillRect(0,0,501,501);
		g.setColor(Color.DARK_GRAY);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){
			g.drawLine(x, 0, x, maxY);
		}

		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.setFont(abadi);

		g2.drawString("GAME OVER", 177, 100);

		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g2.drawString("SCORE = " + textScore, 80, 150);
		g2.drawString("HIGH SCORE = " + textHighScore, 80, 200);
		g2.drawString(newHighScore, 80, 250);
		
		g2.drawString("press s to play again", 80, 300);
		g2.drawString("Press w to turn on warp walls.",80, 350);
		g2.drawString("Press q to quit the game",80,400);
	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);

	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		
		g.clearRect(0, 0, maxX, maxY);
		g.fillRect(0, 0, maxX, maxY);
		g.setColor(Color.BLACK);

		g.setColor(Color.DARK_GRAY);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		}
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in cyan and as a circle
		g.setColor(Color.CYAN);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillOval(x + 10, y + 10, SnakeGame.squareSize - 20, SnakeGame.squareSize - 20);
		
	}

	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();
		
		//Draw head in dark red as a circle
		g.setColor(Color.RED);
		Point head = coordinates.pop();
		g.fillOval((int)head.getX()+5, (int)head.getY()+5, SnakeGame.squareSize-10, SnakeGame.squareSize-10);
		
		//Draw rest of snake in orange as smaller squares
		g.setColor(Color.ORANGE);
		for (Point p : coordinates) {
			g.fillRect((int)p.getX()+10, (int)p.getY()+10, SnakeGame.squareSize-20, SnakeGame.squareSize-20);
		}

	}

	private void displayInstructions(Graphics g) {
		//display game grid for background
		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;

		//color for bg and grid
		g.setColor(Color.BLACK);
		g.fillRect(0,0,501,501);
		g.setColor(Color.DARK_GRAY);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){
			g.drawLine(x, 0, x, maxY);
		}

		//color for text
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.setFont(abadi);

		g2.drawString("SNAKE", 210, 100);
        g2.drawString("Press s to begin",100,200);
		g2.drawString("Press w to turn on warp walls", 100, 250);
        g2.drawString("Press q to quit the game",100,300);
	}

	//attempt to display the options GUI once again
	protected static void displayOptionsGUI(){
		SnakeGame.optionsPanel.setVisible(true);
		SnakeGame.optionsPanel.setFocusable(true);
		SnakeGame.optionsPanel.requestFocusInWindow();
	}
}

