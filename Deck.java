import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.ImageIcon;


public class Deck{
	public static final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	public static final String[] numbers = {"Ace", "Two", "Three", "Four", "Five", "Six",
		"Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	public static final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};


	public Deck(){}

	public ArrayList<Card> createNormalDeck(boolean red){
		Card[] deck = new Card[52];
		for(int i=0; i<suits.length; i++){
			for(int j=0; j<numbers.length; j++){
				deck[(i*13)+j] = new Card(suits[i], numbers[j], values[j], new ImageIcon(getClass().getResource("cartas/"+numbers[j]+suits[i]+".png")), red);
			}
		}
		ArrayList<Card> aux = new ArrayList<Card>(Arrays.asList( deck ));
		return aux;
	}

	public void printDeck(ArrayList<Card> deck){
		for(int i=0; i<deck.size(); i++){
			//System.out.println(deck[i].getNumber()+" of "+deck[i].getSuit());
			System.out.printf("%-5s of %-10s\n", deck.get(i).getNumber(), deck.get(i).getSuit());
		}
	}

	public void shuffleDeck(ArrayList<Card> deck){
		Collections.shuffle(deck);
	}
}