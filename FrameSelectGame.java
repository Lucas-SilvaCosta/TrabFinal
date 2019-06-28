import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class FrameSelectGame extends JFrame{

	private static Jogos[] jogos = {new Blackjack()};
	private static JButton[] but = new JButton[jogos.length];
	private int informaBotaoClicado;
	private JButton voltar;

	/* 	informaBotaoClicado --> indica qual botao chamou esse frame, parece ele saber
	 * 			     		    que ação tomar.
	 * 
	 *	0 - jogar
	 *	1 - tutoriais
	 */
	public FrameSelectGame(int informaBotaoClicado){
		super("Selecione o jogo...");
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(0,153,25));

		this.informaBotaoClicado = informaBotaoClicado;

		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		buttons.setBackground(new Color(0,153,25));
		buttons.add(Box.createVerticalStrut(75));
		ButtonHandler bHandler = new ButtonHandler(); 
		for(int i=0; i<jogos.length; i++){
			but[i] = new JButton(jogos[i].getNome());
			but[i].addActionListener(bHandler);
			but[i].setMaximumSize(new Dimension(100, 25));
			buttons.add(but[i]);
			if(!(jogos.length-1 == i)){
				buttons.add(Box.createVerticalStrut(15));
			}
		}
		voltar = new JButton("Voltar");
		voltar.addActionListener(bHandler);
		voltar.setMaximumSize(new Dimension(100, 25));
		buttons.add(Box.createVerticalStrut(80));
		buttons.add(voltar);
		add(buttons);

		
	}

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            if(e.getSource() == voltar){
            	dispose();
            	FrameMenu f = new FrameMenu();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(400, 400);
				f.setVisible(true);
            }
            for(int i=0; i<jogos.length; i++){
	            if(e.getSource() == but[i]){
	            	if(informaBotaoClicado == 0){
	            		System.out.println("certo");
	            	}
	            	if(informaBotaoClicado == 1){
	            		dispose();
	            		jogos[i].Tutorial();
	            	}
	            }
	        }
       	}
	}

	public void create(FrameSelectGame f){
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, (200+(jogos.length*40)));
		f.setVisible(true);
	}

	/*
	public static void main(String args[]){
		FrameSelectGame f = new FrameSelectGame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
	}
	*/
}