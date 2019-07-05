import java.awt.Dimension;
import javax.swing.*;

public class Teste extends JFrame{

	public Teste(){
		super("Teste");
		JLabel teste= new JLabel(new ImageIcon(getClass().getResource("cartas/AceClubs.png")));
		teste.setIcon(new ImageIcon(getClass().getResource("cartas/TwoClubs.png")));
		System.out.println("");
		teste.setPreferredSize(new Dimension(110, 180));
		teste.setMaximumSize(new Dimension(110, 180));
		add(teste);
	}

	public static void main(String args[]){
		String teste = "aaa \n aaaa";
		String aux[] = teste.split("\\n");
		for(int i=0; i<aux.length; i++){
			System.out.println(aux[i]);
		}
	}
}