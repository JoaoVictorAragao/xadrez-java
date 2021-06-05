package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.XadrexPeça;
import boardgame.Position;
import boardgame.Tabuleiro;

public class Rei extends XadrexPeça {

	public Rei(Tabuleiro board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
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

		return mat;
	}
}
