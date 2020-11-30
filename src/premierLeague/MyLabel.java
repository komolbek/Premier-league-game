package premierLeague;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyLabel extends JLabel {
	
	public MyLabel(String text, int width, int height) {
		
		Border border = BorderFactory.createLineBorder(Color.green, 3); // Create border
			
		// JLabel = a GUI component to display area for text, image or both
		
		this.setText(text); // Set text of the this
		this.setForeground(Color.cyan);
		
		// this.setHorizontalTextPosition(Jthis.RIGHT); // Set text left, right, centre of icon
		// this.setVerticalTextPosition(Jthis.CENTER); // Set text top, bottom, centre of icon
		this.setFont(new Font("MV Voli", Font.PLAIN, 20)); // Set font of text and size
		this.setBackground(Color.darkGray); // Set background colour
		this.setOpaque(true); // Display background colour
		this.setBorder(border); // Set border 
		this.setHorizontalAlignment(JLabel.CENTER); // Set alignment
		this.setVerticalAlignment(JLabel.CENTER); // Set alignment
		this.setBounds(0, 0, width, height); // Set x, y and  width and  height
	}
}
