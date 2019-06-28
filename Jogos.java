public abstract class Jogos{
	private String nome;
	private String tutorial;
	//private JFrame frameJogo;

	//public abstract void Jogar();
	public abstract void Tutorial();

	public String getNome(){ return this.nome; }
	public void setNome(String n){ this.nome = n; }
}