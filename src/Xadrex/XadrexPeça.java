package Xadrex;

import boardgame.Pe�a;
import boardgame.Tabuleiro;

public abstract class XadrexPe�a extends Pe�a {
	private Cor cor;

	public XadrexPe�a(Tabuleiro board, Cor cor) {
		super(board);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	
	
}
