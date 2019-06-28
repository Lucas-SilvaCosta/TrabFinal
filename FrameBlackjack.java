import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class FrameBlackjack extends JFrame{

	private BorderLayout bl;
	private JPanel panelDeck, panelCPU, panelPlayer;
	private JLabel labelDeck;
	private JButton comprar, terminar;
	private ArrayList<JLabel> playerCards, cpuCards;
	private ArrayList<Card> doubleDeck = createDoubleDeck();
	private boolean playerTerminou;

	public FrameBlackjack(){
		super("Blackjack");
		bl = new BorderLayout(5, 5);
		setLayout(bl);
		getContentPane().setBackground(new Color(0,153,25));



		panelDeck = new JPanel();
		panelDeck.setLayout(new FlowLayout());
		panelDeck.setBackground(new Color(0,153,25));
		panelDeck.setPreferredSize(new Dimension(250, 545));
		JPanel panelDeckComp = new JPanel();
		panelDeckComp.setLayout(new BoxLayout(panelDeckComp, BoxLayout.Y_AXIS));
		panelDeckComp.setBackground(new Color(0,153,25));
		labelDeck = new JLabel("Deck");
		labelDeck.setPreferredSize(new Dimension(110, 180));
		labelDeck.setMaximumSize(new Dimension(110, 180));
		labelDeck.setOpaque(true);
		labelDeck.setBackground(Color.WHITE);
		comprar = new JButton("Comprar");
		comprar.setMaximumSize(new Dimension(110, 25));
		terminar = new JButton("Terminar");
		terminar.setMaximumSize(new Dimension(110, 25));
		ButtonHandler bHandler = new ButtonHandler();
		comprar.addActionListener(bHandler);
		terminar.addActionListener(bHandler);
		panelDeckComp.add(Box.createVerticalStrut(120));
		panelDeckComp.add(labelDeck);
		panelDeckComp.add(Box.createVerticalStrut(15));
		panelDeckComp.add(comprar);
		panelDeckComp.add(Box.createVerticalStrut(15));
		panelDeckComp.add(terminar);
		panelDeck.add(panelDeckComp);

		panelCPU = new JPanel();
		panelCPU.setBackground(new Color(0,153,25));
		panelCPU.setLayout(new BoxLayout(panelCPU, BoxLayout.Y_AXIS));
		JPanel cpuHand = new JPanel();
		cpuHand.setAlignmentY(Component.CENTER_ALIGNMENT);
		cpuHand.setLayout(new BoxLayout(cpuHand, BoxLayout.X_AXIS));
		cpuHand.setBackground(new Color(0,153,25));
		cpuCards = new ArrayList<JLabel>();
		/*cpuCards.add(new JLabel("uma carta"));
		cpuCards.add(new JLabel("outra carta"));
		cpuCards.add(new JLabel("mais uma carta"));
		cpuCards.add(new JLabel("mds carta"));*/

		cpuCards.trimToSize();
		for(int i=0; i<cpuCards.size(); i++){
			cpuCards.get(i).setPreferredSize(new Dimension(110, 180));
			cpuCards.get(i).setMaximumSize(new Dimension(110, 180));
			cpuCards.get(i).setOpaque(true);
			cpuCards.get(i).setBackground(Color.RED);
			cpuHand.add(cpuCards.get(i));
			cpuHand.add(Box.createHorizontalStrut(20));

		}
		panelCPU.add(cpuHand);


		panelPlayer = new JPanel();
		panelPlayer.setLayout(new BoxLayout(panelPlayer, BoxLayout.Y_AXIS));
		panelPlayer.setBackground(new Color(0,153,25));
		panelPlayer.setPreferredSize(new Dimension(800, 250));
		JPanel playerHand = new JPanel();
		playerHand.setAlignmentY(Component.CENTER_ALIGNMENT);
		playerHand.setLayout(new BoxLayout(playerHand, BoxLayout.X_AXIS));
		playerHand.setBackground(new Color(0,153,25));
		playerCards = new ArrayList<JLabel>();
		/*playerCards.add(new JLabel("uma carta"));
		playerCards.add(new JLabel("outra carta"));
		playerCards.add(new JLabel("mais uma carta"));*/
		playerCards.trimToSize();
		for(int i=0; i<playerCards.size(); i++){
			playerCards.get(i).setPreferredSize(new Dimension(110, 180));
			playerCards.get(i).setMaximumSize(new Dimension(110, 180));
			playerCards.get(i).setOpaque(true);
			playerCards.get(i).setBackground(Color.RED);
			playerHand.add(playerCards.get(i));
			playerHand.add(Box.createHorizontalStrut(20));

		}
		panelPlayer.add(playerHand);




		add(panelDeck, BorderLayout.EAST);
		add(panelCPU, BorderLayout.CENTER);
		add(panelPlayer, BorderLayout.SOUTH);
	}

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            if(playerTerminou == false){
            	if(e.getSource() == comprar){

            	}
            	if(e.getSource() == terminar){
            		playerTerminou == true;
            	}
            }
       	}
	}

	public ArrayList<Card> createDoubleDeck(){
		ArrayList<Card> aux = new ArrayList<Card>();
		Deck deckCreator = new Deck();
		aux.addAll(deckCreator.createNormalDeck());
		aux.addAll(deckCreator.createNormalDeck());
		deckCreator.shuffleDeck(aux);
		//deckCreator.printDeck(aux);
		//System.out.print(aux.size());
		return aux;
	}

	public static void main(String args[]){
		FrameBlackjack f = new FrameBlackjack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 800);
		f.setVisible(true);
	}
}