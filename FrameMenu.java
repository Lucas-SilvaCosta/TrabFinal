import java.awt.FlowLayout;
//import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class FrameMenu extends JFrame{

	public JButton jogar, tutoriais, historico;
	public JLabel titulo;

	public FrameMenu(){
		super("Menu");

		setLayout(new FlowLayout());
		titulo = new JLabel("Titulo", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 32));
		//titulo.setMinimumSize(new Dimension(100, 400));
		//titulo.setMaximumSize(new Dimension(100, 400));
		//titulo.setSize(new Dimension(100, 400));
		titulo.setPreferredSize(new Dimension(400, 100));
		//titulo.setOpaque(true);
		//titulo.setBackground(Color.RED);
		//System.out.println(titulo.isBackgroundSet());
		//System.out.println(titulo.getBackground());
		//getContentPane().setBackground(Color.BLACK);
		add(titulo);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		jogar = new JButton("Jogar");
		tutoriais = new JButton("Tutoriais");
		historico = new JButton("Histórico");
		System.out.println(tutoriais.getPreferredSize());
		jogar.setMaximumSize(tutoriais.getPreferredSize());
		buttonPanel.add(jogar);
		buttonPanel.add(tutoriais);
		buttonPanel.add(historico);
		add(buttonPanel);


	}

	public static void main(String args[]){
		FrameMenu f = new FrameMenu();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
	}
}