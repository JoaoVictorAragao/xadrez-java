package boardgame;


public class Tabuleiro {
	
	private int linhas;
	private int coluna;
	private Pe�a[][] pe�as;
	
	
	public Tabuleiro(int linhas, int coluna) {
		this.linhas = linhas;
		this.coluna = coluna;
		pe�as = new Pe�a[linhas][coluna];
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
	
	public Pe�a peca(int linha, int coluna) {
		return pe�as[linha][coluna];
	}
	
	public Pe�a peca(Position position){
		return pe�as[position.getRow()][position.getColuna()];
	}
	
	public void botaPeca(Pe�a peca, Position position) {
		pe�as[position.getRow()][position.getColuna()] = peca;
		peca.position = position;
	}
	
	
}
