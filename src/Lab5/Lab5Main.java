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

public class Lab5Main
{
	public static final String[] FULL_NAMES = 
		{
		"alanine","arginine", "asparagine", 
		"aspartic acid", "cysteine",
		"glutamine",  "glutamic acid",
		"glycine" ,"histidine","isoleucine",
		"leucine",  "lysine", "methionine", 
		"phenylalanine", "proline", 
		"serine","threonine","tryptophan", 
		"tyrosine", "valine"};

	public static final String[] SHORT_NAMES = 
		{ "A","R", "N", "D", "C", "Q", "E", 
		"G",  "H", "I", "L", "K", "M", "F", 
		"P", "S", "T", "W", "Y", "V" };
		
	public static Scanner userInput = new Scanner(System.in);

	public static Random random = new Random();
	
	
	// Method for running the whole game
	public static void beginGame()
	{		
		boolean contGame = true;
	    int score = 0;
		
	    // Get the amount of time for the game to run
		System.out.println("Welcome to my command line window pathetic human. How many seconds would you like for your trial to last?");
		int time = userInput.nextInt();
		long start = System.currentTimeMillis();
		long end = start + time * 1000;
		userInput.nextLine(); // for making the scanner go to the next line so that it does not get input into userEntry below

		
		// Game start
		System.out.println("\nLET THE GAMES BEGIN! You have " + time + " seconds insolet cur!\n");
		while (System.currentTimeMillis() < end && contGame == true) 
		{
			
			// Generate random amino acid
			int amino = random.nextInt(FULL_NAMES.length - 1);
			
			
			// User answer
			System.out.println("FOOL! give me the amino acid abbreviation for " + FULL_NAMES[amino].toUpperCase() + "!");
		    String userEntry = (userInput.nextLine()).toUpperCase();
		    
		    
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
		}
    	
		// Final score
		System.out.println("Your PUNY human brain was able to achieve a score of " + score + " points!");
	}
	
	
	
	public static void main(String[] args)
	{
		Lab5Timer timer = new Lab5Timer();
		
		
		
		beginGame();
	}
}

