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
		this.sequence = sequence.toUpperCase();
	}
	
	public String getHeader()
	{
		return header.substring(1);
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
	
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception
	{
		BufferedReader fileContent = new BufferedReader(new FileReader(new File(filepath)));
		List<FastaSequence> FSList = new ArrayList<FastaSequence>();
		StringBuffer tempHeader = new StringBuffer();
		StringBuffer tempSeq = new StringBuffer();
		
		String nextLine = fileContent.readLine();
		tempHeader.append(nextLine);
		
        for (nextLine = fileContent.readLine(); nextLine != null; nextLine = fileContent.readLine())
        {

        	if (nextLine.isEmpty())
        	{
        		;
        	}
        	else if (nextLine.charAt(0) == '>' && nextLine.isEmpty() != true)
        	{
        		FSList.add(new FastaSequence(tempHeader.toString(), tempSeq.toString()));
        		tempHeader.setLength(0);
        		tempSeq.setLength(0);
        		tempHeader.append(nextLine);
        	}
        	
        	else
        	{
        		tempSeq.append(nextLine);
        	}
        }
        
        fileContent.close();
        
        FSList.add(new FastaSequence(tempHeader.toString(), tempSeq.toString())); 
        return FSList;
        
	}
	
}

