package languageApp;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPage {

	private JFrame frame;
	private JTextField title;
	private JTextField quesText;
	private JTextField ansText;
	Map<Word, Integer> questions;
	int currentQ = 0;
	int score;
	Word currentW;
	JButton btnSend;
	private JTextField truth;
	public TestPage() {
		initialize();
	}

	
	private void initialize() {
		
		Image image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\plus2.png");
		Image image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\dictIcon.png");     
		Image image3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\eclipse-workspace\\languageApp\\bin\\images\\dictIcon.png");     
		Image image4 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\Beni Sına.png");     
		Image image5 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\testDesign.png");     

		Image scaledImage1 = image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image scaledImage2 = image2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image scaledImage3 = image3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		//Image scaledImage4 = image4.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
		
        ImageIcon icon1 = new ImageIcon(scaledImage1);
        ImageIcon icon2 = new ImageIcon(scaledImage2);
        ImageIcon icon3 = new ImageIcon(scaledImage3);
        ImageIcon icon4 = new ImageIcon(image4);
        ImageIcon icon5 = new ImageIcon(image5);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(245, 235, 230));
		frame.getContentPane().setLayout(null);
		
		
		truth = new JTextField();
		truth.setEditable(false);
		truth.setHorizontalAlignment(SwingConstants.CENTER);
		truth.setFont(new Font("Segoe Print", Font.BOLD, 11));
		truth.setBounds(199, 168, 77, 46);
		frame.getContentPane().add(truth);
		truth.setColumns(10);
		truth.setVisible(false);
		
		
		JButton wordButton = new JButton(icon2);
		wordButton.setBackground(new Color(245, 235, 230));
		wordButton.setBounds(377, 517, 60, 60);
		wordButton.setBorder(null);
		wordButton.addActionListener(e -> {
			frame.setVisible(false);
			new myWords();
		});
		JLabel lblNewLabel = new JLabel(icon4);
		
		
		JButton btnBack = new JButton("Testi Bitir");
		JButton btnKolay = new JButton("Kolay");
		JButton btnOrta = new JButton("Orta");
		JButton btnZor = new JButton("Zor");

		btnBack.setVisible(false);
		
		btnSend = new JButton("İleri");
		btnSend.setBackground(new Color(255, 255, 255));
		btnSend.setBounds(297, 275, 77, 46);
		btnSend.setOpaque(false);
		btnSend.setVisible(false);
		btnSend.addActionListener(e -> {
			if(btnSend.getText().equals("İleri")) {
				String answer = ansText.getText();
				answer = answer.toLowerCase().strip();
				if(answer.equals(currentW.turDef.toLowerCase().strip())) {
					for(Word word: Start.allWords.keySet()) {
						if(word.turDef.equals(answer)) {
							truth.setVisible(true);
							truth.setText("Doğru!!");
							Timer timer = new Timer(1000, new ActionListener() {
			                    public void actionPerformed(ActionEvent e) {
			                        truth.setVisible(false);
			                        if(Start.allWords.get(word) <= 60) {
										Start.allWords.put(word, Start.allWords.get(word) + 30);
										score += 1;
										System.out.println(word + " " + Start.allWords.get(word));
									}
			                    }
			                });
			                timer.setRepeats(false); // Ensure the timer only fires once
			                timer.start();
			                break;
						}
					}
				}
				else {
					for(Word word: Start.allWords.keySet()) {
						if(word.turDef.equals(currentW.turDef)) {
							truth.setVisible(true);
							truth.setText(word.turDef);
							Timer timer = new Timer(2000, new ActionListener() {
			                    public void actionPerformed(ActionEvent e) {
			                        truth.setVisible(false);
			                        if(Start.allWords.get(word) > 0) {
										Start.allWords.put(word, Start.allWords.get(word) - 30);
										System.out.println(word + " " + Start.allWords.get(word));
									}
			                    }
			                });
			                timer.setRepeats(false); // Ensure the timer only fires once
			                timer.start();
			                break;
						}
					}
				}
				currentQ += 1;
				ansText.setText("");
				askQuestion(questions, currentQ);
			}
			else if(btnSend.getText().equals("Bitir")){
	            try {
	            	FileWriter fileWriter = new FileWriter("src/words.txt");
	                fileWriter.close();
					
					String endtext = "";
		            for(Word word: Start.allWords.keySet()) {
		            	endtext += word.engDef + "-" + word.turDef + "-" + word.secDef + "-" + Start.allWords.get(word) + "\n";
		            }
		            FileWriter writer = new FileWriter("src/words.txt", true);
		            writer.write(endtext);
		            writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} // The 'true' parameter indicates append mode
        		JOptionPane.showInternalMessageDialog(null, "Skorunuz: " + score);
        		score = 0;
        		btnSend.setText("İleri");
	            lblNewLabel.setIcon(icon4);
				btnKolay.setVisible(true);
				btnOrta.setVisible(true);
				btnZor.setVisible(true);
				title.setVisible(true);
				btnBack.setVisible(false);
				btnSend.setVisible(false);
				quesText.setVisible(false);
				ansText.setVisible(false);
			}
		});
		
		quesText = new JTextField();
		quesText.setEditable(false);
		quesText.setFont(new Font("Segoe Print", Font.BOLD, 14));
		quesText.setHorizontalAlignment(SwingConstants.CENTER);
		quesText.setBounds(101, 137, 262, 39);
		quesText.setBackground(new Color(0, 0, 0));
		quesText.setBorder(null);
		quesText.setOpaque(false);
		quesText.setVisible(false);
		

		
		ansText = new JTextField();
		ansText.setHorizontalAlignment(SwingConstants.CENTER);
		ansText.setText("");
		ansText.setFont(new Font("Segoe Print", Font.BOLD, 16));
		ansText.setBounds(101, 213, 262, 39);
		frame.getContentPane().add(ansText);
		ansText.setColumns(10);
		ansText.setBackground(new Color(0, 0, 0));
		ansText.setOpaque(false);
		ansText.setBorder(null);
		ansText.setVisible(false);
		frame.getContentPane().add(quesText);
		quesText.setColumns(10);
		btnSend.setBorder(null);
		
		frame.getContentPane().add(btnSend);
		btnBack.setFont(new Font("Segoe Print", Font.BOLD, 14));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setBounds(41, 10, 111, 52);
		btnBack.setOpaque(false);
		btnBack.setBorder(null);
		btnBack.addActionListener(e -> {
			score = 0;
			lblNewLabel.setIcon(icon4);
			btnKolay.setVisible(true);
			btnOrta.setVisible(true);
			btnZor.setVisible(true);
			title.setVisible(true);
			btnBack.setVisible(false);
			btnSend.setVisible(false);
			quesText.setVisible(false);
			ansText.setVisible(false);
			btnSend.setText("İleri");
		});
		frame.getContentPane().add(btnBack);
		
		btnKolay.setFont(new Font("Segoe Print", Font.BOLD, 19));
		btnKolay.setBounds(189, 137, 98, 46);
		btnKolay.setBackground(new Color(0, 0, 0));
		btnKolay.setOpaque(false);
		btnKolay.setBorder(null);
		btnKolay.addActionListener(e -> {
			lblNewLabel.setIcon(icon5);
			btnKolay.setVisible(false);
			btnOrta.setVisible(false);
			btnZor.setVisible(false);
			title.setVisible(false);
			btnBack.setVisible(true);
			btnSend.setVisible(true);
			quesText.setVisible(true);
			ansText.setVisible(true);
			questions = createTest("K");
			askQuestion(questions, currentQ);
		});
		frame.getContentPane().add(btnKolay);
		
		btnZor.setOpaque(false);
		btnZor.setFont(new Font("Segoe Print", Font.BOLD, 19));
		btnZor.setBorder(null);
		btnZor.setBackground(Color.BLACK);
		btnZor.setBounds(189, 289, 98, 46);
		btnZor.addActionListener(e -> {
			lblNewLabel.setIcon(icon5);
			btnKolay.setVisible(false);
			btnOrta.setVisible(false);
			btnZor.setVisible(false);
			title.setVisible(false);
			btnBack.setVisible(true);
			btnSend.setVisible(true);
			quesText.setVisible(true);
			ansText.setVisible(true);
			questions = createTest("H");
			askQuestion(questions, currentQ);
		});
		frame.getContentPane().add(btnZor);
		
		btnOrta.setOpaque(false);
		btnOrta.setFont(new Font("Segoe Print", Font.BOLD, 19));
		btnOrta.setBorder(null);
		btnOrta.setBackground(Color.BLACK);
		btnOrta.setBounds(189, 213, 98, 46);
		btnOrta.addActionListener(e -> {
			lblNewLabel.setIcon(icon5);
			btnKolay.setVisible(false);
			btnOrta.setVisible(false);
			btnZor.setVisible(false);
			title.setVisible(false);
			btnBack.setVisible(true);
			btnSend.setVisible(true);
			quesText.setVisible(true);
			ansText.setVisible(true);
			questions = createTest("M");
			askQuestion(questions, currentQ);
	    });
		frame.getContentPane().add(btnOrta);
		
		title = new JTextField();
		title.setFont(new Font("Segoe Print", Font.BOLD, 22));
		title.setEditable(false);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setText("Sına Beni");
		title.setBounds(126, 22, 223, 52);
		frame.getContentPane().add(title);
		title.setColumns(10);
		title.setOpaque(false);
		title.setBorder(null);
		frame.getContentPane().add(wordButton);
		
		JButton plusButton = new JButton(icon1);
		plusButton.setBorderPainted(false);
		plusButton.setBackground(new Color(245, 235, 230));
		plusButton.setBounds(211, 517, 60, 60);
		plusButton.setBorder(null);
		plusButton.addActionListener(e -> {
			frame.setVisible(false);
			new addWord();
		});
		frame.getContentPane().add(plusButton);
		
		lblNewLabel.setBounds(0, 0, 469, 577);
		frame.getContentPane().add(lblNewLabel);
		

		frame.setBounds(550, 125, 483, 614);
		frame.setVisible(true);
	}
	
	private Map<Word, Integer> createTest(String type) {
		score = 0;
		Map<Word, Integer> questions = new HashMap<>();
		ArrayList<Word> easy = new ArrayList<>();
		ArrayList<Word> med = new ArrayList<>();
		ArrayList<Word> hard = new ArrayList<>();
		
		for(Word word: Start.allWords.keySet()) {
			if(Start.allWords.get(word) < 30) {
				hard.add(word);
			}
			else if(Start.allWords.get(word) >= 30 && Start.allWords.get(word) <= 60) {
				med.add(word);
			}
			else {
				easy.add(word);
			}
		}
		Collections.shuffle(easy);
		Collections.shuffle(med);
		Collections.shuffle(hard);
		if(type.equals("K")) {
			for(int i = 0; i < Math.min(7, easy.size()); i++) {
				questions.put(easy.get(i), Start.allWords.get(easy.get(i)));
			}
			for(int i = 0; i < Math.min(3, med.size()); i++) {
				questions.put(med.get(i), Start.allWords.get(med.get(i)));
			}
		}
		else if(type.equals("M")) {
			for(int i = 0; i < Math.min(3, easy.size()); i++) {
				questions.put(easy.get(i), Start.allWords.get(easy.get(i)));
			}
			for(int i = 0; i < Math.min(5, med.size()); i++) {
				questions.put(med.get(i), Start.allWords.get(med.get(i)));
			}
			for(int i = 0; i < Math.min(2, hard.size()); i++) {
				questions.put(hard.get(i), Start.allWords.get(hard.get(i)));
			}
		}
		else if(type.equals("H")) {
			for(int i = 0; i < Math.min(3, med.size()); i++) {
				questions.put(med.get(i), Start.allWords.get(med.get(i)));
			}
			for(int i = 0; i < Math.min(7, hard.size()); i++) {
				questions.put(hard.get(i), Start.allWords.get(hard.get(i)));
			}
		}
		for(Word word: questions.keySet()) {
			System.out.println(word.turDef);
		}
		System.out.println();
		return questions;
	}
	private void askQuestion(Map<Word, Integer> questions, int i) {
		if(i <= questions.keySet().size()-1) {
			ArrayList<Word> valuesList = new ArrayList<>(questions.keySet());
			//Random random = new Random();
	        //int randomValue = random.nextInt(2); 
	        quesText.setText("\"" + valuesList.get(i).engDef + "\" kelimesinin anlamı nedir?");
			currentW = new Word(valuesList.get(i).engDef, valuesList.get(i).turDef, valuesList.get(i).secDef);
			if(i == questions.keySet().size()-1) {
				btnSend.setText("Bitir");
			}
		}
	}
}
