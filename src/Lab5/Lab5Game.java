package Lab5;

import java.util.concurrent.*; 
import java.util.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font;
import java.awt.GridLayout;    
import java.awt.FlowLayout;    
import java.awt.*;    


import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.*;




public class Lab5Game extends JFrame
{
	private int count = 30;
	private String entry = "";
	private boolean gameRunning = false;
	
	private JButton startButton = new JButton("Start");
	private JButton cancelButton = new JButton("Cancel");
	
	final JFrame frame = new JFrame();
	final JLabel label = new JLabel("QUIZ TIME!");
	final JLabel time = new JLabel(Integer.toString(count));
	
	final JPanel mainPanel = new JPanel(new BorderLayout());
	final JPanel gamePanel = new JPanel();
	final JPanel topBar = new JPanel();
	final BoxLayout gameBoxLayout = new BoxLayout(gamePanel, BoxLayout.Y_AXIS);
	final BoxLayout topBarBoxLayout = new BoxLayout(topBar, BoxLayout.Y_AXIS);

	

	final JPanel answerBar = new JPanel(new FlowLayout());
	final JPanel buttonBar = new JPanel(new GridLayout(0, 2, 0, 20));

	
	final JTextField userEntry = new JTextField(10);
	private int numCorrect = 0;
	private int numWrong = 0;
	
	final JLabel correct = new JLabel(Integer.toString(numCorrect));
	final JLabel wrong = new JLabel(Integer.toString(numWrong));
	
	final Random r = new Random();

	Container container;
	
	
	private Timer timer = new Timer(1000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			count--;
			time.setText(Integer.toString(count));
			if (count == 0)
				gameEnd();
			
		}
	});

	
	private static final String[] FULL_NAMES = 
		{
		"Alanine","Arginine", "Asparagine", 
		"Aspartic acid", "Cysteine",
		"Glutamine",  "Glutamic acid",
		"Glycine" ,"Histidine","Isoleucine",
		"Leucine",  "Lysine", "Methionine", 
		"Phenylalanine", "Proline", 
		"Serine","Threonine","Tryptophan", 
		"Tyrosine", "Valine"};

	private static final String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" };
	
	private int amino = r.nextInt(FULL_NAMES.length - 1);
	final JLabel aminoLabel = new JLabel("CLICK START!");

	
	private class startGame implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameStart();
		}
	}
	
	private class endGame implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameEnd();
		}
	}
	
	private class getEntry implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (gameRunning == true)
			{	
				entry = userEntry.getText();
				if (SHORT_NAMES[amino].equals(entry.toUpperCase()))
				{
					numCorrect += 1;
					correct.setText(Integer.toString(numCorrect));
				}
				
				else
				{
					numWrong += 1;
					wrong.setText(Integer.toString(numWrong));
	
				}
				amino = r.nextInt(FULL_NAMES.length - 1);
				aminoLabel.setText(FULL_NAMES[amino]);
			}
			userEntry.setText("");
		}
	}
	
	public Lab5Game()
	{
		frame.setTitle("Amino Acid Game");
		frame.setSize(300,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		initializeGame();

		aminoLabel.setHorizontalAlignment(JTextField.CENTER);
		aminoLabel.setFont(new Font("Arial", Font.BOLD, 24));

		frame.add(gamePanel, BorderLayout.CENTER);
		
	}
	
	private void initializeGame()
	{
		
		gamePanel.setLayout(gameBoxLayout);
		
		createTopBar();
		
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		gamePanel.add(topBar);
		
		gamePanel.add(aminoLabel);
		
		answerBar.add(new JLabel("Answer: "));
		answerBar.add(userEntry);
		userEntry.setHorizontalAlignment(JTextField.CENTER);
		userEntry.addActionListener(new getEntry());
		gamePanel.add(answerBar);
		
		buttonBar.add(startButton);
		startButton.addActionListener(new startGame());

		buttonBar.add(cancelButton);
		cancelButton.addActionListener(new endGame());
		
		gamePanel.add(buttonBar);
		cancelButton.setEnabled(false);


	}
	
	private void createTopBar()
	{
		
		//topBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Border bevel = BorderFactory.createLoweredBevelBorder();

		JPanel top = new JPanel(new GridLayout(0, 2));
		JPanel mid = new JPanel(new GridLayout(0, 2));
		JPanel bot = new JPanel(new GridLayout(0, 2));

		top.setBackground(Color.lightGray);
		mid.setBackground(Color.lightGray);
		bot.setBackground(Color.lightGray);
		
		top.setBorder(bevel);
		mid.setBorder(bevel);
		bot.setBorder(bevel);

		
		topBar.setLayout(topBarBoxLayout);
		//topBar.setBackground(Color.lightGray);
		//time.setHorizontalAlignment(JTextField.CENTER);
		
		top.add(new JLabel("Time: "));
		top.add(time);

		mid.add(new JLabel("Correct: "));
		correct.setForeground(Color.green);
		mid.add(correct);

		bot.add(new JLabel("Wrong: "));
		wrong.setForeground(Color.red);
		bot.add(wrong);

		topBar.add(top);
		topBar.add(mid);
		topBar.add(bot);
	}
	
	private void gameStart()
	{
		time.setText("30");
		startButton.setEnabled(false);
		cancelButton.setEnabled(true);
		count = 30;
		numCorrect = 0;
		numWrong = 0;
		gameRunning = true;
		
		timer.start();	
		
		amino = r.nextInt(FULL_NAMES.length - 1);
		aminoLabel.setText(FULL_NAMES[amino]);
		correct.setText(Integer.toString(numCorrect));
		wrong.setText(Integer.toString(numWrong));
	}
	
	private void gameEnd()
	{
		timer.stop();
		time.setText("GAME OVER");
		gameRunning = false;
		startButton.setEnabled(true);
		cancelButton.setEnabled(false);
		
	}
	
	public static void main(String[] args)
	{
		new Lab5Game();
	}
}
