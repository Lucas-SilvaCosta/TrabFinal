import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

class FrameFimBlackjack extends JFrame{
	
	private JButton menu, novoJogo;

	public FrameFimBlackjack(String res){
		super("Fim");
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(0,153,25));

		JLabel resultado = new JLabel(res, SwingConstants.CENTER);
		resultado.setFont(new Font("Serif", Font.PLAIN, 32));
		resultado.setPreferredSize(new Dimension(250, 100));
		add(resultado);

		JPanel panelBut = new JPanel();
		panelBut.setLayout(new BoxLayout(panelBut, BoxLayout.X_AXIS));
		panelBut.setBackground(new Color(0,153,25));
		menu = new JButton("Menu");
		menu.setMaximumSize(new Dimension(110, 25));
		ButtonHandler bHandler = new ButtonHandler();
		menu.addActionListener(bHandler);
		novoJogo = new JButton("Novo Jogo");
		novoJogo.setMaximumSize(new Dimension(110, 25));
		novoJogo.addActionListener(bHandler);
		panelBut.add(menu);
		panelBut.add(Box.createHorizontalStrut(15));
		panelBut.add(novoJogo);

		add(panelBut);
	}

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menu){
            	dispose();
            	FrameMenu f = new FrameMenu();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(400, 400);
				f.setVisible(true);
            }
            if(e.getSource() == novoJogo){
            	dispose();
            	Blackjack b = new Blackjack();
            	b.jogar();
            }
       	}
	}

	public static void main(String[] args){
		FrameFimBlackjack f = new FrameFimBlackjack("AA");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 200);
		f.setVisible(true);
	}
}