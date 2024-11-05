package Lab5;

import java.util.*;

import java.awt.*;    
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

//import javax.swing.*;

public class Lab5Game extends JFrame
{
	private boolean gameRunning = false;
	
	private int amino = 0;
	private int count = 30;
	private int numCorrect = 0;
	private int numWrong = 0;
	
	private final Random r = new Random();
	
	private JButton cancelButton = new JButton("Cancel");
	private JButton startButton = new JButton("Start");
	
	private final JFrame frame = new JFrame();

	private final JLabel aminoLabel = new JLabel("CLICK START!");
	private final JLabel correct = new JLabel(Integer.toString(numCorrect));
	private final JLabel time = new JLabel(Integer.toString(count));
	private final JLabel wrong = new JLabel(Integer.toString(numWrong));
	
	private final JPanel answerBar = new JPanel(new FlowLayout());
	private final JPanel buttonBar = new JPanel(new GridLayout(0, 2, 10, 0));
	private final JPanel gamePanel = new JPanel();
	private final JPanel topBar = new JPanel();
		
	private final JTextField userEntry = new JTextField(10);
			
	private final Timer timer = new Timer(1000, new ActionListener()
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

	private String entry = "";	
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

	private class answerAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			checkAnswer();
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
	
	private class startGame implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			gameStart();
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
		
		gameInitialize();

		aminoLabel.setHorizontalAlignment(JTextField.CENTER);
		aminoLabel.setFont(new Font("Arial", Font.BOLD, 24));

		frame.add(gamePanel, BorderLayout.CENTER);
		
	}
	
	// Checks what the user submits in the answer box, then calculates score and generates a new amino acid
	private void checkAnswer()
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
			
			// Select a new amino acid
			amino = r.nextInt(FULL_NAMES.length - 1);
			aminoLabel.setText(FULL_NAMES[amino]);
		}
		
		// Remove previous answer from answer box
		userEntry.setText("");
	}

	// Builds the top part of the application consisting of the timer and correct/incorrect answers
	private void createTopBar()
	{
		topBar.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));
		topBar.setLayout(new BoxLayout(topBar, BoxLayout.Y_AXIS));
		
		JPanel top = new JPanel(new GridLayout(0, 2));
		JPanel mid = new JPanel(new GridLayout(0, 2));
		JPanel bot = new JPanel(new GridLayout(0, 2));
		
		top.setBackground(Color.lightGray);
		mid.setBackground(Color.lightGray);
		bot.setBackground(Color.lightGray);
		
		top.setBorder(BorderFactory.createLoweredBevelBorder());
		mid.setBorder(BorderFactory.createLoweredBevelBorder());
		bot.setBorder(BorderFactory.createLoweredBevelBorder());
		
		top.add(new JLabel("Time: "));
		top.add(time);

		mid.add(new JLabel("Correct: "));
		mid.add(correct);

		bot.add(new JLabel("Wrong: "));
		bot.add(wrong);

		topBar.add(top);
		topBar.add(Box.createRigidArea(new Dimension(0, 10))); // To add some space between elements
		topBar.add(mid);
		topBar.add(Box.createRigidArea(new Dimension(0, 10))); // To add some space between elements
		topBar.add(bot);
	}
	
	// Ends the game. Occurs when timer expires or if the user clicks "cancel"
	private void gameEnd()
	{
		cancelButton.setEnabled(false);
		startButton.setEnabled(true);
		
		time.setText("GAME OVER");
		timer.stop();
		
		gameRunning = false;
	}
	
	// Processes that only need to be done when the game is started up
	private void gameInitialize()
	{
		gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
		gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Timer and score
		createTopBar();
		gamePanel.add(topBar);
		
		// Amino acid question
		gamePanel.add(aminoLabel); 
		aminoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		aminoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0)); // Adds some space below the label
		
		// Spot for the user to answer
		answerBar.add(new JLabel("Answer: "));
		answerBar.add(userEntry);
		userEntry.setHorizontalAlignment(JTextField.CENTER);
		userEntry.addActionListener(new answerAction());
		gamePanel.add(answerBar);
		
		// Start/cancel buttons at the bottom of the application
		buttonBar.add(startButton);
		startButton.addActionListener(new startGame());
		buttonBar.add(cancelButton);
		cancelButton.addActionListener(new endGame());
		cancelButton.setEnabled(false);
		gamePanel.add(buttonBar);
	}
	
	// Starts the game, meaning the answer box becomes functional and the timer begins ticking
	private void gameStart()
	{
		count = 30;
		time.setText(Integer.toString(count));
		timer.start();	
		
		numCorrect = 0;
		numWrong = 0;
		correct.setText(Integer.toString(numCorrect));
		wrong.setText(Integer.toString(numWrong));
		
		userEntry.setText(""); // Removes anything that the user had typed in the answer box prior to starting the game
				
		cancelButton.setEnabled(true);
		startButton.setEnabled(false);

		gameRunning = true;
		
		amino = r.nextInt(FULL_NAMES.length - 1);
		aminoLabel.setText(FULL_NAMES[amino]);
	}
	
	public static void main(String[] args)
	{
		new Lab5Game();
	}
}
