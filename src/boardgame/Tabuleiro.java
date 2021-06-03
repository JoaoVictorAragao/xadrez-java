package boardgame;


public class Tabuleiro {
	
	private int linhas;
	private int coluna;
	private Peça[][] peças;
	
	
	public Tabuleiro(int linhas, int coluna) {
		this.linhas = linhas;
		this.coluna = coluna;
		peças = new Peça[linhas][coluna];
	}


	public int getLinhas() {
		return linhas;
	}


	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}


	public int getColuna() {
		return coluna;
	}


	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public Peça peca(int linha, int coluna) {
		return peças[linha][coluna];
	}
	
	public Peça peca(Position position){
		return peças[position.getRow()][position.getColuna()];
	}
	
	public void botaPeca(Peça peca, Position position) {
		peças[position.getRow()][position.getColuna()] = peca;
		peca.position = position;
	}
	
	
}
