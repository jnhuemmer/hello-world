package Lab0_Attempt_2.Lab4;

import java.io.*;
import java.util.*;

/*
public class FastaSequence
{
	public FastaSequence(header, sequence)
	{
		this.header = header;
		this.sequence = sequence;
	}
}
*/

public class Lab4_Main 
{
	
	public static void writeTableSummary(List<FastaSequence> list, File outputFile) throws Exception
	{
		StringBuffer seq = new StringBuffer();
		
		StringBuffer fastaRow = new StringBuffer("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
		
		
		BufferedWriter writeOutput = new BufferedWriter(new FileWriter(outputFile));
		
		writeOutput.write(fastaRow.toString());
		
		
		for (FastaSequence entry : list)
		{
			writeOutput.write(entry.getHeader());
			seq.append(entry.getSequence());
			for (int i = 0; i < seq.length(); i++)
			{
				; // Write something to count each base in the sequence (.charAt())
			}
		}
		writeOutput.close();
		
		outputFile.createNewFile();

	}
	
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Hello world!");
        
        //BufferedReader fileContent = new BufferedReader(new FileReader(new File("C:\\Users\\Jacob\\Desktop\\Wormhole\\Yohe_Lab\\NonBatMasterFasta\\Canis_lupus.newname.tblastn.fa")));

		List<FastaSequence> mainFSList = FastaSequence.readFastaFile("G:\\Other computers\\DeepThought\\Wormhole\\Yohe_Lab\\NonBatMasterFasta\\Canis_lupus.newname.tblastn.fa");
		
		for (FastaSequence x : mainFSList)
		{
			System.out.println("\n" + x);
		}
		
		
		File outputFile = new File("C:\\Users\\Jacob\\Desktop\\outputFastaTable.txt");
		
		FastaSequence seq = new FastaSequence("WOW", "agGCct");
		
		writeTableSummary(mainFSList, outputFile);
		
		System.out.println(seq.getGCRatio());
	}
}
