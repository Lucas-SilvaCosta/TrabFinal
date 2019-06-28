import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class FrameTutorial extends JFrame{

	public JLabel titulo, descricao;

	public FrameTutorial(String n, String d){
		super("Tutorial");
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(0,153,25));

		titulo = new JLabel(n, SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 32));
		titulo.setPreferredSize(new Dimension(400, 100));
		add(titulo);

		add(Box.createVerticalStrut(50));

		JPanel descPanel = new JPanel();
		descricao = new JLabel(d);
		descricao.setUI(MultiLineLabelUI.labelUI);
		descricao.setFont(new Font("Serif", Font.PLAIN, 18));
		descPanel.setPreferredSize(new Dimension(400, 200));
		descPanel.setBackground(new Color(0,153,25));
		descPanel.add(descricao);
		add(descPanel);

		add(Box.createVerticalStrut(75));
		JButton voltar = new JButton("Voltar");
		voltar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
					FrameMenu f = new FrameMenu();
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setSize(400, 400);
					f.setVisible(true);
				}
		});
		add(voltar);
	}

	public void create(FrameTutorial f){
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
	}

	
	public static void main(String args[]){
		FrameTutorial f = new FrameTutorial("teste", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa testando aaaaaaaaaaaaaaaaaaaa");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
	}
	
}