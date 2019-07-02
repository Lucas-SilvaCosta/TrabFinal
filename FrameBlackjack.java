import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import javax.swing.*;

public class FrameBlackjack extends JFrame{

	private BorderLayout bl;
	private JPanel panelDeck, panelCPU, panelPlayer, playerHand, cpuHand;
	private JLabel labelDeck, labelRod, labelVit, labelEmp, labelDer;
	private JButton comprar, terminar;
	private ArrayList<JLabel> labelPlayerCards, labelCpuCards;
	private ArrayList<Card> playerCards, cpuCards;
	private final Deck deckManager = new Deck();
	private final ArrayList<Card> doubleDeck = createDoubleDeck();
	/* build = true, depois de construir o frame pela primeira vez se torna false */
	private boolean cpuDone = false;
	private boolean playerTerminou;
	private int rodadas, vitorias, empates, derrotas;

	public FrameBlackjack(){
		super("Blackjack");
		bl = new BorderLayout(5, 5);
		setLayout(bl);
		getContentPane().setBackground(new Color(0,153,25));

		rodadas  = 1;
		vitorias = 0;
		empates  = 0;
		derrotas = 0;

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
		cpuHand = new JPanel();
		cpuHand.setAlignmentY(Component.CENTER_ALIGNMENT);
		cpuHand.setLayout(new BoxLayout(cpuHand, BoxLayout.X_AXIS));
		cpuHand.setBackground(new Color(0,153,25));
		cpuCards = new ArrayList<Card>();
		compraCarta(cpuCards);
		compraCarta(cpuCards);
		labelCpuCards = new ArrayList<JLabel>();
		/*cpuCards.add(new JLabel("uma carta"));
		cpuCards.add(new JLabel("outra carta"));
		cpuCards.add(new JLabel("mais uma carta"));
		cpuCards.add(new JLabel("mds carta"));*/

		labelCpuCards.trimToSize();
		for(int i=0; i<cpuCards.size(); i++){
			labelCpuCards.add(new JLabel(cpuCards.get(i).getName()));
			labelCpuCards.get(i).setPreferredSize(new Dimension(110, 180));
			labelCpuCards.get(i).setMaximumSize(new Dimension(110, 180));
			labelCpuCards.get(i).setOpaque(true);
			labelCpuCards.get(i).setBackground(Color.RED);
			cpuHand.add(labelCpuCards.get(i));
			cpuHand.add(Box.createHorizontalStrut(20));

		}
		panelCPU.add(cpuHand);


		panelPlayer = new JPanel();
		panelPlayer.setLayout(new BoxLayout(panelPlayer, BoxLayout.Y_AXIS));
		panelPlayer.setBackground(new Color(0,153,25));
		panelPlayer.setPreferredSize(new Dimension(800, 250));
		playerHand = new JPanel();
		playerHand.setAlignmentY(Component.CENTER_ALIGNMENT);
		playerHand.setLayout(new BoxLayout(playerHand, BoxLayout.X_AXIS));
		playerHand.setBackground(new Color(0,153,25));
		playerCards = new ArrayList<Card>();
		compraCarta(playerCards);
		compraCarta(playerCards);
		labelPlayerCards = new ArrayList<JLabel>();
		/*playerCards.add(new JLabel("uma carta"));
		playerCards.add(new JLabel("outra carta"));
		playerCards.add(new JLabel("mais uma carta"));
		playerCards.trimToSize();*/
		for(int i=0; i<playerCards.size(); i++){
			labelPlayerCards.add(new JLabel(playerCards.get(i).getName()));
			labelPlayerCards.get(i).setPreferredSize(new Dimension(110, 180));
			labelPlayerCards.get(i).setMaximumSize(new Dimension(110, 180));
			labelPlayerCards.get(i).setOpaque(true);
			labelPlayerCards.get(i).setBackground(Color.RED);
			playerHand.add(labelPlayerCards.get(i));
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
            		//System.out.println("entrou");
            		compraCarta(playerCards);
            		attMao(playerHand, labelPlayerCards, playerCards);
            		if(!cpuDone){ cpu(); }
            	}
            	if(e.getSource() == terminar){
            		playerTerminou = true;
            		if(cpuDone){
            			fimRodada();
            		}else{
            			cpu();
            		}
            	}
            }
       	}
	}

	public ArrayList<Card> createDoubleDeck(){
		ArrayList<Card> aux = new ArrayList<Card>();
		aux.addAll(deckManager.createNormalDeck());
		aux.addAll(deckManager.createNormalDeck());
		deckManager.shuffleDeck(aux);
		//deckCreator.printDeck(aux);
		//System.out.print(aux.size());
		return aux;
	}

	public void compraCarta(ArrayList<Card> mao){
		//System.out.println("Entrou aqui tbm");
		mao.add(doubleDeck.get(0));
		doubleDeck.remove(0);
		mao.trimToSize();
		doubleDeck.trimToSize();
		//deckManager.printDeck(mao);
	}

	public void attMao(JPanel p, ArrayList<JLabel> al, ArrayList<Card> ac){
		for(int i=al.size(); i<ac.size(); i++){
			//System.out.println();
			//System.out.println("i: "+i+"===========================");
			//System.out.println("ac.get("+i+").getName(): "+ac.get(i).getName());
			al.add(new JLabel(ac.get(i).getName()));
			//System.out.println(al.get(i).getText());
			al.get(i).setPreferredSize(new Dimension(110, 180));
			al.get(i).setMaximumSize(new Dimension(110, 180));
			al.get(i).setOpaque(true);
			al.get(i).setBackground(Color.RED);
			p.add(al.get(i));
			p.add(Box.createHorizontalStrut(20));
		}
		//p.setBackground(Color.WHITE);
		p.repaint();
		setVisible(false);
		setVisible(true);
	}

	public void cpu(){
		int[] plays = new int[3];
		for(int i=0; i<plays.length; i++){
			plays[i] = 0;
		}
		// looks for Aces
		int numbAces = 0;
		for(int i=0; i<cpuCards.size(); i++){
			if(cpuCards.get(i).getValue() == 1){
				numbAces = numbAces+1;
			}
		}
		if(numbAces>2){ numbAces=2; }
		
		for(int i=0; i<cpuCards.size(); i++){
			plays[0] = plays[0]+cpuCards.get(i).getValue();
		}
		if(numbAces>0){
			plays[1] = plays[0]+10;
		}
		if(numbAces>1){
			plays[2] = plays[1]+10;
		}

		for(int i=0; i<plays.length; i++){
			System.out.print("plays["+i+"] = "+plays[i]+";   ");
			if(plays[i] >= 19 && plays[i] <= 21){ cpuDone = true; }
		}
		if(plays[0] > 21 || plays[1] > 21 || plays[2] > 21){
			if(plays[0] > 21 && plays[1] == 0 && plays[2] == 0){ cpuDone = true; }
			if(plays[0] > 21 && plays[1] > 21 && plays[2] == 0){ cpuDone = true; }
			if(plays[0] > 21 && plays[1] > 21 && plays[2] > 21){ cpuDone = true; }
		}
		System.out.println();

		if(!cpuDone){
			compraCarta(cpuCards);
			attMao(cpuHand, labelCpuCards, cpuCards);
			if(playerTerminou){ cpu(); }
		}else{
			if(playerTerminou){ fimRodada(); }
		}
	}

	public void fimRodada(){
		System.out.println("s");
	}

	public static void main(String args[]){
		FrameBlackjack f = new FrameBlackjack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 800);
		f.setVisible(true);
	}
}