public class Blackjack extends Jogos{

	public Blackjack(){
		super.setNome("Blackjack");
	}

	public String getNome(){ return super.getNome(); }

	@Override
	public void Tutorial(){
		System.out.println("aa");
		FrameTutorial f = new FrameTutorial(getNome(), "aaa");
		f.create(f);
	}
}