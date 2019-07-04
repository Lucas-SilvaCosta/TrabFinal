import java.awt.Dimension;
import javax.swing.*;

public class Teste extends JFrame{

	public Teste(){
		super("Teste");
		JLabel teste= new JLabel(new ImageIcon(getClass().getResource("cartas\\AceClubs.png")));
		teste.setIcon(new ImageIcon(getClass().getResource("cartas\\TwoClubs.png")));
		teste.setPreferredSize(new Dimension(110, 180));
		teste.setMaximumSize(new Dimension(110, 180));
		add(teste);
	}

	public static void main(String args[]){
		Teste t = new Teste();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setSize(400, 200);
		t.setVisible(true);
	}
}