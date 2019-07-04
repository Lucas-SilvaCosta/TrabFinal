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
import javax.swing.*;

public class FrameBlackjack extends JFrame{

	private BorderLayout bl;
	private JPanel panelDeck, panelCPU, panelPlayer, playerHand, cpuHand, panelInfo;
	private JLabel labelDeck, labelRod, labelPlacar;
	private JButton comprar, terminar, reset;
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
		panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		panelInfo.setBackground(new Color(0,153,25));
		labelRod = new JLabel("Rodada: "+rodadas);
		labelRod.setBackground(Color.RED);
		labelPlacar = new JLabel(vitorias+"V "+empates+"E "+derrotas+"D");
		labelPlacar.setBackground(Color.RED);
		panelInfo.add(labelRod);
		panelInfo.add(Box.createVerticalStrut(15));
		panelInfo.add(labelPlacar);
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
		reset = new JButton("Prox. rodada");
		reset.setMaximumSize(new Dimension(110, 25));
		reset.setVisible(false);
		ButtonHandler bHandler = new ButtonHandler();
		comprar.addActionListener(bHandler);
		terminar.addActionListener(bHandler);
		reset.addActionListener(bHandler);
		panelDeckComp.add(Box.createVerticalStrut(120));
		panelDeckComp.add(panelInfo);
		panelDeckComp.add(Box.createVerticalStrut(15));
		panelDeckComp.add(labelDeck);
		panelDeckComp.add(Box.createVerticalStrut(15));
		panelDeckComp.add(comprar);
		panelDeckComp.add(Box.createVerticalStrut(15));
		panelDeckComp.add(terminar);
		panelDeckComp.add(Box.createVerticalStrut(15));
		panelDeckComp.add(reset);
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

		labelCpuCards.trimToSize();
		for(int i=0; i<cpuCards.size(); i++){
			labelCpuCards.add(new JLabel(cpuCards.get(i).getImgTras()));
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
			labelPlayerCards.add(new JLabel(playerCards.get(i).getImgFrente()));
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
            }else{
            	if(e.getSource() == reset){
            		if(rodadas == 6){
            			fim();
            		}else{
	            		reset.setVisible(false);
	            		novaRodada();
	            	}
            	}
            }
       	}
	}

	public ArrayList<Card> createDoubleDeck(){
		ArrayList<Card> aux = new ArrayList<Card>();
		aux.addAll(deckManager.createNormalDeck(false));
		aux.addAll(deckManager.createNormalDeck(true));
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

	public void limpaMao(JPanel p, ArrayList<JLabel> al){
		/*for(int i=0; i<al.size(); i++){
			//System.out.println("al.get("+i+")= "+al.get(i).getText());
			p.remove(al.get(i));
			//p.remove(playerHand);
		}*/
		p.removeAll();
		p.repaint();
		setVisible(false);
		setVisible(true);
	}

	public void attMao(JPanel p, ArrayList<JLabel> al, ArrayList<Card> ac){
		limpaMao(p, al);
		for(int i=0; i<ac.size(); i++){
			//System.out.println();
			//System.out.println("i: "+i+"===========================");
			//System.out.println("ac.get("+i+").getName(): "+ac.get(i).getName());
			if(p == playerHand){ al.add(i, new JLabel(ac.get(i).getImgFrente())); }else{
				al.add(i, new JLabel(ac.get(i).getImgTras()));
			}
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
			if(plays[i] >= 17 && plays[i] <= 21){ cpuDone = true; }
		}
		System.out.println();
		if(plays[0] > 21 || plays[1] > 21 || plays[2] > 21){
			if(plays[0] > 21 && plays[1] == 0 && plays[2] == 0){ cpuDone = true; }
			if(plays[0] > 21 && plays[1] > 21 && plays[2] == 0){ cpuDone = true; }
			if(plays[0] > 21 && plays[1] > 21 && plays[2] > 21){ cpuDone = true; }
		}

		if(!cpuDone){
			compraCarta(cpuCards);
			attMao(cpuHand, labelCpuCards, cpuCards);
			if(playerTerminou){ cpu(); }
		}else{
			if(playerTerminou){ fimRodada(); }
		}
	}

	public void fimRodada(){
		if(rodadas<=5){
			if(rodadas == 5){ 
				reset.setText("Fim");
			}
			rodadas = rodadas+1;
			labelRod.setText("Rodada: "+rodadas);
			labelRod.setOpaque(true);
			if(verificaResultado() == 'V'){ vitorias = vitorias+1; }
			if(verificaResultado() == 'E'){ empates = empates+1; }
			if(verificaResultado() == 'D'){ derrotas = derrotas+1; }
			labelPlacar.setText(vitorias+"V "+empates+"E "+derrotas+"D");
			labelPlacar.setOpaque(true);
			//deckManager.printDeck(cpuCards);
			//deckManager.printDeck(playerCards);
			mostraCartas();
		}
	    reset.setVisible(true);
	}

	public void novaRodada(){
		int aux = cpuCards.size();
		for(int i=0; i<aux; i++){
			doubleDeck.add(cpuCards.get(0));
			cpuCards.remove(0);
		}
		aux = playerCards.size();
		for(int i=0; i<aux; i++){
			doubleDeck.add(playerCards.get(0));
			playerCards.remove(0);
		}
		doubleDeck.trimToSize();
		cpuCards.trimToSize();
		playerCards.trimToSize();
		deckManager.shuffleDeck(doubleDeck);
		limpaMao(playerHand, labelPlayerCards);
		limpaMao(cpuHand, labelCpuCards);
		playerTerminou = false;
		cpuDone = false;
		labelRod.setOpaque(false);
		labelPlacar.setOpaque(false);
		compraCarta(cpuCards);
		compraCarta(cpuCards);
		attMao(cpuHand, labelCpuCards, cpuCards);
		compraCarta(playerCards);
		compraCarta(playerCards);
		attMao(playerHand, labelPlayerCards, playerCards);
	}

	public void mostraCartas(){
		for(int i=0; i<cpuCards.size(); i++){
			labelCpuCards.get(i).setIcon(cpuCards.get(i).getImgFrente());
		}
		cpuHand.repaint();
		setVisible(false);
		setVisible(true);
	}

	// 'V'  player vencer
	// 'E'	empate
	// 'D'	player perdeu
	public char verificaResultado(){
		int maoCpu = 0;
		int ace = 0;
		for(int i=0; i<cpuCards.size(); i++){
			maoCpu = maoCpu+cpuCards.get(i).getValue();
			if(cpuCards.get(i).getValue() == 1){ ace=ace+1; }
		}
		if(maoCpu>21){
			maoCpu = 0;
		}else{
			for(int i=0; i<ace; i++){
				maoCpu = maoCpu+10;
				if(maoCpu>21){ maoCpu = maoCpu-10; }
			}
		}
		int maoPlayer = 0;
		ace = 0;
		for(int i=0; i<playerCards.size(); i++){
			maoPlayer = maoPlayer+playerCards.get(i).getValue();
			if(playerCards.get(i).getValue() == 1){ ace=ace+1; }
		}
		if(maoPlayer>21){
			maoPlayer = 0;
		}else{
			for(int i=0; i<ace; i++){
				maoPlayer = maoPlayer+10;
				if(maoPlayer>21){ maoPlayer = maoPlayer-10; }
			}
		}

		if(maoCpu != maoPlayer){
			if(maoCpu > maoPlayer){ return 'D'; }
			if(maoCpu < maoPlayer){ return 'V'; }
		}else{ return 'E'; }
		return 'R';
	}

	public void fim(){
		FrameFimBlackjack f;
		if(vitorias == derrotas){
			f = new FrameFimBlackjack("Empate");
		}else{
			if(vitorias>derrotas){
				f = new FrameFimBlackjack("Vitória");
			}else{
				f = new FrameFimBlackjack("Derrota");
			}
		}
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 200);
		f.setVisible(true);
		dispose();
	}

	public static void create(){
		FrameBlackjack f = new FrameBlackjack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1200, 800);
		f.setVisible(true);
	}

	public static void main(String[] args){
		FrameBlackjack f = new FrameBlackjack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1200, 800);
		f.setVisible(true);
	}
}