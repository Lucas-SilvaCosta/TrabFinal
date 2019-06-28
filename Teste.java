import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Teste{

	public static void main(String args[]){
		Deck d = new Deck();
		ArrayList<Card> myDeck = d.createNormalDeck();

		d.printDeck(myDeck);
		System.out.println("\nShuffle? y/n");
		Scanner scan = new Scanner( System.in );
		String aux = scan.next();
		System.out.println("\n\n");
		if(aux.equals("y")){
			d.shuffleDeck(myDeck);
			d.printDeck(myDeck);
		}
	}
}