import java.awt.FlowLayout;
//import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
		getContentPane().setBackground(new Color(0,153,25));
		add(titulo);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		ButtonListener bListener = new ButtonListener();
		jogar = new JButton("Jogar");
		jogar.addActionListener(bListener);
		tutoriais = new JButton("Tutoriais");
		tutoriais.addActionListener(bListener);
		historico = new JButton("Historico");
		historico.addActionListener(bListener);
		//System.out.println(tutoriais.getPreferredSize());
		jogar.setMaximumSize(tutoriais.getPreferredSize());
		buttonPanel.add(Box.createVerticalStrut(35));
		buttonPanel.add(jogar);
		buttonPanel.add(Box.createVerticalStrut(15));
		buttonPanel.add(tutoriais);
		buttonPanel.add(Box.createVerticalStrut(15));
		buttonPanel.add(historico);
		buttonPanel.setBackground(new Color(0,153,25));
		add(buttonPanel);

	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            //System.out.println((e.getSource() == jogar));
            if(e.getSource() == jogar){
            	dispose();
            	FrameSelectGame s = new FrameSelectGame(0);
				s.create(s);
            }
            if(e.getSource() == tutoriais){
            	dispose();
            	FrameSelectGame s = new FrameSelectGame(1);
				s.create(s);
            }
       	}
	}

	public static void main(String args[]){
		FrameMenu f = new FrameMenu();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
	}
}