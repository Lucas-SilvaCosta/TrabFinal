public class Card{
	private String suit;
	private String number;
	private int value;

	public Card(String s, String n, int v){
		suit = s;
		number = n;
		value = v;
	}

	public String getSuit(){ return suit; }
	public String getNumber(){ return number; }
	public int getValue(){ return value; }
	public String getName(){ return number+" of "+suit; }
}