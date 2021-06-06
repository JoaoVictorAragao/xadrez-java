package Xadrex.pe�as;

import Xadrex.Cor;
import Xadrex.XadrexPe�a;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Rainha extends XadrexPe�a {

	public Rainha(Tabuleiro board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possivelMovs() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

		Position p = new Position(0, 0);

		// Acima da pe�a
		p.setValores(position.getRow() - 1, position.getColuna());
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setRow(p.getRow() - 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Esquerda da pe�a
		p.setValores(position.getRow(), position.getColuna() - 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Direita da pe�a
		p.setValores(position.getRow(), position.getColuna() + 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Abaixo da pe�a
		p.setValores(position.getRow() + 1, position.getColuna());
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setRow(p.getRow() + 1);
		}

		if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// NW
		p.setValores(position.getRow() - 1, position.getColuna() - 1);
		while (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
			mat[p.getRow()][p.getColuna()] = true;
			p.setValores(p.getRow() - 1, p.getColuna() - 1);
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
