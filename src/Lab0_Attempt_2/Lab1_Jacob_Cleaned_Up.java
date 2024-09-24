package Lab0_Attempt_2;

import java.util.*;


public class Lab1_Jacob_Cleaned_Up 
{
	// Constants
	public static final Random RANDOM = new Random();
	public static final String DNABASES = "ACGT";
	public static final double[] PROBABILITIES = {0.12, 0.38, 0.39, 0.11}; // Change these values to alter probabilities (corresponds to DNABASES string order)
	
	// Method that generates a triplet based on the DNABSES and PROBABILITIES variables
	public static String genTriplet()
	{
		String outString = "";
		
		// Loops for three bases
		for (int i = 0; i < 3; i++)
		{
			double baseScore = RANDOM.nextDouble();
			double probTotal = 0;
			
			// Loops until it finds a base to add
			for (int j = 0; j < DNABASES.length(); j++)
			{
				probTotal += PROBABILITIES[j];
				
				if (baseScore < probTotal)
				{
					outString += DNABASES.charAt(j);
					break;
				}
			}
		}
		return outString;
	}
	
	// MAIN METHOD
	public static void main(String[] args)
	{
		String trackTriplet = "AAA"; // Change this to track a different triplet
		
		int numTrackTriplet = 0;
		
		// Generate 1000 triplets and print them
		for (int i = 0; i < 1000; i++)
		{
			String triplet = genTriplet();
			System.out.println(triplet);
			
			if (triplet.equals(trackTriplet))
			{
				numTrackTriplet += 1;
			}
		}
		System.out.println(trackTriplet + " was generated " + numTrackTriplet + " times!");
	}
}
