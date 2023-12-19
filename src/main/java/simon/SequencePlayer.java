package simon;

import java.awt.Color;

public class SequencePlayer extends Thread {

	private Color[] sequence;
	private Display display;
	private Simon simon;

	public SequencePlayer(Color[] sequence, Display display, Simon simon) {
		this.sequence = sequence;
		this.display = display;
		this.simon = simon;
	}

	@Override
	public void run() {

		try {
			for (int i = 0; i < sequence.length; i++) {
				display.on(sequence[i]);
				Thread.sleep(1000);
				display.off(sequence[i]);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
		}

		simon.sequenceRan();
	}
}
