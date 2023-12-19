package simon;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class SimonButton extends JPanel implements MouseListener{
	private Color onColor;
	private Color offColor;
	private ColorListener colorListener;
	private int noteNumber;
	private SynthController synth;

	public SimonButton(Color onColor, Color offColor, ColorListener colorListener, int noteNumber, SynthController synth) {
		this.addMouseListener(this);
		this.onColor = onColor;
		this.offColor = offColor;
		this.setBackground(offColor);
		this.colorListener = colorListener;
		this.noteNumber = noteNumber;
		this.synth = synth;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		on();
		colorListener.onColorPressed(onColor);
	}

	public void mouseReleased(MouseEvent e) {
		off();
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void on() {
		this.setBackground(onColor);
		synth.setNoteNumber(noteNumber);
		synth.startPlayingNote();
	}
	
	public void off() {
		this.setBackground(offColor);
		synth.stopPlayingNote();
	}
}
