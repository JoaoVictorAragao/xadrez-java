package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.XadrexPeça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Bispo extends XadrexPeça{

	public Bispo(Tabuleiro board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possivelMovs() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

		Position p = new Position(0, 0);

		// NW
		p.setValores(position.getRow() - 1, position.getColuna() - 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setValores(p.getRow()-1, p.getColuna()-1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// NE
		p.setValores(position.getRow() - 1, position.getColuna() + 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setValores(p.getRow() - 1, p.getColuna() + 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// SE
		p.setValores(position.getRow() + 1, position.getColuna() + 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setValores(p.getRow() + 1, p.getColuna() + 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// SW
		p.setValores(position.getRow() + 1, position.getColuna() - 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setValores(p.getRow() + 1, p.getColuna() - 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		return mat;
	}

}
