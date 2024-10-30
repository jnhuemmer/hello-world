package Lab5;

import java.util.concurrent.*; 
import java.util.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.*;




public class Lab5Game extends JFrame
{
	private int count = 31;
	private JButton startButton = new JButton("Start");
	final JLabel label = new JLabel("WELCOME TO MY DOMAIN FOOL!");
	final JLabel time = new JLabel();
	final Random r = new Random();

	
	private static final String[] FULL_NAMES = 
		{
		"alanine","arginine", "asparagine", 
		"aspartic acid", "cysteine",
		"glutamine",  "glutamic acid",
		"glycine" ,"histidine","isoleucine",
		"leucine",  "lysine", "methionine", 
		"phenylalanine", "proline", 
		"serine","threonine","tryptophan", 
		"tyrosine", "valine"};

	private static final String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" };
	
	
	private class startGame implements ActionListener
	{
		public void actionPerformed(ActionEvent Arg0)
		{
			gameStart();
		}
	}
	
	
	
	public Lab5Game()
	{
		setTitle("Amino Acid Game");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new BorderLayout());
		
		add(label, BorderLayout.CENTER);
		label.setHorizontalAlignment(JTextField.CENTER);
		
		add(startButton, BorderLayout.SOUTH);
		startButton.setHorizontalAlignment(JTextField.CENTER);
		startButton.addActionListener(new startGame());
	}
	
	private void gameStart()
	{
		add(time, BorderLayout.NORTH);
		time.setHorizontalAlignment(JTextField.CENTER);
		
		Timer timer = new Timer(1000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				count--;
				time.setText("Time remaining: " + count);
			}
		});
		
		timer.start();	
		
		boolean contGame = true;
		
		while (contGame == true) 
		{
			
			// Generate random amino acid
			int amino = r.nextInt(FULL_NAMES.length - 1);
			
			
			// User answer
			label.setText("FOOL! give me the amino acid abbreviation for " + FULL_NAMES[amino].toUpperCase() + "!");
		    
		    /*
		    // Answer check
		    if (SHORT_NAMES[amino].equals(userEntry.toUpperCase()))
		    {
		    	score += 1;
		    	System.out.println("\nGah! That's right! Don't get too comfortable! You have only scored " + score + " points in " + (time - (end - System.currentTimeMillis())/1000) + " seconds.");
		    }
		    else
		    {
		    	contGame = false;
		    	System.out.println("\nMUAHAHAHAA! WROOONG! The correct answer is " + SHORT_NAMES[amino] + "!");
		    	
		    } 
		    */
			contGame = false;
		}
	}
	
	public static void main(String[] args)
	{
		new Lab5Game();
	}
}
