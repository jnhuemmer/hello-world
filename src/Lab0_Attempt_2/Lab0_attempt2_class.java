package Lab0_Attempt_2;

import java.util.*;

public class Lab0_attempt2_class 
{

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
		}

		

}
