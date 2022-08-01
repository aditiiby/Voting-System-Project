/**
 * Represents a voting paper in the Antarctica election process. 
 *
 * @author Aditi Malu 22526301
 * @version 1.0 2020
 */
import java.util.ArrayList;

public class VotingPaper
{
    // the numbers marked on the paper 
    private ArrayList<Integer> marks;

    /**
     * Constructor for objects of class VotingPaper. 
     * s will be a (possibly empty) sequence of integers, separated by commas. 
     * e.g. if s is "1,22,-13,456", marks is set to <1,22,-13,456>. 
     */
    public VotingPaper(String s)
    {
       marks = new ArrayList<>();
       if (!s.isEmpty())
          for (String x : s.split(",")) 
              marks.add(Integer.parseInt(x));
    }
    
    /**
     * Returns the contents of the paper.
     */
    public ArrayList<Integer> getMarks()
    {
       // TODO 1
       return marks; 
    }

    /**
     * Returns true iff the paper has the correct number of marks, 
     * i.e. one for each candidate. 
     */
    public boolean isCorrectLength(int noOfCandidates)
    {
       // TODO 2 
       if (noOfCandidates == marks.size()) 
           return true; 
       
       return false;
}
     
    /**
     * Returns true iff the sum of the marks is legal, 
     * i.e. no more than total. 
     */
    public boolean isLegalTotal(int total) 
    {
        // TODO 3
       int sum = 0; 
       for (Integer m: marks){
        sum += m; 
        }
        
        if (sum<=total){
        return true;
        }
        
        return false;
}
   


    /**
     * Returns true iff there are negative marks. 
     */
    public boolean anyNegativeMarks()
    {
       // TODO 4
       for (Integer negativeMarks : marks) {
           if (negativeMarks < 0){
               return true;
           }
        }
       return false; 
     
    }



    /**
     * Returns true iff the paper is formal. 
     * It must be the right length with no negative marks and a legal total. 
     */
    public boolean isFormal(int noOfCandidates)
    {
       // TODO 5
      
      if(isCorrectLength(noOfCandidates))
      {
          if (!anyNegativeMarks()) 
          {
              if (isLegalTotal(noOfCandidates))
              {
                    return true;
              }
          }
          
      }
      return false;
      
    }
    
    /**
     * Adds the appropriate number of votes to each candidate.
     * The kth number goes to the kth candidate.
     */
    public void updateVoteCounts(ArrayList<Candidate> cs)
    {
       // TODO 19
    	for(int i=0;i<cs.size();i++){
    		cs.get(i).addToCount(marks.get(i));
    	}
    }

    /**
     * Returns the indices in marks which have the highest number.
     * e.g. if marks = <4,4,1,5,2>, it returns <3> (because the highest number is at index 3).
     * e.g. if marks = <5,4,1,2,5>, it returns <0,4>.
     * e.g. if marks = <1,1,1,1,1>, it returns <0,1,2,3,4>.
     */
    public ArrayList<Integer> highestVote()
    {
       // TODO 20
    	ArrayList<Integer> indices = new ArrayList<>();
    	int index = 0;
    	int highest = marks.get(index);
    	
    	for(int i=1;i<marks.size();i++){
    		if(highest<marks.get(i)){
    			index=i;  			
    			highest=marks.get(i);
    		}
    	}
    	
    	indices.add(index);
    	
    	for(int j=0;j<marks.size();j++){
    		if(highest==marks.get(j) && j!=index){
    			index=j;
    			indices.add(index);
    		}
    	}
    	
       return indices;
    }
    
    /**
     * Adds the appropriate number of wins to each candidate.
     * If there are n equal-highest numbers, each of those 
     * candidates receives 1/n wins. 
     */
    public void updateWinCounts(ArrayList<Candidate> cs)
    {
       // TODO 21
    	ArrayList<Integer> indices = highestVote();
    	if(indices.size()==1){
    		cs.get(indices.get(0)).addToWins(1.0);
    	}
    	
    	if(indices.size()>1){
    		for(Integer index: indices){
    			cs.get(index).addToWins(1.0/indices.size());
    		}
    	}
    	
    }
}
