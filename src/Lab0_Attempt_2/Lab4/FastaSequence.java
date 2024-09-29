package Lab0_Attempt_2.Lab4;

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
		return this.header;
	}
	
	public String getSequence()
	{
		return this.sequence;
	}

	public float getGCRatio()
	{
		int numGC = 0;
		for (int i = 0; i < this.sequence.length(); i++)
		{
			if (this.sequence.charAt(i) == 'C' || this.sequence.charAt(i) == 'G')
				{
					numGC += 1;
				}
			return ((float)numGC)/sequence.length();
		}
	}
}

