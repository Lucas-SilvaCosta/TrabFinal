import java.util.List;
import java.util.Arrays;

public class Deck{
	public static final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	public static final String[] numbers = {"Ace", "Two", "Three", "Four", "Five", "Six",
		"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};


	public Deck(){}

	public List<Card> createNormalDeck(){
		Card[] deck = new Card[52];
		for(int i=0; i<suits.length; i++){
			for(int j=0; j<numbers.length; j++){
				deck[(i*13)+j] = new Card(suits[i], numbers[j]);
			}
		}
		return Arrays.asList( deck );
	}

	public void printDeck(List<Card> deck){
		for(int i=0; i<deck.size(); i++){
			//System.out.println(deck[i].getNumber()+" of "+deck[i].getSuit());
			System.out.printf("%-5s of %-10s\n", deck.get(i).getNumber(), deck.get(i).getSuit());
		}
	}
}