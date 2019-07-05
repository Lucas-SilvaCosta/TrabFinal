import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

class FrameFimBlackjack extends JFrame{
	
	private JButton menu, novoJogo, save;
	private JTextField nome;
	private String score;

	public FrameFimBlackjack(String res, String s){
		super("Fim");
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(0,153,25));

		score = s;

		JLabel resultado = new JLabel(res, SwingConstants.CENTER);
		resultado.setFont(new Font("Serif", Font.PLAIN, 32));
		resultado.setPreferredSize(new Dimension(250, 100));
		add(resultado);

		JPanel panelHis = new JPanel();
		panelHis.setLayout(new BoxLayout(panelHis, BoxLayout.X_AXIS));
		//panelHis.setPreferredSize(new Dimension(250, 30));
		panelHis.setBackground(new Color(0,153,25));
		nome = new JTextField("Nome");
		nome.setPreferredSize(new Dimension(150, 25));
		ButtonHandler bHandler = new ButtonHandler();
		save = new JButton("Save");
		save.setMaximumSize(new Dimension(80, 25));
		save.addActionListener(bHandler);
		panelHis.add(nome);
		panelHis.add(Box.createHorizontalStrut(15));
		panelHis.add(save);
		add(panelHis);

		add(Box.createVerticalStrut(50));

		JPanel panelBut = new JPanel();
		panelBut.setLayout(new BoxLayout(panelBut, BoxLayout.X_AXIS));
		//panelBut.setPreferredSize(new Dimension(250, 30));
		panelBut.setBackground(new Color(0,153,25));
		menu = new JButton("Menu");
		menu.setMaximumSize(new Dimension(110, 25));
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
            if(e.getSource() == save){
            	FileManager f =new FileManager();
            	f.openFile();
            	f.addTexts(nome.getText(), score);
            	f.closeFile();
            }
       	}
	}

	public static void main(String[] args){
		FrameFimBlackjack f = new FrameFimBlackjack("AA", "1/1/1");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(250, 200);
		f.setVisible(true);
	}
}