package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.XadrexPeça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Torre extends XadrexPeça {

	public Torre(Tabuleiro board, Cor cor) {
		super(board, cor);

	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] possivelMovs() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

		Position p = new Position(0, 0);

		// Acima da peça
		p.setValores(position.getRow() - 1, position.getColuna());
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setRow(p.getRow() - 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Esquerda da peça
		p.setValores(position.getRow(), position.getColuna() - 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Direita da peça
		p.setValores(position.getRow(), position.getColuna() + 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Abaixo da peça
		p.setValores(position.getRow() + 1, position.getColuna());
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setRow(p.getRow() + 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		return mat;
	}

}
