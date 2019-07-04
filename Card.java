import javax.swing.*;

public class Card{
	private String suit;
	private String number;
	private int value;
	private Icon imgFrente;
	private Icon imgTras;

	public Card(String s, String n, int v, Icon i, boolean red){
		suit = s;
		number = n;
		value = v;
		imgFrente = i;
		if(red){
			imgTras = new ImageIcon(getClass().getResource("cartas\\Red.png"));
		}else{
			imgTras = new ImageIcon(getClass().getResource("cartas\\Blue.png"));
		}
	}

	public String getSuit(){ return suit; }
	public String getNumber(){ return number; }
	public int getValue(){ return value; }
	public String getName(){ return number+" of "+suit; }
	public Icon getImgFrente(){ return imgFrente; }
	public Icon getImgTras(){ return imgTras; }
	public void changeBack(){ imgTras = new ImageIcon(getClass().getResource("cartas\\Red.png")); }
}