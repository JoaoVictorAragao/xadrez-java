package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.Partida;
import Xadrex.XadrexPeça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Rei extends XadrexPeça {

	private Partida partida;

	public Rei(Tabuleiro tabuleiro, Cor cor, Partida partida) {
		super(tabuleiro, cor);
		this.partida = partida;
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean PodeMov(Position position) {
		XadrexPeça p = (XadrexPeça) getBoard().peca(position);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] possivelMovs() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];

		Position p = new Position(0, 0);

		// acima
		p.setValores(position.getRow() - 1, position.getColuna());
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// abaixo
		p.setValores(position.getRow() + 1, position.getColuna());
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Esquerda
		p.setValores(position.getRow(), position.getColuna() - 1);
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// Direita
		p.setValores(position.getRow(), position.getColuna() + 1);
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// NW
		p.setValores(position.getRow() - 1, position.getColuna() - 1);
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// NE
		p.setValores(position.getRow() - 1, position.getColuna() + 1);
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// SW
		p.setValores(position.getRow() + 1, position.getColuna() - 1);
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		// SE
		p.setValores(position.getRow() + 1, position.getColuna() + 1);
		if (getBoard().PosicaoExiste(p) && PodeMov(p)) {
			mat[p.getRow()][p.getColuna()] = true;
		}

		if (getCount() == 0 && !partida.getCheck()) {

			Position posT1 = new Position(position.getRow(), position.getColuna() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getColuna() + 1);
				Position p2 = new Position(position.getRow(), position.getColuna() + 2);
				if (getBoard().peca(p1) == null && getBoard().peca(p2) == null) {
					mat[position.getRow()][position.getColuna() + 2] = true;
				}
			}

			Position posT2 = new Position(position.getRow(), position.getColuna() - 4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getColuna() - 1);
				Position p2 = new Position(position.getRow(), position.getColuna() - 2);
				Position p3 = new Position(position.getRow(), position.getColuna() - 3);
				if (getBoard().peca(p1) == null && getBoard().peca(p2) == null && getBoard().peca(p3) == null) {
					mat[position.getRow()][position.getColuna() - 2] = true;
				}
			}
		}

		return mat;
	}

	private boolean testRookCastling(Position position) {
		XadrexPeça p = (XadrexPeça) getBoard().peca(position);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getCount() == 0;
	}

}
