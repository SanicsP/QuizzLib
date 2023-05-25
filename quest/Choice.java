package quest;

public class Choice {
//field
	private String enonce;
	private boolean good;
//constructor
	public Choice(String enonce , boolean isgood) {
		this.enonce = enonce;
		this.good = isgood;
	}
	
//getters 
	public String getEnonce() {
		return enonce;
	}
	
	public boolean isGood() {
		return good;
	}
	
//setters 
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	public void setGood(boolean isgood) {
		this.good = isgood;
	}
	
	
}

