import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;


/**
 * Custom reusable JButton class.
 * Used in order to avoid some 
 * boilerplate code 
 */

public class MyButton extends JButton {
	
	public MyButton(String title, ActionListener listener) {
		this.setText(title);
		this.addActionListener(listener);
		this.setFont(new Font("Comic sans", Font.BOLD, 15));
	}
	
	public MyButton(String title, ActionListener listener, Color backgroundColor) {
		this.setText(title);
		this.addActionListener(listener);
		this.setFont(new Font("Comic sans", Font.BOLD, 15));
		this.setOpaque(true);
		this.setBorderPainted(false);
		this.setBackground(backgroundColor);
	}
	
	public MyButton(String title, int x, ActionListener listener) {
		this.setSize(130, 50);
		this.setLocation(x, 420);
		this.setOpaque(false);
		this.setVisible(true);
		this.setText(title);
		this.addActionListener(listener);
		this.setFont(new Font("Comic sans", Font.BOLD, 15));
		/* myButton.addActionListener(e -> System.out.println("Hii")); // lambda */
	}
}
