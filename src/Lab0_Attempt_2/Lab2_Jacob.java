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

	public static void beginGame()
	{
		System.out.println("LET THE GAMES BEGIN");

		Random random = new Random();
	    Scanner aminoEntry = new Scanner(System.in);

		
		long start = System.currentTimeMillis();
		long end = start + 30 * 1000;
		while (System.currentTimeMillis() < end) 
		{
			int amino = random.nextInt(FULL_NAMES.length - 1);
			
			System.out.println("FOOL! GIVE ME THE AMINO ACID ABBREVIATION FOR " + FULL_NAMES[amino]);
		    String userEntry = (aminoEntry.nextLine()).toUpperCase();
		    
		    if (SHORT_NAMES[userEntry] != null)
		    {
		    	
		    }
		   

			
		}

	}
	
	public static void main(String[] args)
	{
		beginGame();
	}
}

