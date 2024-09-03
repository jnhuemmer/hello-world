package Lab0_Attempt_2;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class Lab1_chatGPT 
{
	private static final Random RANDOM = new Random();

    // Probabilities for each nucleotide
    private static final double P_A = 0.12;
    private static final double P_T = 0.11;
    private static final double P_C = 0.38;
    private static final double P_G = 0.39;

    // Method to generate a nucleotide based on probabilities
    private static char generateNucleotide() {
        double rand = RANDOM.nextDouble();
        
        System.out.println(rand);
        
        if (rand < P_A) {
            return 'A';
        } else if (rand < P_A + P_T) {
            return 'T';
        } else if (rand < P_A + P_T + P_C) {
            return 'C';
        } else {
            return 'G';
        }
    }

    // Method to generate a single triplet
    private static String generateTriplet() {
        StringBuilder triplet = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            triplet.append(generateNucleotide());
        }
        return triplet.toString();
    }

    // Method to generate a sequence of triplets
    public static String generateSequence(int numTriplets) {
        StringBuilder sequence = new StringBuilder(numTriplets * 3);
        for (int i = 0; i < numTriplets; i++) {
            sequence.append(generateTriplet());
        }
        return sequence.toString();
    }

    public static void main(String[] args) {
        int numTriplets = 1000; // Number of triplets you want to generate
        String sequence = generateSequence(numTriplets);
        System.out.println("Generated DNA sequence: " + sequence);
        
        
        // JACOB CODE ####################################################################
        Dictionary seqCounts = new Hashtable();
        seqCounts = countTriplets(sequence);
        countBases("AAA", seqCounts);
        
    }
	
    // JACOB CODE
   	public static Dictionary countTriplets(String sequence)
	{
		Dictionary countingTable = new Hashtable();
		
		for (int counter = 0; counter < sequence.length(); counter += 3)
		{
			String triplet = String.valueOf(sequence.charAt(counter)) + String.valueOf(sequence.charAt(counter + 1)) + String.valueOf(sequence.charAt(counter + 2));
			
			if (countingTable.get(triplet) != null)
				{
				countingTable.put(triplet, (int)countingTable.get(triplet) + 1);
				}
			else
				{
				countingTable.put(triplet, 1);				
				}
		}
		return countingTable;
	}
	
   
	public static void countBases(String desiredTriplet, Dictionary tripletNumberTable)
	{
		if (tripletNumberTable.get(desiredTriplet) != null)
		{
			System.out.println("The triplet " + desiredTriplet + " occurs " + tripletNumberTable.get(desiredTriplet) + " times in the sequence!");
		}
		else
		{
			System.out.println("The triplet " + desiredTriplet + " does not occur in the sequence!");
		}
	}

	
    
}







