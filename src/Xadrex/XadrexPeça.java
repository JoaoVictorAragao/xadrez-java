package Xadrex;

import boardgame.Pe�a;
import boardgame.Position;
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

	protected boolean PecaInimiga(Position position) {
		XadrexPe�a p = (XadrexPe�a)getBoard().peca(position);
		return p != null && p.getCor() != cor;
	}
	
}
