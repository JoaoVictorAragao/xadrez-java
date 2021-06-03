package Xadrex.peças;

import Xadrex.Cor;
import Xadrex.XadrexPeça;
import boardgame.Tabuleiro;

public class Rei extends XadrexPeça{

	public Rei(Tabuleiro board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "R";
	}
}
