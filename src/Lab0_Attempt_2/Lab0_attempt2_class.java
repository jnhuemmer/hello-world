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
			System.out.println(tripletString);
			return tripletString;
		}
		
		public static Dictionary countTriplets(String sequence)
		{
			Dictionary countingTable = new Hashtable();
			
			for (int counter = 0; counter < sequence.length(); counter += 3)
			{
				String triplet = (string) (sequence.charAt(counter); //+ sequence.charAt(counter + 1) + sequence.charAt(counter + 2);
				System.out.println(triplet);
			}
			return countingTable;
		}
		
		public static void main(String[] args) 
		{
			
			Dictionary nucBases = new Hashtable();
			
			nucBases.put(0, "A");
			nucBases.put(1, "T");
			nucBases.put(2, "C");
			nucBases.put(3, "G");
			
			System.out.println(nucBases);
						
			String baseString = "";
			
			for (int length=0; length<1000; length++)
			{
				baseString += generateTriplet(nucBases);
				
			}
			System.out.println(baseString);
			
			countTriplets(baseString);
		}

		

}
