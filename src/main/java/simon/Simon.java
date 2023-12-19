package simon;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.sound.midi.Synthesizer;

public class Simon implements ColorListener {

	public static void main(String[] args) {
		Simon simon = new Simon();
	}
	
	private Color[] sequence;
	private int playerGuessIndex;
	private Synthesizer synth;
	private Display display;
	private GameState gameState;
	
	public Simon() {
		this.display = new Display(this);
		this.gameState = GameState.NOT_STARTED;
		display.gameStateChanged(gameState);
		this.sequence = new Color[0];
		//sequenceRan();
	}
	
	public void startGame() {
		if (gameState == GameState.NOT_STARTED || gameState == GameState.GAME_OVER) {
			sequence = new Color[1];
			sequence[0] = getRandomColor();
			gameState = GameState.SIMON_TURN;
			display.gameStateChanged(gameState);
			playSequence();
			
		}
	}

	public void onColorPressed(Color color) {
		if (gameState == gameState.PLAYERS_TURN) {
			// Is it the correct guess?
			if (color.equals(sequence[playerGuessIndex])) {
				//TODO: Do we need to wait for more guesses?
				if (playerGuessIndex < sequence.length - 1) {
					playerGuessIndex++;
				}else {
					//Add to the sequence
					Color[] temp = new Color[sequence.length + 1];
					for (int i = 0; i < sequence.length; i++) {
						temp[i] = sequence[i];
					}
					sequence = temp;
					sequence[sequence.length - 1] = getRandomColor();
					//Simons turn
					gameState = GameState.SIMON_TURN;
					display.gameStateChanged(gameState);
					//play the sequence
					playSequence();
					
				}
			}else {
				//incorrect guess set gameState to game over
				gameState = GameState.GAME_OVER;
				display.gameStateChanged(gameState);
			}
		}
		
	}

	private Color getRandomColor() {
		Color[] colors = new Color[] {
				Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW
		};
		int index = new Random().nextInt(colors.length);
		return colors[index];
	}
	
	private void playSequence() {
		SequencePlayer sequencePlayer = new SequencePlayer(sequence, display, this);
		sequencePlayer.start();
	}
	
	public void sequenceRan() {
		playerGuessIndex = 0;
		gameState = GameState.PLAYERS_TURN;
		display.gameStateChanged(gameState);
	}
}
