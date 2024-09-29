package Lab0_Attempt_2.Lab4;

import java.io.*;

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
	public static void main(String[] args) throws Exception
	{
		System.out.println("Hello world!");
        
        BufferedReader fileContent = new BufferedReader(new FileReader(new File("C:\\Users\\Jacob\\Desktop\\Wormhole\\Yohe_Lab\\NonBatMasterFasta\\Canis_lupus.newname.tblastn.fa")));
        int numLines = 0;
        
        for (String nextLine = fileContent.readLine(); nextLine != null; nextLine = fileContent.readLine())
        {
        	numLines += 1;
        	System.out.println(nextLine + "\t line number " + numLines);
        }        
	
        FastaSequence seq = new FastaSequence("WOW", "agct");
	}
}
