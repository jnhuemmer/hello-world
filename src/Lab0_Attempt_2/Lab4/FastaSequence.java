package Lab0_Attempt_2.Lab4;

import java.io.*;
import java.util.*;

public class FastaSequence
{
	private final String header;
	private final String sequence;
	
	public FastaSequence(String header, String sequence)
	{
		this.header = header;
		this.sequence = sequence.toUpperCase(); // Just to keep case consistent
	}
	
	public String getHeader()
	{
		return header;
	}
	
	public String getSequence()
	{
		return sequence;
	}
	
	@Override
	public String toString()
	{
		return (header + "\n" + sequence );
	}

	// Gets the ratio of G and C nucleotides relative to the length of a sequence
	public float getGCRatio()
	{
		int numGC = 0;
		for (int i = 0; i < sequence.length(); i++)
		{
			if (sequence.charAt(i) == 'C' || sequence.charAt(i) == 'G')
				{
					numGC += 1;
				}
		}
		return ((float)numGC)/(sequence.length());
	}
	
	// Static factory method using FastaSequences from a file
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{
		BufferedReader fileContent = new BufferedReader(new FileReader(new File(filepath)));
		List<FastaSequence> FSList = new ArrayList<FastaSequence>();
		
		// StringBuffers used to avoid creating a bunch of string objects
		StringBuffer tempHeader = new StringBuffer();
		StringBuffer tempSeq = new StringBuffer();
		
		String nextLine = fileContent.readLine(); 	// The logic of the loop will miss the first sequence if the first line is not read in before the loop
		tempHeader.append(nextLine);
		
		// Reads through whole file
        for (nextLine = fileContent.readLine(); nextLine != null; nextLine = fileContent.readLine())
        {
        	// Does nothing if a line is empty or just contains "\n"
        	if (nextLine.isEmpty())
        	{
        		;
        	}
        	
        	// If line is a header
        	else if (nextLine.charAt(0) == '>')
        	{
        		FSList.add(new FastaSequence(tempHeader.toString().substring(1), tempSeq.toString()));
        		tempHeader.setLength(0);
        		tempSeq.setLength(0);
        		tempHeader.append(nextLine);
        	}
        	
        	// If line is a part of the coding sequence
        	else
        	{
        		tempSeq.append(nextLine.replace("\n", ""));
        	}
        }
        FSList.add(new FastaSequence(tempHeader.toString(), tempSeq.toString()));  // Adds the last entry since it's missed by the loop
        fileContent.close();
        return FSList;
	}
}