package Lab0_Attempt_2;

import java.util.*;

public class Lab0_attempt2_class 
{

		public static long getMax(double probability)
		{
			if (probability >= 0.001)
			{
				if (probability >= 0.01)
				{
					if (probability >= 0.1)
					{
						return 10;
					}
					else
					{
					return 100;
					}
				}
				else
				{
					return 1000;
				}
			}
			else
			{
				String stringProb = probability + "";
				String mult = stringProb.substring(stringProb.indexOf("E") + 2, stringProb.length());
				int multInt = Integer.parseInt(mult);
					
				long max = 1;
				for (int count = 0; count < multInt; count++)
				{
					max *= 10;
				}
				return max;
			}
		}
		
		
		public static String generateTriplet(Dictionary nucDict)
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
		
		
		public static Dictionary alterProbability(int aFreq, int tFreq, int cFreq, int gFreq, Dictionary originalDict)
		{
			getMax(aFreq);
			
		}
		
		
		public static void main(String[] args) 
		{
			
			String trackTriplet = "AAA";
			
			Dictionary nucBases = new Hashtable();
			
			nucBases.put(0, "A");
			nucBases.put(1, "T");
			nucBases.put(2, "C");
			nucBases.put(3, "G");
									
			String baseString = "";
						
			for (int length=0; length<1000; length++)
			{
				baseString += generateTriplet(nucBases);
				
			}
			System.out.println(baseString);
			Dictionary baseNumbers = new Hashtable();
			baseNumbers = countTriplets(baseString);
			countBases(trackTriplet, baseNumbers);
			
			double cool = 0.0007;
			
			long reallyCool = getMax(cool);
			
			System.out.println(reallyCool);
			System.out.println(getMax(0.001));
			System.out.println(getMax(0.01));
			System.out.println(getMax(0.1));

			System.out.println(getMax(0.000000000000000001));


			
		}

/* QUESTIONS
 * 	
 * Assuming each base has an equal chance to appear, we would expect the AAA sequence to appear (also considering only one ORF) with a ratio of 1/81 
 */

}
