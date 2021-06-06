package Xadrex.pe�as;

import Xadrex.Cor;
import Xadrex.XadrexPe�a;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Cavalo extends XadrexPe�a{

	public Cavalo(Tabuleiro board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean PodeMov(Position position) {
		XadrexPe�a p = (XadrexPe�a) getBoard().peca(position);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] possivelMovs() {boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

	Position p = new Position(0, 0);

	p.setValores(position.getRow() - 1, position.getColuna() -2);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	p.setValores(position.getRow() + 1, position.getColuna() -2);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	p.setValores(position.getRow() + 1, position.getColuna() + 2);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	p.setValores(position.getRow() - 1, position.getColuna() + 2);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	p.setValores(position.getRow() - 2, position.getColuna() - 1);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	
	p.setValores(position.getRow() + 2, position.getColuna() - 1);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	p.setValores(position.getRow() + 2, position.getColuna() + 1);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	p.setValores(position.getRow() - 2, position.getColuna() + 1);
	if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
		mat[p.getRow()][p.getColuna()] = true;
	}

	return mat;
}
	
}
