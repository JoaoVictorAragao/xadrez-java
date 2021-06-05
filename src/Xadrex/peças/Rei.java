package Xadrex.pe�as;

import Xadrex.Cor;
import Xadrex.XadrexPe�a;
import boardgame.Tabuleiro;

public class Rei extends XadrexPe�a{

	public Rei(Tabuleiro board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possivelMov() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColuna()];
		return mat;
	}
}
