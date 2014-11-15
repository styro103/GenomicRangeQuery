/*
 * Shaun Mbateng
 * Genomic Range Query
 * This program finds the minimal genomic ranges of an a set of given queries.
 * This program has an O(N+M) time complexity (Assuming Valid User Inputs).
 */

import java.util.Arrays; //For Handling Arrays
import java.util.Scanner; //For Inputs

public class GenomicRangeQuery 
{
	public static void main(String[] args) 
	{
		String seq; //Sequence of Nucleotides
		int [] P; //Starting Values in Ranges
		int [] Q; //Ending Valies on Ranges
		int [] MIF; //Array of Minimal Impact Factors
		int M; //Number of Queries (Length of Arrays)
		Query Minimals = new Query(); //Create New Instance of Query
		Scanner kbd = new Scanner(System.in); //For Inputting Sequence
		Scanner cin = new Scanner(System.in); //For Inputting Kth Queries
		
		//Enter Sequence
		System.out.print("Enter the Sequence: ");
		seq = kbd.nextLine();
		
		//Enter and Set Array Length
		System.out.print("Enter the Number of Queries: ");
		M = cin.nextInt();
		P = new int [M];
		Q = new int [M];
		
		//Fill Arrays
		for (int i=0; i<M; i++)
		{
			System.out.print("Enter Element "+(i+1)+" of P: ");
			P[i] = cin.nextInt();
			System.out.print("Enter Element "+(i+1)+" of Q: ");
			Q[i] = cin.nextInt();
		}
		
		cin.close(); //Close cin, No Longer Needed
		kbd.close(); //Close kbd, No Longer Needed
		
		MIF = Minimals.getGenomicRange(seq, P, Q); //Get Minimal Impact Factors
		System.out.println(); //Print Line Space
		System.out.println("Minimals: "+Arrays.toString(MIF)); //Display Them
	}
}
