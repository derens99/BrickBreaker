package brick;

public class Score {
	
	private int score;
	
	public Score(){
		score = 0;
	}
	
	public void add(int s){
		score += s;
	}
	
	public String getScore(){
		return "Score: " + score;
	}

}
