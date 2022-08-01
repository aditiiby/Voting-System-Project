/**
 * Manages the Antarctica election process. 
 *
 * @author Aditi Malu 22526301
 * @version 1.0 2020
 */
import java.util.ArrayList;

public class Election
{
    // the list of candidates
    private ArrayList<Candidate> candidates;
    // the list of voting papers
    private ArrayList<VotingPaper> papers;
    // the file of election information
    private FileIO file;

    /**
     * Constructor for objects of class Election.
     * Creates the three field objects.
     */
    public Election(String filename)
    {
       // TODO 13
       
       file= new FileIO(filename);
       candidates= new ArrayList<>();
       papers= new ArrayList<>(); 
       
       
    }
    
    /**
     * Constructor for objects of class Election with default files.
     * It uses k to select from the sample input files.
     */
    public Election(int k)
    {
       this("election" + k + ".txt");
    }
    
    /**
     * Returns the candidates list.
     */
    public ArrayList<Candidate> getCandidates()
    {
       // TODO 14
       return candidates;
    }
    
    /**
     * Returns the papers list.
     */
    public ArrayList<VotingPaper> getPapers()
    {
       // TODO 15
       return papers;
    }
    
    /**
     * Returns the read-in file contents.
     */
    public ArrayList<String> getFile()
    {
        //TODO 16
        return file.getLines();
        
    }
    
    /**
     * Use the file information to initialise the other two fields.
     * Reads the candidates, then discards exactly one blank line, then reads the voting papers.
     * See the sample input files for examples of the format.
     */
    public void processFile() 
    {
       // TODO 17
    	boolean foundNewLine = false;  
    	for(String line : this.file.getLines()){
    		if(line.equals("") & !foundNewLine){
    		  foundNewLine = true;
    		  continue;
    		}
    		
    		if(!foundNewLine)
    		  this.candidates.add(new Candidate(line));
    		else
    		  this.papers.add(new VotingPaper(line));
      }    
    }
    
    

    
    /**
     * Adds each formal vote to each candidate, both numbers of votes and numbers of wins.
     * Returns the number of informal votes.
     */
    public int conductCount()
    {
       // TODO 22
    	int informals=0;
       for(VotingPaper vp: papers){
    	   if(vp.isFormal(candidates.size())){
    		  vp.updateVoteCounts(candidates);
    		  vp.updateWinCounts(candidates);
    	   }else{
    		   informals++;
    	   }
       }
    	return informals;
    }

    /**
     * Returns and prints a summary of the election result. 
     * See the sample output files for the required format. 
     */
    public String getStandings()
    {
       // TODO 18
    	String s = "";
    	for(Candidate c: this.candidates){
    		s+=c.getStanding();
    		s+="\n";
    	}
    	System.out.println(s);
       return s;
    }
    
    /**
     * Returns the winner of the election. 
     * Selects the candidate with the highest number of votes; if multiple 
     * candidates are equal, selects the one with the highest number of wins. 
    */
    public Candidate winner()
    {
       // TODO 23
    	ArrayList<Candidate> winners=new ArrayList<>();
    	int index = 0;
    	int highestVote = candidates.get(index).getNoOfVotes();
    	for(int i=1;i<candidates.size();i++){
    		if(highestVote<candidates.get(i).getNoOfVotes()){
    			highestVote=candidates.get(i).getNoOfVotes();
    			index=i;
    		}
    	}
    	
    	winners.add(candidates.get(index));
    	
    	for(int j=0;j<candidates.size();j++){
    		if(highestVote==candidates.get(j).getNoOfVotes() && j!=index){
    			index=j;
    			winners.add(candidates.get(index));
    		}
    	}

    	if(winners.size()==1){
    		return winners.get(0);
    	}else{
    		int winIndex = 0;
    		double highestWins = winners.get(0).getNoOfWins();
    		
    		for(int i=1;i<winners.size();i++){
    			if(highestWins<winners.get(i).getNoOfWins()){
    				highestWins=winners.get(i).getNoOfWins();
    				winIndex=i;
    			}
    		}
    		
    		return winners.get(winIndex);
    	}
    }
}