package simon;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;

public class Display {

	private JFrame frame;
	private JButton redButton;
	private JButton blueButton;
	private JButton yellowButton;
	private JButton greenButton;

	public Display() {
		this.blueButton = new JButton();
		this.yellowButton = new JButton();
		this.redButton = new JButton();
		this.greenButton = new JButton();
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(2, 2));
		redButton.setOpaque(true);
		blueButton.setOpaque(true);
		greenButton.setOpaque(true);
		yellowButton.setOpaque(true);
		frame.add(yellowButton);
		frame.add(blueButton);
		frame.add(redButton);
		frame.add(greenButton);
		Color red = Color.RED;
		Color yellow = Color.YELLOW;
		Color blue = Color.BLUE;
		Color green = Color.GREEN;

		redButton.setBackground(red);
		yellowButton.setBackground(yellow);
		greenButton.setBackground(green);
		blueButton.setBackground(blue);
		this.frame.setSize(1200, 800);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.frame.setVisible(true);
	}
}
