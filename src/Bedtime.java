import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Bedtime extends JFrame {
	private static final long serialVersionUID = -5104284727596819L;

	JRangeSlider rangeSlider = new JRangeSlider(0, 100, 25, 75,
			JRangeSlider.HORIZONTAL);

	JTextPane textPane = new JTextPane();

	String formatMinsTime(int mins) {
		if (mins < 12 * 60) {
			return mins / 60 + ":" + mins % 60 + "AM";
		}

		return (mins / 60) - 12 + ":" + mins % 60 + "PM";
	}

	String formatMins(int mins) {
		return mins / 60 + ":" + mins % 60;
	}

	public Bedtime() {
		super("Bedtime");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		setSize(700,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		rangeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				update();

			}
		});

		add(rangeSlider);

		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		textPane.setEditable(false);
		textPane.setFont(new Font("Comic Sans", 1, 32));

		add(textPane);
		
		update();

		setVisible(true);

	}

	protected void update() {
		int minsInADay = 24 * 60;

		int wakeupTime = (int) (((float) rangeSlider.getLowValue() / 100) * minsInADay);
		int bedTime = (int) (((float) rangeSlider.getHighValue() / 100) * minsInADay);

		int range = Math.abs(rangeSlider.getLowValue()
				- rangeSlider.getHighValue());

		int minsOfSleep = (int) (((float) (100 - range) / 100) * minsInADay);
		int minsInTheDay = minsInADay - minsOfSleep;
		textPane.setText("Wakeup time: " + formatMinsTime(wakeupTime)
				+ "\nBedtime: " + formatMinsTime(bedTime)
				+ "\nMins in the Day: " + formatMins(minsInTheDay)
				+ "\nMins of sleep  : " + formatMins(minsOfSleep));

	}

	public static void main(String[] args) {
		new Bedtime();
	}
}
