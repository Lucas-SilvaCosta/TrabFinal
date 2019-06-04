public class Card{
	private String suit;
	private String number;

	public Card(String s, String n){
		suit = s;
		number = n;
	}

	public String getSuit(){ return suit; }
	public String getNumber(){ return number; }
}