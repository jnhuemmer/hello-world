package Lab6;

import java.util.*;

import java.awt.*;    
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

//import javax.swing.*;

public class Lab6PrimeGui extends JFrame
{
	private boolean primeRun = false;
	
	private int thePrimeNumber = 0;
	
	private JButton cancelButton = new JButton("Cancel");
	private JButton startButton = new JButton("Start");
	
	private final JFrame frame = new JFrame();
	
	private final JLabel elapsedTime = new JLabel("0 Sec");
	private final JLabel input = new JLabel("Input: ");
	private final JLabel maxNumber = new JLabel("0");
	private final JLabel numberOfPrime = new JLabel("0");
	private final JLabel primeNumbers = new JLabel ("Numbers:");
	private final JLabel status = new JLabel ("Status:");
	private final JLabel runningLabel = new JLabel("Idle");
	private final JLabel timeElapsed = new JLabel ("Time Elapsed:");
	
	private final JOptionPane getInput = new JOptionPane("Enter a large integer:");
	
	private final JPanel answerPanel = new JPanel(new FlowLayout());
	private final JPanel buttonBar = new JPanel(new GridLayout(2, 0, 0, 10));
	private final JPanel infoPanel = new JPanel();
	private final JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 0));
	private final JPanel runtimeInfoPanel = new JPanel(new GridLayout(4, 2, 0, 0));
	
	private final JScrollPane answerList = new JScrollPane(answerPanel);
	
	private final JTextField userEntry = new JTextField(10);

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
			primeCalcStart();
		}
	}
	
	public Lab6PrimeGui()
	{
		frame.setTitle("Prime");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		guiInitialize();

		frame.add(mainPanel, BorderLayout.CENTER);
		
	}
	
	// Checks what the user submits in the answer box, then calculates score and generates a new amino acid
	private void checkAnswer()
	{
		System.out.println("huh");
	}

	// Ends the game. Occurs when timer expires or if the user clicks "cancel"
	private void gameEnd()
	{
		System.out.println("STOP!");
		cancelButton.setEnabled(false);
		startButton.setEnabled(true);

		
		primeRun = false;
	}
	
	private void getPrime()
	{
		boolean askInt = true;
		while (askInt == true)
		{
			System.out.println("Clicked start!");
			String userInput = JOptionPane.showInputDialog("Enter a large integer:");
			try
			{
				// User clicks cancel
				if (userInput == null)
				{
					askInt = false;
				}
				else
				{
					int tempPrime = Integer.valueOf(userInput);
					askInt = false;
					thePrimeNumber = tempPrime;
				}
			}
			catch (NumberFormatException e)
			{
	            JOptionPane.showMessageDialog(null, userInput + " is not an integer."); 
			}
		}
	}
	
	// Processes that only need to be done when the game is started up
	private void guiInitialize()
	{
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		runtimeInfoPanel.add(input);
		runtimeInfoPanel.add(maxNumber);
		runtimeInfoPanel.add(timeElapsed);
		runtimeInfoPanel.add(elapsedTime);
		runtimeInfoPanel.add(primeNumbers);
		runtimeInfoPanel.add(numberOfPrime);
		runtimeInfoPanel.add(status);
		runtimeInfoPanel.add(runningLabel);
		
		infoPanel.add(runtimeInfoPanel);
		
		buttonBar.add(startButton);
		startButton.addActionListener(new startGame());
		
		infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		buttonBar.add(cancelButton);
		cancelButton.addActionListener(new endGame());
		
		infoPanel.add(buttonBar);
		
		mainPanel.add(infoPanel);
		
		answerPanel.add(new JLabel("Prime numbers go here:"));
		
		mainPanel.add(answerList);
	}
	
	// Starts the game, meaning the answer box becomes functional and the timer begins ticking
	private void primeCalcStart()
	{
		
		// Get User input
		getPrime();
		
		primeRun = true;
		maxNumber.setText(thePrimeNumber + "");
		runningLabel.setText("Thinking..."); // Make it so that if the user clicks cancel, this does not change. Maybe while loop for primeRun?
		
		
	}
	
	public static void main(String[] args)
	{
		new Lab6PrimeGui();
	}
}
