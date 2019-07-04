public class Blackjack extends Jogos{

	public Blackjack(){
		super.setNome("Blackjack");
		super.setTutorial("Jogue contra o Computador. Você começa com duas cartas e o objetivo é fazer com que a soma das cartas chegue a 21. A cada rodada um jogador pode comprar uma carta, ou passar caso sua soma já seja suficiente. Não se esqueça que o A(às) pode valer 1 ou 11. Ganha quem chegar mais próximo de 21.");
	}

	@Override
	public void tutorial(){
		FrameTutorial f = new FrameTutorial(super.getNome(), super.getTutorial());
		f.create(f);
	}

	public void jogar(){
		FrameBlackjack f = new FrameBlackjack();
		f.create();
	}
}