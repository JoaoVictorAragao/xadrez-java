package Xadrex;

import boardgame.Peça;
import boardgame.Tabuleiro;

public class XadrexPeça extends Peça {
	private Cor cor;

	public XadrexPeça(Tabuleiro board, Cor cor) {
		super(board);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	
	
}
