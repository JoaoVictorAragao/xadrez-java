package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.Partida;
import Xadrex.XadrexPeça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Peão extends XadrexPeça {

	private Partida partida;

	public Peão(Tabuleiro tabuleiro, Cor cor, Partida partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public boolean[][] possivelMovs() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

		Position p = new Position(0, 0);
		if (getCor() == Cor.BRANCO) {
			p.setValores(position.getRow() - 1, position.getColuna());
			if (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}

			p.setValores(position.getRow() - 2, position.getColuna());
			Position p2 = new Position(position.getRow() - 1, position.getColuna());
			if (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p) && getBoard().PosicaoExiste(p2)
					&& !getBoard().TemPeca(p2) && getCount() == 0) {
				mat[p.getRow()][p.getColuna()] = true;
			}

			p.setValores(position.getRow() - 1, position.getColuna() - 1);
			if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}

			p.setValores(position.getRow() - 1, position.getColuna() + 1);
			if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}

		} // #specialmove en passant white
		if (position.getRow() == 3) {
			Position left = new Position(position.getRow(), position.getColuna() - 1);
			if (getBoard().PosicaoExiste(left) && PecaInimiga(left)
					&& getBoard().peca(left) == partida.getEnPassant()) {
				mat[left.getRow() - 1][left.getColuna()] = true;
			}
			Position right = new Position(position.getRow(), position.getColuna() + 1);
			if (getBoard().PosicaoExiste(right) && PecaInimiga(right)
					&& getBoard().peca(right) == partida.getEnPassant()) {
				mat[right.getRow() - 1][right.getColuna()] = true;
			}
		} else {
			p.setValores(position.getRow() + 1, position.getColuna());
			if (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}

			p.setValores(position.getRow() + 2, position.getColuna());
			Position p2 = new Position(position.getRow() + 1, position.getColuna());
			if (getBoard().PosicaoExiste(p) && !getBoard().TemPeca(p) && getBoard().PosicaoExiste(p2)
					&& !getBoard().TemPeca(p2) && getCount() == 0) {
				mat[p.getRow()][p.getColuna()] = true;
			}

			p.setValores(position.getRow() + 1, position.getColuna() + 1);
			if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}

			p.setValores(position.getRow() + 1, position.getColuna() - 1);
			if (getBoard().PosicaoExiste(p) && PecaInimiga(p)) {
				mat[p.getRow()][p.getColuna()] = true;
			}
		}

		if (position.getRow() == 4) {
			Position left = new Position(position.getRow(), position.getColuna() - 1);
			if (getBoard().PosicaoExiste(left) && PecaInimiga(left)
					&& getBoard().peca(left) == partida.getEnPassant()) {
				mat[left.getRow() + 1][left.getColuna()] = true;
			}
			Position right = new Position(position.getRow(), position.getColuna() + 1);
			if (getBoard().PosicaoExiste(right) && PecaInimiga(right)
					&& getBoard().peca(right) == partida.getEnPassant()) {
				mat[right.getRow() + 1][right.getColuna()] = true;
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "p";
	}

}
