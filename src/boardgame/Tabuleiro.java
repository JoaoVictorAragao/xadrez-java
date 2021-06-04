package boardgame;


public class Tabuleiro {
	
	private int linhas;
	private int coluna;
	private Peça[][] peças;
	
	
	public Tabuleiro(int linhas, int coluna) {
		if(linhas <1 || coluna < 1) {
			throw new BoardException("Erro ao criar tabuleiro: Deve-se ter ao menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.coluna = coluna;
		peças = new Peça[linhas][coluna];
	}


	public int getLinhas() {
		return linhas;
	}

	public int getColuna() {
		return coluna;
	}

	public Peça peca(int linha, int coluna) {
		if(!PosicaoExiste(linha, coluna)) {
			throw new BoardException("Posição não existe no tabuleiro");
		}
		return peças[linha][coluna];
	}
	
	public Peça peca(Position position){
		if(!PeçaExiste(position)) {
			throw new BoardException("Já possuí uma peça nessa posição");
		}
		return peças[position.getRow()][position.getColuna()];
	}
	
	public void botaPeca(Peça peca, Position position) {
		if(TemPeca(position)) {
			throw new BoardException("Já tem uma peça nessa posição" +position);
		}
		peças[position.getRow()][position.getColuna()] = peca;
		peca.position = position;
	}
	
	private boolean PosicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < this.linhas && this.coluna >= 0 && coluna < this.coluna;
	}
	
	public boolean PeçaExiste(Position position) {
		return PosicaoExiste(position.getRow(), position.getColuna());
	}
	
	public boolean TemPeca(Position position) {
		if(!PeçaExiste(position)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		return peca(position) != null;
	}
	
}
