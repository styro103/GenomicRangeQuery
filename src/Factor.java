// you can also use imports, for example:
// import java.util.*;
import java.util.Arrays;
// you can use System.out.println for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Factor 
{
	public int [] getMinimals(String S, int [] P, int [] Q) 
    {
        /*
         * Unfinished project. Change code later. Find first instance of each letter in sequence.
         * Check each element of p, if greater than instance of letter, then return number.
         * Check A, then C, then G, then T
         */
		// write your code in Java SE 8
        int [] impact = new int [P.length];
		int [] ps = new int[S.length()+1];
		
		ps[0] = 0;
		
		for (int i=1; i<ps.length; i++)
			ps[i] = ps[i-1]+convert(S.charAt(i-1));
        
        if (S.length()==1)
            Arrays.fill(impact, convert(S.charAt(0)));
        else
        {   
        	for (int i=0; i<impact.length; i++)
            {   
                if (P[i]==Q[i])
                {
                    impact[i] = convert(S.charAt(P[i]));
                    continue;
                }
            }
        }
        
        return impact;
    }
    
    public int convert(char c)
    {
        switch (c)
        {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
        }
        
        return 0;
    }
}
