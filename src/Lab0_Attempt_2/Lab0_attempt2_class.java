package Lab0_Attempt_2;

import java.util.*;

public class Lab0_attempt2_class 
{
			
	/* QUESTIONS
	 * 	
	 * Notes: Over the course of programming this I got to remember how to program in Java (I learned in high school but that was a while ago!
	 * I initially used dictionaries but around question 3 I realized arrays can do pretty much the same thing leading to the alterProbability 
	 * method being kind of unnecessary. I am fairly happy with the rest of the program though!
	 * 
	 * Question 2
	 * Assuming each base has an equal chance to appear, we would expect the AAA sequence to appear 1.6% of the time (0.25^3)
	 * Generally, the AAA sequence occurs between 12 and 20 times in the sequence, which is fairly close to what is expected (1.2% - 2.0%)
	 * 
	 * Question 3
	 * With the specified custom probabilities, it would be expected that the AAA sequence appears 0.17% of the time
	 * Generally, the AAA sequence occurs between 0 and 3 times, which is fairly close the what is expected (0% - 0.3%)
	 * 
	 * Question 4 (chatGPT question)
	 * I have never actually used chatGPT for programming before, it's a bit mind boggling how well this seems to work straight out of the box!
	 * The query I used was "Write me some code in java that will generate a random sequence of DNA nucleotide triplets based upon established probabilities".
	 * The chatGPT code may be viewed in the Lab1_chatGPT.java class
	 * I modified the nucleic acid probabilities and sequence length after chatGPT generated all the code and it seemingly worked.
	 * I read through the code and it should be functioning properly based on that read through (generating a random double and checking the nucleotide range it falls in)
	 * It was through this that I also realized I could have used random to generate a float to make my code more efficient and I also learned what a stringbuilder is
	 * To check further I added some of my own code to count AAA triplets to see if the counts looked similar to my results
	 * Though I did not use a statistical test, eyeballing it with both produced very similar result 
	 */
		
	
		// Method generates a new nucleotide dictionary based on custom nucleotide probabilities
		public static Dictionary alterProbability(double aFreq, double tFreq, double cFreq, double gFreq)
		{
			double minFreq = Math.min(aFreq, tFreq);
			minFreq = Math.min(minFreq, cFreq);
			minFreq = Math.min(minFreq, gFreq);
						
			long maxNumber = getMax(minFreq);
						
			Dictionary tempDict = new Hashtable();
			
			tempDict.put("A", (int)(aFreq * maxNumber));
			tempDict.put("T", (int)((tFreq + aFreq) * maxNumber));
			tempDict.put("C", (int)((cFreq + aFreq + tFreq) * maxNumber));
			tempDict.put("G", (int)((gFreq + aFreq + tFreq + cFreq) * maxNumber));
			
			return tempDict;
		}
			
		// Method checks the triplet count dictionary and formats the output 
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

		// Method goes through the input sequence (length must be divisible by 3) and counts triplets, adding them to a dictionary
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
		
		// Method generates an array based on previously calculated custom probability values in a dictionary
		public static String[] customProbArray(Dictionary customProb)
		{
			String[] tempArray = new String[(int)customProb.get("G")] ;
			
			int count = 0;
			
			while (count < (int)customProb.get("A"))
			{
				tempArray[count] = "A";
				count++;
			}
			while (count < (int)customProb.get("T"))
			{
				tempArray[count] = "T";
				count++;
			}
			while (count < (int)customProb.get("C"))
			{
				tempArray[count] = "C";
				count++;
			}
			while (count < (int)customProb.get("G"))
			{
				tempArray[count] = "G";
				count++;
			}
			return tempArray;
		}
	
		// Method generates a sequences based on the array generated from custom probabilities
		public static String generateTripletCustomProb(String[] nucArray)
		{
			Random random = new Random();
			String tripletString = "";
			
			for (int three = 0; three < 3; three++) 
			{
				int baseID = random.nextInt(nucArray.length - 1);
				tripletString += nucArray[baseID];
			}
			return tripletString;
		}
		
		// Method generates a sequence based on a default nucleotide dictionary. This method will not function if that dictionary is altered
		public static String generateTripletEqualProb(Dictionary nucDict)
		{
			Random random = new Random();
			String tripletString = "";
			for (int three = 0; three < 3; three++) 
			{
				int baseID = random.nextInt(4);
				tripletString += nucDict.get(baseID);
			}
			return tripletString;
		}

		// Method determines the number to multiply custom probabilities by for array generation
		public static long getMax(double probability)
		{
			if (probability >= 0.001)
			{
				if (probability >= 0.01)
				{
					if (probability >= 0.1)
					{
						return 100;
					}
					else
					{
					return 1000;
					}
				}
				else
				{
					return 10000;
				}
			}
			else
			{
				String stringProb = probability + "";
				String mult = stringProb.substring(stringProb.indexOf("E") + 2, stringProb.length());
				int multInt = Integer.parseInt(mult);
				
				
				long max = 1;
				for (int count = 0; count <= multInt; count++)
				{
					max *= 10;
				}
				
				return max;
			}
		}
		

			
		
		// MAIN METHOD
		public static void main(String[] args) 
		{
			
			// Question 1
			String trackTriplet = "AAA";
			
			Dictionary nucBases = new Hashtable();
			
			nucBases.put(0, "A");
			nucBases.put(1, "T");
			nucBases.put(2, "C");
			nucBases.put(3, "G");
									
			String baseString = "";
						
			for (int length=0; length<1000; length++)
			{
				baseString += generateTripletEqualProb(nucBases);
			}
			
			// Question 2
			System.out.println(baseString);
			Dictionary baseNumbers = new Hashtable();
			baseNumbers = countTriplets(baseString);
			countBases(trackTriplet, baseNumbers);
						
			//Question 3
			double aProb = 0.12;
			double cProb = 0.38;
			double gProb = 0.39;
			double tProb = 0.11;
			
			
			Dictionary customNucBases = alterProbability(aProb, cProb, gProb, tProb);		
			String[] customNucArray = customProbArray(customNucBases);
			String customBaseString = "";
			
			for (int length=0; length<1000; length++)
			{
				customBaseString += generateTripletCustomProb(customNucArray);
			}
			
			System.out.println(customBaseString);
			Dictionary customBaseNumbers = new Hashtable();
			customBaseNumbers = countTriplets(customBaseString);
			countBases(trackTriplet, customBaseNumbers);
		}
}
