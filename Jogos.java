public abstract class Jogos{
	private String nome;
	private String tutorial;
	//private JFrame frameJogo;

	public abstract void jogar();
	public abstract void tutorial();

	public String getNome(){ return this.nome; }
	public void setNome(String n){ this.nome = n; }

	public String getTutorial(){ return this.tutorial; }
	public void setTutorial(String t){ this.tutorial = t; }
}