package languageApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.GroupLayout.Alignment;
import java.util.*;
import javax.swing.table.*;

public class myWords {

	private JFrame frame;
	private JTextField txtMyWords;
	private JButton addButton;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTextField textField;

	public myWords() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 235, 230));
		frame.setBounds(550, 125, 483, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Image image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\plus2.png");
		Image image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\essayIcon.png");     
		Image image3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\eclipse-workspace\\languageApp\\bin\\images\\dictIcon.png");     
		Image image4 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deniz\\Desktop\\myWords APP\\images\\myWords Design.png");     

		Image scaledImage1 = image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image scaledImage2 = image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image scaledImage3 = image3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		//Image scaledImage4 = image4.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
		
        ImageIcon icon1 = new ImageIcon(scaledImage1);
        ImageIcon icon2 = new ImageIcon(scaledImage2);
        ImageIcon icon3 = new ImageIcon(scaledImage3);
        ImageIcon icon4 = new ImageIcon(image4);
		
        
        
		String[] data = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
		
		
		
		Border border = BorderFactory.createEtchedBorder();
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBorder(null);
		textField.setVisible(false);
		
		JRadioButton hideButton = new JRadioButton("Sakla");
		hideButton.setFont(new Font("Segoe Print", Font.BOLD, 12));
		hideButton.setBounds(57, 456, 75, 21);
		hideButton.setBackground(new Color(0, 0, 0));
		hideButton.setOpaque(false);
		hideButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	textField.setVisible(true);
                }
                else {
                	textField.setVisible(false);
                }
            }
        });
		frame.getContentPane().add(hideButton);
		textField.setBackground(new Color(203, 108, 230));
		textField.setBounds(67, 100, 110, 319);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(67, 100, 342, 319);
		frame.getContentPane().add(scrollPane_1);
		
		DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Türkçe");
        model.addColumn("İngilizce");
        model.addColumn("İkinci Hal");

        
		table = new JTable();
		table.setBackground(new Color(245, 235, 230));
		table.setFont(new Font("Segoe Print", Font.BOLD, 12));
        for(Word element: Start.allWords.keySet()) {
        	model.addRow(new Object[] {element.turDef, element.engDef, element.secDef});
        }
        table.setRowHeight(25);
		table.setModel(model);
		scrollPane_1.setViewportView(table);
		
		Word[] allWords = Start.allWords.keySet().toArray(new Word[Start.allWords.size()]);

		txtMyWords = new JTextField();
		txtMyWords.setFont(new Font("Segoe Print", Font.BOLD, 21));
		txtMyWords.setEditable(false);
		txtMyWords.setHorizontalAlignment(SwingConstants.CENTER);
		txtMyWords.setText("Kelimelerim");
		txtMyWords.setOpaque(false);
		txtMyWords.setBorder(null);
		txtMyWords.setBounds(164, 22, 143, 36);
		frame.getContentPane().add(txtMyWords);
		txtMyWords.setColumns(10);
		
		JButton testButton = new JButton(icon2);
		testButton.setBorderPainted(false);
		testButton.setBackground(new Color(245, 235, 230));
		testButton.setBounds(49, 517, 60, 60);
		testButton.addActionListener(e -> {
			frame.setVisible(false);
			new TestPage();
		});
		
		frame.getContentPane().add(testButton);
		
		addButton = new JButton(icon1);
		addButton.setBorderPainted(false);
		addButton.setBackground(new Color(245, 235, 230));
		addButton.setBounds(203, 517, 60, 60);
		addButton.addActionListener(e -> {
			frame.setVisible(false);
			new addWord();
		});
		
		
		JButton delButton = new JButton("Sil");
		delButton.setFont(new Font("Segoe Print", Font.BOLD, 12));
		delButton.setBackground(new Color(0, 0, 0));
		delButton.setBorder(null);
		delButton.setOpaque(false);
		delButton.setBounds(358, 448, 75, 36);
		delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	String dataCol1 = model.getValueAt(selectedRow, 0).toString();
                	String dataCol2 = model.getValueAt(selectedRow, 1).toString();
                	String dataCol3 = model.getValueAt(selectedRow, 2).toString();
                    
                    for(Word word: Start.allWords.keySet()) {
                    	if(word.turDef.equals(dataCol1) && word.secDef.equals(dataCol3) && word.engDef.equals(dataCol2)) {
                    		Start.allWords.remove(word);
                    		break;
                    	}
                    }
                    try {
            	        File file = new File("src/words.txt");
            	        Scanner scanner = new Scanner(file);
            	        String endText = "";        	           
            	        while (scanner.hasNextLine()) {
            	            String line = scanner.nextLine();
            	            String[] parts = line.split("-");
            	            if(!parts[0].equals(dataCol2)) {
            	            	endText += line + "\n";
            	            }
            	        }
            	        FileWriter writer = new FileWriter("src/words.txt", false); // The 'false' parameter indicates write mode
        	            writer.write("");
        	            writer.close();
        	            
        	            FileWriter writer2 = new FileWriter("src/words.txt", true);
        	            writer2.write(endText);
        	            writer2.close();
            	        scanner.close();
            	    } catch (FileNotFoundException e1) {
            	        e1.printStackTrace();
            	    } catch (IOException e1) {
						e1.printStackTrace();
					}
                	model.removeRow(selectedRow);
                }
            }
        });
		
		frame.getContentPane().add(delButton);
		
		frame.getContentPane().add(addButton);
		JLabel lblNewLabel = new JLabel(icon4);
		lblNewLabel.setBounds(0, 0, 469, 577);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}
}
