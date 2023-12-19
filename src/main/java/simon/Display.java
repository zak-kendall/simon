package simon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.sound.midi.Synthesizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

public class Display {

	private JFrame frame;
	private SimonButton redButton;
	private SimonButton blueButton;
	private SimonButton yellowButton;
	private SimonButton greenButton;
	private JButton startButton;
	private SynthController synth;
	private Simon simon;

	public Display(final Simon simon) {
		synth = new SynthController();
		synth.setInstrument("Banjo");
		this.simon = simon;
		this.blueButton = new SimonButton(Color.blue, new Color(0.5f, 0.5f, 1.0f), simon, 60, synth);
		this.yellowButton = new SimonButton(Color.yellow, new Color(1.0f, 1.0f, 0.7f), simon, 63, synth);
		this.redButton = new SimonButton(Color.red, new Color(1.0f, 0.5f, 0.5f), simon, 66, synth);
		this.greenButton = new SimonButton(Color.green, new Color(0.5f, 1.0f, 0.5f), simon, 70, synth);
		this.startButton = new JButton();
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(3, 3));

		// frame.add(yellowButton);
		frame.add(yellowButton);
		frame.add(new JPanel());
		frame.add(blueButton);
		frame.add(new JPanel());
		frame.add(startButton);
		frame.add(new JPanel());
		frame.add(redButton);
		frame.add(new JPanel());
		frame.add(greenButton);

		this.frame.setSize(1200, 800);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				simon.startGame();

			}
		});

		this.frame.setVisible(true);
	}

	public void on(Color color) {
		System.out.println(color);
		if (color.equals(Color.RED)) {
			redButton.on();
		} else if (color.equals(Color.BLUE)) {
			blueButton.on();
		} else if (color.equals(Color.GREEN)) {
			greenButton.on();
		} else if (color.equals(Color.YELLOW)) {
			yellowButton.on();
		}
	}

	public void off(Color color) {
		if (color.equals(Color.RED)) {
			redButton.off();
		} else if (color.equals(Color.BLUE)) {
			blueButton.off();
		} else if (color.equals(Color.GREEN)) {
			greenButton.off();
		} else if (color.equals(Color.YELLOW)) {
			yellowButton.off();
		}
	}

	
	public void gameStateChanged(GameState gameState) {
		if (gameState == GameState.NOT_STARTED) {
			startButton.setText("Click to start");
		}
		if (gameState == GameState.SIMON_TURN) {
			startButton.setText("Watch the sequence");
		}
		if (gameState == GameState.PLAYERS_TURN) {
			startButton.setText("Your turn");
		}
		if (gameState == GameState.GAME_OVER) {
			startButton.setText("Game over, click to play again");
		
	}
	}
}
