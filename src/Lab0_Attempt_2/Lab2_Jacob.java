package Lab0_Attempt_2;

import java.util.concurrent.*; 
import java.util.*;
import java.io.*;

public class Lab2_Jacob 
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
	
	public static int TIME = 30;

	public static void beginGame()
	{
		System.out.println("LET THE GAMES BEGIN! You have " + TIME + " seconds pitiful human!\n");

		Random random = new Random();
	    Scanner aminoEntry = new Scanner(System.in);
	    
	    boolean contGame = true;
	    int score = 0;
	    
		long start = System.currentTimeMillis();
		long end = start + TIME * 1000;
		while (System.currentTimeMillis() < end && contGame == true) 
		{
			int amino = random.nextInt(FULL_NAMES.length - 1);
			
			System.out.println("FOOL! give me the amino acid abbreviation for " + FULL_NAMES[amino].toUpperCase() + "!");
		    String userEntry = (aminoEntry.nextLine()).toUpperCase();
		    		    
		    if (SHORT_NAMES[amino].equals(userEntry.toUpperCase()))
		    {
		    	score += 1;
		    	System.out.println("\nGah! That's right! Don't get too comfortable! You have only scored " + score + " points in " + (TIME - (end - System.currentTimeMillis())/1000) + " seconds.");
		    	
		    }
		    else
		    {
		    	contGame = false;
		    	System.out.println("\nMUAHAHAHAA! WROOONG! The correct answer is " + SHORT_NAMES[amino] + "!");
		    }
		 
		}
    	System.out.println("Your PUNY human brain was able to achieve a score of " + score + " points!");
	}
	
	public static void main(String[] args)
	{
		beginGame();
	}
}

