/*
 * The class finds the minimal genomic ranges of each query..
 * This runs in O(N+M).
 */

import java.util.Arrays; //For Handling Arrays

class Query 
{
	public int [] getGenomicRange(String S, int [] P, int [] Q) //Function to Get Genomic Range of Queries
	{
		boolean same = true; //Whether or Not Sequence is Filled With Same Character
		int N = S.length(); //Length of Sequence
		int M = P.length; //Number of Queries
		int [] impacts = new int [M]; //Array of Minimal Impact Factors
		
		//Edge Cases
		if (N>1) //If More Than 1 Nucleotide in Sequence
		{
			for (int i=1; i<S.length(); i++) //Check if Array is Sequence is Repetition of Single Nucleotide
			{
				if (S.charAt(i)!=S.charAt(0))
	            {
	                same = false;
	                break;
	            }
			}
		}
		if (same) //If Only One Distinct Nucleotide
            Arrays.fill(impacts, convert(S.charAt(0))); //Set All Impacts to Impact Factor of Nucleotide
		else
		{
			int minFactPos = minNucl(S); //Position of Minimum Impact Factor of Whole Sequence
			int [][] ps = new int [N][4]; //Matrix of Prefix Sums of Each Nucleotide
			
			for (int i=0; i<N; i++) //Initialize Matrix
				ps[i][convert(S.charAt(i))-1] = 1; //Mark Location of Every Nucleotide
			for (int i=1; i<N; i++) //Fill Matrix With Prefix Sums
			{
				for (int j=0; j<4; j++)
					ps[i][j] += ps[i-1][j];
			}
			for (int K=0; K<M; K++)
            {   
                int x = P[K]; //Staring Value in Range
                int y = Q[K]; //Ending Value in Range
                
				//Edge Cases
				if (x==y) //If Same Position to Look at in Sequence (Same Nucleotide)
                {
                    impacts[K] = convert(S.charAt(x)); //Fill Position of Impacts With Impact Factor of Nucleotide
                    continue; //Goto Next Iteration of For Loop
                }
				if (x<=minFactPos && y>=minFactPos) //If Range Includes Minimum Factor's Position
				{
					impacts[K] = convert(S.charAt(minFactPos)); //Fill Position of Impacts With Minimum Impact Factor of Whole Sequence
                    continue; //Goto Next Iteration of For Loop
				}
				///*
				for (int j=0; j<4; j++) //General Case
				{
					//For Each Query, Check Each Nucleotide From Minimum
						//If it Exists Between Range, Mark as Minimum Factor
					int subt = 0;
					
					if (x>0) //If Starting Range Value is Not First Index
						subt = ps[x-1][j]; //Get Count of Nucleotide Appearances Before Staring Value 
					if (ps[y][j]-subt>0) //Subtract Amount Before Starting Value, If More Than Zero
					{
						impacts[K] = j+1; //Must be Minimum
						break; //Onto the Next Query
					}
				}
				//*/
				//impacts[K] = convert(S.charAt(minNucl(S.substring(x, y+1)))); Easy Version, But Makes the Runtime O(N*M)
            }
		}
		
		return impacts; //Return Array
	}
	
	public int convert(char c) //Function to Convert Nucleotide to Impact Factor
    {
        switch (c) //Depending on Passed Character, Return Integer
        {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return -1; //Invalid Nucleotide Character
        }
    }
	
	public int minNucl(String S) //Function to Find Minimum Nucleotide Factor in Sequence
	{
		//Search if Sequence Contains Minimum Possible Factor, If Not, Check Next Possible Minimum
		if (S.contains("A"))
			return S.indexOf('A');
		if (S.contains("C"))
			return S.indexOf('C');
		if (S.contains("G"))
			return S.indexOf('G');
		if (S.contains("T"))
			return S.indexOf('T');
		
		return -1; //No Valid Nucleotide Characters in Sequence
	}
}
