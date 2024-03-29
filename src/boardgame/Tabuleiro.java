package boardgame;


public class Tabuleiro {
	
	private int linhas;
	private int coluna;
	private Pe�a[][] pe�as;
	
	
	public Tabuleiro(int linhas, int coluna) {
		if(linhas <1 || coluna < 1) {
			throw new BoardException("Erro ao criar tabuleiro: Deve-se ter ao menos uma linha e uma coluna");
		}
		this.linhas = linhas;
		this.coluna = coluna;
		pe�as = new Pe�a[linhas][coluna];
	}


	public int getLinhas() {
		return linhas;
	}

	public int getColuna() {
		return coluna;
	}

	public Pe�a peca(int linha, int coluna) {
		if(!PosicaoExiste(linha, coluna)) {
			throw new BoardException("Posi��o n�o existe no tabuleiro");
		}
		return pe�as[linha][coluna];
	}
	
	public Pe�a peca(Position position){
		if(!PosicaoExiste(position)) {
			throw new BoardException("J� possu� uma pe�a nessa posi��o");
		}
		return pe�as[position.getRow()][position.getColuna()];
	}
	
	public void botaPeca(Pe�a peca, Position position) {
		if(TemPeca(position)) {
			throw new BoardException("J� tem uma pe�a nessa posi��o: " +position);
		}
		pe�as[position.getRow()][position.getColuna()] = peca;
		peca.position = position;
	}
	
	public Pe�a removePeca(Position position) {
		if(!PosicaoExiste(position.getRow(), position.getColuna())) {
			throw new BoardException("Posi��o n�o existe no tabuleiro");
		}
		if(peca(position) == null) {
			return null;
		}
		Pe�a aux = peca(position);
		aux.position = null;
		pe�as[position.getRow()][position.getColuna()] = null;
		return aux;
		
	}
	
	private boolean PosicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < this.linhas && coluna >= 0 && coluna < this.coluna;
	}
	
	public boolean PosicaoExiste(Position position) {
		return PosicaoExiste(position.getRow(), position.getColuna());
	}
	
	public boolean TemPeca(Position position) {
		if(!PosicaoExiste(position)) {
			throw new BoardException("Posi��o n�o est� no tabuleiro");
		}
		return peca(position) != null;
	}
	
	
	
}
