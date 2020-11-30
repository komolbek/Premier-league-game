package premierLeague;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Main {

	public static void main(String[] args) {
	
		Border border = BorderFactory.createLineBorder(Color.green, 3); // Create border
		
		// JLabel = a GUI component to display area for text, image or both
		
		JLabel label = new JLabel(); // Create a label
		label.setText("My name is Kamol and I like building in Java"); // Set text of the label
		label.setForeground(Color.cyan);
		
		// label.setHorizontalTextPosition(JLabel.RIGHT); // Set text left, right, centre of icon
		// label.setVerticalTextPosition(JLabel.CENTER); // Set text top, bottom, centre of icon
		label.setFont(new Font("MV Voli", Font.PLAIN, 20)); // Set font of text and size
		label.setBackground(Color.darkGray); // Set background colour
		label.setOpaque(true); // Display background colour
		label.setBorder(border); // Set border 
		label.setHorizontalAlignment(JLabel.CENTER); // Set alignment
		label.setVerticalAlignment(JLabel.CENTER); // Set alignment
		label.setBounds(0, 0, 250, 200); // Set x, y and  width and  height

		// MyFrame myFrame = new MyFrame();
		MyFrame myFrame = new MyFrame();
		myFrame.setLayout(null);
		myFrame.add(label);
		myFrame.revalidate();
	}

}
