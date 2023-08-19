package languageApp;

import java.awt.EventQueue;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Start {
 
	private JFrame frame;
	private JTextField txtMyWords;
	private JLabel buttonBack;
	public static Map<Word, Integer> allWords = new HashMap<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Start() {
		initialize();
	}

	private void initialize() {
		
        try {
	        File file = new File("src/words.txt");
	        Scanner scanner = new Scanner(file);
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            String[] parts = line.split("-");
	            if(parts.length == 4) {
	            	Word word = new Word(parts[0], parts[1], parts[2]);
	            	allWords.put(word, Integer.parseInt(parts[3]));
	            }
	            else if(parts.length == 3) {
	            	Word word = new Word(parts[0], parts[1], "");
	            	allWords.put(word, Integer.parseInt(parts[2]));
	            }
	        }
	        scanner.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
        
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 235, 230));
		frame.getContentPane().setLayout(null);
		
		Image image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\startPage Design.png");
		Image image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\eclipse-workspace\\languageApp\\bin\\images\\startButton.png");
		
		Image scaledImage2 = image2.getScaledInstance(115, 42, Image.SCALE_SMOOTH);
		
		ImageIcon icon1 = new ImageIcon(image1);
		ImageIcon icon2 = new ImageIcon(scaledImage2);
		
		
		txtMyWords = new JTextField();
		txtMyWords.setBackground(new Color(255, 255, 255));
		txtMyWords.setBorder(null);
		txtMyWords.setEditable(false);
		txtMyWords.setFont(new Font("Segoe Print", Font.BOLD, 27));
		txtMyWords.setHorizontalAlignment(SwingConstants.CENTER);
		txtMyWords.setText("MY WORDS");
		txtMyWords.setBounds(146, 132, 182, 31);
		frame.getContentPane().add(txtMyWords);
		txtMyWords.setColumns(10);
		
		buttonBack = new JLabel(icon2);
		buttonBack.setBounds(173, 427, 115, 42);
		
		JLabel lblNewLabel = new JLabel(icon1);
		lblNewLabel.setBounds(0, 0, 469, 577);
		

		
		JButton startButton = new JButton("Başla");
		startButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
		startButton.setBackground(new Color(255, 255, 255));
		startButton.setBounds(173, 427, 115, 42);
		startButton.setBorderPainted(false);
		startButton.setOpaque(false);
		startButton.addActionListener(e -> {
			frame.setVisible(false);
			new addWord();
		});
		frame.getContentPane().add(startButton);

		frame.getContentPane().add(buttonBack);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(550, 125, 483, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
