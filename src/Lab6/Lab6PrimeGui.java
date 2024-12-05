package Lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Semaphore;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

// GUI object for calculating prime numbers in sequence
public class Lab6PrimeGui extends JFrame
{
	// Flag will shut off all calculation processes when set to false
	volatile static boolean primeRun = false;
	
	private int thePrimeNumber = 0;
	private int numWorkers = 1;
	
	static final JButton cancelButton = new JButton("Cancel");
	static final JButton startButton = new JButton("Start");
	
	private final JFrame frame = new JFrame();
	
	private static final JLabel elapsedTime = new JLabel("0 Secs");
	private static final JLabel maxNumber = new JLabel("0");
	private static final JLabel numberOfPrime = new JLabel("0");
	private static final JLabel runningLabel = new JLabel("Idle");
	
	private static final JMenuBar menuBar = new JMenuBar();
	
	private static final JMenu menu = new JMenu("Menu");
	
	static final JMenuItem threadControl = new JMenuItem("Threads");

	private static final  JPanel answerPanel = new JPanel(); // Box layout
	private final JPanel buttonBar = new JPanel(new GridLayout(2, 0, 0, 10));
	private final JPanel infoPanel = new JPanel(); // Box layout
	private final JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 0));
	private final JPanel runtimeInfoPanel = new JPanel(new GridLayout(4, 2, 0, 0));
	
	private final static JScrollPane answerList = new JScrollPane(answerPanel);
		
	static final List<Integer> primeList = Collections.synchronizedList(new ArrayList<Integer>());
	
	Lab6PrimeWorkerManager manager = new Lab6PrimeWorkerManager(numWorkers, thePrimeNumber);

	// For cancel button
	protected class end implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			updateStatus("Canceling...");
			primeRun = false;
		}
	}
	
	// For start button
	private class start implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			primeCalcStart();
		}
	}
	
	// For start button
	private class threads implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			getThreads();
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
		
		frame.setJMenuBar(menuBar);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.validate(); // To ensure the frame is drawn after initial start
	}
	
	// Orders and puts all found prime numbers into the answer scroll panel
	public static void fillList()
	{
		Collections.sort(primeList);;
		for (int i : primeList)
		{
			answerPanel.add(new JLabel(i + ""));
		}
		answerList.updateUI(); // To ensure UI change takes place without needing to resize the window
	}

	// Queries the user for a number to search for prime numbers under
	private void getPrime()
	{
		boolean askInt = true;
		
		// While loop used so that if a user inputs a non-integer they are queried again
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
				
				// User enters int
				else
				{
					int tempPrime = Integer.valueOf(userInput);
					askInt = false;
					primeRun = true;
					thePrimeNumber = tempPrime;
				}
			}
			
			// User enter non-int
			catch (NumberFormatException e)
			{
	            JOptionPane.showMessageDialog(null, userInput + " is not an integer."); 
			}
		}
	}
	
	// Queries the user for a number of threads
	private void getThreads()
	{
		boolean askInt = true;
		
		// While loop used so that if a user inputs a non-integer they are queried again
		while (askInt == true)
		{
			System.out.println("Clicked start!");
			String userInput = JOptionPane.showInputDialog("Enter a number of worker threads above 0");
			try
			{
				// User clicks cancel
				if (userInput == null)
				{
					askInt = false;
				}
				
				// User enters int
				else
				{
					int tempThreads = Integer.valueOf(userInput);
					if (tempThreads > 0)
					{
						askInt = false;
						numWorkers = tempThreads;
						manager.updateWorkers(numWorkers);
					}
					
					// Int is 0 or smaller
					else
					{
						JOptionPane.showMessageDialog(null, "Entry must be larger than 0"); 
					}

				}
			}
			
			// User enter non-int
			catch (NumberFormatException e)
			{
	            JOptionPane.showMessageDialog(null, userInput + " is not an integer."); 
			}
		}
	}
	
	// Processes that only need to be done when the program is started up
	private void guiInitialize()
	{
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
		
		threadControl.addActionListener(new threads());
		menu.add(threadControl);
		menuBar.add(menu);
		
		runtimeInfoPanel.add(new JLabel("Input: "));
		runtimeInfoPanel.add(maxNumber);
		runtimeInfoPanel.add(new JLabel ("Time Elapsed:"));
		runtimeInfoPanel.add(elapsedTime);
		runtimeInfoPanel.add(new JLabel ("Numbers:"));
		runtimeInfoPanel.add(numberOfPrime);
		runtimeInfoPanel.add(new JLabel ("Status:"));
		runtimeInfoPanel.add(runningLabel);
		
		infoPanel.add(runtimeInfoPanel);
		
		buttonBar.add(startButton);
		startButton.addActionListener(new start());
		
		infoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		buttonBar.add(cancelButton);
		cancelButton.addActionListener(new end());
		cancelButton.setEnabled(false);
		
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
		
		if (primeRun == true) // For if the user clicked "cancel" in the inputDialogBox
		{
			// Reset from previous job
			primeList.clear();
			resetAnswers();
			updateNumbers(0);
			updateStatus("Thinking..."); // Make it so that if the user clicks cancel, this does not change. Maybe while loop for primeRun?
			maxNumber.setText(thePrimeNumber + "");
			
			threadControl.setEnabled(false);
			cancelButton.setEnabled(true);
			startButton.setEnabled(false);
			manager.updatePrime(thePrimeNumber);
			
			// Manages the prime-number calculators
			Thread managerThread = new Thread(manager);
			managerThread.start();
			
		}
	}
	
	// Method clears out answer list from previous job
	public static void resetAnswers()
	{
		answerPanel.removeAll();
		answerPanel.add(new JLabel("Prime numbers go here"));
		answerList.validate(); // Ensure UI updates
	}
	
	public static void updateNumbers(int numbers)
	{
		numberOfPrime.setText(numbers + "");
	}
	
	public static void updateStatus(String status)
	{
		runningLabel.setText(status);
	}
	
	public static void updateTime(String Seconds)
	{
		elapsedTime.setText(Seconds);
	}
	
	public static void main(String[] args)
	{
		new Lab6PrimeGui();
	}
}
