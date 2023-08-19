package languageApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

public class addWord {

	private JFrame frame;
	private JTextField txtKelimeEkle;
	private JButton wordButton;
	private JTextField engWord;
	private JTextField turkDef;
	private JButton EkleButton;
	private JTextField secWord;

	public addWord() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 235, 230));
		frame.getContentPane().setLayout(null);
		

		
		//Image image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\eclipse-workspace\\languageApp\\bin\\images\\plus2.png");
		Image image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\essayIcon.png");     
		Image image3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\dictIcon.png");     
		Image image4 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\addWord Design.png");
		Image image5 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\eclipse-workspace\\languageApp\\bin\\images\\startButton - Kopya.png");
		Image image6 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\KELİME EKLE.png");
		
		
		//Image scaledImage1 = image1.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		Image scaledImage2 = image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image scaledImage3 = image3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image scaledImage4 = image4.getScaledInstance(483, 600, Image.SCALE_SMOOTH);
		Image scaledImage5 = image5.getScaledInstance(107, 42, Image.SCALE_SMOOTH);
		Image scaledImage6 = image6.getScaledInstance(483, 600, Image.SCALE_SMOOTH);
		
        //ImageIcon icon1 = new ImageIcon(scaledImage1);
        ImageIcon icon2 = new ImageIcon(scaledImage2);
        ImageIcon icon3 = new ImageIcon(scaledImage3);
		ImageIcon icon4 = new ImageIcon(scaledImage4);
		ImageIcon icon5 = new ImageIcon(scaledImage5);
		ImageIcon icon6 = new ImageIcon(scaledImage6);
		
		
		JLabel background = new JLabel(icon6);
		background.setText(" ");
		background.setBounds(0, 0, 469, 577);
		
		wordButton = new JButton(icon3);
		wordButton.setBorderPainted(false);
		wordButton.setBackground(new Color(245, 235, 230));
		wordButton.setBorderPainted(false);
		wordButton.setBounds(356, 517, 60, 60);
		wordButton.addActionListener(e -> {
			frame.setVisible(false);
			new myWords();
		});
		
		JRadioButton verbToggle = new JRadioButton("Bu bir fiil");
		verbToggle.setFont(new Font("Segoe Print", Font.BOLD, 12));
		verbToggle.setBounds(199, 323, 103, 21);
		verbToggle.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    background.setIcon(icon6);
                    secWord.setVisible(false);
                }
                else {
                	background.setIcon(icon4);
                	secWord.setVisible(true);
                }
            }
        });
		
		EkleButton = new JButton("Ekle");
		EkleButton.setFont(new Font("Segoe Print", Font.BOLD, 14));
		EkleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String turk = turkDef.getText();
				String eng = engWord.getText();
				String sec = secWord.getText();
				if(!(turk.equals("Türkçe anlamını giriniz:") || turk.equals("") 
						|| eng.equals("İngilizce kelimeyi giriniz:") || eng.equals(""))) {
					if(verbToggle.isSelected() && !(sec.equals("Kelimenin ikinci halini giriniz:") || sec.equals(""))) {
						try {
				            FileWriter writer = new FileWriter("src/words.txt", true); // The 'true' parameter indicates append mode
				            
				            Word word = new Word(eng, turk, sec);
				            Boolean found = false;
				            for(Word element: Start.allWords.keySet()) {
				            	if(element.getTurDef().equals(turk) && element.getEngDef().equals(eng) && element.getSecDef().equals(sec)) {
					        		JOptionPane.showInternalMessageDialog(null, "Bu kelime zaten var!");
					        		found = true;
					        		break;
				            	}
				            }
				            if(!found) {
				            	Start.allWords.put(word, 0);
				            	writer.write("\n" + eng + "-" + turk + "-" + sec + "-" + 0);
				        		//JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE); 
				        		JOptionPane.showInternalMessageDialog(null, "Kelime eklendi!");
					            writer.close();
				            }
				        } catch (IOException e1) {
				            e1.printStackTrace();
				        }	
					}
					else if(!verbToggle.isSelected()) {
						try {
				            FileWriter writer = new FileWriter("src/words.txt", true); // The 'true' parameter indicates append mode
				            
				            Word word = new Word(eng, turk, "");
				            Boolean found = false;
				            for(Word element: Start.allWords.keySet()) {
				            	if(element.getTurDef().equals(turk) && element.getEngDef().equals(eng) && element.getSecDef().equals(sec)) {
					        		JOptionPane.showInternalMessageDialog(null, "Bu kelime zaten var!");
					        		found = true;
					        		break;
				            	}
				            }
				            if(!found) {
				            	Start.allWords.put(word, 0);
				            	writer.write("\n" + eng + "-" + turk + "-" + 0);
				        		//JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE); 
				        		JOptionPane.showInternalMessageDialog(null, "Kelime eklendi!");
					            writer.close();
				            }
				        } catch (IOException e1) {
				            e1.printStackTrace();
				        }	
					}
				}
			}
		});

		
		
		turkDef = new JTextField();
		turkDef.setText("Türkçe anlamını giriniz:");
		turkDef.setHorizontalAlignment(SwingConstants.CENTER);
		turkDef.setForeground(new Color(30, 30, 30));
		turkDef.setFont(new Font("Segoe Print", Font.BOLD, 13));
		turkDef.setColumns(10);
		turkDef.setOpaque(false);
		turkDef.setBorder(null);
		turkDef.setBounds(114, 201, 251, 25);
		turkDef.addFocusListener(new FocusListener() {
			// placeholder text
            public void focusGained(FocusEvent e) {
                if (turkDef.getText().equals("Türkçe anlamını giriniz:")) {
                	turkDef.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (turkDef.getText().isEmpty()) {
                	turkDef.setText("Türkçe anlamını giriniz:");
                }
            }
        });
		

		
		engWord = new JTextField();
		engWord.setForeground(new Color(30, 30, 30));
		engWord.setFont(new Font("Segoe Print", Font.BOLD, 13));
		engWord.setHorizontalAlignment(SwingConstants.CENTER);
		engWord.setBounds(114, 129, 251, 25);
		engWord.setBorder(null);
		engWord.setOpaque(false);
		engWord.setColumns(10);
		engWord.setText("İngilizce kelimeyi giriniz:");
		engWord.addFocusListener(new FocusListener() {
			// placeholder text
            public void focusGained(FocusEvent e) {
                if (engWord.getText().equals("İngilizce kelimeyi giriniz:")) {
                	engWord.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (engWord.getText().isEmpty()) {
                	engWord.setText("İngilizce kelimeyi giriniz:");
                }
            }
        });
		
		
		
		JButton testButton = new JButton(icon2);
		testButton.setBackground(new Color(245, 235, 230));
		testButton.setBounds(59, 517, 60, 60);
		testButton.setBorderPainted(false);
		testButton.addActionListener(e -> {
			frame.setVisible(false);
			new TestPage();
		});
		
		secWord = new JTextField();
		secWord.setText("Kelimenin ikinci halini giriniz:");
		secWord.setOpaque(false);
		secWord.setHorizontalAlignment(SwingConstants.CENTER);
		secWord.setForeground(new Color(30, 30, 30));
		secWord.setFont(new Font("Segoe Print", Font.BOLD, 13));
		secWord.setColumns(10);
		secWord.setBorder(null);
		secWord.setVisible(false);
		secWord.setBounds(114, 263, 251, 25);
		secWord.addFocusListener(new FocusListener() {
			// placeholder text
            public void focusGained(FocusEvent e) {
                if (secWord.getText().equals("Kelimenin ikinci halini giriniz:")) {
                	secWord.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (secWord.getText().isEmpty()) {
                	secWord.setText("Kelimenin ikinci halini giriniz:");
                }
            }
        });
		
		JButton eraseButton = new JButton("");
		eraseButton.setBounds(285, 368, 33, 37);
		eraseButton.setBackground(Color.white);
		eraseButton.setOpaque(false);
		eraseButton.setBorder(null);
		eraseButton.addActionListener(e -> {
			turkDef.setText("Türkçe anlamını giriniz:");
			engWord.setText("İngilizce kelimeyi giriniz:");
			secWord.setText("Kelimenin ikinci halini giriniz:");
			
		});
		
		
		frame.getContentPane().add(verbToggle);
		
		frame.getContentPane().add(eraseButton);
		frame.getContentPane().add(secWord);
		frame.getContentPane().add(testButton);
		
		
		
		txtKelimeEkle = new JTextField();
		txtKelimeEkle.setEditable(false);
		txtKelimeEkle.setBackground(new Color(244, 215, 56));
		txtKelimeEkle.setFont(new Font("Segoe Print", Font.BOLD, 21));
		txtKelimeEkle.setHorizontalAlignment(SwingConstants.CENTER);
		txtKelimeEkle.setText("Kelime Ekle");
		txtKelimeEkle.setBounds(169, 32, 133, 33);
		txtKelimeEkle.setBorder(null);
		txtKelimeEkle.setColumns(10);
		frame.getContentPane().add(txtKelimeEkle);
		frame.getContentPane().add(engWord);
		frame.getContentPane().add(turkDef);
		EkleButton.setBounds(162, 368, 97, 42);
		EkleButton.setBackground(new Color(255, 255, 255));
		EkleButton.setOpaque(false);
		EkleButton.setBorder(null);
		frame.getContentPane().add(EkleButton);
		frame.getContentPane().add(wordButton);
		
		
		
		frame.getContentPane().add(background);
		frame.setBounds(550, 125, 483, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
