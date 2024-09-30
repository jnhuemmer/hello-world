package Lab0_Attempt_2.Lab4;

import java.io.*;
import java.util.*;

public class Lab4_Main 
{
	
	// Method for taking a list  of FastaSequence objects and converting them into a spreadsheet, counting each nucleotide
	public static void writeTableSummary(List<FastaSequence> list, File outputFile) throws Exception
	{		
		BufferedWriter writeOutput = new BufferedWriter(new FileWriter(outputFile));
		HashMap<Character, Integer> nucCounts = new HashMap<>(5); // Using 5 buckets ensures each nucleotide gets it's own bucket (0, 2, 1, 4)
		nucCounts.put('A', 0);
		nucCounts.put('C', 0);
		nucCounts.put('G', 0);
		nucCounts.put('T', 0);
		
		// Header
		writeOutput.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
		
		// Loop through each FastaSequence Object
		for (FastaSequence entry : list)
		{
			
			// Loop for going through each nucleotide sequence and counting the bases, updates hashmap based on nucleotide
			for (int i = 0; i < entry.getSequence().length(); i++)
			{
				nucCounts.replace(entry.getSequence().charAt(i), nucCounts.get(entry.getSequence().charAt(i)) + 1);
			}
			// Write output
			writeOutput.write(entry.getHeader() + "\t" + nucCounts.get('A') + "\t" + nucCounts.get('C') + "\t" + nucCounts.get('G') + "\t" + nucCounts.get('G') + "\t" + entry.getSequence() + "\n");
			
			// Reset the hash map for the next sequence
			nucCounts.replace('A', 0);
			nucCounts.replace('C', 0);
			nucCounts.replace('G', 0);
			nucCounts.replace('T', 0);
		}
		outputFile.createNewFile();
		writeOutput.close();
	}
	
	
	// Main method
	public static void main(String[] args) throws Exception
	{
		List<FastaSequence> mainFSList = FastaSequence.readFastaFile("G:\\Other computers\\DeepThought\\Wormhole\\Yohe_Lab\\NonBatMasterFasta\\Canis_lupus.newname.tblastn.fa");
		
		for (FastaSequence fs : mainFSList)
		{
			System.out.println(fs.getHeader());
			System.out.println(fs.getSequence());
			System.out.println(fs.getGCRatio());
		}
		
		File outputFile = new File("C:\\Users\\Jacob\\Desktop\\outputFastaTable.txt");
		writeTableSummary(mainFSList, outputFile);
	}
}